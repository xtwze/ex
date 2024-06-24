package consolelib.src.library.persons;

public class Professor extends Reader {
	private String department;

	public Professor(String name, String surname, String readerTicket, String department) {
		super(name, surname, readerTicket);
		this.department = department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return super.toString() + ", Department=" + department;
	}
}
