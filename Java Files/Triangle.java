import java.util.Scanner;

public class Triangle {
    private double sideB;
    private double sideA;
    private double sideC;

    public Triangle(double sideA, double sideB, double sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    public double area() {
        double s = (sideA + sideB + sideC) / 2;
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
    }

    public double perimeter() {
        return sideA + sideB + sideC;
    }
    
    public double Hypotenuse(){
        return Math.sqrt(sideA*sideA + sideB*sideB);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter side A: ");
        double a = sc.nextDouble();
        System.out.print("Enter side B: ");
        double b = sc.nextDouble();
        System.out.print("Enter side C: ");
        double c = sc.nextDouble();

        Triangle t1 = new Triangle(a, b, c);

        for (int i = 0; i < 3; i++) {
            System.out.printf("Triangle %d : %.2f - %.2f - %.2f\n", (i + 1), t1.sideA, t1.sideB, t1.sideC);
            System.out.printf("Area: %.2f\nPerimeter: %.2f\nHypotenuse: %.2f\n", t1.area(), t1.perimeter(), t1.Hypotenuse());
            t1.sideA++;
            t1.sideB++;
            t1.sideC++;
            System.out.println("\n");
        }
        sc.close();
    }
}
