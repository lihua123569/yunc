package com.yunc.upms.rpc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yunc.upms.dao.entity.UpmsPermission;
import com.yunc.upms.dao.entity.UpmsRolePermission;
import com.yunc.upms.dao.mapper.UpmsPermissionMapper;
import com.yunc.upms.dao.mapper.UpmsRolePermissionMapper;
import com.yunc.upms.rpc.pojo.UpmsRolePermissionTreeResponse;
import com.yunc.upms.rpc.service.IUpmsRolePermissionService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lijianhua
 * @since 2017-11-15
 */
@Service
public class UpmsRolePermissionServiceImpl extends ServiceImpl<UpmsRolePermissionMapper, UpmsRolePermission>
		implements IUpmsRolePermissionService {
	@Autowired
	UpmsPermissionMapper upmsPermissionMapper;

	@Override
	public List<UpmsRolePermissionTreeResponse> getTreeByRoleId(Integer roleId) {

		// 最终返回的list
		List<UpmsRolePermissionTreeResponse> rolePermissionResponseList = new ArrayList<UpmsRolePermissionTreeResponse>();

		// 角色权限列表
		EntityWrapper<UpmsRolePermission> ew = new EntityWrapper<UpmsRolePermission>();
		ew.eq("role_id", roleId);
		List<UpmsRolePermission> rolePermissionList = baseMapper.selectList(ew);
		if(rolePermissionList==null){
			
		}
		// 单个系统的全部权限列表
		EntityWrapper<UpmsPermission> pew = new EntityWrapper<UpmsPermission>();
		List<UpmsPermission> permissionList = upmsPermissionMapper.selectList(pew);
		for (UpmsPermission upmsPermission : permissionList) {
			UpmsRolePermissionTreeResponse urptreeResponse = new UpmsRolePermissionTreeResponse();
			BeanUtils.copyProperties(upmsPermission, urptreeResponse);
			rolePermissionResponseList.add(urptreeResponse);
		}
		// 权限list转map
		Map<Integer, UpmsRolePermissionTreeResponse> mapPermission = new HashMap<Integer, UpmsRolePermissionTreeResponse>();
		for (UpmsRolePermissionTreeResponse per : rolePermissionResponseList) {
			mapPermission.put(per.getPermissionId(), per);
		}
		if(rolePermissionList!=null){
			for (UpmsRolePermission rolePermission : rolePermissionList) {
				if (mapPermission.get(rolePermission.getPermissionId()) != null) {
//					mapPermission.get(rolePermission.getPermissionId()).setNocheck(true);
//					mapPermission.get(rolePermission.getPermissionId()).setOpen(true);
					mapPermission.get(rolePermission.getPermissionId()).setChecked(true);
				}
			}
		}
		List<UpmsRolePermissionTreeResponse> list = new ArrayList<UpmsRolePermissionTreeResponse>();
		Iterator iter = mapPermission.entrySet().iterator(); // 获得map的Iterator

		for (Map.Entry<Integer, UpmsRolePermissionTreeResponse> entry : mapPermission.entrySet()) {
			list.add(entry.getValue());
		}
		return list;
	}

}
