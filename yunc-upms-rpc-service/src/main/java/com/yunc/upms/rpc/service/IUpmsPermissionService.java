package com.yunc.upms.rpc.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.yunc.upms.dao.entity.UpmsPermission;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lijianhua
 * @since 2017-11-15
 */
public interface IUpmsPermissionService extends IService<UpmsPermission> {
	 /**
     * 根据用户id获取所拥有的权限(用户和角色权限合集)
     * @param upmsUserId
     * @return
     */
    List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Long upmsUserId);
}
