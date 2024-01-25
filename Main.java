import java.util.Scanner;


public class Main {

    public static String calc(String exp) throws Exception {
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        int actionIndex = -1;

        for (int i = 0; i < actions.length; i++) {
            if (exp.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }

        if (actionIndex == -1) {
            throw new Exception("меньше 2 операндов!");
        }

        exp = exp.replaceAll(" ", "");

        String[] data = exp.split(regexActions[actionIndex]);

        if (data.length < 2) {
            throw new Exception("меньше 2 операндов!");
        }
        if (data.length > 2) {
            throw new Exception("больше 2 операндов!");
        }

        int result = 0;
        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            int a, b;
            boolean isRoman = converter.isRoman(data[0]);
            if (isRoman) {

                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);

            } else {

                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }

            if (a < 1 || a > 10 || b < 1 || b > 10) {
                throw new Exception("число меньше 1 либо больше 10");
            }

            switch (actions[actionIndex]) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                default:
                    result = a / b;
                    break;
            }
            //15->XV
            if (isRoman) {
                if (result < 0) {
                    throw new Exception("в римской системе нет отрицательных чисел");
                }
                return converter.intToRoman(result);
            } else {
                return "" + result;
            }
        } else {
            throw new Exception("Числа должны быть в одном формате");
        }
    }

    // "1 + 2"
    // "0 + 9"
    // "I + XX"
    // "I + 4"
    // "I - II"
    // "IIII + VIIIII"
    // "VI / III"
    // "1 + 2 + 3"
    // "1"

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String exp = scn.nextLine();
        try {
            String resultat = calc(exp);
            System.out.println(resultat);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

