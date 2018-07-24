package org.huangqsh.transaction.service;

import javax.annotation.Resource;

import org.huangqsh.transaction.dao.IUserDao;
import org.huangqsh.transaction.entity.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
	@Resource
	private IUserDao userDao;
	
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
		System.out.println(1/0);
	}
	
	public void deleteById(int id) {
		userDao.deleteUserById(id);
	}
	
	@Transactional(isolation=Isolation.READ_UNCOMMITTED)
	public UserEntity getUserById(int id) {
		return userDao.getUserById(id);
	}
}
