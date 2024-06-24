package consolelib.src.library.items;

public class FictionBook extends Book {
	private String genre;

	public FictionBook(String title, int year, String author, String isbn,
	                   String genre) {
		super(title, year, author, isbn);
		this.genre = genre;
	}

	@Override
	public boolean matches(String str) {
		return super.matches(str) ||
				this.genre.toLowerCase().contains(str.toLowerCase());
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}


	@Override
	public String toString() {
		return super.getBookDetails() + ", Genre=" + this.genre;
	}
}
