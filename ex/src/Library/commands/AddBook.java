package Library.commands;

import Library.LibraryManagmentSystem;
import Library.interfaces.LabraryCommands;

public class AddBook implements LabraryCommands {
    @Override
    public String getName() {
        return "/add_book";
    }


    @Override
    public String getDescription() {
        return "Добавить новую книгу";
    }

    @Override
    public void execute(LibraryManagmentSystem library) {
        System.out.println("Введите название книги: ");
        String title = scanner.nextLine();
        System.out.println("Введите год книги: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите автора книги: ");
        String autor = scanner.nextLine();
        System.out.println("Введите ISBN книги: ");
        String isbn = scanner.nextLine();
        System.out.println("Введите кол-во копий книги: ");

        int copies;
        while ((copies = Integer.parseInt(scanner.nextLine()))< 0){
            System.out.println("Количество копий не может быть отрицательным" +
                    ".\nПопробуйте еще раз.");
        }
    }



}
