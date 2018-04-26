package ua.nure.kolesnikov.module3.db.dao.mysql;

import ua.nure.kolesnikov.module3.db.dao.DAOFactory;
import ua.nure.kolesnikov.module3.db.dao.UserDAO;

public class MysqlDAOFactory extends DAOFactory {

	@Override
	public UserDAO getUserDAO() {
		return new MysqlUserDAO();
	}

}
