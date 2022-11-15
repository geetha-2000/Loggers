package com.logs.dao;

import org.apache.logging.log4j.LogManager;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.logs.model.User;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

	private static final String INSERT_USER_QUERY = "INSERT INTO USER(id,fname,lname,email) values(?,?,?,?)";
	private static final String UPDATE_USER_BY_ID_QUERY = "UPDATE USER SET fname=?,lname=?,email=? WHERE ID=?";
	private static final String GET_USER_BY_ID_QUERY = "SELECT * FROM USER WHERE ID=?";
	private static final String DELETE_USER_BY_ID = "DELETE FROM USER WHERE ID=?";
	private static final String GET_USERS_QUERY = "SELECT * FROM USER";
	
	private static org.apache.logging.log4j.Logger logger= LogManager.getLogger(User.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User saveUser(User user) {
		try {
		jdbcTemplate.update(INSERT_USER_QUERY, user.getId(), user.getFname(), user.getLname(), user.getEmail());
		logger.info("user creation api ended");
		return user;
		
		}
		catch(Exception e)
		{
			logger.error("user already exists");
			return null;
		}
		
		
	}

	@Override
	public User updateUser(User user) {
		logger.trace("Updating a user api ended");
		jdbcTemplate.update(UPDATE_USER_BY_ID_QUERY, user.getFname(), user.getLname(), user.getEmail(), user.getId());
		return user;
	}

	@Override
	public User getById(int id) {
		try {
			
		return jdbcTemplate.queryForObject(GET_USER_BY_ID_QUERY, (rs, rowNum) -> {

			return new User(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"), rs.getString("email"));
		}, id);
		}
		catch(Exception e) {
			logger.error("user does not exists");
			return null;
		}
		
		
}

	@Override
	public void deleteById(int id) {
	
		jdbcTemplate.update(DELETE_USER_BY_ID, id);
		
;
		
	}

	@Override
	public List<User> allUsers() {
		logger.info("fetching users details");
		return jdbcTemplate.query(GET_USERS_QUERY, (rs, rowNum) -> {
			return new User(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"), rs.getString("email"));
		});
	}
}
