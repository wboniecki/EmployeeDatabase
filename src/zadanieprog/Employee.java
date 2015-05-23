/**
 * Created by Wiktor Boniecki.
 */
package zadanieprog;

public class Employee {
    //zmienne
    private String firstName;
    private String lastName;
    private char sex;
    private int divisionNumber;
    private float payment;
    private int age;
    private int childrenAmount;
    private boolean married;

    private float raise;

    //Konstruktor klasy pracownik
    public Employee(String firstName, String lastName, char sex,
                    int divisionNumber, float payment, int age, int childrenAmount,
                    boolean married) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.divisionNumber = divisionNumber;
        this.payment = payment;
        this.age = age;
        this.childrenAmount = childrenAmount;
        this.married = married;
    }

    //metoda sprawdzaj¹ca czy jesteœ w zwi¹zku
    private String checkMarried() {
        if (married) {
            return "married";
        } else {
            return "single";
        }
    }

    //drukowanie pe³nej informacji na temat pracownika
    public void showFullData() {
        System.out.println(firstName + " " + lastName);
        System.out.println(age);
        System.out.println(checkMarried());
        System.out.println("Sex: " + sex);
        System.out.println("Children: " + childrenAmount);
        System.out.println("Division number: " + divisionNumber);
        System.out.println("Payment: " + payment);
    }

    //drukowanie okrojonej informacji o pracowniku
    public void showData() {
        System.out.println(firstName + " " + lastName);
        System.out.println("Payment: " + payment);
    }

    //drukowanie specjalnej infrmacji o pracowniku
    public void showSpecialData() {
        System.out.println(firstName.toUpperCase() + " "
                + lastName.toUpperCase());
    }

    public boolean isHigher(int value) {
        if (value < payment) {
            return true;
        } else {
            return false;
        }
    }

    public void setRaise(float raise) {
        this.raise = raise;
        this.raise += childrenAmount * 0.02;
        if (married) {
            this.raise += 0.03;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public char getSex() {
        return sex;
    }

    public int getDivisionNumber() {
        return divisionNumber;
    }

    public float getPayment() {
        return payment;
    }

    public int getAge() {
        return age;
    }

    public int getChildrenAmount() {
        return childrenAmount;
    }

    public boolean isMarried() {
        return married;
    }

    public float getRaise() {
        return raise;
    }

    public void setDivisionNumber(int divisionNumber) {
        this.divisionNumber = divisionNumber;
    }

    public void setPayment(float payment) {
        this.payment = payment;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setChildrenAmount(int childrenAmount) {
        this.childrenAmount = childrenAmount;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
