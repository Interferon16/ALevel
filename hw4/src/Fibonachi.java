/*
Выведите на экран первые 11 членов последовательности Фибоначчи.
Напоминаем, что первый и второй члены последовательности равны единицам, а каждый следующий — сумме двух предыдущих.

 */

public class Fibonachi {
    private static int actual = 0, summ = 1;
    private static String numbers = "" + summ;

    public static void numbers() {
        numbers(11);
    }

    public static String numbers(int a) {
        for (int i = 0; i < a - 1; i++) {
            summ += actual;
            actual = summ - actual;
            numbers += " - " + summ;
        }
        return numbers;
    }

}
