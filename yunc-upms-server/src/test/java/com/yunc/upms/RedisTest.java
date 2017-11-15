package com.yunc.upms;

import com.liebao.lb7881.cache.impl.redis.RedisManager;
import com.liebao.lb7881.common.excepiton.AppException;
import com.yuke.video.common.enums.ErrorCodeEnum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijh
 * @date 2016年12月11日 上午15:08:39
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/spring/spring-main.xml")
public class RedisTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisTest.class);

 
	/* redis测试 */
	@Test
	public void redisTest() {
		try {
			//存取string
			System.out.println(RedisManager.getInstance().get("downloadDay", String.class) + "------");

			Map<String, Object> m = new HashMap<String, Object>();
			m.put("youli", "youli8888");
			m.put("yyb", "123");

			//存取map
			RedisManager.getInstance().set("test", m, -1);
			@SuppressWarnings("unchecked")
			Map<String, Object> userAdminMap = RedisManager.getInstance().get("test", Map.class);

			System.out.println(userAdminMap.get("youli") + "------" + userAdminMap.get("yyb"));

			 
			//删除list
			RedisManager.getInstance().delete("tbListTest");

		} catch (Exception e) {
			LOGGER.error("图片验证码，delete 调用缓存MemcacheManager异常：" + e);
			throw new AppException(ErrorCodeEnum.SYSTEM_ERROR);
		}


	}


	/* redis测试 */
	@Test
	public void getShouquan() {
		//存取string
		System.out.println(RedisManager.getInstance().get("shouquan", String.class) + "------");

	}
}
