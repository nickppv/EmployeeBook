import java.util.Random;
import java.util.Scanner;

public class EmployeeBook {
    Scanner scan = new Scanner(System.in);
    private Employee[] employeesArray = new Employee[10];
    private static String[] lastNameChoice = {"Иванов", "Николаев", "Попов", "Сидоренко", "Денисов", "Романов", "Генин", "Павлов", "Мкртчян", "Зозуля", "Вилкин", "Петухов", "Маккалистер", "Булкин", "Вареник"};
    private static String[] firstNameChoice = {"Иван", "Николай", "Виктор", "Альберт", "Денис", "Роман", "Гена", "Павел", "Валентин", "Григорий", "Джошуа", "Вениамин", "Степан"};
    private static String[] middleNameChoice = {"Иванович", "Евгеньевич", "Викторович", "Вадимович", "Денисович", "Рюрикович", "Сидорович", "Алексеевич", "Палыч", "Леонидович"};

    {
        for (int i = 0; i < employeesArray.length; i++) {
            Random rand = new Random();
            employeesArray[i] = new Employee(
                    lastNameChoice[rand.nextInt(0, lastNameChoice.length)],
                    firstNameChoice[rand.nextInt(0, firstNameChoice.length)],
                    middleNameChoice[rand.nextInt(0, middleNameChoice.length)],
                    rand.nextInt(1, 6),
                    rand.nextInt(50_000, 350_000)
            );
        }
    }

    /**
     * Возвращает информацию о всех сотрудниках
     * @return sb информация о сотрудниках
     */
    public String getEmployeesList() {
        StringBuilder sb = new StringBuilder();
        for (Employee i: employeesArray) {
            if (i != null) {
                sb.append(i);
            }
        }
        return sb.toString();
    }

    /**
     * Возвращает общие затраты на зарплату сотрудникам
     * @return totalSalary общие затраты на зарплату сотрудникам
     */
    int getTotalSalary() {
        int totalSalary = 0;
        for (Employee i: employeesArray) {
            if (i != null) {
                totalSalary += i.getSalary();
            }
        }
        return totalSalary;
    }

    /**
     * Возвращает сотрудника с минимальной зарплатой
     * @return экземпляр класса Employee
     */
    Employee getEmployeesWithMinimalSalary() {
        Employee poorWorker = null;
        int minimalSalary = employeesArray[0].getSalary();
        for (Employee i: employeesArray) {
            if (i != null && i.getSalary() <= minimalSalary) {
                minimalSalary = i.getSalary();
                poorWorker = i;
            }
        }
        return poorWorker;
    }

    /**
     * Возвращает сотрудника с максимальной зарплатой
     * @return экземпляр класса Employee
     */
    Employee getEmployeesWithMaximumSalary() {
        Employee richWorker = null;
        int maximumSalary = employeesArray[0].getSalary();
        for (Employee i : employeesArray) {
            if (i != null && i.getSalary() >= maximumSalary) {
                maximumSalary = i.getSalary();
                richWorker = i;
            }
        }
        return richWorker;
    }

    /**
     * Возвращает среднее значение зарплат всех сотрудников
     * @return double среднее значение зп
     */
    double getAverageValueSalary() {
        int counter = 0;
        for (Employee i: employeesArray) {
            if (i != null) {
                counter++;
            }
        }
        return (double) getTotalSalary() / counter; // делю на counter, т.к. если мы удалим из массива работников
    }

    /**
     * Печатает в консоль все фамилии сотрудников
     */
    void getLastNames() {
        for (int i = 0; i < employeesArray.length; i++) {
            if (employeesArray[i] == null) {
                continue;
            }
            if (i < employeesArray.length - 1) {
                System.out.print(employeesArray[i].getLastName() + ", ");
            } else {
                System.out.print(employeesArray[i].getLastName() + ".\n");
            }
        }
    }

    /**
     * Индексация зарплаты всех сотрудников на установленную величину
     */
    void indexSalary() {
        System.out.print("Введите значение для индексации зарплаты: ");
        int index = scan.nextInt();
        for (Employee i: employeesArray) {
            if (i == null) {
                continue;
            }
            i.setSalary((int) Math.floor(i.getSalary() / 100. * (index + 100)));
        }
        System.out.printf("Зарплата была проиндексирована на %d процентов.\n", index);
    }

    /**
     * Возвращает работника с минимальной зарплатой по указанному отделу
     * @param department номер отдела
     * @return экземпляр класса Employee
     */
    Employee getEmpWithMinSalaryInSomeDepartment(int department) {
        Employee minEmpSalary = employeesArray[0];
        for (Employee i: employeesArray) {
            if (i == null) {
                continue;
            }
            if (i.getSalary() <= minEmpSalary.getSalary() && i.getDepartment() == department) {
                minEmpSalary = i;
            }
        }
        return minEmpSalary;
    }

    /**
     * Возвращает работника с максимальной зарплатой по указанному отделу
     * @param department номер отдела
     * @return экземпляр класса Employee
     */
    Employee getEmpWithMaxSalaryInSomeDepartment(int department) {
        Employee maxEmpSalary = employeesArray[0];
        for (Employee i : employeesArray) {
            if (i == null) {
                continue;
            }
            if (i.getSalary() >= maxEmpSalary.getSalary() && i.getDepartment() == department) {
                maxEmpSalary = i;
            }
        }
        return maxEmpSalary;
    }

    /**
     * Возвращает среднюю зарплату по указанному отделу
     * @param department номер отдела
     * @return среднюю зарплату по отделу
     */
    double getAvgSalaryInSomeDepartment(int department) {
        int totalSalary = 0;
        int depCounter = 0;
        for (Employee i : employeesArray) {
            if (i != null && i.getDepartment() == department) {
                totalSalary += i.getSalary();
                depCounter++;
            }
        }
        return (double) totalSalary / depCounter;
    }

    /**
     * Индексация зарплаты сотрудников указанного отдела на величину переданного аргумента value
     */
    void indexSalaryInSomeDepartment(int department) {
        System.out.printf(
                "Введите величину, на которую неоходимо проиндексировать зарплату в отделе №" + department + ": ");
        int index = scan.nextInt();
        for (Employee i: employeesArray) {
            if (i != null && i.getDepartment() == department) {
                i.setSalary((int) Math.floor(i.getSalary() / 100. * (index + 100)));
            }
        }
        System.out.println(
                "Зарплата сотрудников " + department + "-го отдела была проиндексирована на " + index + "%."
        );
    }

    /**
     * Печатает данные сотрудников (кроме отдела) из данного отдела
     * @param department номер отдела
     */
    void printEmpInSomeDepartment(int department) {
        for (Employee i : employeesArray) {
            if (i != null && i.getDepartment() == department) {
                System.out.println("id№" + i.getId() + " - " + i.getLastName() + " " +
                        i.getFirstName() + " " +
                        i.getMiddleName() + " - " +
                        i.getSalary());
            }
        }
    }

    /**
     * Печатает сотрудников с зарплатой меньше, чем указанное значение
     */
    void printEmpWithSalaryLessThen() {
        System.out.print("Введите значение зарплаты. Будем искать сотрудников с зарплатой ниже этого значения: ");
        int salaryValue = scan.nextInt();
        int counter = 0;
        System.out.println("Сотрудники с зарплатой ниже " + salaryValue + " рублей:");
        for (Employee i : employeesArray) {
            if (i != null && i.getSalary() < salaryValue) {
                System.out.println("id№" + i.getId() + " " +
                        i.getLastName() + " " +
                        i.getFirstName() + " " +
                        i.getMiddleName() + " с зарплатой " +
                        i.getSalary() + ".");
                counter++;
            }
        }
        if (counter == 0) {
            System.out.println("Нет работников с зарплатой ниже " + salaryValue + " рублей.");
        }
    }

    /**
     * Печатает сотрудников с зарплатой выше, чем указанное значение
     */
    void printEmpWithSalaryMoreThen() {
        System.out.print("Введите значение зарплаты. Будем искать сотрудников с зарплатой выше этого значения: ");
        int salaryValue = scan.nextInt();
        int counter = 0;
        System.out.println("Сотрудники с зарплатой выше " + salaryValue + " рублей.");
        for (Employee i : employeesArray) {
            if (i != null && i.getSalary() >= salaryValue) {
                System.out.println("id№" + i.getId() + " " +
                        i.getLastName() + " " +
                        i.getFirstName() + " " +
                        i.getMiddleName() + " с зарплатой " +
                        i.getSalary() + ".");
                counter++;
            }
        }
        if (counter == 0) {
            System.out.println("Нет работников с зарплатой выше " + salaryValue + " рублей.");
        }
    }

    /**
     * Удаление сотрудника по введенному id
     */
    void deleteEmployee() {
        System.out.print("Введите id объекта для удаления: ");
        int id = scan.nextInt();
        for (int i = 0; i < employeesArray.length; i++) {
            if (employeesArray[i] != null && employeesArray[i].getId() == id) {
                employeesArray[i] = null;
                System.out.println("Объект с id = " + id + " был найден и удален.");
                return;
            }
        }
        System.out.println("Объекта с id = " + id + " не существует.");
    }

    /**
     * Добавляем нового сотрудника, если есть место в массиве
     */
    void setNewEmployee() {
        System.out.println("Раз уж вы вызвали этот метод, значит вы хотите добавить сотрудника.");
        for (int i = 0; i < employeesArray.length; i++) {
            if (employeesArray[i] == null) {
                employeesArray[i] = new Employee();
                System.out.print("Введите фамилию работника: ");
                employeesArray[i].setLastName(scan.next());
                System.out.print("Введите имя работника: ");
                employeesArray[i].setFirstName(scan.next());
                System.out.print("Введите отчество работника: ");
                employeesArray[i].setMiddleName(scan.next());
                System.out.print("Введите его номер отдела: ");
                employeesArray[i].setDepartment(scan.nextInt());
                System.out.print("Введите его будущую зарплату: ");
                employeesArray[i].setSalary(scan.nextInt());
                return;
            }
        }
        System.out.println("Нет свободных вакансий");
    }

    void searchEmployeeByID() {
        System.out.print("Введите ID для поиска: ");
        int exampleID = scan.nextInt();
        for (Employee i : employeesArray) {
            if (i.getId() == exampleID) {
                System.out.println("Сотрудник с id №" + exampleID + " - " + i);
                return;
            }
        }
        System.out.println("Сотрудник с id№" + exampleID + " не был найден.");
    }
}
