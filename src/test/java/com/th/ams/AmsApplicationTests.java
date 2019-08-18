package com.th.ams;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AmsApplicationTests {

	@Before
	public void before(){
		System.out.println("############################## 测试开始 ##############################");
	}

	@Test
	public abstract void contextLoads();

	@After
	public void after(){
		System.out.println("############################## 测试结束 ##############################");
	}
}
