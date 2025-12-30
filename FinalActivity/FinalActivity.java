package FinalActivity;
import java.util.Scanner;
import java.lang.Math;

class FinalActivity {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String fullName = "";
        String address = "";
        String age = "0";
        String montlyAllowance = "0.0";

        Boolean confirmationOne = false;
        Boolean confirmationTwo = false;

        System.out.println("Enter your full name: ");
        fullName = scanner.nextLine();

        System.out.println("Enter your address: ");
        address = scanner.nextLine();

        System.out.println("Enter your age: ");
        try {
            age = scanner.nextLine();
            confirmationOne = true;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number for age: ");
        }
        System.out.println("Enter your monthly allowance: ");
        try {
            montlyAllowance = scanner.nextLine();
            confirmationTwo = true;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number for monthly allowance: ");
        }

        Person person = new Person(fullName, address, Integer.parseInt(age), Double.parseDouble(montlyAllowance));

        Person.Greeting greeting = person.new Greeting();

        System.out.println("\nName: " + person.fullName);
        System.out.println("First Name: " + StringOperation.getFirstName(person.fullName));
        System.out.println("Last Name: " + StringOperation.getLastName(person.fullName));
        System.out.println("Name Length: " + StringOperation.nameLength(person.fullName));
        System.out.println("Uppercase Name: " + StringOperation.toUpper(person.fullName));
        System.out.println("Lowercase Name: " + StringOperation.toLower(person.fullName));
        System.out.println("Contains 'a' in name: " + StringOperation.containsA(person.fullName));

        System.out.println("\nAddress: " + person.address);
        System.out.println("Uppercase Address: " + StringOperation.toUpper(person.address));
        System.out.println("Lowercase Address: " + StringOperation.toLower(person.address));
        System.out.println("City: " + StringOperation.getCity(person.address));

        System.out.println("\nAge (Converted): " + person.age);
        System.out.println("Monthly Allowance (Converted): PHP " + person.montlyAllowance);

        System.out.println("\n");
        greeting.greet(fullName);
        System.out.println("\nEnter amount to spend from allowance: ");
        int spend = scanner.nextInt();
        System.out.println("Enter number of years to add to age: ");
        int year = scanner.nextInt();
        System.out.println("Enter divisor to divide to 50: ");
        int divisor = scanner.nextInt();

        if (confirmationOne && confirmationTwo) {
            Person.ProcessNumData processNumData = new Person.ProcessNumData();
            System.out.println("\nAge after adding 10: " + processNumData.ageThatYear(person.age, year));
            System.out.println("Allowance after Spending 100: PHP " + processNumData.spendAllowance(person.montlyAllowance, spend));
            System.out.println("Division: " + processNumData.divideAllowanceForWeek(50, divisor));
            System.out.println("Invalid Input = 0");
        }
        
        scanner.close();
    }
}

class Person {
    String fullName;
    String address;
    int age;
    double montlyAllowance;

    Person(String fullName, String address, int age, double montlyAllowance) {
        this.fullName = fullName;
        this.address = address;
        this.age = age;
        this.montlyAllowance = montlyAllowance;
    }

    public class Greeting{
        public void greet(String name){
            System.out.println("Welcome to the system, " + name + "!");
        }
    }

    static class ProcessNumData{
        public double spendAllowance(double allowance, double spend){
            return allowance - spend;
        }
        public int ageThatYear(int age, int year){
            return Math.floorDiv(age + year, 1);
        }
        public int divideAllowanceForWeek(int age, int divisor){
            try
            {
                return age / divisor;
            }
            catch (ArithmeticException e)
            {
                System.out.println("Error: Division by zero is not allowed.");
                return 0;
            }
        }
    }
}

class StringOperation{
    public static int nameLength(String name){
        if (name == null || name.trim().isEmpty()) {
            return 0;
        }
        name = name.replace(" ", "");
        return name.length();
    }
    public static String getFirstName(String name){
        if (name == null || name.trim().isEmpty()) {
            return "";
        }
        String[] parts = name.trim().split("\\s+");
        if (parts.length <= 2) {
            return parts[0]; // fallback if there are no middle names
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length - 2; i++) {
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(parts[i]);
        }
        return sb.toString();
    }
    public static String getLastName(String name){
        if (name == null || name.trim().isEmpty()) {
            return "";
        }

        String[] parts = name.trim().split("\\s+");
        return parts[parts.length - 1];
    }
    public static String toUpper(String text){
        if (text == null || text.trim().isEmpty()) {
            return "";
        }
        return text.toUpperCase();
    }
    public static String toLower(String text){
        if (text == null || text.trim().isEmpty()) {
            return "";
        }
        return text.toLowerCase();
    }

    public static String getCity(String address){
        if (address == null || address.trim().isEmpty()) {
            return "";
        }

        String[] parts = address.split(",");
        for (int i = parts.length - 1; i >= 0; i--) {
            String part = parts[i];
            if (part.contains("City")) {
                return part.trim();
            }
        }
        return "";
    }

    public static Boolean containsA(String name){
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        return name.toLowerCase().contains("a");
    }
}

