package org.huangqsh.transaction.service;

import javax.annotation.Resource;

import org.huangqsh.transaction.dao.IUserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserFactory {
	@Resource
	private IUserDao userDao;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteUserById(int id) {
		userDao.deleteUserById(id);
		throw new RuntimeException();
	}
}
