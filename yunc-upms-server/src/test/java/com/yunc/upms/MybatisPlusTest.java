package com.yunc.upms;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yunc.upms.dao.entity.UpmsPermission;
import com.yunc.upms.rpc.service.IUpmsPermissionService;

/**
 * @author lijh
 * @date 2016年12月11日 上午15:08:39
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/spring/spring-main.xml")
public class MybatisPlusTest {
 
	@Autowired
	private IUpmsPermissionService userService;
	@Test
	public void getTrade(){
	 	
		EntityWrapper<UpmsPermission> ew = new EntityWrapper<UpmsPermission>();
		ew.setSqlSelect("pid as pId");
		List<UpmsPermission> empl = userService.selectList(ew);
	    System.out.println(JSONObject.toJSONString(empl));
        System.out.println("设置useSingleQuotes后：");
        System.out.println(JSONObject.toJSONString(empl, SerializerFeature.UseSingleQuotes));
        System.out.println(JSONObject.toJSONString(empl, SerializerFeature.BrowserCompatible));
        System.out.println(JSONObject.toJSONString(empl, SerializerFeature.WriteMapNullValue));
	}
 
	 
}
