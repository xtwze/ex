package consolelib.src.library.items;

public class NonFictionBook extends Book {
	private String subjectArea;

	public NonFictionBook(String title, int year, String author, String isbn,
	                      String subjectArea) {
		super(title, year, author, isbn);
		this.subjectArea = subjectArea;
	}

	public void setSubjectArea(String subjectArea) {
		this.subjectArea = subjectArea;
	}

	@Override
	public String toString() {
		return super.getBookDetails() + ", SubjectArea=" + subjectArea;
	}
}
