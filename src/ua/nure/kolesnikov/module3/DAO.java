package ua.nure.kolesnikov.module3;

import ua.nure.kolesnikov.module3.db.dao.DAOFactory;
import ua.nure.kolesnikov.module3.db.dao.UserDAO;
import ua.nure.kolesnikov.module3.db.entity.User;

public class DAO {

	public static void main(String[] args) {
		
		System.setProperty("daofactory.fqn", "ua.nure.kolesnikov.module3.db.dao.mysql.MysqlDAOFactory");
		System.setProperty("daofactory.fqn", "ua.nure.kolesnikov.module3.db.dao.OracleDAOFactory");
				
		
		User user = new User();
		// adjust user

		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		
		userDAO.create(user);
		
	}

}
