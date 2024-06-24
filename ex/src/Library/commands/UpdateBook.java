package Library.commands;

import Library.LibraryManagmentSystem;
import Library.interfaces.LabraryCommands;
import Library.items.Book;
import Library.items.LibraryItem;

import java.util.List;

public class UpdateBook implements LabraryCommands {
    @Override
    public String getName() {
        return "/update_book";
    }

    @Override
    public String getDescription() {
        return "Изменить свойства книги.";
    }

    @Override
    public void execute(LibraryManagmentSystem library) {
        System.out.println("Введите название книги");
        List<LibraryItem> foundBooks = library.searchLibaryItem(scanner.nextLine());
        Book book = null;

        if (foundBooks.isEmpty()){
            System.out.println("Не найдено");
            return;
        }
        if (foundBooks.size() == 1){
            book = (Book) foundBooks.get(0);
            System.out.println("Найдена книга " + book);
        }else {
            System.out.println("Найдено несколько книг с таким названием:" +
                    String.join("\n",
                            foundBooks.stream().map(item -> item+"").toList())+
                    ".\nВведите номер книги, которую хотите изменить.\n1 - " +
                    "первая книга, 2 - вторая, и т.д.");
            int indexBook = Integer.parseInt(scanner.nextLine());
            while (!(indexBook >= 0 && indexBook < foundBooks.size())) {
                System.out.println("Такой книги нет.\nПопробуйте еще раз.");
                System.out.print(">>> ");
                indexBook = scanner.nextInt() - 1;
            }

            book = (Book) foundBooks.get(indexBook);
        }
    }
}
