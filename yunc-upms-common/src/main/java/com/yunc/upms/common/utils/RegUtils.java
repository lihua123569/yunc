package com.yunc.upms.common.utils;

import com.xiaoleilu.hutool.util.ReUtil;

import java.math.BigDecimal;

public class RegUtils {

	// 小于1000，小数点后最多6位
	public static Boolean isLegalParam(String str) {
		String reg = "^[0-9]+(.[0-9]{0,6})?$";
		return str.matches(reg) && new BigDecimal(str).compareTo(new BigDecimal(100000)) < 0;
	}

	/**
	 * <是否是手机号> <br />
	 * <br />
	 * 2017年标准<br />
	 * <br />
	 * 中国移动: 139、138、137、136、135、134（0-8）、 456789 147（数据卡）、148、1440 478
	 * 159、158、157、150、151、152、 012789 178、1703、1705、1706、 08
	 * 188、187、182、183、184、 23478 198、 <br />
	 * 中国联通: 130、131、132、 012 145（数据卡）、 146 56 156、155、 56 166、
	 * 176、175、1707、1708、1709 、 056 186、185、 56 <br />
	 * 中国电信: 133、1349、 3 149、 1410 19 153、 3 177、173、 1700、1701、1702、 037
	 * 189、180、181、 019 199、
	 * 
	 * @param str
	 * @return [参数说明]
	 * 15 150 151 152 153 非4 155
	 */
	public static Boolean isPhone(String str) {
		return ReUtil.isMatch("^((13[0-9])|(14[1,4-9])|(15[^4,\\D])|(166)|(17[0,3,5-8])|(18[0-9])|(19[8,9]))\\d{8}$",
				str);
	}

	/**
	 * 使用java正则表达式去掉多余的.与0
	 * 
	 * @param s
	 * @return
	 */
	public static String subZeroAndDot(String s) {
		if (s.indexOf(".") > 0) {
			s = s.replaceAll("0+?$", "");// 去掉多余的0
			s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
		}
		return s;
	}

}
