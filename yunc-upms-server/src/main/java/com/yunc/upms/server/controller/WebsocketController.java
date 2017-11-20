package com.yunc.upms.server.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.liebao.lb7881.common.generic.GenericPageResponse;
import com.liebao.lb7881.common.utils.AssertUtils;
import com.liebao.lb7881.common.utils.ResultHandle;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.yunc.upms.common.enums.ErrorCodeEnum;
import com.yunc.upms.dao.entity.UpmsUser;
import com.yunc.upms.rpc.service.IUpmsUserService;
import com.yunc.upms.server.base.BaseController;
import com.yunc.upms.server.request.UpmsUserPageRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lijianhua
 * @since 2017-11-15
 */
@Controller
@RequestMapping("/websocket")
public class WebsocketController extends BaseController{
	
	  @Autowired
	  private IUpmsUserService upmsUserService;
	  
	  /**
	   * 用户首页
	   * @param request
	   * @param m
	   * @return
	   */ 
	  @RequestMapping(value="/websocket",method=RequestMethod.GET)  
	  public String index(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map){
		  return "/user/websocket";
	  } 
	 
	  /**
	   * 用户首页
	   * @param request
	   * @param m
	   * @return
	   */ 
	  @RequestMapping(value="/websocket2",method=RequestMethod.GET)  
	  public String websocket2(String id,HttpSession httpSession,HttpServletRequest request,HttpServletResponse response,Map<String, Object> map){
		
		  UpmsUser user = upmsUserService.selectById(id);
		  
		  httpSession.setAttribute("user",user );
		  httpSession.setAttribute("uid", user.getUserId() );
		  return "/user/websocket2";
	  } 
	  
	  
		/**
		 * 更新数据
		 * @param request
		 * @param m
		 * @return
		 */  
		@RequestMapping(value="/json/ws",method=RequestMethod.POST) 
		@ResponseBody
		public  Map<String, Object>  ws(UpmsUser upmsUser,Map<String, Object> resMap){
			LOGGER.info("enter update upmsUser:{}",upmsUser);
			resMap = ResultHandle.success();
			LOGGER.info("exsit update resMap:{}",JSONUtil.toJsonStr(resMap));
			return resMap;
		} 
}
