package com.yunc.upms.dao.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lijianhua
 * @since 2017-11-15
 */
@TableName("upms_role_permission")
public class UpmsRolePermission extends Model<UpmsRolePermission> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色权限中间表id：编号
     */
    @TableId(value="role_permission_id", type=IdType.AUTO)
	private Integer rolePermissionId;
    /**
     * 角色id：角色编号
     */
	@TableField("role_id")
	private Integer roleId;
    /**
     * 权限id：权限编号
     */
	@TableField("permission_id")
	private Integer permissionId;


	public Integer getRolePermissionId() {
		return rolePermissionId;
	}

	public void setRolePermissionId(Integer rolePermissionId) {
		this.rolePermissionId = rolePermissionId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	@Override
	protected Serializable pkVal() {
		return this.rolePermissionId;
	}

	@Override
	public String toString() {
		return "UpmsRolePermission{" +
			"rolePermissionId=" + rolePermissionId +
			", roleId=" + roleId +
			", permissionId=" + permissionId +
			"}";
	}
}
