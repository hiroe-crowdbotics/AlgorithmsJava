package com.thealgorithms.maths;


public class ComplexNumberUtil {

    public static class ComplexNumber
    {
        public final double REAL;
        public final double IMAGINARY;

        public ComplexNumber(double real, double imaginary)
        {
            REAL = real;
            IMAGINARY = imaginary;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof ComplexNumber num)
            {
                return this.REAL == num.REAL && this.IMAGINARY == num.IMAGINARY;
            }

            return super.equals(obj);
        }

        @Override
        public String toString() {
            return REAL + ((IMAGINARY < 0) ? (" - " + Math.abs(IMAGINARY)) : (" + " + IMAGINARY)) + "i";
        }
    }

    public final static ComplexNumber ZERO = new ComplexNumber(0,0);
    public final static ComplexNumber ONE = new ComplexNumber(1,0);
    public final static ComplexNumber TWO = new ComplexNumber(2,0);
    public final static ComplexNumber PLUS_I = new ComplexNumber(0,1);
    public final static ComplexNumber MINUS_I = new ComplexNumber(0,-1);

    /**
     * add two complex numbers
     * @param num1 the first complex number
     * @param num2 the second complex number
     * @return the sum of num1 and num2
     */
    public static ComplexNumber add(ComplexNumber num1, ComplexNumber num2)
    {
        return new ComplexNumber(num1.REAL + num2.REAL, num1.IMAGINARY + num2.IMAGINARY);
    }

    /**
     * Subtracts the second complex number from the first complex number.
     *
     * @param num1 the first complex number
     * @param num2 the second complex number
     * @return the result of subtracting num2 from num1
     */
    public static ComplexNumber subtract(ComplexNumber num1, ComplexNumber num2)
    {
        return new ComplexNumber(num1.REAL - num2.REAL, num1.IMAGINARY - num2.IMAGINARY);
    }

    /**
     * Multiplies two complex numbers.
     *
     * @param num1 the first complex number
     * @param num2 the second complex number
     * @return the product of num1 and num2
     */
    public static ComplexNumber multiply(ComplexNumber num1, ComplexNumber num2)
    {
        return new ComplexNumber(
                num1.REAL*num2.REAL - num1.IMAGINARY*num2.IMAGINARY,
                num1.REAL*num2.IMAGINARY + num1.IMAGINARY*num2.REAL
        );
    }

    /**
     * Divides the first complex number by the second complex number.
     *
     * @param num1 the numerator complex number
     * @param num2 the denominator complex number
     * @return the result of dividing num1 by num2
     * @throws RuntimeException if the divisor (num2) is zero
     */
    public static ComplexNumber divide(ComplexNumber num1, ComplexNumber num2)
    {
        final double divisor = num2.REAL*num2.REAL + num2.IMAGINARY*num2.IMAGINARY;
        if (divisor == 0)
        {
            throw new RuntimeException("can't divide by zero");
        }

        return new ComplexNumber(
                (num1.REAL*num2.REAL + num1.IMAGINARY*num2.IMAGINARY) / divisor,
                (num1.IMAGINARY*num2.REAL - num1.REAL*num2.IMAGINARY) / divisor
        );
    }

    /**
     * Computes the absolute value (magnitude) of a complex number.
     *
     * @param num the complex number
     * @return the absolute value of num
     */
    public static double abs(ComplexNumber num)
    {
        return Math.sqrt(num.REAL*num.REAL + num.IMAGINARY*num.IMAGINARY);
    }

    /**
     * Computes the exponential function of a complex number.
     *
     * @param num the complex number
     * @return e raised to the power of num
     */
    public static ComplexNumber exp(ComplexNumber num)
    {
        final double coefficient = Math.exp(num.REAL);
        return new ComplexNumber(
                coefficient * Math.cos(num.IMAGINARY),
                coefficient * Math.sin(num.IMAGINARY)
        );
    }

    /**
     * Computes the natural logarithm of a complex number.
     *
     * @param num the complex number
     * @return the natural logarithm of num
     * @throws RuntimeException if num is zero
     */
    public static ComplexNumber ln(ComplexNumber num)
    {
        if (num.equals(ZERO))
        {
            throw new RuntimeException("can't take the logarithm of zero");
        }

        return  new ComplexNumber(
                Math.log(abs(num)),
                Math.atan2(num.IMAGINARY,num.REAL)
        );
    }

    /**
     * Computes the power of a complex number raised to another complex number.
     *
     * @param num1 the base complex number
     * @param num2 the exponent complex number
     * @return num1 raised to the power of num2
     */
    public static ComplexNumber pow(ComplexNumber num1, ComplexNumber num2)
    {
        if (num1.equals(ZERO))
        {
            return num2.equals(ZERO) ? ONE : ZERO;
        }

        return exp(multiply(ln(num1),num2));
    }

    /**
     * Computes the square root of a complex number.
     *
     * @param num the complex number
     * @return the square root of num
     */
    public static ComplexNumber sqrt(ComplexNumber num)
    {
        return pow(num,new ComplexNumber(0.5,0));
    }

    /**
     * Computes the sine of a complex number.
     *
     * @param num the complex number
     * @return the sine of num
     */
    public static ComplexNumber sin(ComplexNumber num)
    {
        ComplexNumber exp1 = exp(multiply(num,PLUS_I));
        ComplexNumber exp2 = exp(multiply(num,MINUS_I));
        return divide(subtract(exp1,exp2),multiply(new ComplexNumber(2,0),PLUS_I));
    }

    /**
     * Computes the cosine of a complex number.
     *
     * @param num the complex number
     * @return the cosine of num
     */
    public static ComplexNumber cos(ComplexNumber num)
    {
        ComplexNumber exp1 = exp(multiply(num,PLUS_I));
        ComplexNumber exp2 = exp(multiply(num,MINUS_I));
        return divide(add(exp1,exp2),TWO);
    }

    /**
     * Computes the tangent of a complex number.
     *
     * @param num the complex number
     * @return the tangent of num
     */
    public static ComplexNumber tan(ComplexNumber num)
    {
        return divide(sin(num),cos(num));
    }

    /**
     * Computes the cotangent of a complex number.
     *
     * @param num the complex number
     * @return the cotangent of num
     */
    public static ComplexNumber cot(ComplexNumber num)
    {
        return divide(cos(num),sin(num));
    }

    /**
     * Computes the arcsine of a complex number.
     *
     * @param num the complex number
     * @return the arcsine of num
     */
    public static ComplexNumber arcsin(ComplexNumber num)
    {
        ComplexNumber temp = sqrt(subtract(ONE,multiply(num,num)));
        return multiply(MINUS_I,ln(add(multiply(PLUS_I,num),temp)));
    }

    /**
     * Computes the arccosine of a complex number.
     *
     * @param num the complex number
     * @return the arccosine of num
     */
    public static ComplexNumber arccos(ComplexNumber num)
    {
        ComplexNumber temp = sqrt(subtract(ONE,multiply(num,num)));
        return multiply(MINUS_I,ln(add(num,multiply(temp,PLUS_I))));
    }

    /**
     * Computes the arctangent of a complex number.
     *
     * @param num the complex number
     * @return the arctangent of num
     */
    public static ComplexNumber arctan(ComplexNumber num)
    {
        return multiply(divide(MINUS_I,TWO),ln(divide(subtract(PLUS_I,num),add(PLUS_I,num))));
    }

    /**
     * Computes the arccotangent of a complex number.
     *
     * @param num the complex number
     * @return the arccotangent of num
     */
    public static ComplexNumber arccot(ComplexNumber num)
    {
        return multiply(divide(MINUS_I,TWO),ln(divide(add(num,PLUS_I),subtract(num,PLUS_I))));
    }

    public static void main(final String[] args) {
    }
}
