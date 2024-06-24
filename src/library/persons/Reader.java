package consolelib.src.library.persons;

import consolelib.src.library.interfaces.Searchable;
import consolelib.src.library.items.LibraryItem;

import java.util.ArrayList;
import java.util.List;

public abstract class Reader implements Searchable<String> {
	protected String name;
	protected String surname;
	protected String readerTicket;
	protected List<LibraryItem> borrowedItems = new ArrayList<>();

	public Reader(String name, String surname, String readerTicket) {
		this.name = name;
		this.surname = surname;
		this.readerTicket = readerTicket;
	}

	@Override
	public boolean matches(String str) {
		return readerTicket.toLowerCase().contains(str.toLowerCase()) ||
				name.toLowerCase().contains(str.toLowerCase()) ||
				surname.toLowerCase().contains(str.toLowerCase());
	}

	public void borrowItem(LibraryItem item) {
		borrowedItems.add(item);
	}

	public void returnItem(LibraryItem item) {
		borrowedItems.remove(item);
	}

	public List<LibraryItem> getBorrowedItems() {
		return borrowedItems;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setReaderTicket(String readerTicket) {
		this.readerTicket = readerTicket;
	}

	@Override
	public String toString() {
		return "Name=" + this.name +
				", Surname=" + this.surname +
				", ReaderTicket=" + this.readerTicket;
	}
}
