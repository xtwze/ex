package consolelib.src.library.persons;

public class Student extends Reader {
	private String studentId;

	public Student(String name, String surname, String readerTicket,
	               String studentId) {
		super(name, surname, readerTicket);
		this.studentId = studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	@Override
	public String toString() {
		return super.toString() + ", StudentId=" + this.studentId;
	}
}
