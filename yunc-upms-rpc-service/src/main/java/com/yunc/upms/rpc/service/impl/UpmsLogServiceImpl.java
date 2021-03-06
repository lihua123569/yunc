package com.yunc.upms.rpc.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yunc.upms.dao.entity.UpmsLog;
import com.yunc.upms.dao.mapper.UpmsLogMapper;
import com.yunc.upms.rpc.service.IUpmsLogService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lijianhua
 * @since 2017-11-15
 */
@Service
public class UpmsLogServiceImpl extends ServiceImpl<UpmsLogMapper, UpmsLog> implements IUpmsLogService {
	
}
