/**
 * Created by Wiktor Boniecki.
 */
package zadanieprog;

import java.io.FileNotFoundException;

public class EmployeeDatabase {
    //G��wna instancja programu
    public static void main(String[] args) throws FileNotFoundException {
        Program employeeDatabase = new Program();
        employeeDatabase.menu();
    }

}