package Library.items;

public class Book extends LibraryItem {
    protected String author;
    protected String isbn;

    // Конструктор для инициализации полей title, year, author и isbn
    public Book(String title, int year, String author, String isbn) {
        super(title, year);// Вызов конструктора суперкласса LibraryItem
        this.author = author;
        this.isbn = isbn;
    }
    // расширяем метод matches
    @Override
    public boolean matches(String str){
        return super.matches(str)|| // Поиск по названию в суперклассе
        this.author.toLowerCase().contains(str.toLowerCase())|| // поиск по автору
        this.isbn.contains(str);// поиск по isbn
    }

    // Метод для получения детальной информации о книге
    public String getBookDetails(){
        return super.toString()+
                "Aitor - " + this.author +
                ", ISBN is " + this.isbn;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
