/*
Для введённого пользователем с клавиатуры натурального числа посчитайте сумму всех его цифр
(заранее не известно сколько цифр будет в числе).
 */


public class Summing {

    public static int summline(int a) {
        int sum = 0, checker = 0;

        for (int i = 0; i < 1; i += checker) {
            int b = a % 10;
            sum += b;
            if (a < 10) {
                checker++;
                break;
            }
            a /= 10;
        }
        return sum;
    }

}
