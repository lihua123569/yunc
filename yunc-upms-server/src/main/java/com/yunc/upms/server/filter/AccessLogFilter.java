package com.yunc.upms.server.filter;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import com.liebao.lb7881.common.utils.IPUtils;
import com.liebao.lb7881.common.utils.UUIDUtils;
import com.yunc.upms.common.enums.DateFormatEnum;
import com.yunc.upms.common.utils.DateUtils;

/**
 * 访问日志过滤器
 * 
 */
@Component("accessLogFilter")
public class AccessLogFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger("ActionAccessLogger");

    private static final String STR_IP = "ip";

    private static final String STR_SESSION_ID = "sessionId";

    private static final String STR_INVOKENO = "invokeNo";

    private static final String STR_EQ = "=";

    private static final String STR_AND = "&";
    
    /**
     * js版本号
     */
    private static String JS_VERSION = DateUtils.format(new Date(), DateFormatEnum.yyyyMMddHHmm);
    
    // 截取参数的最大长度
    protected int maxLength = 32;

    // 不允许记录的action参数列表
    protected Set<String> excludeParams = new HashSet<String>() {
        /**
         */
        private static final long serialVersionUID = 7661624449941012689L;

        {
            add("paymentPassword");
        }
        
    };

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("rawtypes")
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        
        request.setAttribute("jsVersion", JS_VERSION);

        String path = request.getRequestURI();
        
        if (path.startsWith("/static/")) {
            
            chain.doFilter(request, response);
            
            return ;
        }
        
        // 获取当前登录用户
//        HttpSession session = request.getSession();

        // 获取SESSIONID
//        String sessionId = session.getId();

        // 获取用户登录IP
        String ip = IPUtils.getIpAddress(request);

        // 向MDC里面set ip、user
        MDC.put(STR_IP, ip);
        
//        MDC.put(STR_SESSION_ID, sessionId);

        // 调用流水号
        MDC.put(STR_INVOKENO, UUIDUtils.randomUUID());

        // 取parameters
        Enumeration parameters = request.getParameterNames();

        // 计算action method执行方法
        long startTime = System.currentTimeMillis();
        
        long executionTime = 0L;

        // 拼接LOG信息
        StringBuilder message = new StringBuilder(500);
        try {// 调用用户访问的CONTROLLER
            
            message.append("Controller:");
            message.append(path);
            message.append("|Params:");
            StringBuilder params = new StringBuilder();
            while (parameters.hasMoreElements()) {
                String param = (String) parameters.nextElement();
                if (excludeParams.contains(param)) {
                    continue;
                }
                String value = request.getParameter(param);
                params.append(param);
                params.append(STR_EQ);
                params.append(StringUtils.substring(value,0,this.maxLength));
                params.append(STR_AND);
            }
            if (StringUtils.isNotBlank(params.toString())) {
                message.append(params.toString());
            }
            LOGGER.info(message.toString());
            
            chain.doFilter(request, response);
            
            executionTime = System.currentTimeMillis() - startTime;
        } finally {
            // 记录日志
        	LOGGER.info("Controller:{} spend:{}",path,executionTime);
            // 清除MDC里面的历史信息
            MDC.remove(STR_IP);
            
            MDC.remove(STR_SESSION_ID);
            
            MDC.remove(STR_INVOKENO);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
    }
}
