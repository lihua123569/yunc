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
@TableName("upms_user")
public class UpmsUser extends Model<UpmsUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id：编号
     */
    @TableId("user_id")
	private Long userId;
    /**
     * 帐号
     */
	private String username;
    /**
     * 密码MD5(密码+盐)
     */
	private String password;
    /**
     * 盐
     */
	private String salt;
    /**
     * 姓名
     */
	private String realname;
    /**
     * 头像
     */
	private String avatar;
    /**
     * 电话
     */
	private String phone;
    /**
     * 邮箱
     */
	private String email;
    /**
     * 性别
     */
	private Integer sex;
    /**
     * 状态(0:正常,1:锁定)
     */
	private Integer locked;
    /**
     * 创建时间
     */
	private Long ctime;


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public Long getCtime() {
		return ctime;
	}

	public void setCtime(Long ctime) {
		this.ctime = ctime;
	}

	@Override
	protected Serializable pkVal() {
		return this.userId;
	}

	@Override
	public String toString() {
		return "UpmsUser{" +
			"userId=" + userId +
			", username=" + username +
			", password=" + password +
			", salt=" + salt +
			", realname=" + realname +
			", avatar=" + avatar +
			", phone=" + phone +
			", email=" + email +
			", sex=" + sex +
			", locked=" + locked +
			", ctime=" + ctime +
			"}";
	}
}