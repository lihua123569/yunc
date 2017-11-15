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
@TableName("upms_user_organization")
public class UpmsUserOrganization extends Model<UpmsUserOrganization> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户组织中间表id：编号
     */
    @TableId("user_organization_id")
	private Integer userOrganizationId;
    /**
     * 用户id：用户编号
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 组织id：组织编号
     */
	@TableField("organization_id")
	private Integer organizationId;


	public Integer getUserOrganizationId() {
		return userOrganizationId;
	}

	public void setUserOrganizationId(Integer userOrganizationId) {
		this.userOrganizationId = userOrganizationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	@Override
	protected Serializable pkVal() {
		return this.userOrganizationId;
	}

	@Override
	public String toString() {
		return "UpmsUserOrganization{" +
			"userOrganizationId=" + userOrganizationId +
			", userId=" + userId +
			", organizationId=" + organizationId +
			"}";
	}
}
