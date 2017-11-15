/**
 *
 */
package com.yunc.upms.common.constants;


import com.yunc.upms.common.enums.DateFormatEnum;
import com.yunc.upms.common.utils.DateUtils;

import java.util.Date;

/**
 * @author Administrator
 */
public class ApplicationProperties {
    /**
     * js版本号
     */
    public static String jsVersion = DateUtils.format(new Date(), DateFormatEnum.yyyyMMddHHmm);
    /**
	 * redis密码
	 */
	public static String redisPass;
	/**
	 * redis端口
	 */
	public static String redisPort;
	/**
	 * redis ip地址
	 */
	public static String redisServer;
	/**
	 * 图片上传地址
	 */
	public static String picUrl;
	
	public static String appKey;
	public static String umengMessageSecret;
	public static String appMasterSecret;
     
}
