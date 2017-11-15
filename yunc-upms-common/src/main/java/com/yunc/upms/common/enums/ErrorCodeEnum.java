package com.yunc.upms.common.enums;

import com.liebao.lb7881.common.enums.IErrorCodeEnum;

/**
 * @author Administrator
 */
public enum ErrorCodeEnum implements IErrorCodeEnum {

	/**
	 * 未设置密保问题
	 */
	NOT_SET_SECURITY("1015", "未设置密保问题"),
	/**
	 * 密保问题验证码错误
	 */
	VALID_SECURITY_ERROR("1014", "密保问题验证码错误"),
	/**
	 * 头像设置失败
	 */
	SET_HEAD_IMG_ERROR("1013", "头像设置失败"),
	/**
	 * 头像设置失败
	 */
	UPDATE_FORUM_ERROR("1012", "发帖上传图片，更新帖子失败"),
	/**
	 * 发帖上传图片，必须先传帖子id
	 */
	NO_FORUM_ID("1011", "发帖上传图片，必须先传帖子id"),
	/**
	 * 文件不能大于10M
	 */
	FILE_SIZE_BIG("1010", "文件不能大于10M"),

	/**
	 * 您尚未登录
	 */
	NOT_LOGIN("1009", "您尚未登录"),
	/**
	 * 已经点过赞了
	 */
	ALREADY_ZAN("1008", "已经点过赞了"),
	/**
	 * 该用户已存在！
	 */
	PHONE_ERROR("1007", "请正确填写手机号！"),
	/**
	 * 该用户已存在！
	 */
	USER_EXSIT("1006", "用户名已存在！"),
	/**
	 * 该招聘人员已存在！
	 */
	EMPLOYEES_EXSIT("1005", "该招聘人员已存在！"),

	/**
	 * redis缓存错误
	 */
	REDIS_ERROR("1004", "redis缓存错误"),

	/**
	 * 无可用数据导入
	 */
	NO_EXCEL_DATA_IMPORT("1003", "无可用数据导入"),
	/**
	 * 用户名不存在
	 */
	LOGIN_USERNAME_NOT_EXSIT("1002", "用户名不存在！"),
	/**
	 * 用户名或密码错误
	 */
	LOGIN_USERNAME_PWD_ERROR("1001", "用户名或密码错误！"),
	/**
	 * 查询结果为空
	 */
	QUERY_NO_RESULT("9997", "查询结果为空"),

	/**
	 * 参数非法
	 */
	ILLEGAL_ARGUMENT("9998", "参数非法"),

	/**
	 * 日期格式转换异常
	 */
	DATE_PARSE_EXCEPTION("ERROR9997", "日期格式转换异常"),

	/**
	 * 成功
	 */
	SUCCESS("0000", "success"),

	/**
	 * 系统异常
	 */
	SYSTEM_ERROR("9999", "当前网络忙，请稍后再试");

	/**
	 * 响应码
	 */
	private String responseCode;
	/**
	 * 描述
	 */
	private String responseMsg;

	/**
	 * 私有构造函数
	 *
	 * @param responseCode
	 * @param responseMsg
	 */
	private ErrorCodeEnum(String responseCode, String responseMsg) {
		this.responseCode = responseCode;
		this.responseMsg = responseMsg;
	}

	@Override
	public String getResponseCode() {
		return responseCode;
	}

	@Override
	public String getResponseMsg() {
		return responseMsg;
	}

}
