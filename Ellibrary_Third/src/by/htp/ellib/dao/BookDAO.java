package by.htp.ellib.dao;

import java.util.List;

import by.htp.ellib.dao.impl.ConnectionPoolException;
import by.htp.ellib.entity.Book;
import by.htp.ellib.service.ServiceException;

public interface BookDAO {
	
	boolean addingNewBook(Book book) throws DaoException, ConnectionPoolException;
	List<Book> findByAuthor(String author) throws DaoException, ConnectionPoolException;
	List<Book> findByTitle(String title)throws DaoException, ConnectionPoolException;
	List<Book> findByGenre(String genre) throws DaoException, ConnectionPoolException;
	List<Book> showAllBooks()throws DaoException, ConnectionPoolException;
	boolean deleteBook(int id) throws DaoException, ConnectionPoolException;
	
	
}
