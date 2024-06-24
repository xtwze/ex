package consolelib.src.main;

import consolelib.src.library.LibraryManagementSystem;
import consolelib.src.library.command.*;
import consolelib.src.library.interfaces.LibraryCommand;
import consolelib.src.library.items.FictionBook;
import consolelib.src.library.items.LibraryItem;
import consolelib.src.library.items.NonFictionBook;
import consolelib.src.library.persons.Professor;
import consolelib.src.library.persons.Student;

import java.util.*;

public class Main {
	private static Scanner scanner = new Scanner(System.in);
	private static LibraryManagementSystem library = new LibraryManagementSystem();
	private static Map<String, LibraryCommand> libraryCommands =
			new LinkedHashMap<>();

	public static void main(String[] args) {
		addCommands(
				new AddBook(),
				new RemoveBook(),
				new UpdateBook(),
				new AddReader(),
				new RemoveReader(),
				new UpdateReader(),
				new BorrowItem(),
				new ReturnItem(),
				new SeatchLibraryItem(),
				new SearchReaders(),
				new Help()
		);

		System.out.println("Введите '/help' для просмотра доступных команд.");
		System.out.print(">>> ");
		String command;
		while(!(command = scanner.nextLine().toLowerCase()).equals("/exit")) {
			if (libraryCommands.containsKey(command)) {
				libraryCommands.get(command).execute(library);
			} else {
				System.out.println("Такой команды нет.\nВведите '/help' для " +
						"получения справки по всем доступным командам.");
			}

			System.out.print(">>> ");
		}

		scanner.close();
	}

	public static void addCommands(LibraryCommand... newCommands) {
		for (LibraryCommand command : newCommands) {
			libraryCommands.put(command.getName(), command);
		}
	}

	public static List<LibraryCommand> getLibraryCommands() {
		return new ArrayList<>(libraryCommands.values());
	}
}
