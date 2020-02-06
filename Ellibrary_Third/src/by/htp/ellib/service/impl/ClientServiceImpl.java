package by.htp.ellib.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.ellib.dao.DAOProvider;
import by.htp.ellib.dao.DaoException;
import by.htp.ellib.dao.UserDAO;
import by.htp.ellib.dao.impl.DbManager;
import by.htp.ellib.dao.impl.SqlDao;
import by.htp.ellib.entity.User;
import by.htp.ellib.entity.UserData;
import by.htp.ellib.service.ClientService;
import by.htp.ellib.service.ServiceException;
import by.htp.ellib.service.validation.CredentionalValidator;


public class ClientServiceImpl implements ClientService{

	
	@Override
	public User authorization(String login, String password) throws ServiceException {
		
		if(!CredentionalValidator.isCorrect(login, password)) {
			throw new ServiceException();
		}
		
		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = daoProvider.getUserDAO();
		
		User user = null;
				
		try {		
			user =	userDAO.authorization(login, password);
		}catch(DaoException e) {
			throw new ServiceException();// (e)
		}
		System.out.println("clientServiceImpl.authorization");
		return user;
	}

	@Override
	public boolean registration(UserData userData) throws ServiceException {
				
		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = daoProvider.getUserDAO();
	
		try {
			userDAO.registration(userData);
			System.out.println("clientServiceImpl.registration");

		} catch (DaoException e) {
			throw new ServiceException();// (e)
		} finally {
			
		}
		return true;
	}

	@Override
	public List<UserData> seeAllUsers() throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = daoProvider.getUserDAO();
		
		List<UserData> users=new ArrayList<>();
		
		try {
			users=userDAO.seeAllUsers();
			
		} catch (DaoException e) {
			throw new ServiceException();// (e)
		} finally {
			
		}
		return users;
	}

}
