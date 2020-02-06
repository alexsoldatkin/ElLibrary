package by.htp.ellib.service;

import java.util.List;

import by.htp.ellib.dao.impl.ConnectionPoolException;
import by.htp.ellib.entity.Book;

public interface LibraryService {
	
	boolean addingNewBook(Book book) throws ServiceException, ConnectionPoolException;
	List<Book> findByAuthor(String author) throws ServiceException, ConnectionPoolException;
	List<Book> findByTitle(String title) throws ServiceException, ConnectionPoolException;
	List<Book> findByGenre(String genre) throws ServiceException, ConnectionPoolException;
	List<Book> showAllBooks() throws ServiceException, ConnectionPoolException;
	void deleteBook(int id) throws ServiceException, ConnectionPoolException;
	
	

}
