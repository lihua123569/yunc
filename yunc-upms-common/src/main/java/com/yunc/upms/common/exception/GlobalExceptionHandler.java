package com.yunc.upms.common.exception;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.liebao.lb7881.common.excepiton.AppException;

//@ControllerAdvice(annotations=RestController.class)
//@ControllerAdvice(basePackages={"com.xxx","com.ooo"})
@ControllerAdvice
public class GlobalExceptionHandler {
	
	  private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	  
//    @ExceptionHandler(RuntimeException.class)
    //    @ExceptionHandler(value={RuntimeException.class,MyRuntimeException.class})
    @ExceptionHandler//处理所有异常
    @ResponseBody //在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
    public String exceptionHandler(Exception ex, HttpServletResponse response) {
    	   
        String code;
        
        String message;
        //已知异常处理
        if (ex instanceof AppException) {
            
            AppException appException = (AppException) ex;
            
            code = appException.getResponseCode();
            
            message = appException.getErrorMessage();
            
            LOGGER.info("AppException occoured,code:{},message:{}",code,message);
            
            LOGGER.error("AppException occoured",ex);
            
        }else {//未知异常处理
            
            LOGGER.info("unknown exception occoured");
            
            LOGGER.error("unknown exception occoured",ex);
            
            code = "9999";
            
            message = "服务器繁忙，请稍后再试~";
        }
        
    	JSONObject parm = new JSONObject();
     
        parm.put("responseCode", code);
        parm.put("responseMsg", message);
        return parm.toJSONString();
    }
}