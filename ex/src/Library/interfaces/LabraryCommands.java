package Library.interfaces;

import Library.LibraryManagmentSystem;

import java.util.Scanner;

// Интерфейс для определения команд библиотеки
public interface LabraryCommands {
    Scanner scanner = new Scanner(System.in);
    // Метод для получения имени команды
    String getName();
    // Метод для получения описания команды
    String getDescription();
    // Метод для выполнения команды
    void execute(LibraryManagmentSystem library);
}
