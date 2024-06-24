package Library.commands;

import Library.LibraryManagmentSystem;
import Library.interfaces.LabraryCommands;
import Library.persons.Professor;
import Library.persons.Reader;
import Library.persons.Student;

import java.util.List;

public class UpdateReader implements LabraryCommands {
    @Override
    public String getName() {
        return "/update_reader";
    }

    @Override
    public String getDescription() {
        return "Изменить свойства читателя.";
    }

    @Override
    public void execute(LibraryManagmentSystem library) {
        System.out.println("Введите читательский билет");
        List<Reader> foundReaders = library.searchReaders(scanner.nextLine());
        if (foundReaders.isEmpty()) {
            System.out.println("Читатель не найден");
            return;
        }
        System.out.println("Найден читатель: " + foundReaders.get(0));

        System.out.print("Введите новое имя читателя: ");
        String name = scanner.nextLine();
        System.out.print("Введите новую фамилию читателя: ");
        String surname = scanner.nextLine();
        System.out.print("Введите новый читательский билет: ");
        String readerTicket = scanner.nextLine();

        if(foundReaders.get(0) instanceof Student){
            System.out.println("Введите новый id студента ");
            library.updateReader(foundReaders.get(0), name, surname, readerTicket, scanner.nextLine());
        }else if(foundReaders.get(0) instanceof Professor) {
            System.out.print("Введите новый отдел профессора: ");
            library.updateReader(foundReaders.get(0), name, surname,
                    readerTicket, scanner.nextLine());
        }
        System.out.println("Читатель обновлен");

    }
}
