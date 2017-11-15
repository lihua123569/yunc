/**
 * 
 */
package com.yunc.upms.common.utils;

import org.apache.commons.collections.MapUtils;

import com.yunc.upms.common.enums.ErrorCodeEnum;

import java.util.HashMap;
import java.util.Map;


/**
 * @author pan
 *
 */
public class ResultHandle {
	
	/**
	 * 响应码
	 */
	private static final String RESPONSE_CODE = "responseCode";
	/**
	 * 响应信息
	 */
	private static final String RESPONSE_MSG = "responseMsg";
	
	
	/**
	 * 判断返回的结果是否正确
	 * @param result
	 * @return
	 */
	public static boolean isSuccess(Map<String, Object> result){
		
		return "0000".equals(MapUtils.getString(result, RESPONSE_CODE));
	}

	/**
	 * 成功
	 * @return
	 */
	public static Map<String, Object> success(){
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put(RESPONSE_CODE, ErrorCodeEnum.SUCCESS.getResponseCode());
		
		result.put(RESPONSE_MSG, ErrorCodeEnum.SUCCESS.getResponseMsg());
		
		return result;
	}
	
	/**
	 * 失败错误返回报文
	 * @param errorCodeEnum
	 * @return
	 */
	public static Map<String, Object> fail(ErrorCodeEnum errorCodeEnum){
		
		return fail(new HashMap<String, Object>(), errorCodeEnum);
	}
	
	/**
	 * 失败错误报文返回
	 * @param result
	 * @param errorCodeEnum
	 * @return
	 */
	public static Map<String, Object> fail(Map<String, Object> result ,ErrorCodeEnum errorCodeEnum){

		result.put(RESPONSE_CODE, errorCodeEnum.getResponseCode());
		
		result.put(RESPONSE_MSG, errorCodeEnum.getResponseMsg());
		
		return result;
	}
	
	
	
	
}
