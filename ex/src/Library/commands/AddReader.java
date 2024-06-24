package Library.commands;

import Library.LibraryManagmentSystem;
import Library.interfaces.LabraryCommands;
import Library.persons.Professor;
import Library.persons.Student;

public class AddReader implements LabraryCommands {


    @Override
    public String getName() {
        return "/add_reader";
    }

    @Override
    public String getDescription() {
        return "Добавить нового читателя";
    }

    @Override
    //Вводим данные
    public void execute(LibraryManagmentSystem library) {
        System.out.println("Введите имя читателя");
        String name = scanner.nextLine();
        System.out.println("Введите фамилию читателя");
        String surname = scanner.nextLine();
        System.out.println("Введите читательский билет");
        String readTicket = scanner.nextLine();
        while (!library.searchReaders(readTicket).isEmpty()){
            System.out.println("Такой читатель уже есть!\n Попробуйте ещё!");
            readTicket = scanner.nextLine();
        }
        System.out.println("Введи тип читателя(1 - СТУДЕНТ, 2 - ПРОФЕССОР)");
        int typeReader = Integer.parseInt(scanner.nextLine());
        while (typeReader != 1 && typeReader != 2){
            System.out.println("Такоо типа нет!\n Попробуй ещё!");
            typeReader = Integer.parseInt(scanner.nextLine());
        }

        if(typeReader == 1){
            System.out.println("Введите id студента ");
            library.addReader(new Student(name,surname,readTicket,scanner.nextLine()));
        }else {
            System.out.println("Введите депортамент професстора ");
            library.addReader(new Professor(name,surname,readTicket,scanner.nextLine()));
        }
        System.out.println("Читатель добавлен!");
    }

}
