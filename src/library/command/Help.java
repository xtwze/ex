package consolelib.src.library.command;

import consolelib.src.library.LibraryManagementSystem;
import consolelib.src.library.interfaces.LibraryCommand;
import consolelib.src.main.Main;

public class Help implements LibraryCommand {
	@Override
	public String getName() {
		return "/help";
	}

	@Override
	public String getDescription() {
		return "Справка по всем командам.";
	}

	@Override
	public void execute(LibraryManagementSystem library) {
		for (LibraryCommand command : Main.getLibraryCommands()) {
			System.out.println("'"+command.getName()+"'\n"+command.getDescription()+"\n");
		}
		System.out.println("'/exit'\nВыход из системы.");
	}
}
