package com.yunc.upms.server.request;

import com.baomidou.mybatisplus.annotations.TableId;
import com.liebao.lb7881.common.generic.GenericPageRequest;
import com.liebao.lb7881.common.generic.GenericRequest;

/**
 * <p>
 * 
 * </p>
 *
 * @author lijianhua
 * @since 2017-09-18
 */
public class UpmsUserPageRequest  extends GenericPageRequest{

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
	/**
	 * @return 返回 userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param  userId 
	 * userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * @return 返回 username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param  username 
	 * username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return 返回 password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param  password 
	 * password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return 返回 salt
	 */
	public String getSalt() {
		return salt;
	}
	/**
	 * @param  salt 
	 * salt
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	/**
	 * @return 返回 realname
	 */
	public String getRealname() {
		return realname;
	}
	/**
	 * @param  realname 
	 * realname
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}
	/**
	 * @return 返回 avatar
	 */
	public String getAvatar() {
		return avatar;
	}
	/**
	 * @param  avatar 
	 * avatar
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	/**
	 * @return 返回 phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param  phone 
	 * phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return 返回 email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param  email 
	 * email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return 返回 sex
	 */
	public Integer getSex() {
		return sex;
	}
	/**
	 * @param  sex 
	 * sex
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	/**
	 * @return 返回 locked
	 */
	public Integer getLocked() {
		return locked;
	}
	/**
	 * @param  locked 
	 * locked
	 */
	public void setLocked(Integer locked) {
		this.locked = locked;
	}
	/**
	 * @return 返回 ctime
	 */
	public Long getCtime() {
		return ctime;
	}
	/**
	 * @param  ctime 
	 * ctime
	 */
	public void setCtime(Long ctime) {
		this.ctime = ctime;
	}

 
}
