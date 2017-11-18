package com.yunc.upms.server.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@RequestMapping("/upmsUser")
public class UpmsUserController extends BaseController{
	
	  @Autowired
	  private IUpmsUserService upmsUserService;
	  
	  /**
	   * 用户首页
	   * @param request
	   * @param m
	   * @return
	   */ 
	  @RequestMapping(value="/index",method=RequestMethod.GET)  
	  public String index(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map){
		  return "/user/index";
	  } 
	  
	  /**
	   * 新增用户
	   * @param request
	   * @param m
	   * @return
	   */ 
	  @RequestMapping(value="/json/create",method=RequestMethod.POST) 
	  @ResponseBody
	  public  Map<String, Object>  create(UpmsUser upmsUser,Map<String, Object> resMap){
		  LOGGER.info("enter create upmsUser:{}",upmsUser);
		  resMap = ResultHandle.success();
		  long time = System.currentTimeMillis();
		  upmsUser.setCtime(time);
		  upmsUserService.insert(upmsUser);
		  LOGGER.info("exsit create resMap:{}",JSONUtil.toJsonStr(resMap));
		  return resMap;
	  } 
	  
	  /**
		 * 更新获取数据
		 * @param request
		 * @param m
		 * @return
		 */ 
		@RequestMapping(value="/json/update",method=RequestMethod.GET) 
		@ResponseBody
		public  Map<String, Object>  update(Long userId,Map<String, Object> resMap){
			LOGGER.info("enter create userId:{}",userId);
			resMap = ResultHandle.success();
			resMap.put("info", upmsUserService.selectById(userId));
			LOGGER.info("exsit update resMap:{}",JSONUtil.toJsonStr(resMap));
	    	return resMap;
		} 
		
		/**
		 * 更新数据
		 * @param request
		 * @param m
		 * @return
		 */ 
		@RequestMapping(value="/json/update",method=RequestMethod.POST) 
		@ResponseBody
		public  Map<String, Object>  update(UpmsUser upmsUser,Map<String, Object> resMap){
			LOGGER.info("enter update upmsUser:{}",upmsUser);
			resMap = ResultHandle.success();
			AssertUtils.isTrue(upmsUserService.updateById(upmsUser),ErrorCodeEnum.SYSTEM_ERROR);
			LOGGER.info("exsit update resMap:{}",JSONUtil.toJsonStr(resMap));
			return resMap;
		} 
		
		/**
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param id
		 * @return [参数说明]
		 * 
		 * @return Map<String,Object> [返回类型说明]
		 * @exception throws [违例类型] [违例说明]
		 * @see [类、类#方法、类#成员]
		 */
		@RequestMapping(value="/json/list")
		@ResponseBody
		public GenericPageResponse<List<UpmsUser>> list(UpmsUserPageRequest upmsUserPageRequest,Map<String, Object> resMap){
			LOGGER.info("enter list upmsUserPageRequest:{}",upmsUserPageRequest);
			
			resMap = ResultHandle.success();
			EntityWrapper<UpmsUser> ew = new EntityWrapper<UpmsUser>();
			ew.orderBy("ctime", false);
			UpmsUser user = new UpmsUser();
			BeanUtils.copyProperties(upmsUserPageRequest, user);
			ew.setEntity(user);
			Page<UpmsUser> page = upmsUserService.selectPage(new Page<UpmsUser>(upmsUserPageRequest.getPageNum(), upmsUserPageRequest.getPageSize() ), ew);
		 
			GenericPageResponse<List<UpmsUser>> n = new GenericPageResponse<List<UpmsUser>>();
			n.setTotalCount((long) page.getTotal());
			n.setObject(page.getRecords());
			LOGGER.info("exsit list n:{}",n);
			
			return n;
		}
		/**
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param id
		 * @return [参数说明]
		 * 
		 * @return Map<String,Object> [返回类型说明]
		 * @exception throws [违例类型] [违例说明]
		 * @see [类、类#方法、类#成员]
		 */
		@RequestMapping(value="/json/lists")
		@ResponseBody
		public List list(String id,Map<String, Object> resMap){
			
			resMap = ResultHandle.success();
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			Map<String,Object> 
			m = new HashMap<String,Object>();
			m.put("id", 1);
			m.put("name", "lihua");
			m.put("pId", "0");
			list.add(m);
			m = new HashMap<String,Object>();
			m.put("id", 2);
			m.put("name", "lihua2");
			m.put("pId", "0");
			list.add(m);
			m = new HashMap<String,Object>();
			m.put("id", 3);
			m.put("name", "lihua3");
			m.put("pId", "1");
			list.add(m);
			return list;
		}
		
	  
}
