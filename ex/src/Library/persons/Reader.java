package Library.persons;

import Library.interfaces.Searchable;
import Library.items.LibraryItem;

import java.util.ArrayList;
import java.util.List;

public abstract class Reader implements Searchable<String> {
    protected String name;// имя
    protected String surname; // фамилия
    protected String readTicket; //номер читательского билета
    protected List<LibraryItem> borrowedItems = new ArrayList<>();

    // Конструктор для инициализации полей name, surname и readerTicket
    public Reader(String name,String surname, String readTicket){
        this.name = name;
        this.surname = surname;
        this.readTicket = readTicket;
    }

    // Реализация метода matches для поиска по имени, фамилии и читательскому билету
    @Override
    public boolean matches(String str){
        return readTicket.toLowerCase().contains(str.toLowerCase())||
                name.toLowerCase().contains(str.toLowerCase())||
                surname.toLowerCase().contains(str.toLowerCase());
    }
    public void borrowItem(LibraryItem item){// Добавление предмета в список заимствованных
        borrowedItems.add(item);
    }
    public void returnItem(LibraryItem item){
        borrowedItems.remove(item);// Удаление предмета из списка заимствованных
    }

    // Метод для получения списка заимствованных предметов
    public List<LibraryItem> getBorrowedItems(){
        return borrowedItems;// Возвращение списка заимствованных предметов
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReadTicket(String readTicket) {
        this.readTicket = readTicket;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    @Override
    public String toString(){
        return "Name is " + this.name +
                ", surname is " + this.surname +
                ", ReaderTicket is " + this.readTicket;
    }
}
