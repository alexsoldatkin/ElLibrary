package by.htp.ellib.dao;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import by.htp.ellib.controller.Controller;
import by.htp.ellib.dao.impl.SQLUserDAO;
import by.htp.ellib.entity.User;
import by.htp.ellib.entity.UserData;

public class Main {

	public static void main(String[] args) throws DaoException, IOException {
		
		SQLUserDAO dao = new SQLUserDAO();
		User user=null;
		user = dao.authorization("alex", "12345");
		
		System.out.println(user.toString());
		
//		if(user==null){{
//			user=dao.registration();
//		}
		System.out.println(user.getName());

	}

}
