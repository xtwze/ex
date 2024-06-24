package consolelib.src.library.command;

import consolelib.src.library.LibraryManagementSystem;
import consolelib.src.library.interfaces.LibraryCommand;
import consolelib.src.library.persons.Reader;

import java.util.List;
import java.util.Scanner;

public class RemoveReader implements LibraryCommand {
	@Override
	public String getName() {
		return "/remove_reader";
	}

	@Override
	public String getDescription() {
		return "Удалить читателя.";
	}

	@Override
	public void execute(LibraryManagementSystem library) {
		System.out.print("Введите читательский билет: ");
		List<Reader> foundReaders = library.searchReaders(scanner.nextLine());

		if (foundReaders.isEmpty()) {
			System.out.println("Читатель не найден");
			return;
		}

		if (!foundReaders.get(0).getBorrowedItems().isEmpty()) {
			System.out.print("Читатель не вернул все книги. Хотите " +
					"продолжить ?\n" +
					"[Да/Нет]: ");

			String responseUser = scanner.nextLine().toLowerCase();

			while (true) {
				if ("да".startsWith(responseUser)) {
					library.removeReader(foundReaders.get(0), true);
					break;
				} else if ("нет".startsWith(responseUser)) {
					break;
				}

				System.out.print("[Да/Нет]: ");
				responseUser = scanner.nextLine().toLowerCase();
			}
		} else {
			library.removeReader(foundReaders.get(0));
		}
	}
}
