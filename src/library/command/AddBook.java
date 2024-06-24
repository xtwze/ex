package consolelib.src.library.command;

import consolelib.src.library.LibraryManagementSystem;
import consolelib.src.library.interfaces.LibraryCommand;
import consolelib.src.library.items.Book;
import consolelib.src.library.items.FictionBook;
import consolelib.src.library.items.NonFictionBook;

import java.util.Scanner;

public class AddBook implements LibraryCommand {
	@Override
	public String getName() {
		return "/add_book";
	}

	@Override
	public String getDescription() {
		return "Добавить новую книгу.";
	}

	@Override
	public void execute(LibraryManagementSystem library) {
		System.out.print("Введите название книги: ");
		String title = scanner.nextLine();
		System.out.print("Введите год книги: ");
		int year = Integer.parseInt(scanner.nextLine());
		System.out.print("Введите автора книги: ");
		String author = scanner.nextLine();
		System.out.print("Введите ISBN книги: ");
		String isbn = scanner.nextLine();
		System.out.print("Введите количество копий книги: ");

		int copies;
		while ((copies = Integer.parseInt(scanner.nextLine())) < 0) {
			System.out.println("Количество копий не может быть отрицательным" +
					".\nПопробуйте еще раз.");
		}

		System.out.print("Выберите тип книги\n1: Художественная книга\n2: " +
				"Научно-популярная книга\n");

		System.out.print(">>> ");
		int typeBook = Integer.parseInt(scanner.nextLine());
		while (typeBook != 1 && typeBook != 2) {
			System.out.println("Такого типа книг нет");
			System.out.print(">>> ");
			typeBook = Integer.parseInt(scanner.nextLine());
		}

		if (typeBook == 1) {
			System.out.print("Введите жанр книги: ");
			String genre = scanner.nextLine();
			library.addBook(new FictionBook(title, year, author, isbn, genre)
					, copies);
		} else {
			System.out.print("Введите тему книги: ");
			String subjectArea = scanner.nextLine();
			library.addBook(new NonFictionBook(title, year, author, isbn,
					subjectArea), copies);
		}

		System.out.println("Книга добавлена.");
	}
}
