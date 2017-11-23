package com.yunc.upms.server.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.liebao.lb7881.common.generic.GenericPageResponse;
import com.liebao.lb7881.common.utils.AssertUtils;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.yunc.upms.common.enums.ErrorCodeEnum;
import com.yunc.upms.common.utils.ResultHandle;
import com.yunc.upms.dao.entity.UpmsPermission;
import com.yunc.upms.dao.entity.UpmsUser;
import com.yunc.upms.rpc.service.IUpmsPermissionService;
import com.yunc.upms.server.base.BaseController;
import com.yunc.upms.server.request.UpmsPermissionPageRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lijianhua
 * @since 2017-11-15
 */
@Controller
@RequestMapping("/upmsPermission")
public class UpmsPermissionController extends BaseController {

	@Autowired
	private IUpmsPermissionService upmsPermissionService;

	
	 /**
	   * 权限管理首页
	   * @param request
	   * @param m
	   * @return
	   */ 
	  @RequestMapping(value="/index",method=RequestMethod.GET)  
	  public String index(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map){
		  return "/permission/index";
	  } 
	  
	  
	  /**
	   * 新增用户
	   * @param request
	   * @param m
	   * @return
	   */ 
	  @RequestMapping(value="/json/create",method=RequestMethod.POST) 
	  @ResponseBody
	  public  Map<String, Object>  create(UpmsPermission upmsPermission,Map<String, Object> resMap){
		  LOGGER.info("enter create upmsPermission:{}",upmsPermission);
		  resMap = ResultHandle.success();
		  long time = System.currentTimeMillis();
		  upmsPermission.setCtime(time);
		  upmsPermissionService.insert(upmsPermission);
		  LOGGER.info("exsit create resMap:{}",JSONUtil.toJsonStr(resMap));
		  return resMap;
	  } 
	  
	  /**
		 * 更新获取数据
		 * @param request
		 * @param m
		 * @return
		 */ 
		@RequestMapping(value="/json/update/{id}",method=RequestMethod.GET) 
		@ResponseBody
		public  Map<String, Object>  update(@PathVariable("id") int id,Map<String, Object> resMap){
			LOGGER.info("enter create id:{}",id);
			resMap = ResultHandle.success();
			resMap.put("info", upmsPermissionService.selectById(id));
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
		public  Map<String, Object>  update(UpmsPermission upmsPermission,Map<String, Object> resMap){
			LOGGER.info("enter update upmsPermission:{}",upmsPermission);
			resMap = ResultHandle.success();
			AssertUtils.isTrue(upmsPermissionService.updateById(upmsPermission),ErrorCodeEnum.SYSTEM_ERROR);
			LOGGER.info("exsit update resMap:{}",JSONUtil.toJsonStr(resMap));
			return resMap;
		} 
		
	  
	  
	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @param id
	 * @return [参数说明]
	 * 
	 * @return Map<String,Object> [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "/json/list")
	@ResponseBody
	public GenericPageResponse<List<UpmsPermission>> list(UpmsPermissionPageRequest upmsPermissionPageRequest,Map<String, Object> resMap){
		LOGGER.info("enter list upmsPermissionPageRequest:{}",upmsPermissionPageRequest);
		
		resMap = ResultHandle.success();
		EntityWrapper<UpmsPermission> ew = new EntityWrapper<UpmsPermission>();
		ew.orderBy("ctime", false);
		UpmsPermission uper = new UpmsPermission();
		BeanUtils.copyProperties(upmsPermissionPageRequest, uper);
		ew.setEntity(uper);
		Page<UpmsPermission> page = upmsPermissionService.selectPage(new Page<UpmsPermission>(upmsPermissionPageRequest.getPageNum(), upmsPermissionPageRequest.getPageSize() ), ew);
	 
		GenericPageResponse<List<UpmsPermission>> n = new GenericPageResponse<List<UpmsPermission>>();
		n.setTotalCount((long) page.getTotal());
		n.setObject(page.getRecords());
		LOGGER.info("exsit list n:{}",n);
		
		return n;
	}
	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @param id
	 * @return [参数说明]
	 * 
	 * @return Map<String,Object> [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "/json/ztreeList")
	@ResponseBody
	public Map<String, Object> ztreeList(UpmsPermissionPageRequest upmsPermissionPageRequest,Map<String, Object> resMap){
		LOGGER.info("enter list upmsPermissionPageRequest:{}",upmsPermissionPageRequest);
		
		resMap = ResultHandle.success();
		EntityWrapper<UpmsPermission> ew = new EntityWrapper<UpmsPermission>();
		 
		resMap.put("info", upmsPermissionService.selectList(ew));
		 
		return resMap;
	}
}
