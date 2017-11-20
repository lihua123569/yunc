package com.yunc.upms.server.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import com.yunc.upms.dao.entity.UpmsUser;


public class LoginInterceptor implements HandlerInterceptor {

	private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
	
	/**
	 * 在action之前执行,如果返回true,则继续向后执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String _log = getIpAddress(request);
		 if(handler instanceof HandlerMethod){
			 HandlerMethod handlerMethod = (HandlerMethod)handler;
			 _log+= " 访问  "+handlerMethod.getBean().toString()+" - " + handlerMethod.getMethod().getName();
		 }else if(handler instanceof DefaultServletHttpRequestHandler){
			 DefaultServletHttpRequestHandler defaultHandler = (DefaultServletHttpRequestHandler)handler;
			 _log+= " 访问  "+  defaultHandler.toString();
			 
		 }
		 
		UpmsUser u = (UpmsUser) request.getSession().getAttribute("user");
		 if(u==null ){
			 response.sendRedirect("/upmsUser/login?ReturnURL="+request.getServletPath());
			 return false;
		 } 
		
		log.info(_log);
		return true;
	}

/**
 * 在Action 方法执行完毕之后,执行(没有抛异常的话)
 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		// TODO 自动生成的方法存根

	}

	
	/**
	 * 在Action 方法执行完毕之后,无论是否抛出异常,通常用来进行异常处理
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO 自动生成的方法存根
		if (null != ex) {
			log.warn("Action 错误:" + ex.getMessage());
		}
	}

	
	
	
	  /** 
	   * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址, 
	   * 
	   * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？ 
	   * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。 
	   * 
	   * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 
	   * 192.168.1.100 
	   * 
	   * 用户真实IP为： 192.168.1.110 
	   * 
	   * @param request 
	   * @return 
	   */
	  public  String getIpAddress(HttpServletRequest request) { 
	    String ip = request.getHeader("x-forwarded-for"); 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_CLIENT_IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getRemoteAddr(); 
	    } 
	    return ip; 
	  } 
	    
	  
}
