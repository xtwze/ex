package consolelib.src.library.items;

import consolelib.src.library.interfaces.Searchable;
import consolelib.src.library.persons.Reader;

import java.util.ArrayList;
import java.util.List;

public abstract class LibraryItem implements Searchable<String> {
	protected String title;
	protected int year;
	protected boolean available = true;
	protected List<Reader> borrowers = new ArrayList<>();

	public LibraryItem(String title, int year) {
		this.title = title;
		this.year = year;
	}

	@Override
	public boolean matches(String name) {
		return this.title.toLowerCase().contains(name.toLowerCase());
	}

	public void borrowItem(Reader borrower) {
		if (available) {
			borrowers.add(borrower);
		}
	}

	public void returnItem(Reader borrower) {
		borrowers.remove(borrower);
	}

	public List<Reader> getBorrowers() {
		return borrowers;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Title=" + this.title +
				", Year=" + this.year +
				", Available=" + this.available;
	}
}
