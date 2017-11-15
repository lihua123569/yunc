package com.yunc.upms.rpc.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yunc.upms.dao.entity.UpmsUser;
import com.yunc.upms.dao.mapper.UpmsUserMapper;
import com.yunc.upms.rpc.service.IUpmsUserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lijianhua
 * @since 2017-11-15
 */
@Service
public class UpmsUserServiceImpl extends ServiceImpl<UpmsUserMapper, UpmsUser> implements IUpmsUserService {
	
}
