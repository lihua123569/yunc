package com.yunc.upms;

import org.junit.Test;
import org.slf4j.impl.StaticLoggerBinder;

import java.net.URL;


/**
 * <jar 包冲突> <功能详细描述> 时 间: 2017年6月6日 下午7:34:03 <br/>
 *
 * @author Administrator
 * @version [版本号, 2017年6月6日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class JarConflict {
	@Test
	public void get() {
		URL url = StaticLoggerBinder.class.getProtectionDomain().getCodeSource().getLocation();
		System.out.println("path:" + url.getPath() + "  name:" + url.getFile());
	}
}
