package ua.nure.kolesnikov.module3.db.dao.mysql;

import ua.nure.kolesnikov.module3.db.dao.UserDAO;
import ua.nure.kolesnikov.module3.db.entity.User;

public class MysqlUserDAO implements UserDAO {

	@Override
	public void create(User user) {
		System.out.println("MysqlUserDAO#create");
	}

	@Override
	public User find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User findByLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}

}
