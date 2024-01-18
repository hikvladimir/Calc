import java.util.Scanner;

public class Main {
    public static int calc (String exp) throws  NullPointerException {
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
            System.out.println("Некорректное выражение");
                  }
            exp=exp.replaceAll(" ", "");

        String[] data = exp.split(regexActions[actionIndex]);

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

                System.out.println(converter.intToRoman(result));
            } else {
                System.out.println(result);
            }
        } else {
            System.out.println("Числа должны быть в одном формате");
        }
        return result;
    }
    public static void main(String[] args) {


        Scanner scn = new Scanner(System.in);
        String exp = scn.nextLine();
        try {
            calc(exp);
        } catch (NullPointerException e) {
            System.out.println("в римской системе нет отрицательных чисел");
        }

    }

}
