package com.yunc.upms.server.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yunc.upms.common.utils.ResultHandle;
import com.yunc.upms.dao.entity.UpmsPermission;
import com.yunc.upms.rpc.service.IUpmsPermissionService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lijianhua
 * @since 2017-11-15
 */
@Controller
@RequestMapping("/upmsPermission")
public class UpmsPermissionController {
	
	 
		  @Autowired
		  private IUpmsPermissionService upmsPermissionService;
		  
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
			public Map<String,Object> list(String id,Map<String, Object> resMap){
				EntityWrapper<UpmsPermission> ew = new EntityWrapper<UpmsPermission>();
				ew.setSqlSelect("permission_id as id ,pid,name");
				resMap = ResultHandle.success();
				resMap.put("info", upmsPermissionService.selectList(ew));
				return resMap;
			}
			

	
}
