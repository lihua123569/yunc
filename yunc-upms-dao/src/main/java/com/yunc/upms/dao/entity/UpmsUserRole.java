package com.yunc.upms.dao.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lijianhua
 * @since 2017-11-15
 */
@TableName("upms_user_role")
public class UpmsUserRole extends Model<UpmsUserRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户角色中间表：用户角色编号
     */
    @TableId("user_role_id")
	private Integer userRoleId;
    /**
     * 用户id：用户编号
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 角色id：角色编号
     */
	@TableField("role_id")
	private Integer roleId;


	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	protected Serializable pkVal() {
		return this.userRoleId;
	}

	@Override
	public String toString() {
		return "UpmsUserRole{" +
			"userRoleId=" + userRoleId +
			", userId=" + userId +
			", roleId=" + roleId +
			"}";
	}
}
