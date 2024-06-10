import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;


class NumberTooLargeException extends Exception {
    public NumberTooLargeException(String message) {
        super(message);
    }
}

public class Main {
    private static final Map<Character, Integer> romanNumerals = new HashMap<>();
    static {
        romanNumerals.put('I', 1);
        romanNumerals.put('V', 5);
        romanNumerals.put('X', 10);

    }

    public static void main(String[] args) {
        try {
            String resultat = calc();
            System.out.println(resultat);
        } catch (NumberTooLargeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String calc() throws NumberTooLargeException {
        Scanner scanner = new Scanner(System.in);
        String stroka = scanner.nextLine();
        String itogg = null;

        String[] numbers = stroka.split("[+\\-*/]");
        for (String number : numbers) {
            if (romanToArabic(number.trim()) > 10) {
                throw new NumberTooLargeException("Число не может быть больше 10");
            }
        }

        if (stroka.contains("+")) {
            String[] strings = stroka.split("\\+");
            long a = romanToArabic(strings[0].trim());
            long b = romanToArabic(strings[1].trim());
            long c = a + b;
            itogg = arabicToRoman((int)c);
        } else if (stroka.contains("-")) {
            String[] strings = stroka.split("-");
            long a = romanToArabic(strings[0].trim());
            long b = romanToArabic(strings[1].trim());
            long c = a - b;
            itogg = arabicToRoman((int)c);
        } else if (stroka.contains("*")) {
            String[] strings = stroka.split("\\*");
            long a = romanToArabic(strings[0].trim());
            long b = romanToArabic(strings[1].trim());
            long c = a * b;
            itogg = arabicToRoman((int)c);
        } else if (stroka.contains("/")) {
            String[] strings = stroka.split("/");
            long a = romanToArabic(strings[0].trim());
            long b = romanToArabic(strings[1].trim());
            long c = a / b;
            itogg = arabicToRoman((int)c);
        } else {
            itogg = "Повторите, пожалуйста попытку.";
        }

        return itogg;
    }


    private static int romanToArabic(String roman) {
        int result = 0;
        for (int i = 0; i < roman.length(); i++) {
            if (i > 0 && romanNumerals.get(roman.charAt(i)) > romanNumerals.get(roman.charAt(i - 1))) {
                result += romanNumerals.get(roman.charAt(i)) - 2 * romanNumerals.get(roman.charAt(i - 1));
            } else {
                result += romanNumerals.get(roman.charAt(i));
            }
        }
        return result;
    }

    private static String arabicToRoman(int number) {
        String[] romanNumerals = {
                "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
        };
        int[] romanValues = {
                1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
        };

        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < romanValues.length; i++) {
            while (number >= romanValues[i]) {
                number -= romanValues[i];
                roman.append(romanNumerals[i]);
            }
        }
        return roman.toString();
    }
}