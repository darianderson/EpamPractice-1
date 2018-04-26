package ua.nure.kolesnikov.module3.db.dao;

import ua.nure.kolesnikov.module3.db.entity.User;

public interface UserDAO {
	void create(User user);
	User find();
	boolean update(User user);
	boolean delete(User user);
	
	User findByLogin(String login);
	
	

}
