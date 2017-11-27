package com.yunc.upms.rpc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yunc.upms.dao.entity.UpmsPermission;
import com.yunc.upms.dao.entity.UpmsUser;
import com.yunc.upms.dao.mapper.UpmsPermissionMapper;
import com.yunc.upms.dao.mapper.UpmsUserMapper;
import com.yunc.upms.rpc.service.IUpmsPermissionService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lijianhua
 * @since 2017-11-15
 */
@Service
public class UpmsPermissionServiceImpl extends ServiceImpl<UpmsPermissionMapper, UpmsPermission> implements IUpmsPermissionService {

	@Autowired
	UpmsUserMapper upmsUserMapper;
	
	@Override
	public List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Long upmsUserId) {
		  // 用户不存在或锁定状态
        UpmsUser upmsUser = upmsUserMapper.selectById(upmsUserId);
        if (null == upmsUser || 1 == upmsUser.getLocked()) {
           System.out.println("selectUpmsPermissionByUpmsUserId : upmsUserId={}"+upmsUserId);
            return null;
        }
        List<UpmsPermission> upmsPermissions = baseMapper.selectUpmsPermissionByUpmsUserId(upmsUserId);
        return upmsPermissions;
	}
	
}
