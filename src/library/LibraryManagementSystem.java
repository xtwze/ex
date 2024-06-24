package consolelib.src.library;

import consolelib.src.library.items.Book;
import consolelib.src.library.items.FictionBook;
import consolelib.src.library.items.LibraryItem;
import consolelib.src.library.items.NonFictionBook;
import consolelib.src.library.persons.Professor;
import consolelib.src.library.persons.Reader;
import consolelib.src.library.persons.Student;

import java.util.*;

public class LibraryManagementSystem {
	private List<LibraryItem> items = new ArrayList<>();
	private List<Reader> readerList = new ArrayList<>();
	private Map<LibraryItem, Integer> availableCopies = new HashMap<>();

	public void addBook(Book book, int count) {
		items.add(book);
		availableCopies.put(book, count);
	}

	public void removeBook(Book book) {
		if (book.getBorrowers().isEmpty()) {
			items.remove(book);
			availableCopies.remove(book);
		}
	}

	public void updateBook(Book book, String title, int year, String author,
	                       String isbn, String genreOrSubject, int copies) {
		book.setTitle(title);
		book.setYear(year);
		book.setAuthor(author);
		book.setIsbn(isbn);

		if (book instanceof FictionBook fictionBook) {
			fictionBook.setGenre(genreOrSubject);
		} else if (book instanceof NonFictionBook nonFictionBook) {
			nonFictionBook.setSubjectArea(genreOrSubject);
		}

		if (availableCopies.containsKey(book)) {
			availableCopies.put(book, copies);
		}
	}

	public void addReader(Reader reader) {
		readerList.add(reader);
	}

	public void removeReader(Reader reader, boolean force) {
		if (!force && reader.getBorrowedItems().isEmpty()) {
			readerList.remove(reader);
			System.out.println("Читатель удален.");
		} else if (force) {
			for (LibraryItem item : reader.getBorrowedItems()) {
				this.returnItem(reader, item);
			}
			readerList.remove(reader);
			System.out.println("Книги возвращены.");
			System.out.println("Читатель удален.");
		}
	}

	public void removeReader(Reader reader) {
		this.removeReader(reader, false);
	}

	public void updateReader(Reader reader, String name, String surname,
	                         String readerTicket,
	                         String studentIdOrDepartment) {
			reader.setName(name);
			reader.setSurname(surname);
			reader.setReaderTicket(readerTicket);

			if (reader instanceof Student student) {
				student.setStudentId(studentIdOrDepartment);
			} else if (reader instanceof Professor professor) {
				professor.setDepartment(studentIdOrDepartment);
			}
	}

	public boolean borrowItem(Reader reader, LibraryItem item) {
		if (availableCopies.containsKey(item) && item.isAvailable()) {
			reader.borrowItem(item);
			item.borrowItem(reader);
			availableCopies.put(item, availableCopies.get(item) - 1);

			if (availableCopies.get(item) == 0) {
				item.setAvailable(false);
			}

			return true;
		}

		return false;
	}

	public void returnItem(Reader reader, LibraryItem item) {
		if (availableCopies.containsKey(item)) {
			reader.returnItem(item);
			item.returnItem(reader);
			availableCopies.put(item, availableCopies.get(item) + 1);
			item.setAvailable(true);
		}
	}

	public List<LibraryItem> searchLibraryItems(String query) {
		return items.stream().filter((item) -> item.matches(query)).toList();
	}

	public List<Reader> searchReaders(String query) {
		return readerList.stream().filter((reader) -> reader.matches(query)).toList();
	}
}
