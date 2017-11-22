package com.yunc.upms.server.controller;


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
import com.xiaoleilu.hutool.json.JSONUtil;
import com.yunc.upms.common.enums.ErrorCodeEnum;
import com.yunc.upms.common.utils.ResultHandle;
import com.yunc.upms.dao.entity.UpmsSystem;
import com.yunc.upms.rpc.service.IUpmsSystemService;
import com.yunc.upms.server.base.BaseController;
import com.yunc.upms.server.request.UpmsSystemPageRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lijianhua
 * @since 2017-11-15
 */
@Controller
@RequestMapping("/upmsSystem")
public class UpmsSystemController extends BaseController{
	@Autowired
	private IUpmsSystemService upmsSystemService;

	
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
	  public  Map<String, Object>  create(UpmsSystem upmsPermission,Map<String, Object> resMap){
		  LOGGER.info("enter create upmsPermission:{}",upmsPermission);
		  resMap = ResultHandle.success();
		  long time = System.currentTimeMillis();
		  upmsPermission.setCtime(time);
		  upmsSystemService.insert(upmsPermission);
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
			resMap.put("info", upmsSystemService.selectById(userId));
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
		public  Map<String, Object>  update(UpmsSystem upmsPermission,Map<String, Object> resMap){
			LOGGER.info("enter update upmsPermission:{}",upmsPermission);
			resMap = ResultHandle.success();
			AssertUtils.isTrue(upmsSystemService.updateById(upmsPermission),ErrorCodeEnum.SYSTEM_ERROR);
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
	public GenericPageResponse<List<UpmsSystem>> list(UpmsSystemPageRequest upmsPermissionPageRequest,Map<String, Object> resMap){
		LOGGER.info("enter list upmsPermissionPageRequest:{}",upmsPermissionPageRequest);
		
		resMap = ResultHandle.success();
		EntityWrapper<UpmsSystem> ew = new EntityWrapper<UpmsSystem>();
		ew.orderBy("ctime", false);
		UpmsSystem uper = new UpmsSystem();
		BeanUtils.copyProperties(upmsPermissionPageRequest, uper);
		ew.setEntity(uper);
		Page<UpmsSystem> page = upmsSystemService.selectPage(new Page<UpmsSystem>(upmsPermissionPageRequest.getPageNum(), upmsPermissionPageRequest.getPageSize() ), ew);
	 
		GenericPageResponse<List<UpmsSystem>> n = new GenericPageResponse<List<UpmsSystem>>();
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
	public Map<String, Object> ztreeList(UpmsSystemPageRequest upmsPermissionPageRequest,Map<String, Object> resMap){
		LOGGER.info("enter list upmsPermissionPageRequest:{}",upmsPermissionPageRequest);
		
		resMap = ResultHandle.success();
		EntityWrapper<UpmsSystem> ew = new EntityWrapper<UpmsSystem>();
		 
		resMap.put("info", upmsSystemService.selectList(ew));
		 
		return resMap;
	}
}
