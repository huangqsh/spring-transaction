package org.huangqsh.transaction.service;

import javax.annotation.Resource;

import org.huangqsh.transaction.dao.IUserDao;
import org.huangqsh.transaction.entity.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
	@Resource
	private IUserDao userDao;
	
	@Resource
	private UserFactory userFactory;
	
	@Transactional()
	public void addUser(UserEntity userEntity) {
		System.out.println("----------------------add开始--------------------------");
		userDao.addUser(userEntity);
		System.out.println("---------------------线程沉睡4s---------------- ---------");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("--------------  ---add抛出异常，事物回滚-------- -----------");
		// RuntimeException属于unchecked异常会触发事物回滚，但是checked异常是不会触发事物回滚的
		// 以上只是默认情况，可以通过noRollbackFor和rollbackFor属性进行设置
		throw new RuntimeException("抛出异常");
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteById(int id) {
		userDao.deleteUserById(id);
		try {
			userFactory.deleteUserById(id+1);
		} catch (Exception e) {
			System.out.println("捕获到异常");
		}
			
	}
	

	//设置事物隔离级别为READ_UNCOMMITTED，即可读未提交事物，这会导致脏的，
	@Transactional(isolation=Isolation.READ_UNCOMMITTED)
	public UserEntity getUserById(int id) {
		return userDao.getUserById(id);
	}
}
