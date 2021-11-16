package sankar.learn.math;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class FloatVsBigDecimal {
    /**
     * Refer the following link to understand about the contest of the problem
     * https://www.red-gate.com/hub/product-learning/sql-prompt/the-dangers-of-using-float-or-real-datatypes
     * https://arshadsuraj.medium.com/java-floating-point-numbers-rounding-problem-solution-a07e019b9dd5
     * https://dzone.com/articles/never-use-float-and-double-for-monetary-calculatio
     *
     * Comparing float and double:
     * https://howtodoinjava.com/java-examples/correctly-compare-float-double/
     */

    @Test
    public void guessOutput0() {
        float a = 3.1456f;
        float b = 1.1f;
        float c = a * b;
        System.out.println(c);//31456 * 11 = 346016, so 3.1456 * 1.1 should be 3.46016, But check the output.
        //the actual output is 3.4601603 which is not equal to 3.46016.
        //This is due to the IEE 754 representation used to store floating points in java.
    }

    @Test
    public void guessOutput1() {
        /**
         * Problems with floating point calculation
         * Add 0.1 11 times
         * Multiply 0.1 * 11
         * The output of both should be theoretically same
         * But check the actual output
         */
        float sum = 1.1111f;
        int i = 11;
        while (i > 0) {
            sum = sum + 1.1111f;
            i--;
        }

        float mul = 1.1111f * 11;
        if (sum == mul) {
            System.out.printf("The result of addition %s and multiplication %s are equal", sum, mul);
        } else {
            System.out.printf("The result of addition %s and multiplication %s are not equal", sum, mul);
        }
    }

    @Test
    public void aboutFloatAndBigDecimal() {
        /**
         * Floating point data types accommodate very big numbers but sacrifice precision.
         * They are handy for some types of scientific calculations, but are dangerous when used more widely,
         * because they can introduce big rounding errors.
         *
         * Floating-point arithmetic is all about tolerating and managing approximation in order to avoid overflow errors in calculations.
         * In the real world, we usually care about precision in numbers and will, instead, sacrifice space and resources in order to avoid overflow.
         *
         * What is the solution? how can we do floating-point calculation accurately?
         *
         * The answer is using the appropriate data type. Instead of float or double, we can use BigDecimal class.
         * This class provides operation for arithmetic, comparison, hashing, rounding, manipulation and format conversion.
         * With great precision, this class can handle both very small and very large floating-point numbers.
         */
    }

    @Test
    public void bigDecimalBasicProperties() {
        /**
         * How to use big decimal:
         * https://blogs.oracle.com/javamagazine/post/four-common-pitfalls-of-the-bigdecimal-class-and-how-to-avoid-them
         */
        BigDecimal a = new BigDecimal("1.00");
        //a = a + 1; not allowed, BigDecimal is non-mutable

        BigDecimal b = new BigDecimal("1.0");
        System.out.printf("1.00 == 1.0 %s\n", a == b);
        System.out.printf("1.00.equals(1.0) %s\n", a.equals(b));
        System.out.printf("1.00.compareTo(1.0) %s\n", a.compareTo(b));//because compareTo compares unscaled value of big decimal
        //Big decimal maintains the exact representation of decimal numbers 1.00 is not equals to 1.0, 1.00 is equal to 1.00

        BigDecimal c = new BigDecimal(Float.valueOf(3.456789f));
        System.out.printf("Precision of big decimal %s is %s\n", c, c.precision());
        System.out.printf("Scale of big decimal %s is %s\n", c, c.scale());
        System.out.println("Unscaled value 1234 and scale 3 produces "+BigDecimal.valueOf(1234, 3));
        /**
         * Precision: Total number of significant digits
         * Scale: Number of digits to the right of the decimal point
         * Big decimal is represented as unscaled value and scale, unscaled value is the number without decimal points,
         * Ex: 1.234 unscaled value is 1234 and scale is 3
         */

        BigDecimal d = new BigDecimal(1.1f); // produces 1.10000002384185791015625
        BigDecimal d1 = new BigDecimal("1.1");// produces 1.1
        BigDecimal d2 = BigDecimal.valueOf(123456.345678968678); //produces 123456.34567896868, rounded off because double onl supports 15-17 digits precision
        System.out.printf("%s %s %s", d, d1, d2);
        /**
         * Beware of the floating point constructor of big decimal, because floating points are stored different.
         * Always convert floating values to string before creating big decimal
         */
    }

    @Test
    public void calculateMaturityAmount() {
        /**
         * Suppose we have a principal amount p,
         * Calculate the final maturity amount, for  particular interest percentage
         */
        BigDecimal principal = new BigDecimal("1000.25");
        BigDecimal interestPer365Days = new BigDecimal("7.05");

        //maturity amount for one month, 30 days
        //(principal * interest / 365) * 30
        principal.multiply(interestPer365Days).divide(BigDecimal.valueOf(365)).multiply(BigDecimal.valueOf(30)).toPlainString();

    }

    @Test
    public void toValueMethods() {
        BigDecimal a = BigDecimal.valueOf(10003, 3);
        System.out.printf("a.toPlainString() %s, a.toString() %s, toEngineeringString() %s, toBigInteger() %s\n",
                a.toPlainString(), a.toString(), a.toEngineeringString(), a.toBigInteger());
        BigDecimal b = BigDecimal.valueOf(10003, 12);
        System.out.printf("toPlainString() %s, toString() %s, toEngineeringString() %s, toBigInteger() %s\n",
                b.toPlainString(), b.toString(), b.toEngineeringString(), b.toBigInteger());
        /**
         * toPlainString() - prints the number without exponential notation like 2.3E4
         * toEngineeringString() - prints the number with exponential notation if needed
         * toBigInteger() - drops the decimal part
         */

        System.out.printf("toBigIntegerExact() %s", a.toBigIntegerExact());
        /**
         * The java.math.BigDecimal.toBigIntegerExact() is an inbuilt method in java that converts this BigDecimal to a BigInteger, checking for lost information.
         * An exception is thrown if this BigDecimal has a nonzero fractional part.
         */
    }

    @Test
    public void bigDecimalOperations() {
        BigDecimal a = new BigDecimal("356.345");
        BigDecimal res = a.divide(new BigDecimal("0.1"));
        System.out.println(res);
    }


}
