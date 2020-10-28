package com.example.springboot.logger;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 文件名称:       LoggerTest
 * 版权:          版权所有 2020-2025 南京国睿信维软件有限公司
 * 文件版本号:     V1.0
 * 作者:          FZH
 * 创建时间:      2020年08月18日12:34
 * 描述：   
 *    测试日志
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {
	// private static final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

	@Test
	public void test1() {
		log.debug("debug......");
		log.info("info......");
		log.error("error.....");

		//日志参数数据
		String name = "zhangsan";
		int age = 20;
		log.info("name={},age={}",name,age);
	}

}
