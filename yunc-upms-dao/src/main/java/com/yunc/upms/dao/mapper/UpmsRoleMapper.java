package com.yunc.upms.dao.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yunc.upms.dao.entity.UpmsRole;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author lijianhua
 * @since 2017-11-15
 */
public interface UpmsRoleMapper extends BaseMapper<UpmsRole> {
	
	List<UpmsRole> selectUpmsRoleByUpmsUserId(Long userId);
		
}