package com.logs.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.logs.dao.UserRepository;
import com.logs.model.User;



@Service
@Repository
public class UserService {
	
	
	@Autowired
	UserRepository userDao;
	
	
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserService.class);
	
public User createUser(User user) {
		
		if(user!=null && user.getEmail()==null)
		{
			logger.warn("Email is missing");
			return null;
		}
		else
		{
			
			return user;
		}
	}

public User getById(int id) {
	logger.info("fetch user by id");
	User user=userDao.getById(id);
	return user;
	}

public User updateUser(User user) {
	logger.info("updating user");
	User newUser = userDao.updateUser(user);
	
	return newUser;
}
public void deleteUser(int uid) {
		
	logger.info("deleting user");
		userDao.deleteById(uid);

}

	public List<User> getUser() {
		logger.info("fetch all users");
		return userDao.allUsers();
	}
}
