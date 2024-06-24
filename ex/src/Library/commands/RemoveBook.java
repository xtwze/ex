package Library.commands;

import Library.LibraryManagmentSystem;
import Library.interfaces.LabraryCommands;
import Library.items.Book;
import Library.items.LibraryItem;

import java.util.List;

public class RemoveBook implements LabraryCommands {
    @Override
    public String getName() {
        return "/remove_book";
    }

    @Override
    public String getDescription() {
        return "Удалить все копии книги.";
    }

    @Override
    public void execute(LibraryManagmentSystem library) {
        System.out.println("Введите название книги: ");

        // Получаем список книг, которые соответствуют введенному названию
        List<LibraryItem> foundBooks = library.searchLibaryItem(scanner.nextLine());
        // Если книги не найдены, выводим сообщение и завершаем выполнение команды
        if(foundBooks.isEmpty()){
            System.out.println("Книги не найдены.");
            return;
        }
        // Если найдена одна книга, выводим информацию о ней и удаляем
        if(foundBooks.size() == 1){
            System.out.println("Найдена книга " + foundBooks.get(0));
            library.removeBook((Book) foundBooks.get(0));
        }else {
            // Если найдено несколько книг, выводим их список и просим выбрать одну из них
            System.out.println("Найдено несколько книг: " +
                    String.join("\n",
                            foundBooks.stream().map(LibraryItem::toString).toList()) +
                    ".\nВведите номер книги, которую хотите удалить.\n1 - первая книга, 2 - вторая, и т.д.");
            int indexBook = Integer.parseInt(scanner.nextLine())-1;
            // Проверяем, что номер книги корректный (находится в пределах списка)
            while (!(indexBook >= 0 && indexBook < foundBooks.size())){
                System.out.println("Такой книги нет.\nПопробуйте еще раз.");
                System.out.print(">>> ");
                indexBook = Integer.parseInt(scanner.nextLine()) - 1;
            }
            // Удаляем выбранную книгу
            library.removeBook((Book) foundBooks.get(indexBook));
        }

        // Выводим сообщение об успешном удалении книги
        System.out.println("Книга удалена.");

    }
}
