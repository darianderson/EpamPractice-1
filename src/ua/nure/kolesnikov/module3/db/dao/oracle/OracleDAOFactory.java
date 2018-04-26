package ua.nure.kolesnikov.module3.db.dao.oracle;

import ua.nure.kolesnikov.module3.db.dao.DAOFactory;
import ua.nure.kolesnikov.module3.db.dao.UserDAO;

public class OracleDAOFactory extends DAOFactory {

	@Override
	public UserDAO getUserDAO() {
		return new OracleUserDAO();
	}

}
