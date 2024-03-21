import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        EmployeeBook book = new EmployeeBook();

        System.out.println(
                "Список всех работников и вся информация о них: \n" + book.getEmployeesList());
        System.out.println(
                "Общие затраты на зарплату сотрудникам составляют: " + book.getTotalSalary());
        System.out.print(
                "Минимальная зарплата у: " + book.getEmployeesWithMinimalSalary().toString());
        System.out.print(
                "Максимальная зарплата у: " + book.getEmployeesWithMaximumSalary().toString());
        System.out.printf(
                "Среднее значение зарплаты составляет: %.2f рублей.\n", book.getAverageValueSalary());

        // Получаем все фамилии
        System.out.print("Выводим все фамилии: ");
        book.getLastNames();
        System.out.println();

        // Индексируем зарплату всех сотрудников на введенное значение
        book.indexSalary();

        // Информация о работниках после индексации зарплаты
        System.out.println(
                "Изменения после индексации зарплаты: \n" + book.getEmployeesList());

        // Изменения и общая информация по указанному отделу
        System.out.print("Введите номер отдела для подробной информации по нему: ");
        int department = scan.nextInt();
        Employee departmentMinSalary = book.getEmpWithMinSalaryInSomeDepartment(department);
        System.out.println("Самая низкая зарплата в " + department + "-м отделе у работника: " +
                departmentMinSalary.getLastName() + " " + departmentMinSalary.getFirstName() + " " +
                departmentMinSalary.getMiddleName() + " с зарплатой " +
                departmentMinSalary.getSalary() + " рублей.");
        Employee departmentMaxSalary = book.getEmpWithMaxSalaryInSomeDepartment(department);
        System.out.println("Самая высокая зарплата в " + department + "-м отделе у работника: " +
                departmentMaxSalary.getLastName() + " " + departmentMaxSalary.getFirstName() + " " +
                departmentMaxSalary.getMiddleName() + " с зарплатой " +
                departmentMaxSalary.getSalary() + " рублей.");
        System.out.printf("Средняя зарплата по отделу №%d составляет %.2f рублей.\n", department,
                book.getAvgSalaryInSomeDepartment(department));
        // индексируем зарплату сотрудников указанного отдела
        book.indexSalaryInSomeDepartment(department);
        book.printEmpInSomeDepartment(department);
        System.out.println();

        // Фильтруем сотрудников по зарплате
        book.printEmpWithSalaryLessThen();
        book.printEmpWithSalaryMoreThen();

        // Удаляем сотрудников
        book.deleteEmployee();
        book.deleteEmployee();

        // Выведем для проверки все фамилии
        book.getLastNames();
        System.out.println();

        // Внесем нового сотрудника. Или несколько.
        book.setNewEmployee();
        book.setNewEmployee();
        System.out.println("Список всех работников и вся информация о них: \n" + book.getEmployeesList());

        // ищем сотрудника по ID
        book.searchEmployeeByID();
    }

}