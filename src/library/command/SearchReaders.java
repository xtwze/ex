package consolelib.src.library.command;

import consolelib.src.library.LibraryManagementSystem;
import consolelib.src.library.interfaces.LibraryCommand;
import consolelib.src.library.items.LibraryItem;
import consolelib.src.library.persons.Reader;

import java.util.List;
import java.util.Scanner;

public class SearchReaders implements LibraryCommand {
	@Override
	public String getName() {
		return "/search_readers";
	}

	@Override
	public String getDescription() {
		return "Поиск читателей по имени, фамилии, номеру читательского " +
				"билета.";
	}

	@Override
	public void execute(LibraryManagementSystem library) {
		System.out.print("Введите запрос для поиска: ");
		List<Reader> results = library.searchReaders(scanner.nextLine());

		if (!results.isEmpty()) {
			System.out.println("Найденные читатели: ");
			System.out.println(String.join("\n",
					results.stream().map(Reader::toString).toList()));
		} else {
			System.out.println("Читатели на найдены.");
		}
	}
}
