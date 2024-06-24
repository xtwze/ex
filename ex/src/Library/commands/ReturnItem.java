package Library.commands;

import Library.LibraryManagmentSystem;
import Library.interfaces.LabraryCommands;
import Library.items.LibraryItem;
import Library.persons.Reader;

import java.util.List;

public class ReturnItem implements LabraryCommands {
    @Override
    public String getName() {
        return "/return_item";
    }

    @Override
    public String getDescription() {
        return "Забрать книгу у читателя.";
    }

    @Override
    public void execute(LibraryManagmentSystem library) {
        System.out.print("Введите читательский билет: ");
        List<Reader> foundReaders = library.searchReaders(scanner.nextLine());
        Reader reader = null;

        System.out.print("Введите название книги: ");
        List<LibraryItem> foundBooks = library.searchLibaryItem(scanner.nextLine());
        LibraryItem item = null;

        // Если читатели не найдены, выводим сообщение и завершаем выполнение команды
        if(foundReaders.isEmpty()){
            System.out.println("Читатель не найден!");
            return;
        }
        reader = foundReaders.get(0);

        // Если книги не найдены, выводим сообщение и завершаем выполнение команды
        if(foundBooks.isEmpty()){
            System.out.println("Книга не найдена");
            return;
        }

        if(foundBooks.size() == 1){
            //Если одна
            System.out.println("Найдена книга.\n" +
                    foundBooks.stream().map(LibraryItem::toString).toList());
            item = foundBooks.get(0);
        }else {
            // Если найдено несколько книг, выводим список книг и просим пользователя выбрать одну из них
            System.out.println("Найдено несколько книг: " +
                    String.join("; ",
                            foundBooks.stream().map(LibraryItem::toString).toList()) +
                    ".\nВведите номер книги, которую хотите удалить.\n1 - " +
                    "первая книга, 2 - вторая, и т.д.");
        }
        int indexBook = Integer.parseInt(scanner.nextLine())-1;
        // Проверяем, чтобы введенный номер книги был корректным
        while (!(indexBook >= 0 && indexBook < foundBooks.size())) {
            System.out.println("Такой книги нет.\nПопробуйте еще раз.");
            indexBook = Integer.parseInt(scanner.nextLine()) - 1;
        }
        // Устанавливаем выбранную книгу в переменную item
        item = foundBooks.get(indexBook);
        // Возвращаем книгу в библиотеку
        library.returnItem(reader, item);
        // Выводим сообщение о возврате книги
        System.out.println("У читателя: " + reader + "\n" +
                "Возвращена книга: " + item);
    }
}
