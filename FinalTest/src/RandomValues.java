import java.util.Arrays;
import java.util.Random;

public class RandomValues {
    public static double[] generateTestValues(int n, double a, double b) throws IllegalArgumentException {
        if(a > b || n < 0) {
            throw new IllegalArgumentException("Illegal arguements: a is greater than b or n is negative!");
        }

        double[] result = new double[n];
        while(n > 0) {
            Random numRandom = new Random();
            result[--n] = numRandom.nextDouble() * (b - a) + a;
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            double[] res1 = generateTestValues(0, 1.0, 10.0);
            System.out.println(Arrays.toString(res1));
            double[] res2 =  generateTestValues(5, 1.0, 10.0);
            System.out.println(Arrays.toString(res2));
            double[] res3 = generateTestValues(10, 10.0, 10.0);
            System.out.println(Arrays.toString(res3));
            double[] res4 = generateTestValues(-5, 10.0, 1.0);
            System.out.println(Arrays.toString(res4));
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal arguements: a is greater than b or n is negative!");
        }
    }
}