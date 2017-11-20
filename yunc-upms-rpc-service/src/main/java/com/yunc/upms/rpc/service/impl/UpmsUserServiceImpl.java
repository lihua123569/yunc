package com.yunc.upms.rpc.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.liebao.lb7881.common.utils.AssertUtils;
import com.yunc.upms.common.enums.ErrorCodeEnum;
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
	@Override
	public UpmsUser login(UpmsUser upmsUser) {
		 
		UpmsUser u = baseMapper.selectOne(upmsUser);
		AssertUtils.isTrue(u != null, ErrorCodeEnum.LOGIN_USERNAME_NOT_EXSIT);

		AssertUtils.isTrue(upmsUser.getPassword().equals(u.getPassword()), ErrorCodeEnum.LOGIN_USERNAME_PWD_ERROR);
//		AssertUtils.isTrue(DigestUtils.md5Hex(userRequest.getPassword()).equals(u.getPassword()), ErrorCodeEnum.LOGIN_USERNAME_PWD_ERROR);
		return u;
	}

}
