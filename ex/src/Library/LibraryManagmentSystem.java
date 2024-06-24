package Library;


import Library.items.Book;
import Library.items.FictionBook;
import Library.items.LibraryItem;
import Library.items.NonFictionBook;
import Library.persons.Professor;
import Library.persons.Reader;
import Library.persons.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Класс LibraryManagementSystem для управления библиотекой
public class LibraryManagmentSystem {
    private List<LibraryItem> items = new ArrayList<>(); // Список всех предметов библиотеки
    private List<Reader> readerList = new ArrayList<>();// Список всех читателей
    private Map<LibraryItem, Integer> avaibleCopies = new HashMap<>();// Карта предметов и доступных копий

    // Метод для добавления книги в библиотеку
    public void addBook(Book book, int count) {
        items.add(book);
        avaibleCopies.put(book, count);
    }

    // Метод для удаления книги из библиотеки, если она не заимствована
    public void removeBook(Book book) {
        if (book.getBookDetails().isEmpty()) {
            items.remove(book);
            avaibleCopies.remove(book);
        }
    }

    public void updateBook(Book book, String title, int year, String author,
                           String ibsn, String genreOrSubject, int copies) {
        book.setAuthor(author);
        book.setIsbn(ibsn);
        book.setTitle(title);
        book.setYear(year);
        // Установка жанра для художественной литературы
        if (book instanceof FictionBook fictionBook) { //instanceof используется для проверки, является ли объект экземпляром определ. класса/интерфейса
            fictionBook.setGenre(genreOrSubject);

            //Для научной литературы
        } else if (book instanceof NonFictionBook nonFictionBook) {
            nonFictionBook.setSubjectArea(genreOrSubject);
        }
        // Проверка наличия книги в карте availableCopies перед обновлением количества копий
        if (avaibleCopies.containsKey(book)) {
            avaibleCopies.put(book, copies);
        }
    }

    // Метод для добавления читателя в библиотеку
    public void addReader(Reader reader) {
        readerList.add(reader);
    }

    // Метод для удаления читателя из библиотеки (с учетом заимствованных предметов)
    public void removeReader(Reader reader, boolean force) {
        // Проверка условия, если удаление не принудительное и у читателя нет заимствованных предметов
        if (!force && reader.getBorrowedItems().isEmpty()) {
            readerList.remove(reader);// Удаление читателя из списка читателей
            System.out.println("Читатель удалён");
        } else {// принудительное удаление читателя
            if (force) {
                //Возвращаем взаимствованные предметы
                for (LibraryItem item : reader.getBorrowedItems()) {
                    this.returnItem(reader, item);// Возврат предметов в библиотеку
                }
                readerList.remove(reader);// удаление читателя из списка
                System.out.println("Книги возвращены");
                System.out.println("Читатель удален");
            }
        }
    }

    // Метод для удаления читателя из библиотеки (без учёта заимствованных предметов)
    public void removeReader(Reader reader) {
        this.removeReader(reader, false);
    }

    public void updateReader(Reader reader, String name, String surname,
                             String readerTicket, String studentIdOrDepartment) {
        reader.setName(name);
        reader.setSurname(surname);
        reader.setReadTicket(readerTicket);
        // Установка ID студента для студентов
        if (reader instanceof Student student) {
            student.setStudentId(studentIdOrDepartment);
        } else {
            // депортамент для профессоров
            if (reader instanceof Professor professor) {
                professor.setDepartament(studentIdOrDepartment);
            }
        }
    }

    // Метод для заимствования предмета читателем
    public boolean borrowItem(Reader reader, LibraryItem item) {
        // Проверяем, содержится ли предмет в карте доступных копий и доступен ли он для заимствования
        if (avaibleCopies.containsKey(item) && item.isAvailable()) {
            reader.borrowItem(item);// Добавляем предмет в список заимствованных предметов читателя
            item.borrowItem(reader);// Добавляем читателя в список заимствователей предмета
            // Уменьшаем количество доступных копий предмета на 1
            avaibleCopies.put(item,avaibleCopies.get(item) - 1);
            // Проверяем, не стало ли количество доступных копий равным нулю
            if(avaibleCopies.get(item) == 0) {
                // Если количество копий равно нулю, устанавливаем предмет как недоступный
                item.setAvailable(false);
            }
            // Возвращаем true, чтобы указать, что операция заимствования прошла успешно
            return true;
        }
        // Возвращаем false, чтобы указать, что операция заимствования не удалась
        return false;
    }
    // Метод для возврата предмета читателем
    public void returnItem(Reader reader, LibraryItem item){
        // Проверяем, содержится ли предмет в карте доступных копий
        if(avaibleCopies.containsKey(item)){
            // Удаляем предмет из списка заимствованных предметов читателя
            reader.returnItem(item);
            // Удаляем читателя из списка заимствователей предмета
            item.returnItem(reader);
            // Увеличиваем количество доступных копий предмета на 1
            avaibleCopies.put(item,avaibleCopies.get(item)+1);
            // Устанавливаем предмет как доступный для заимствования
            item.setAvailable(true);
        }
    }
    // Метод для поиска предметов в библиотеке по строковому запросу
    public List<LibraryItem> searchLibaryItem(String query){
        // Используем поток (stream) для фильтрации предметов в списке items
        return items.stream().
                // Фильтруем предметы, оставляя только те, которые соответствуют запросу
                filter((item) -> item.matches(query)).
                // Преобразуем отфильтрованный поток обратно в список
                toList();
    }
    public List<Reader> searchReaders(String query){
        return readerList.stream().filter((reader) -> reader.matches(query)).toList();
                // Фильтруем читателей, оставляя только тех, которые соответствуют запросу
                // Преобразуем отфильтрованный поток обратно в список
    }


}
