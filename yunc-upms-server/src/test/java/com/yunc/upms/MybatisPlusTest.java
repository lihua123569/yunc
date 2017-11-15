package com.yunc.upms;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.yuke.video.api.controller.VideoPicController;
import com.yuke.video.api.entity.Forum;
import com.yuke.video.api.entity.User;
import com.yuke.video.api.entity.VideoPic;
import com.yuke.video.api.request.VideoPicPageRequest;
import com.yuke.video.api.service.IForumService;
import com.yuke.video.api.service.IUserService;
import com.yuke.video.api.service.IUserZanService;

/**
 * @author lijh
 * @date 2016年12月11日 上午15:08:39
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/spring/spring-main.xml")
public class MybatisPlusTest {
 
//	@Autowired
//	private IEmployBaseService employBaseService;
	@Autowired
	private IUserService userService;
	@Test
	public void getTrade(){
	 	
//		EntityWrapper<EmployBase> ew = new EntityWrapper<EmployBase>();
//		Page<EmployBase> pageList = employBaseService.selectPage(new Page<>(1,10), ew);
		
		User empl = userService.selectById("911423689340194817");
	    System.out.println(JSONObject.toJSONString(empl));
        System.out.println("设置useSingleQuotes后：");
        System.out.println(JSONObject.toJSONString(empl, SerializerFeature.UseSingleQuotes));
        System.out.println(JSONObject.toJSONString(empl, SerializerFeature.BrowserCompatible));
        System.out.println(JSONObject.toJSONString(empl, SerializerFeature.WriteMapNullValue));
	}
 
	
	
	
	 @Autowired
	private IForumService forumService;
	
	@Test
	public void updateForum(){
		Forum f = new Forum();
		f.setTid(1L);
		f.setAuthorid(2L);
		f.setReason("213000");
		f.setSubject("asd");
		forumService.updateById(f);
	}
	
	@Test
	public void insertForum(){
		Forum f = new Forum();
		f.setTid(1L);
		f.setAuthorid(2L);
		f.setReason("213");
		f.setSubject("asd");
		forumService.insert(f);
	}
	@Test
	public void queryForum(){
		Forum f = new Forum();
		f.setTid(1L);
	 
		System.out.println(forumService.selectById(1L));
	}
	@Autowired
	private IUserZanService userZanService;
	@Test
	public void queryZan(){
		Forum f = new Forum();
		f.setTid(1L);
		
		System.out.println(userZanService.selectById(1L));
	}
	 
	 
}
