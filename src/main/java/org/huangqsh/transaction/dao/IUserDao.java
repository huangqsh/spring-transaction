package org.huangqsh.transaction.dao;

import org.huangqsh.transaction.entity.UserEntity;

public interface IUserDao {
	
	void addUser(UserEntity userEntity);
	
	UserEntity getUserById(int id);
	
	void deleteUserById(int id);
}
