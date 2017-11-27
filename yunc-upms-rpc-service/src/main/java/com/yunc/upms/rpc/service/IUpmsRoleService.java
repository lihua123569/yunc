package com.yunc.upms.rpc.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.yunc.upms.dao.entity.UpmsRole;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lijianhua
 * @since 2017-11-15
 */
public interface IUpmsRoleService extends IService<UpmsRole> {
	List<UpmsRole> selectUpmsRoleByUpmsUserId(Long userId );
}
