package by.htp.ellib.dao;

import java.util.List;

import by.htp.ellib.entity.Book;
import by.htp.ellib.entity.User;
import by.htp.ellib.entity.UserData;

public interface UserDAO {

	User authorization(String login, String password) throws DaoException;
	
	boolean registration(UserData userData)  throws DaoException;
	List<UserData> seeAllUsers()  throws DaoException;
}
