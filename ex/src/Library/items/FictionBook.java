package Library.items;

public class FictionBook extends Book{
    private String genre;
    // конструктор для иницаализации
    public FictionBook(String title, int year, String author, String isbn, String genre) {
        super(title, year, author, isbn);
        this.genre = genre;
    }
    @Override
    public boolean matches(String str){
        return super.matches(str) ||// Поиск по названию, автору и ISBN в суперклассе
                this.genre.toLowerCase().contains(str.toLowerCase());// Поиск по жанру
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    @Override
    public String toString(){
        return super.getBookDetails() + " Genre is " + this.genre;
    }
}
