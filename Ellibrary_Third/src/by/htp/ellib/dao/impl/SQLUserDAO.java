package by.htp.ellib.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.ellib.dao.DaoException;
import by.htp.ellib.dao.UserDAO;
import by.htp.ellib.entity.User;
import by.htp.ellib.entity.UserData;

public class SQLUserDAO extends SqlDao implements UserDAO{

	private static final String QUERY_CHECK_CREDENTIONALS = "SELECT * FROM library.users WHERE login=? and password=?";
	private static final ConnectionPool pool = ConnectionPool.getInstance();
	private static final String SEE_ALL_USERS_STATEMENT = "SELECT * FROM library.users";
	
	@Override
	public User authorization(String userLogin, String userPassword) throws DaoException{
		Connection con = null;
		PreparedStatement st= null;
	    ResultSet rs = null;
	    System.out.println("null");
		User user = null;
		
		try {
		    System.out.println("0");

			con = DriverManager.getConnection(url, login, password);
		    System.out.println("1");


			st = con.prepareStatement(QUERY_CHECK_CREDENTIONALS);
		    System.out.println("2");

			st.setString(1, userLogin);
			st.setString(2, userPassword);
			

			rs = st.executeQuery();
			System.out.println("SQLUserDAO.authentification");

			if(rs.next()) {
				user  = createUser(rs);
			}

		} catch (SQLException e) {
		    System.out.println("daoex");
			throw new DaoException(e);
		} finally {
			close(rs, st, con);
		}

		return user;
	}
	
	
	private UserData createUser(ResultSet rs) throws SQLException {
		UserData user = new UserData();
		
		user.setId(rs.getInt(1));
		user.setName(rs.getString(5));
		user.setSurname(rs.getString(6));
		user.setLogin(rs.getString(2));
		user.setSex(rs.getString(7));
		user.setBirthYear(rs.getLong(8));
		user.setEmail(rs.getString(4));
		
		return user;
		
	}
	
	
	private void close(ResultSet rs, PreparedStatement st, Connection con) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			// log
		}
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			// log
		}
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			// log
		}
	}


	@Override
	public boolean registration(UserData userData) throws DaoException {
		Connection con = null;
		PreparedStatement stUsers= null;
		ResultSet rs = null;
		
		User user = null;
		
		try {
			con = DriverManager.getConnection(url, login, password);
			con.setAutoCommit(false);
			
			String REGISTRATION_STATEMENT = "INSERT INTO library.users (login,password,email,name,surname,sex,birthYear)"+ "VALUES (?,?,?,?,?,?,?)";
			
			stUsers = con.prepareStatement(REGISTRATION_STATEMENT);
			
			stUsers.setString(1,userData.getLogin());
			stUsers.setString(2,userData.getPassword());
			stUsers.setString(3,userData.getEmail());
			stUsers.setString(4,userData.getName());
			stUsers.setString(5,userData.getSurname());
			stUsers.setString(6,userData.getSex());
			stUsers.setLong(7,userData.getBirthYear());
					
			stUsers.executeUpdate();
			

			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new DaoException(e);
			}
			throw new DaoException(e);
		} finally {
			
		}
		return true;
	}


	@Override
	public List<UserData> seeAllUsers() throws DaoException {

		Connection con = null;
		PreparedStatement st= null;
	    ResultSet rs = null;
	    
		List<UserData> users=new ArrayList<>();

		try {			
			con=DriverManager.getConnection(url, login, password);
			con.setAutoCommit(false);
			
			st=con.prepareStatement(SEE_ALL_USERS_STATEMENT);
			
			rs=st.executeQuery();
			
			while (rs.next()) {
				UserData user=createUser(rs);
				users.add(user);
				}
		}
		catch(SQLException e) {
				throw new DaoException(e);
			}
		
		return users;
	}
	



}
