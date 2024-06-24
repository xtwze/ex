package consolelib.src.library.command;

import consolelib.src.library.LibraryManagementSystem;
import consolelib.src.library.interfaces.LibraryCommand;
import consolelib.src.library.items.Book;
import consolelib.src.library.items.LibraryItem;
import consolelib.src.library.persons.Reader;

import java.util.List;
import java.util.Scanner;

public class BorrowItem implements LibraryCommand {
	@Override
	public String getName() {
		return "/borrow_item";
	}

	@Override
	public String getDescription() {
		return "Выдать книгу читателю.";
	}

	@Override
	public void execute(LibraryManagementSystem library) {
		System.out.print("Введите читательский билет: ");
		List<Reader> foundReaders = library.searchReaders(scanner.nextLine());
		Reader reader = null;
		System.out.print("Введите название книги: ");
		List<LibraryItem> foundBooks = library.searchLibraryItems(scanner.nextLine());
		LibraryItem item = null;

		if (foundReaders.isEmpty()) {
			System.out.println("Читатель не найден");
			return;
		}
		reader = foundReaders.get(0);

		if (foundBooks.isEmpty()) {
			System.out.println("Книги не найдены.");
			return;
		}

		if (foundBooks.size() == 1) {
			item = foundBooks.get(0);
			System.out.println("Найдена книга.\n" + item);
		} else {
			System.out.println("Найдено несколько книг: " +
					String.join("\n",
							foundBooks.stream().map(LibraryItem::toString).toList()) +
					".\nВведите номер книги, которую хотите удалить.\n1 - " +
					"первая книга, 2 - вторая, и т.д.");

			System.out.print(">>> ");
			int indexBook = Integer.parseInt(scanner.nextLine()) - 1;
			while (!(indexBook >= 0 && indexBook < foundBooks.size())) {
				System.out.println("Такой книги нет.\nПопробуйте еще раз.");
				System.out.print(">>> ");
				indexBook = Integer.parseInt(scanner.nextLine()) - 1;
			}

			item = foundBooks.get(indexBook);
		}

		if (!item.isAvailable()) {
			System.out.println("Книга недоступна.");
			return;
		}

		library.borrowItem(reader, item);

		System.out.println("Книга выдана.");
	}
}
