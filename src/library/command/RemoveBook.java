package consolelib.src.library.command;

import consolelib.src.library.LibraryManagementSystem;
import consolelib.src.library.interfaces.LibraryCommand;
import consolelib.src.library.items.Book;
import consolelib.src.library.items.LibraryItem;

import java.util.List;
import java.util.Scanner;

public class RemoveBook implements LibraryCommand {
	@Override
	public String getName() {
		return "/remove_book";
	}

	@Override
	public String getDescription() {
		return "Удалить все копии книги.";
	}

	@Override
	public void execute(LibraryManagementSystem library) {
		System.out.print("Введите название книги: ");
		List<LibraryItem> foundBooks = library.searchLibraryItems(scanner.nextLine());

		if (foundBooks.isEmpty()) {
			System.out.println("Книги не найдены.");
			return;
		}

		if (foundBooks.size() == 1) {
			System.out.println("Найдена книга.\n"+ foundBooks.get(0));
			library.removeBook((Book) foundBooks.get(0));
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

			library.removeBook((Book) foundBooks.get(indexBook));
		}


		System.out.println("Книга удалена.");
	}
}
