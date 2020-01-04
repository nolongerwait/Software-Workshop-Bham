import java.util.function.Function;

/**
 *  The class provides a method that computes an approximation of a
 *  definite integral of a function f on an interval [a,b].
 *  
 *  @version 2018-10-25
 *  @author Manfred Kerber
 */
public class Integral{

    /**
     *  The method computes the approximation of a definite integral
     *  by the sum of the areas of n rectangles of the same width.
     *  @param f The function of which the integral is to be computed.
     *  @param a The left endpoint of the interval for which the area
     *  is to be determined.
     *  @param b The right endpoint of the interval for which the area
     *  is to be determined.
     *  @param n The number of rectangles used for the approximation.
     *  @exception IllegalArgumentException if the left endpoint is
     *  bigger than the right endpoint.
     *  @return An approximation of the definite integral of the
     *  function f on the interval [a,b] by n rectangles.
     */
    public static double integral(Function<Double,Double> f,
                                  double a, double b, int n) {
        if (a <= b) {
            // to accumulate the areas
            double sum = 0;
            // The width of a single rectangle.
            double delta = (b - a)/n;
            /* 
             *  The sum accumlates (if multipled by the width of the
             *  areas) the area of all the rectangles considered so
             *  far, that is up to the number i.
             */
            for (int i = 0; i < n; i++) {
                sum += f.apply(a + i * delta);
            }
            /*
             * The combined area is computed by the width (the same
             * for all areas, namely delta) times the sum of all the
             * heights of the rectangles.
             */
            return delta * sum;
        } else { // b < a
            // IllegalArgumentException if b < a.
            throw new IllegalArgumentException("Left endpoint of interval "
                        + "not smaller than right endpoint.");
        }
    }

    /*
     *  Main method to test the method on four different functions.
     */

    public static void main(String[] args){
        Function<Double, Double> square = x -> {return x * x;};
        Function<Double, Double> sin = x    -> {return Math.sin(x);};
        Function<Double, Double> exp = x    -> {return Math.exp(x);};
        Function<Double, Double> poly = x   -> {return x*x*x + x*x + x +1;};

        double a = 0;
        double b = 1;
        int    n = 1000;
        
        System.out.println("area square: " + integral(square, a, b, n));
        System.out.println("area sin:    " + integral(sin,    a, b, n));
        System.out.println("area exp:    " + integral(exp,    a, b, n));
        System.out.println("area poly:   " + integral(poly,   a, b, n));
        System.out.println("area poly:   " + integral(poly,   b, a, n));
    }
}
