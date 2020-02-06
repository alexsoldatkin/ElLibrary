package by.htp.ellib.dao.impl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.ellib.dao.BookDAO;
import by.htp.ellib.dao.DaoException;
import by.htp.ellib.entity.Book;
import by.htp.ellib.entity.User;
import by.htp.ellib.entity.UserData;


public class SQLBookDAO extends SqlDao implements BookDAO{

	String ADD_NEW_BOOK_STATEMENT = "INSERT INTO books (title, author, price, genre, release_year) VALUES (?,?,?,?,?)";
	String FIND_BY_AUTHOR_STATEMENT = "SELECT * FROM library.books WHERE author=?";
	String FIND_BY_TITLE_STATEMENT = "SELECT * FROM library.books WHERE title=?";
	String FIND_BY_GENRE_STATEMENT = "SELECT * FROM books WHERE genre=?";
	String FIND_BY_RELEASE_YEAR_STATEMENT = "SELECT * FROM books WHERE release_year=?";
	String SHOW_ALL_BOOKS_STATEMENT="SELECT * FROM library.books";
	String DELETE_BOOK_STATEMENT="DELETE FROM library.books WHERE id_book=? and title=?";
	String DELETE_BOOK_BY_ID="DELETE FROM library.books WHERE id_book=?";
	String DELETE_BOOK_TITLE="DELETE FROM library.books WHERE title=?";

	Connection con = null;
	PreparedStatement st= null;
	ResultSet rs = null;
	ConnectionPool connectionPool=ConnectionPool.getInstance();
	@Override
	public List<Book> findByAuthor(String author) throws DaoException, ConnectionPoolException {
		
		List<Book> books=new ArrayList<>();

		try {			
			con=connectionPool.takeConnection();
			con.setAutoCommit(false);
			
			st=con.prepareStatement(FIND_BY_AUTHOR_STATEMENT);	
			st.setString(1, author);
						
			rs=st.executeQuery();
			
			while (rs.next()) {
				Book book=createBook(rs);
				books.add(book);
				}
			}
			catch(SQLException e) {
					throw new DaoException(e);
			}
			finally {
				connectionPool.closeConnection(rs, st, con);
			}
		return books;
		
	}
	
	public List<Book> findByTitle(String title) throws DaoException, ConnectionPoolException {
		
		List<Book> books=new ArrayList<>();

		try {			
			con=connectionPool.takeConnection();
			con.setAutoCommit(false);
			
			st=con.prepareStatement(FIND_BY_TITLE_STATEMENT);	
			st.setString(1, title);
						
			rs=st.executeQuery();
			
			while (rs.next()) {
				Book book=createBook(rs);
				books.add(book);
				}
			}
		
			catch(SQLException e) {
					throw new DaoException(e);
			}
		finally {
			connectionPool.closeConnection(rs, st, con);
		}
		return books;
	}
	
	public List<Book> findByGenre(String genre) throws DaoException, ConnectionPoolException {
		List<Book> books=new ArrayList<>();

		 
		try {			
			con=connectionPool.takeConnection();
			con.setAutoCommit(false);
			
			st=con.prepareStatement(FIND_BY_GENRE_STATEMENT);	
			st.setString(1, genre);
						
			rs=st.executeQuery();
			
			while (rs.next()) {
				Book book=createBook(rs);
				books.add(book);
				}
			}
		
		catch(SQLException e) {
				throw new DaoException(e);
			}
		finally {
			connectionPool.closeConnection(rs, st, con);
		}
		
		return books;
	}
	
	public Book createBook(ResultSet rs) throws SQLException {
		Book book=new Book();
		
		book.setId(rs.getInt(1));
		book.setTitle(rs.getString(2));
		book.setAuthor(rs.getString(3));
		book.setLongPrice(rs.getLong(4));
		book.setGenre(rs.getString(5));
		book.setLongReleaseYear(rs.getLong(6));
		return book;
	}


	@Override
	public List<Book> showAllBooks() throws DaoException, ConnectionPoolException {
		
		List<Book> books=new ArrayList<>();

		try {			
			con=connectionPool.takeConnection();
			con.setAutoCommit(false);
			
			st=con.prepareStatement(SHOW_ALL_BOOKS_STATEMENT);
			
			rs=st.executeQuery();
			
			while (rs.next()) {
				Book book=createBook(rs);
				books.add(book);
				}
			}
		
		catch(SQLException e) {
				throw new DaoException(e);
			}
		finally {
			connectionPool.closeConnection(rs, st, con);
		}
		return books;
		
		
	}

	
	public boolean addingNewBook(Book book) throws DaoException, ConnectionPoolException {
		
		try {
			con = connectionPool.takeConnection();
			con.setAutoCommit(false);
			
			st= con.prepareStatement(ADD_NEW_BOOK_STATEMENT);
			
			st.setString(1,book.getTitle());
			st.setString(2,book.getAuthor());
			st.setLong(3,book.getLongPrice());
			st.setString(4,book.getGenre());
			st.setLong(5, book.getLongReleaseYear());
					
			st.executeUpdate();
			
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new DaoException(e);
			}
			throw new DaoException(e);
		} 
		finally {
			connectionPool.closeConnection(rs, st, con);
		}
		return true;
}

	
	@Override
	public boolean deleteBook(int id) throws DaoException, ConnectionPoolException {
		
		try {			
			con=connectionPool.takeConnection();
			con.setAutoCommit(false);
			
			st=con.prepareStatement(DELETE_BOOK_BY_ID);
			st.setInt(1, id);
			
			st.executeUpdate();
						
		}
		catch(SQLException e) {
				throw new DaoException(e);
			}
		finally {
			connectionPool.closeConnection(rs, st, con);
		}
		return true;
	}	
}
