package by.htp.ellib.service;

import java.util.List;

import by.htp.ellib.entity.User;
import by.htp.ellib.entity.UserData;

public interface ClientService {
	
	User authorization(String login, String password) throws ServiceException;
	boolean registration(UserData user) throws ServiceException;
	List<UserData> seeAllUsers() throws ServiceException;
	
}
