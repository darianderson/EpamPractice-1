package ua.nure.kolesnikov.module3.db.dao.oracle;


import ua.nure.kolesnikov.module3.db.dao.UserDAO;
import ua.nure.kolesnikov.module3.db.entity.User;

public class OracleUserDAO implements UserDAO {

	@Override
	public void create(User user) {
		System.out.println("OracleUserDAO#create");
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
