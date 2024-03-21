import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        Employee[] employeesArray = new Employee[10];
        String[] lastNameChoice = {"Иванов", "Николаев", "Попов", "Сидоренко", "Денисов", "Романов", "Генин", "Павлов", "Мкртчян", "Зозуля", "Вилкин", "Петухов", "Маккалистер", "Булкин", "Вареник"};
        String[] firstNameChoice = {"Иван", "Николай", "Виктор", "Альберт", "Денис", "Роман", "Гена", "Павел", "Валентин", "Григорий", "Джошуа", "Вениамин", "Степан"};
        String[] middleNameChoice = {"Иванович", "Евгеньевич", "Викторович", "Вадимович", "Денисович", "Рюрикович", "Сидорович", "Алексеевич", "Палыч", "Леонидович"};
        for (int i = 0; i < employeesArray.length; i++) {
            employeesArray[i] = new Employee(
                    lastNameChoice[rand.nextInt(0, lastNameChoice.length)],
                    firstNameChoice[rand.nextInt(0, firstNameChoice.length)],
                    middleNameChoice[rand.nextInt(0, middleNameChoice.length)],
                    rand.nextInt(1, 6),
                    rand.nextInt(50_000, 350_000)
            );
        }

        System.out.println(
                "Список всех работников и вся информация о них: \n" + getEmployeesList(employeesArray));
        System.out.println(
                "Общие затраты на зарплату сотрудникам составляют: " + getTotalSalary(employeesArray));
        System.out.print(
                "Минимальная зарплата у: " + getEmployeesWithMinimalSalary(employeesArray).toString());
        System.out.print(
                "Максимальная зарплата у: " + getEmployeesWithMaximumSalary(employeesArray).toString());
        System.out.printf(
                "Среднее значение зарплаты составляет: %.2f рублей.\n", getAverageValueSalary(employeesArray));

        // Получаем все фамилии
        System.out.print("Выводим все фамилии: ");
        getLastNames(employeesArray);
        System.out.println();

        // Индексируем зарплату всех сотрудников на случайную величину от 10 до 30
        indexSalary(employeesArray, rand.nextInt(10, 30));

        // Информация о работниках после индексации зарплаты
        System.out.println(
                "Список всех работников и вся информация о них: \n" + getEmployeesList(employeesArray));

        System.out.print("Введите номер отдела для подробной информации по нему: ");
        int department = scan.nextInt();
        Employee departmentMinSalary = getEmpWithMinSalaryInSomeDepartment(employeesArray, department);
        System.out.println("Самая низкая зарплата в " + department + "-м отделе у работника: " +
                departmentMinSalary.getLastName() + " " + departmentMinSalary.getFirstName() + " " +
                departmentMinSalary.getMiddleName() + " с зарплатой " +
                departmentMinSalary.getSalary() + " рублей.");
        Employee departmentMaxSalary = getEmpWithMaxSalaryInSomeDepartment(employeesArray, department);
        System.out.println("Самая высокая зарплата в " + department + "-м отделе у работника: " +
                departmentMaxSalary.getLastName() + " " + departmentMaxSalary.getFirstName() + " " +
                departmentMaxSalary.getMiddleName() + " с зарплатой " +
                departmentMaxSalary.getSalary() + " рублей.");
        System.out.printf("Средняя зарплата по отделу №%d составляет %.2f рублей.\n", department,
                getAvgSalaryInSomeDepartment(employeesArray, department));
        indexSalaryInSomeDepartment(employeesArray, department, rand.nextInt(10, 30));
        printEmpInSomeDepartment(employeesArray, department);

        System.out.print("Получить значение для фильтрации сотрудников: ");
        int empFilter = scan.nextInt();
        printEmpWithSalaryLessThen(employeesArray, empFilter);
        printEmpWithSalaryMoreThen(employeesArray, empFilter);

    }

    /**
     * Возвращает информацию о всех сотрудниках
     * @param employeesArray массив сотрудников
     * @return sb информация о сотрудниках
     */
    static String getEmployeesList(Employee[] employeesArray) {
        StringBuilder sb = new StringBuilder();
        for (Employee i: employeesArray) {
            sb.append(i.toString());
        }
        return sb.toString();
    }

    /**
     * Возвращает общие затраты на зарплату сотрудникам
     * @param employeesArray массив сотрудников
     * @return totalSalary общие затраты на зарплату сотрудникам
     */
    static int getTotalSalary(Employee[] employeesArray) {
        int totalSalary = 0;
        for (Employee i: employeesArray) {
            totalSalary += i.getSalary();
        }
        return totalSalary;
    }

    /**
     * Возвращает сотрудника с минимальной зарплатой
     * @param employeesArray массив сотрудников
     * @return экземпляр класса Employee
     */
    static Employee getEmployeesWithMinimalSalary(Employee[] employeesArray) {
        Employee poorWorker = null;
        int minimalSalary = employeesArray[0].getSalary();
        for (Employee i: employeesArray) {
            if (i.getSalary() <= minimalSalary) {
                minimalSalary = i.getSalary();
                poorWorker = i;
            }
        }
        return poorWorker;
    }

    /**
     * Возвращает сотрудника с максимальной зарплатой
     * @param employeesArray массив сотрудников
     * @return экземпляр класса Employee
     */
    static Employee getEmployeesWithMaximumSalary(Employee[] employeesArray) {
        Employee richWorker = null;
        int maximumSalary = employeesArray[0].getSalary();
        for (Employee i : employeesArray) {
            if (i.getSalary() >= maximumSalary) {
                maximumSalary = i.getSalary();
                richWorker = i;
            }
        }
        return richWorker;
    }

    /**
     * Возвращает среднее значение зарплат всех сотрудников
     * @param employeesArray массив сотрудников
     * @return double среднее значение зп без округления
     */
    static double getAverageValueSalary(Employee[] employeesArray) {
        return (double) getTotalSalary(employeesArray) / employeesArray.length;
    }

    /**
     * Печатает в консоль все фамилии сотрудников
     * @param employeesArray массив сотрудников
     */
    static void getLastNames(Employee[] employeesArray) {
        for (int i = 0; i < employeesArray.length; i++) {
            if (i < employeesArray.length - 1) {
                System.out.print(employeesArray[i].getLastName() + ", ");
            } else {
                System.out.print(employeesArray[i].getLastName() + ".\n");
            }
        }
    }

    /**
     * Индексация зарплаты всех сотрудников на величину переданного аргумента value
     * @param employees массив сотрудников
     * @param value значение в процентах для индексации зарплаты
     */
    static void indexSalary(Employee[] employees, int value) {
        for (Employee i: employees) {
            i.setSalary(i.getSalary() / 100 * (value + 100));
        }
        System.out.printf("Зарплата была проиндексирована на %d процентов.\n", value);
    }

    /**
     * Возвращает работника с минимальной зарплатой по указанному отделу
     * @param employees массив сотрудников
     * @param department номер отдела
     * @return экземпляр класса Employee
     */
    static Employee getEmpWithMinSalaryInSomeDepartment(Employee[] employees, int department) {
        Employee minEmpSalary = employees[0];
        for (Employee i: employees) {
            if (i.getSalary() <= minEmpSalary.getSalary() && i.getDepartment() == department) {
                minEmpSalary = i;
            }
        }
        return minEmpSalary;
    }

    /**
     * Возвращает работника с максимальной зарплатой по указанному отделу
     * @param employees массив сотрудников
     * @param department номер отдела
     * @return экземпляр класса Employee
     */
    static Employee getEmpWithMaxSalaryInSomeDepartment(Employee[] employees, int department) {
        Employee maxEmpSalary = employees[0];
        for (Employee i : employees) {
            if (i.getSalary() >= maxEmpSalary.getSalary() && i.getDepartment() == department) {
                maxEmpSalary = i;
            }
        }
        return maxEmpSalary;
    }

    /**
     * Возвращает среднюю зарплату по указанному отделу
     * @param employees массив сотрудников
     * @param department номер отдела
     * @return среднюю зарплату по отделу
     */
    static double getAvgSalaryInSomeDepartment(Employee[] employees, int department) {
        int totalSalary = 0;
        int depCounter = 0;
        for (Employee i : employees) {
            if (i.getDepartment() == department) {
                totalSalary += i.getSalary();
                depCounter++;
            }
        }
        return (double) totalSalary / depCounter;
    }

    /**
     * Индексация зарплаты сотрудников указанного отдела на величину переданного аргумента value
     * @param employees массив сотрудников
     * @param value значение в процентах для индексации зарплаты
     */
    static void indexSalaryInSomeDepartment(Employee[] employees, int department, int value) {
        for (Employee i: employees) {
            if (i.getDepartment() == department) {
                i.setSalary(i.getSalary() / 100 * (value + 100));
            }
        }
        System.out.println(
                "Зарплата сотрудников " + department + "-го отдела была проиндексирована на " + value + "%."
        );
    }

    /**
     * Печатает данные сотрудников (кроме отдела) из данного отдела
     * @param employees массив сотрудников
     * @param department номер отдела
     */
    static void printEmpInSomeDepartment(Employee[] employees, int department) {
        for (Employee i : employees) {
            if (i.getDepartment() == department) {
                System.out.println("id№" + i.getId() + " - " + i.getLastName() + " " +
                        i.getFirstName() + " " +
                        i.getMiddleName() + " - " +
                        i.getSalary());
            }
        }
    }

    /**
     * Печатает сотрудников с зарплатой меньше, чем value
     * @param employees массив сотрудников
     * @param value значение зарплаты, ниже которого выводим сотрудников на печать
     */
    static void printEmpWithSalaryLessThen(Employee[] employees, int value) {
        int counter = 0;
        System.out.println("Ищем сотрудников с зарплатой ниже " + value + " рублей.");
        for (Employee i : employees) {
            if (i.getSalary() < value) {
                System.out.println("id№" + i.getId() + " " +
                        i.getLastName() + " " +
                        i.getFirstName() + " " +
                        i.getMiddleName() + " с зарплатой " +
                        i.getSalary() + ".");
                counter++;
            }
        }
        if (counter == 0) {
            System.out.println("Нет работников с зарплатой ниже " + value + " рублей.");
        }
    }

    /**
     * Печатает сотрудников с зарплатой выше, чем value
     * @param employees массив сотрудников
     * @param value значение зарплаты, выше которого выводим сотрудников на печать
     */
    static void printEmpWithSalaryMoreThen(Employee[] employees, int value) {
        int counter = 0;
        System.out.println("Ищем сотрудников с зарплатой выше " + value + " рублей.");
        for (Employee i : employees) {
            if (i.getSalary() >= value) {
                System.out.println("id№" + i.getId() + " " +
                        i.getLastName() + " " +
                        i.getFirstName() + " " +
                        i.getMiddleName() + " с зарплатой " +
                        i.getSalary() + ".");
                counter++;
            }
        }
        if (counter == 0) {
            System.out.println("Нет работников с зарплатой выше " + value + " рублей.");
        }
    }
}