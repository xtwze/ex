package Library.persons;

public class Professor extends Reader{
    private String departament;
    public Professor(String name, String surname, String readTicket, String departament) {
        super(name, surname, readTicket);
        this.departament = departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }
    public String toString(){
        return super.toString() + "Professor departament is " + this.departament;
    }
}
