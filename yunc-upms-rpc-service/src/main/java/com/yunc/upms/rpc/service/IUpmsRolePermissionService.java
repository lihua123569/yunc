package com.yunc.upms.rpc.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.yunc.upms.dao.entity.UpmsRolePermission;
import com.yunc.upms.rpc.pojo.UpmsRolePermissionTreeResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lijianhua
 * @since 2017-11-15
 */
public interface IUpmsRolePermissionService extends IService<UpmsRolePermission> {
	List<UpmsRolePermissionTreeResponse> getTreeByRoleId(Integer roleId);
}
