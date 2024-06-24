package consolelib.src.library.items;

import consolelib.src.library.persons.Reader;

import java.util.List;
import java.util.regex.Pattern;

public abstract class Book extends LibraryItem {
	protected String author;
	protected String isbn;

	public Book(String title, int year, String author, String isbn) {
		super(title, year);
		this.author = author;
		this.isbn = isbn;
	}

	@Override
	public boolean matches(String str) {
		return super.matches(str) ||
				this.author.toLowerCase().contains(str.toLowerCase()) ||
				this.isbn.contains(str);
	}

	public String getBookDetails() {
		return super.toString() +
				", Author=" + this.author +
				", ISBN=" + this.isbn;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}
