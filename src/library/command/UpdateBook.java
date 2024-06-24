package consolelib.src.library.command;

import consolelib.src.library.LibraryManagementSystem;
import consolelib.src.library.interfaces.LibraryCommand;
import consolelib.src.library.items.Book;
import consolelib.src.library.items.FictionBook;
import consolelib.src.library.items.LibraryItem;
import consolelib.src.library.items.NonFictionBook;

import java.util.List;
import java.util.Scanner;

public class UpdateBook implements LibraryCommand {
	@Override
	public String getName() {
		return "/update_book";
	}

	@Override
	public String getDescription() {
		return "Изменить свойства книги.";
	}

	@Override
	public void execute(LibraryManagementSystem library) {
		System.out.print("Введите название книги: ");
		List<LibraryItem>  foundBooks = library.searchLibraryItems(scanner.nextLine());
		Book book = null;

		if (foundBooks.isEmpty()) {
			System.out.println("Не найдено.");
			return;
		}

		if (foundBooks.size() == 1) {
			book = (Book) foundBooks.get(0);
			System.out.println("Найдена книга.\n"+ book);
		} else {
			System.out.println("Найдено несколько книг с таким названием: " +
					String.join("\n",
							foundBooks.stream().map(item -> item+"").toList()) +
					".\nВведите номер книги, которую хотите изменить.\n1 - " +
					"первая книга, 2 - вторая, и т.д.");

			System.out.print(">>> ");
			int indexBook = scanner.nextInt() - 1;
			while (!(indexBook >= 0 && indexBook < foundBooks.size())) {
				System.out.println("Такой книги нет.\nПопробуйте еще раз.");
				System.out.print(">>> ");
				indexBook = scanner.nextInt() - 1;
			}

			book = (Book) foundBooks.get(indexBook);
		}

		System.out.print("Введите новое название книги: ");
		String newTitle = scanner.nextLine();
		System.out.print("Введите новый год книги: ");
		int year = Integer.parseInt(scanner.nextLine());
		System.out.print("Введите нового автора книги: ");
		String author = scanner.nextLine();
		System.out.print("Введите новый ISBN книги: ");
		String isbn = scanner.nextLine();
		System.out.print("Введите новое количество копий книги: ");
		int copies = Integer.parseInt(scanner.nextLine());
		if ((book instanceof FictionBook)) {
			System.out.print("Введите новый жанр книги: ");
			library.updateBook(book, newTitle, year, author, isbn,
					scanner.nextLine(), copies);
		} else if (book instanceof NonFictionBook) {
			System.out.print("Введите новую тему книги: ");
			library.updateBook(book, newTitle, year, author, isbn,
					scanner.nextLine(), copies);
		}

		System.out.println("Книга обновлена.");
	}
}
