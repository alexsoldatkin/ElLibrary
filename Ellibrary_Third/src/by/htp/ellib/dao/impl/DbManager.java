package by.htp.ellib.dao.impl;

import java.util.ResourceBundle;

public class DbManager {

	private final static DbManager instance = new DbManager();

	ResourceBundle jdbcProperties = ResourceBundle.getBundle("resources.db");

	public static DbManager getInstance() {
		return instance;
	}
	
	public String getValue(String key) {
		return jdbcProperties.getString(key);
	}
}
