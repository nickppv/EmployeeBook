import java.util.Objects;

public class Employee {
    private final int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private int department;
    private int salary;

    static public int idCounter = 1;

    public Employee(String lastName, String firstName, String middleName, int department, int salary) {
        this.id = idCounter;
        idCounter++;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }
    public String getLastName() {
        return lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public int getDepartment() {
        return department;
    }
    public int getSalary() {
        return salary;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public void setDepartment(int department) {
        this.department = department;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return department == employee.department &&
                salary == employee.salary &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(middleName, employee.middleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, middleName, department, salary);
    }

    @Override
    public String toString() {
        return "Работник номер " + id +
                ", ФИО: " + lastName + " " + firstName + " " + middleName +
                ", отдел: " + department +
                ", зарплата: " + salary + ".\n";
    }
}
