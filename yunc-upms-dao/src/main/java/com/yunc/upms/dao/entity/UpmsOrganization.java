package com.yunc.upms.dao.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
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
@TableName("upms_organization")
public class UpmsOrganization extends Model<UpmsOrganization> {

    private static final long serialVersionUID = 1L;

    /**
     * 组织id：编号
     */
    @TableId(value="organization_id", type=IdType.AUTO)
	private Integer organizationId;
    /**
     * 所属上级
     */
	private Integer pid;
    /**
     * 组织名称
     */
	private String name;
    /**
     * 组织描述
     */
	private String description;
	private Long ctime;


	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCtime() {
		return ctime;
	}

	public void setCtime(Long ctime) {
		this.ctime = ctime;
	}

	@Override
	protected Serializable pkVal() {
		return this.organizationId;
	}

	@Override
	public String toString() {
		return "UpmsOrganization{" +
			"organizationId=" + organizationId +
			", pid=" + pid +
			", name=" + name +
			", description=" + description +
			", ctime=" + ctime +
			"}";
	}
}
