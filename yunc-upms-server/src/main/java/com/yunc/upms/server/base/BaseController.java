package com.yunc.upms.server.base;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController {
	public Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/druid/index")
	public String index() {
		return "redirect:/druid/index.html";
	}

	@ModelAttribute
	public final void init(HttpServletRequest _request, HttpServletResponse _response, HttpSession _session) throws Exception {
	}

	/**
	 * <输出任意对象的null字段>
	 * 
	 * @param obj
	 *            [参数说明]
	 * @return
	 */
	public String toJSONStr(Object obj) {
		return JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue);
	}
}
