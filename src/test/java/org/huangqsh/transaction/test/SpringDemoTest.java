package org.huangqsh.transaction.test;

import javax.annotation.Resource;

import org.huangqsh.transaction.entity.UserEntity;
import org.huangqsh.transaction.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring-core.xml"})
public class SpringDemoTest {
	@Resource
	private UserService userService;
	
	//测试隔离级别
	@Test
	public void testIsolation() {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("---------------------查询开始-------------------------");
				UserEntity userEntity = userService.getUserById(2);;
				System.out.println(userEntity.getUsername());
			}
		});
		thread.start();
		UserEntity userEntity = new UserEntity(2, "悟空", "111");
		userService.addUser(userEntity);
	}
	
	//测试传播属性
	@Test
	public void testPropagation() {

	}
	
}
