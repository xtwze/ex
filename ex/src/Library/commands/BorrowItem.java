package Library.commands;

import Library.LibraryManagmentSystem;
import Library.interfaces.LabraryCommands;
import Library.items.LibraryItem;
import Library.persons.Reader;

import java.util.List;

// command для заимствования предмета читателем
public class BorrowItem implements LabraryCommands {
    @Override
    public String getName() {
        return "/borrow_item";
    }

    @Override
    public String getDescription() {
        return "Выдать книгу читателю";
    }

    @Override
    public void execute(LibraryManagmentSystem library) {
        System.out.println("Введите читательский билет");
        List<Reader> foundReaders = library.searchReaders(scanner.nextLine());
        Reader reader = null;
        System.out.println("Введите название книги: ");
        List<LibraryItem> foundBooks = library.searchLibaryItem(scanner.nextLine());
        LibraryItem item = null;

        if (foundReaders.isEmpty()) {
            System.out.println("Читатель не найден");
            return;
        }
        reader = foundReaders.get(0);
        // Проверка, найдена ли книга
        if (foundBooks.isEmpty()) {
            System.out.println("Книги не найдены");
            return;
        }
        // Если найдена одна книга
        if (foundBooks.size() == 1) {
            item = foundBooks.get(0);
            System.out.println("Найдена книга " + item);
        } else {
            // Если найдено несколько книг, выбор одной из них
            System.out.println("Найдено несколько книг: " +
                    String.join("\n", foundBooks.stream().map(LibraryItem::toString).toList()) +
                    ".\nВведите номер книги, которую хотите взять.\n1 - первая книга, 2 - вторая, и т.д.");
        }
        int indexBook = Integer.parseInt(scanner.nextLine()) - 1;
        while (!(indexBook >= 0 && indexBook < foundBooks.size())) {
            System.out.println("Такой книги нет! Попробуй ещё");
            indexBook = Integer.parseInt(scanner.nextLine()) - 1;
        }
        item = foundBooks.get(indexBook);

        // Проверка, доступна ли книга
        if (!item.isAvailable()) {
            System.out.println("Книга недоступна");
            return;
        }
        library.borrowItem(reader, item);
        System.out.println("Книга выдана.");
    }

}
