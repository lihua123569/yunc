/**  
 * Copyright © 2017猎宝网络科技股份有限公司. All rights reserved.
 *
 * @Title: MysqlGeneral.java
 * @Prject: sboot
 * @Package: com.sboot
 * @Description: TODO
 * @author: lijh  
 * @date: 2017年2月27日 下午2:29:27
 * @version: V1.0  
 */
package com.yunc.upms;

import java.io.File;
import java.io.IOException;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @ClassName: MysqlGeneral
 * @Description: TODO
 * @author: lijh
 * @date: 2017年2月27日 下午2:29:27
 */
public class MysqlGenerator {

	/**
	 * <p>
	 * MySQL 生成演示
	 * </p>
	 */
	public static void main(String[] args) {
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
//		项目绝对路径
		gc.setOutputDir(getRootPath() + "/src/main/java/");
		gc.setFileOverride(true);
		gc.setActiveRecord(true);// 开启 activeRecord 模式
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(true);// XML ResultMap
		gc.setBaseColumnList(false);// XML columList
		gc.setAuthor("lijianhua");
		gc.setOpen(true);
		// 自定义文件命名，注意 %s 会自动填充表实体属性！
		// gc.setMapperName("%sDao");
		// gc.setXmlName("%sDao");
		// gc.setServiceName("MP%sService");
		// gc.setServiceImplName("%sServiceDiy");
		// gc.setControllerName("%sAction");
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setTypeConvert(new MySqlTypeConvert(){
			// 自定义数据库表字段类型转换【可选】
			@Override
			public DbColumnType processTypeConvert(String fieldType) {
				System.out.println("转换类型：" + fieldType);
				return super.processTypeConvert(fieldType);
			}
		});
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("123456");
//		dsc.setPassword("123456");
		dsc.setUrl("jdbc:mysql://127.0.0.1:3306/yunc?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true");
//		dsc.setUrl("jdbc:mysql://127.0.0.1:3306/video-app?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true");
		mpg.setDataSource(dsc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		// strategy.setCapitalMode(true);// 全局大写命名
		// strategy.setDbColumnUnderline(true);//全局下划线命名
//		strategy.setTablePrefix(new String[] { "bmd_", "mp_" });// 此处可以修改为您的表前缀
		strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
		// strategy.setInclude(new String[] { "user" }); // 需要生成的表
		// strategy.setExclude(new String[]{"test"}); // 排除生成的表
		// 自定义实体父类
		// strategy.setSuperEntityClass("com.baomidou.demo.TestEntity");
		// 自定义实体，公共字段
		// strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
		// 自定义 mapper 父类
		// strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
		// 自定义 service 父类
		// strategy.setSuperServiceClass("com.baomidou.demo.TestService");
		// 自定义 service 实现类父类
		// strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
		// 自定义 controller 父类
		// strategy.setSuperControllerClass("com.baomidou.demo.TestController");
		// 【实体】是否生成字段常量（默认 false）
		// public static final String ID = "test_id";
		// strategy.setEntityColumnConstant(true);
		// 【实体】是否为构建者模型（默认 false）
		// public User setName(String name) {this.name = name; return this;}
		// strategy.setEntityBuliderModel(true);
		
//		strategy.setInclude(new String[]{"security"});
//		strategy.setExclude(new String[]{"user"});
		mpg.setStrategy(strategy);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent("com.yunc.upms");// 自定义包路径
//		pc.setModuleName("server");//com.sboot.user.controller
		pc.setController("controller");// 这里是控制器包名，默认 web
		mpg.setPackageInfo(pc);

		// 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
	/*	InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
				this.setMap(map);
			}
		};
		List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
		focList.add(new FileOutConfig("/template/entity.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输入文件名称
				return "/develop/code/my_" + tableInfo.getEntityName() + ".java";
			}
		});
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);*/

		// 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
		/*// 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
		 TemplateConfig tc = new TemplateConfig();
		 tc.setController("UserController");
		 tc.setEntity("User");
		 tc.setMapper("UserMapper");
		 tc.setXml("UserMapperXml");
		 tc.setService("UserService");
		 tc.setServiceImpl("UserServiceImpl");
		 mpg.setTemplate(tc);*/

		// 执行生成
		mpg.execute();

		// 打印注入设置
		/*System.err.println(mpg.getCfg().getMap().get("abc"));*/
	}
	  /**
     * 获取项目根路径
     *
     * @return 项目路径
     */
    private static String getRootPath() {
        File directory = new File("");// 参数为空
        String courseFile = null;
        try {
            courseFile = directory.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courseFile;
    }
}
