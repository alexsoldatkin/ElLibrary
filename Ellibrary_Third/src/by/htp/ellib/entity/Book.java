package by.htp.ellib.entity;

import java.util.ArrayList;
import java.util.List;

public class Book {
	
	private long id;

	private String title;
	private long longPrice;
	private String author;
	private String genre;
	private long longReleaseYear;
	

	public Book() {}
	
	public Book(String title, String author, long longPrice,String genre, long longReleaseYear) {
		this.title=title;
		this.author=author;
		this.longPrice=longPrice;
		this.genre=genre;
		this.longReleaseYear=longReleaseYear;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", longPrice=" + longPrice + ", author=" + author + ", genre="
				+ genre + ", releaseYear=" + longReleaseYear + "]";
	}


	public long getLongReleaseYear() {
		return longReleaseYear;
	}

	public void setLongReleaseYear(long longReleaseYear) {
		this.longReleaseYear = longReleaseYear;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public long getLongPrice() {
		return longPrice;
	}

	public void setLongPrice(long longPrice) {
		this.longPrice = longPrice;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
