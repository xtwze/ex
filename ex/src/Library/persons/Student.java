package Library.persons;

public class Student extends Reader {
    private String studentId;
    public Student(String name, String surname, String readTicket, String studentId) {
        super(name, surname, readTicket);
        this.studentId = studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    @Override
    public String toString(){
        return super.toString() + "Student id is " + this.studentId;
    }
}
