package com.yunc.upms.rpc.service;

import com.baomidou.mybatisplus.service.IService;
import com.yunc.upms.dao.entity.UpmsUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lijianhua
 * @since 2017-11-15
 */
public interface IUpmsUserService extends IService<UpmsUser> {
	public UpmsUser login(UpmsUser upmsUser);
}
