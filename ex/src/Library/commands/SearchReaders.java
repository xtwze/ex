package Library.commands;

import Library.LibraryManagmentSystem;
import Library.interfaces.LabraryCommands;
import Library.persons.Reader;

import java.util.List;

public class SearchReaders implements LabraryCommands {
    @Override
    public String getName() {
        return "/search_readers";
    }

    @Override
    public String getDescription() {
        return "Поиск читателей по имени, фамилии, номеру читательского билета.";
    }

    @Override
    public void execute(LibraryManagmentSystem library) {
        System.out.println("Введите запрос для поиска ");
        List<Reader> results = library.searchReaders(scanner.nextLine());

        if(!results.isEmpty()){
            // Выводим найденных чиателей
            System.out.println("Найденные читатели: ");
            System.out.println(String.join("\n",
                    results.stream().map(Reader::toString).toList()));
        }else {
            System.out.println("Читатели не найдены.");
        }

    }
}
