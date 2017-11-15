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
@TableName("upms_user_permission")
public class UpmsUserPermission extends Model<UpmsUserPermission> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户权限中间表id：编号
     */
    @TableId("user_permission_id")
	private Integer userPermissionId;
    /**
     * 用户id：用户编号
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 权限id：权限编号
     */
	@TableField("permission_id")
	private Integer permissionId;
    /**
     * 权限类型： -1减权限， 1增权限
     */
	private Integer type;


	public Integer getUserPermissionId() {
		return userPermissionId;
	}

	public void setUserPermissionId(Integer userPermissionId) {
		this.userPermissionId = userPermissionId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	protected Serializable pkVal() {
		return this.userPermissionId;
	}

	@Override
	public String toString() {
		return "UpmsUserPermission{" +
			"userPermissionId=" + userPermissionId +
			", userId=" + userId +
			", permissionId=" + permissionId +
			", type=" + type +
			"}";
	}
}
