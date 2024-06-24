package Library.items;

import Library.interfaces.Searchable;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

// Абстрактный класс LibraryItem реализует интерфейс Searchable
public abstract class LibraryItem implements Searchable<String> {
    protected String title;
    protected int year;
    protected boolean available = true;
    protected List<Reader> borrowers = new ArrayList<>();

    // Конструктор для инициализации полей title и year
    public LibraryItem(String title, int year) {
        this.title = title;
        this.year = year;
    }

    // Реализация метода matches для поиска по названию

    public boolean matches(String name) {
        return this.title.toLowerCase().contains(name.toLowerCase());
    }

    // Метод для заимствования предмета
    public void borrowItem(Reader borrower) {
        if (available) {
            borrowers.add(borrower);
        }
    }

    // Метод для возврата предмета
    public void returnItem(Reader borrower) {
        borrowers.remove(borrower);
        }

    // Метод для получения списка заемщиков
    public List<Reader> getBorrowers() {
        return borrowers;
    }

    // Метод для проверки доступности предмета
    public boolean isAvailable() {
        return available;
    }

    // Метод для установки доступности предмета
    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Метод для установки названия предмета
    public void setTitle(String title) {
        this.title = title;
    }

    // Метод для установки года выпуска предмета
    public void setYear(int year) {
        this.year = year;
    }

    // Переопределение метода toString для удобного отображения информации о предмете
    @Override
    public String toString() {
        return "Title=" + this.title +
                ", Year=" + this.year +
                ", Available=" + this.available;
    }
}
