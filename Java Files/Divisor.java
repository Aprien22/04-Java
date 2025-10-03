public class Divisor
{
    public static void main(String[] args) {
        int sum = 0;
        int number = 28; // Example number
        System.out.print("Divisors:");
        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                // Print divisors nicely
                if (i != 1){
                    System.out.printf(" +");
                }
                System.out.printf(" %d", i);
                sum += i;
            }
        }
        System.out.printf("\nSum: %d", sum);
    }
}