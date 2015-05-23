/**
 * Created by Wiktor Boniecki.
 */

package zadanieprog;

import java.io.*;
import java.util.Scanner;

public class Program {
    private Employee[] database = new Employee[100]; //tablica obiektów Employee
    private Scanner sc = new Scanner(System.in);
    private Scanner sc2 = new Scanner(System.in);
    private File databaseFile = new File("database.txt"); //src do pliku z baz¹
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private PrintWriter newdb;
    private String currentLine = null;
    private boolean married;
    private String employeeData[] = new String[7];
    //zmienne pomocnicze
    private int option;
    private int editor;
    private int counter;
    private int womanCounter;
    private float avaragePayment;
    private float manPayment;
    private float womenPayment;

    //Menu w kosoli zapêtlone do wyjœcia na klawisz 7
    public void menu() throws FileNotFoundException {
        while (true) {
            System.out.println("Menu:");
            System.out.println("0. Import database.");
            System.out.println("1. Show data.");
            System.out.println("2. Add employee.");
            System.out.println("3. Export database.");
            System.out.println("4. Delete employee");
            System.out.println("5. Edit employee");
            System.out.println("6. Addons");
            System.out.println("7. Exit");
            option = sc.nextInt();

            if (option == 7) {
                break;
            }

            switch (option) {
                case 0:
                    importDatabase();
                    break;
                case 1:
                    printData();
                    break;
                case 2:
                    addEmployeeForm();
                    break;
                case 3:
                    exportDatabase();
                    break;
                case 4:
                    deleteEmployee();
                    break;
                case 5:
                    editDatabase();
                    break;
                case 6:
                    addons();
                    break;

                default:
                    break;
            }
        }
    }

    //Importowanie bazy danych, jeœli nie ma bazy tworzy pust¹
    private void importDatabase() throws FileNotFoundException {
        try {
            fileReader = new FileReader(databaseFile);
            bufferedReader = new BufferedReader(fileReader);
            while ((currentLine = bufferedReader.readLine()) != null) {
                employeeData = currentLine.split(", ");
                addEmployee(employeeData);
            }
            fileReader.close();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            createEmptyDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Metoda tworz¹ca pust¹ bazê
    private void createEmptyDatabase() throws FileNotFoundException {
        newdb = new PrintWriter("database.txt");
        newdb.close();
    }

    //Eksport bazy do pliku txt
    private void exportDatabase() throws FileNotFoundException {
        newdb = new PrintWriter("database.txt");
        for (int i = 0; i < database.length; i++) {
            if (database[i] == null) {
                break;
            }
            newdb.println(database[i].getLastName() + ", " + database[i].getFirstName() + ", " + database[i].getSex() + ", " + database[i].getDivisionNumber() + ", " + database[i].getPayment() + ", " + database[i].getAge() + ", " + database[i].getChildrenAmount());

        }
        newdb.close();
    }

    //Formularz dodwania nowego pracownika
    private void addEmployeeForm() {
        System.out.println("Name:");
        employeeData[1] = sc2.nextLine();
        System.out.println("Last name:");
        employeeData[0] = sc2.nextLine();
        System.out.println("SEX (M/W):");
        employeeData[2] = sc2.nextLine();
        System.out.println("Division:");
        employeeData[3] = sc2.nextLine();
        System.out.println("Payment:");
        employeeData[4] = sc2.nextLine();
        System.out.println("Age:");
        employeeData[5] = sc2.nextLine();
        System.out.println("Children amount:");
        employeeData[6] = sc2.nextLine();

        addEmployee(employeeData);
    }

    //0 - lastName
    //1 - firstName
    //2 - sex
    //3 - division
    //4 - payment
    //5 - age
    //6 - children

    //Dodawanie nowego obeiktu pracownika do tablicy
    private void addEmployee(String[] employeeData) {
        if (Integer.parseInt(employeeData[6]) > 0) {
            married = true;
        } else {
            married = false;
        }
        for (int i = 0; i < database.length; i++) {
            if (database[i] == null) {
                database[i] = new Employee(employeeData[1], employeeData[0], employeeData[2].charAt(0), Integer.parseInt(employeeData[3]), Float.parseFloat(employeeData[4]), Integer.parseInt(employeeData[5]), Integer.parseInt(employeeData[6]), married);
                break;
            }
        }
    }

    //Edytowanie bazy
    private void editDatabase() {
        printFullData();
        System.out.println("----------");
        System.out.println("Choose nr to edit:");
        editor = sc.nextInt();
        database[editor].showSpecialData();
        System.out.println("What u want to edit");
        System.out.println("1. Division number.");
        System.out.println("2. Payment");
        System.out.println("3. Age");
        System.out.println("4. Children amount");
        System.out.println("5. Married");
        System.out.println("6. Last Name");
        option = sc.nextInt();

        switch (option) {
            case 1:
                System.out.println("Type new division:");
                database[editor].setDivisionNumber(sc.nextInt());
                break;
            case 2:
                System.out.println("Type new payment:");
                database[editor].setPayment(sc.nextInt());
                break;
            case 3:
                System.out.println("Type new age:");
                database[editor].setAge(sc.nextInt());
                break;
            case 4:
                System.out.println("Type new children amount:");
                database[editor].setChildrenAmount(sc.nextInt());
                break;
            case 5:
                System.out.println("Are married? (Yes - 1/No - 0):");
                option = sc.nextInt();
                if (option == 1) {
                    database[editor].setMarried(true);
                } else if (option == 0) {
                    database[editor].setMarried(false);
                }
                break;
            case 6:
                if (database[editor].getSex() == 'W') {
                    System.out.println("Type new last name:");
                    database[editor].setLastName(sc2.nextLine());
                } else {
                    System.out.println("Acces denied.");
                }
                break;
            default:
                break;
        }
    }

    //Kasowanie pracowników z bazy
    private void deleteEmployee() {
        printFullData();
        System.out.println("----------");
        System.out.println("Choose nr to delete:");
        editor = sc.nextInt();

        for (int i = 0; i < database.length; i++) {
            if (i >= editor) {
                if (database[i + 1] == null) {
                    database[i] = null;
                    break;
                } else {
                    database[i] = database[i + 1];
                }
            }
        }
    }

    //Menu nr 6. Pozycje dodatkowe
    private void addons() {
        System.out.println("Choose option:");
        System.out.println("1. Show employees with payment filter");
        System.out.println("2. Show average payment in division");
        System.out.println("3. Show highest payment by man and women");
        System.out.println("4. Average woman payment / average man payment");
        System.out.println("5. Increase all payments by 10%");
        option = sc.nextInt();

        switch (option) {
            case 1:
                System.out.println("Type payment:");
                employeePaymentFilter(sc.nextFloat());
                break;
            case 2:
                System.out.println("Type division:");
                averagePaymentInDivision(sc.nextInt());
                break;
            case 3:
                paymentBySex();
                break;
            case 4:
                payments();
                break;
            case 5:
                increasePayments();
                break;
            default:
                break;
        }
    }

    //Zwiêkszanie pensji wszystkim pracownikom o 10%
    private void increasePayments() {
        for (int i = 0; i < database.length; i++) {
            if (database[i] == null) {
                break;
            }
            database[i].setPayment(database[i].getPayment() * 1.1f);
        }
    }

    //stosunek œredniej p³acy kobiet do œredniej p³acy mê¿czyzn
    private void payments() {
        manPayment = 0;
        womenPayment = 0;
        counter = 0;
        womanCounter = 0;

        for (int i = 0; i < database.length; i++) {
            if (database[i] == null) {
                break;
            }
            if (database[i].getSex() == 'W') {
                womanCounter++;
                womenPayment += database[i].getPayment();
            }
            if (database[i].getSex() == 'M') {
                counter++;
                manPayment += database[i].getPayment();
            }
        }

        System.out.println("Average woman payment / average man payment is " + (womenPayment / womanCounter) / (manPayment / counter));
    }

    //Najwy¿sze wynagrodzenie dla obu p³ci
    private void paymentBySex() {
        manPayment = 0;
        womenPayment = 0;
        for (int i = 0; i < database.length; i++) {
            if (database[i] == null) {
                break;
            }
            if (database[i].getSex() == 'W' && database[i].getPayment() > womenPayment) {
                womenPayment = database[i].getPayment();
            }
            if (database[i].getSex() == 'M' && database[i].getPayment() > manPayment) {
                manPayment = database[i].getPayment();
            }
        }
        System.out.println("Highest payment in man is " + manPayment);
        System.out.println("Highest payment in woman is " + womenPayment);
    }

    //Srednie wynagrodzenie dla podanej dywizji
    private void averagePaymentInDivision(int division) {
        counter = 0;
        avaragePayment = 0;
        for (int i = 0; i < database.length; i++) {
            if (database[i] == null) {
                break;
            }
            if (database[i].getDivisionNumber() == division) {
                counter++;
                avaragePayment += database[i].getPayment();
            }
        }

        System.out.println("Average payment in division " + division + " is " + avaragePayment / counter);
    }

    //Wyœwietlanie pensji wy¿szej od podanej przez usera w konsoli
    private void employeePaymentFilter(float payment) {
        counter = 0;
        for (int i = 0; i < database.length; i++) {
            if (database[i] == null) {
                break;
            }
            if (database[i].getPayment() >= payment) {
                counter++;
            }
        }
        System.out.println("Employees with higher payment than " + payment + " is " + counter);
    }

    //drukowanie pe³nej listy
    private void printFullData() {
        for (int i = 0; i < database.length; i++) {
            if (database[i] == null) {
                break;
            }
            System.out.println("----- " + i + " -----");
            database[i].showFullData();
        }
    }

    //drukowanie okrojonej listy
    private void printData() {
        for (int i = 0; i < database.length; i++) {
            if (database[i] == null) {
                break;
            }
            database[i].showData();
        }
    }


}
