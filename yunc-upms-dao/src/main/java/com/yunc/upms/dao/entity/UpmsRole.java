package com.yunc.upms.dao.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
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
@TableName("upms_role")
public class UpmsRole extends Model<UpmsRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id：编号
     */
    @TableId("role_id")
	private Integer roleId;
    /**
     * 角色名称
     */
	private String name;
    /**
     * 角色标题
     */
	private String title;
    /**
     * 角色描述
     */
	private String description;
    /**
     * 创建时间
     */
	private Long ctime;
    /**
     * 排序
     */
	private Long orders;


	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Long getOrders() {
		return orders;
	}

	public void setOrders(Long orders) {
		this.orders = orders;
	}

	@Override
	protected Serializable pkVal() {
		return this.roleId;
	}

	@Override
	public String toString() {
		return "UpmsRole{" +
			"roleId=" + roleId +
			", name=" + name +
			", title=" + title +
			", description=" + description +
			", ctime=" + ctime +
			", orders=" + orders +
			"}";
	}
}
