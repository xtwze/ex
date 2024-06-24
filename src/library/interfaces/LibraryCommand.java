package consolelib.src.library.interfaces;

import consolelib.src.library.LibraryManagementSystem;

import java.util.Scanner;

public interface LibraryCommand {
	Scanner scanner = new Scanner(System.in);
	String getName();
	String getDescription();
	void execute(LibraryManagementSystem library);
}
