package com.thealgorithms.maths;

import java.util.ArrayList;
import java.util.Collections;

/**
 * In number theory, a vampire number (or true vampire number) is a composite
 * natural number with an even number of digits, that can be factored into two
 * natural numbers each with half as many digits as the original number and not
 * both with trailing zeroes, where the two factors contain precisely all the
 * digits of the original number, in any order, counting multiplicity. The first
 * vampire number is 1260 = 21 × 60.
 *
 * @see <a href='https://en.wikipedia.org/wiki/Vampire_number'>Vampire number on Wikipedia</a>
 */
public final class VampireNumber {
    private VampireNumber() {
    }

    public static void main(String[] args) {
        test(10, 1000);
    }

    static void test(int startValue, int stopValue) {
        int countofRes = 1;
        StringBuilder res = new StringBuilder();

        for (int i = startValue; i <= stopValue; i++) {
            for (int j = i; j <= stopValue; j++) {
                // System.out.println(i+ " "+ j);
                if (isVampireNumber(i, j, true)) {
                    countofRes++;
                    res.append("").append(countofRes).append(": = ( ").append(i).append(",").append(j).append(" = ").append(i * j).append(")").append("\n");
                }
            }
        }
        System.out.println(res);
    }

    static boolean isVampireNumber(int a, int b, boolean noPseudoVamireNumbers) {
        // Pseudo vampire numbers don't have to be of n/2 digits. E.g., 126 = 6 x 21 is such a number.
        if (noPseudoVamireNumbers) {
            if (a * 10 <= b || b * 10 <= a) {
                return false;
            }
        }

        String mulDigits = splitIntoDigits(a * b, 0);
        String faktorDigits = splitIntoDigits(a, b);

        return mulDigits.equals(faktorDigits);
    }

    // Method to split a pair of numbers to digits and sort them in the ascending order.
    static String splitIntoDigits(int num, int num2) {
        StringBuilder res = new StringBuilder();

        ArrayList<Integer> digits = new ArrayList<>();
        while (num > 0) {
            digits.add(num % 10);
            num /= 10;
        }
        while (num2 > 0) {
            digits.add(num2 % 10);
            num2 /= 10;
        }
        Collections.sort(digits);
        for (int i : digits) {
            res.append(i);
        }

        return res.toString();
    }
}
