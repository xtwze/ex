package Library.commands;

import Library.LibraryManagmentSystem;
import Library.interfaces.LabraryCommands;
import Library.items.LibraryItem;

import java.util.List;

public class SeatchLibraryItem implements LabraryCommands {
    @Override
    public String getName() {
        return "/search_library_item";
    }

    @Override
    public String getDescription() {
        return "Поиск книг по название, автору, жанру, ISBN.";
    }

    @Override
    public void execute(LibraryManagmentSystem library) {
        System.out.println("Введите запрос для поиска ");
        List<LibraryItem> results = library.searchLibaryItem(scanner.nextLine());

        //Проверка нашлись ли книги
        if(!results.isEmpty()){
            System.out.println("Найденные книги: ");
            System.out.println(String.join("\n",
                    results.stream().map(LibraryItem::toString).toList()));
        }else {
            System.out.println("Книги не найдены.");
        }
    }
}
