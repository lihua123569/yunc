package com.yunc.upms.rpc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yunc.upms.dao.entity.UpmsRole;
import com.yunc.upms.dao.mapper.UpmsRoleMapper;
import com.yunc.upms.rpc.service.IUpmsRoleService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lijianhua
 * @since 2017-11-15
 */
@Service
public class UpmsRoleServiceImpl extends ServiceImpl<UpmsRoleMapper, UpmsRole> implements IUpmsRoleService {

	@Override
	public List<UpmsRole> selectUpmsRoleByUpmsUserId(Long userId) {
		return baseMapper.selectUpmsRoleByUpmsUserId(userId);
	}
	
}
