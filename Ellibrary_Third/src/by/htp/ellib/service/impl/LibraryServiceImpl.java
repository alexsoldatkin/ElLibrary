package by.htp.ellib.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.htp.ellib.dao.BookDAO;
import by.htp.ellib.dao.DAOProvider;
import by.htp.ellib.dao.DaoException;
import by.htp.ellib.dao.UserDAO;
import by.htp.ellib.dao.impl.ConnectionPoolException;
import by.htp.ellib.entity.Book;
import by.htp.ellib.service.LibraryService;
import by.htp.ellib.service.ServiceException;

public class LibraryServiceImpl implements LibraryService {

	@Override
	public boolean addingNewBook(Book book) throws ServiceException, ConnectionPoolException {
		
		DAOProvider daoProvider = DAOProvider.getInstance();
		BookDAO bookDAO = daoProvider.getBookDAO();
	
		try {
			bookDAO.addingNewBook(book);

		} catch (DaoException e) {
			throw new ServiceException();// (e)
		} finally {
			
		}
		return true;
	}

	@Override
	public List<Book> findByAuthor(String author) throws ServiceException, ConnectionPoolException {
		
		DAOProvider daoProvider = DAOProvider.getInstance();
		BookDAO bookDAO = daoProvider.getBookDAO();
		
		List<Book> books=new ArrayList<>();
		
		try {
			books=bookDAO.findByAuthor(author);
			

		} catch (DaoException e) {
			throw new ServiceException();// (e)
		} finally {
			
		}
		return books;
	}

	@Override
	public List<Book> findByTitle(String title) throws ServiceException, ConnectionPoolException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		BookDAO bookDAO = daoProvider.getBookDAO();
		
		List<Book> books=new ArrayList<>();
		
		try {
			books=bookDAO.findByTitle(title);

		} catch (DaoException e) {
			throw new ServiceException();// (e)
		} finally {
			
		}
		return books;
	}

	@Override
	public List<Book> findByGenre(String genre) throws ServiceException, ConnectionPoolException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		BookDAO bookDAO = daoProvider.getBookDAO();
		
		List<Book> books=new ArrayList<>();
		
		try {
			books=bookDAO.findByGenre(genre);
			
		} catch (DaoException e) {
			throw new ServiceException();// (e)
		} finally {
			
		}
		return books;
	}

	@Override
	public List<Book> showAllBooks() throws ServiceException, ConnectionPoolException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		BookDAO bookDAO = daoProvider.getBookDAO();
		
		List<Book> books=new ArrayList<>();
		
		try {
			books=bookDAO.showAllBooks();
			
		} catch (DaoException e) {
			throw new ServiceException();// (e)
		} finally {
			
		}
		return books;
	}

	@Override
	public void deleteBook(int id) throws ServiceException, ConnectionPoolException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		BookDAO bookDAO = daoProvider.getBookDAO();
		
		try {
			bookDAO.deleteBook(id);
			
		} catch (DaoException e) {
			throw new ServiceException();// (e)
		} finally {
			
		}
	}

}
