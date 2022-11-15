package com.logs.controller;


import com.logs.model.User;
import com.logs.service.UserService;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

	//@Autowired
	//UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	private static org.apache.logging.log4j.Logger logger= LogManager.getLogger(UserController.class);

	@PostMapping("/user")
	public User addUser(@RequestBody User user) {
		logger.info("User creation api started ");
		return userService.createUser(user);

	}

	@PutMapping("/user")
	public User updateUser(@RequestBody User user) {
		logger.info("User updation api started");
		return userService.updateUser(user);

	}

	@GetMapping("/user/{id}")
	public User getUser(@PathVariable("id") int id) {
		logger.info("fetching a particular user details api started");
		return userService.getById(id);
	}

	@GetMapping("/users")
	public List<User> getUsers() {
		logger.info("fetching all users details api started");
		return userService.getUser();
	}

	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		logger.info("deleting a user api started");
		userService.deleteUser(id);
	}

}
