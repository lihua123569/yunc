package com.yunc.upms.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.liebao.lb7881.common.utils.AssertUtils;
import com.liebao.lb7881.common.utils.ResultHandle;
import com.xiaoleilu.hutool.convert.Convert;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.yunc.upms.common.enums.ErrorCodeEnum;
import com.yunc.upms.dao.entity.UpmsPermission;
import com.yunc.upms.dao.entity.UpmsRolePermission;
import com.yunc.upms.rpc.pojo.UpmsRolePermissionTreeResponse;
import com.yunc.upms.rpc.service.IUpmsPermissionService;
import com.yunc.upms.rpc.service.IUpmsRolePermissionService;
import com.yunc.upms.server.base.BaseController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lijianhua
 * @since 2017-11-15
 */
@Controller
@RequestMapping("/upmsRolePermission")
public class UpmsRolePermissionController extends BaseController {
	@Autowired
	private IUpmsPermissionService upmsPermissionService;

	@Autowired
	private IUpmsRolePermissionService upmsRolePermissionService;

	/**
	 * 新增权限
	 * 
	 * @param request
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/json/permission", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> create(String permissions, Integer id, Map<String, Object> resMap) {
		LOGGER.info("enter permission ,id:{},permissions:{}", id, permissions);
		resMap = ResultHandle.success();
		JSONArray datas = JSONArray.parseArray(permissions);

		List<Integer> deleteIds = new ArrayList<>();
		//新增权限
		for (int i = 0; i < datas.size(); i++) {
			JSONObject json = datas.getJSONObject(i);
			if (!json.getBoolean("checked")) {
				deleteIds.add(json.getIntValue("permissionId"));
			} else {
				// 新增权限
				UpmsRolePermission upmsRolePermission = new UpmsRolePermission();
				upmsRolePermission.setRoleId(id);
				upmsRolePermission.setPermissionId(json.getIntValue("permissionId"));
				AssertUtils.isTrue(upmsRolePermissionService.insert(upmsRolePermission),ErrorCodeEnum.SYSTEM_ERROR);
			}
		}
		// 删除权限
		if (deleteIds.size() > 0) {
			AssertUtils.isTrue(upmsRolePermissionService.deleteBatchIds(deleteIds),ErrorCodeEnum.SYSTEM_ERROR);
		}

		LOGGER.info("exsit create resMap:{}", JSONUtil.toJsonStr(resMap));
		return resMap;
	}
	/**
	 * 新增权限
	 * 
	 * @param request
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/json/getTreeByRoleId", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getTreeByRoleId(Integer roleId, Map<String, Object> resMap) {
		LOGGER.info("enter permission ,permissions:{}", roleId);
		resMap = ResultHandle.success();
		resMap.put("info", upmsRolePermissionService.getTreeByRoleId(roleId));
		/*Map<Integer, UpmsRolePermissionTreeResponse> map = new HashMap<Integer, UpmsRolePermissionTreeResponse>();
		List<UpmsRolePermissionTreeResponse> list = new ArrayList<UpmsRolePermissionTreeResponse>();
		Iterator iter = map.entrySet().iterator(); // 获得map的Iterator
	       
		for (Map.Entry<Integer, UpmsRolePermissionTreeResponse> entry : map.entrySet()) {
			list.add(entry.getValue());
		}
		 */
//		EntityWrapper<UpmsPermission> ew = new EntityWrapper<UpmsPermission>();
//		 
//		resMap.put("info", upmsPermissionService.selectList(ew));
		LOGGER.info("exsit create resMap:{}", JSONUtil.toJsonStr(resMap));
		return resMap;
	}
}
