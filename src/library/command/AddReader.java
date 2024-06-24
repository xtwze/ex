package consolelib.src.library.command;

import consolelib.src.library.LibraryManagementSystem;
import consolelib.src.library.interfaces.LibraryCommand;
import consolelib.src.library.items.FictionBook;
import consolelib.src.library.items.NonFictionBook;
import consolelib.src.library.persons.Professor;
import consolelib.src.library.persons.Reader;
import consolelib.src.library.persons.Student;

import java.util.List;
import java.util.Scanner;

public class AddReader implements LibraryCommand {
	@Override
	public String getName() {
		return "/add_reader";
	}

	@Override
	public String getDescription() {
		return "Добавить нового читателя.";
	}

	@Override
	public void execute(LibraryManagementSystem library) {
		System.out.print("Введите имя читателя: ");
		String name = scanner.nextLine();
		System.out.print("Введите фамилию читателя: ");
		String surname = scanner.nextLine();
		System.out.print("Введите читательский билет: ");

		String readerTicket = scanner.nextLine();
		while (!library.searchReaders(readerTicket).isEmpty()) {
			System.out.println("Такой читатель уже есть.\nПопробуйте еще раз.");
			readerTicket = scanner.nextLine();
		}

		System.out.print("Выберите тип читателя\n" +
				"1: Студент\n" +
				"2: Профессор\n");
		System.out.print(">>> ");
		int typeReader = Integer.parseInt(scanner.nextLine());
		while (typeReader != 1 && typeReader != 2) {
			System.out.println("Такого типа читателя нет.\nПопробуйте еще раз.");
			System.out.print(">>> ");
			typeReader = Integer.parseInt(scanner.nextLine());
		}

		if (typeReader == 1) {
			System.out.print("Введите идентификатор студента: ");
			library.addReader(new Student(name, surname, readerTicket, scanner.nextLine()));
		} else {
			System.out.print("Введите отдел профессора: ");
			library.addReader(new Professor(name, surname, readerTicket, scanner.nextLine()));
		}

		System.out.println("Читатель добавлен.");
	}
}
