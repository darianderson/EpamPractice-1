package ua.nure.kolesnikov.module3.db.dao;

public abstract class DAOFactory {

	private static DAOFactory instance;

	// System properties daofactory.fqn
	// java -Ddaofactory.fqn=com.my.db.dao.mysql.MysqlDAOFactory
	public static synchronized DAOFactory getInstance() {
		if (instance == null) {
			String className = System.getProperty("daofactory.fqn");
			try {
				Class<?> clazz = Class.forName(className);
				instance = (DAOFactory) clazz.newInstance();
			} catch (Exception ex) {
				throw new IllegalStateException("Cannot instatiate class: " + className);
			}
		}
		return instance;
	}

	protected DAOFactory() {

	}

	///////////////////

	public abstract UserDAO getUserDAO();

}
