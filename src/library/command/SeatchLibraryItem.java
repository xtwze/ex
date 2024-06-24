package consolelib.src.library.command;

import consolelib.src.library.LibraryManagementSystem;
import consolelib.src.library.interfaces.LibraryCommand;
import consolelib.src.library.items.LibraryItem;

import java.util.List;
import java.util.Scanner;

public class SeatchLibraryItem implements LibraryCommand {
	@Override
	public String getName() {
		return "/search_library_item";
	}

	@Override
	public String getDescription() {
		return "Поиск книг по название, автору, жанру, ISBN.";
	}

	@Override
	public void execute(LibraryManagementSystem library) {
		System.out.print("Введите запрос для поиска: ");
		List<LibraryItem> results = library.searchLibraryItems(scanner.nextLine());

		if (!results.isEmpty()) {
			System.out.println("Найденные книги: ");
			System.out.println(String.join("\n",
					results.stream().map(LibraryItem::toString).toList()));
		} else {
			System.out.println("Книги не найдены.");
		}
	}
}
