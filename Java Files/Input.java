import java.util.Scanner;

public class Input {

    public static 
    public static void main(String[] args){
        
        Scanner input = new Scanner(System.in);

        System.out.print("Enter Name: ");
        String name = input.nextLine();

        System.out.print("Enter Age: ");
        int age = input.nextInt();

        int[] grades = new int[5];
        int sum = 0;

        for (int i = 0; i < grades.length; i++)
        {
            System.out.print(i + 1 + ". ");
            grades[i] = input.nextInt();
            sum += grades[i];
        }

        double ave = (double) sum / grades.length;

        System.out.printf("%s, you are %d years old", name ,age);
        System.out.println("\nYour grades is " + ave);

        if (ave >= 90)
        {
            System.out.println("High Grades");
        }
        else if (ave < 90 && ave > 75)
        {
            System.out.println("You Passed");
        }
        else
        {
            System.out.println("You Failed");
        }

        System.out.print("Favorite Day? 1-7: ");
        int day = input.nextInt();

        switch (day) {
            case 1:
                System.out.println("It's Monday");
                break;
            case 2:
                System.out.println("It's Tuesday");
                break;
            case 3:
                System.out.println("It's Wednesday");
                break;
            case 4:
                System.out.println("It's Thursday");
                break;
            case 5:
                System.out.println("It's Friday");
                break;
            case 6:
                System.out.println("It's Saturday");
                break;
            case 7:
                System.out.println("It's Sunday");
                break;
            default:
                System.out.println("Yo");
        }

        for (int i = 0; i < 10; i++)
        {
            System.out.println(i + 1);
        }
        


        input.close();

    }
}



