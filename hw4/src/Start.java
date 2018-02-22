import java.util.*;

public class Start {

    private static void arrayPrinter(int[] a) {
        int length=a.length;
        for (int i = 0; i < length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    private static void arrayPrinter(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println("");
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + " ");
            }
        }
    }


    public static void main(String[] args) {
        System.out.println("Демонстрация");
        System.out.println("Введите номер задания");
        System.out.println("1 - сумма чисел введённого числа");
        System.out.println("2 - последовательность чисел Фибоначчи");
        System.out.println("3 - количество \"счастливых\" билетов в пачке");
        System.out.println("4 - количество симетричных комбинаций времени в сутках");
        System.out.println("5 - сгенерировать рандомный массив строк 5, столбцов 8 итервал от 10 до 99");
        System.out.println("6 - сгенерировать рандомный массив строк 7, столбцов 4 итервал от -5 до +5");
        System.out.println("    и вывести на экран индекс строки с наибольшим по модулю произведением элементов");
        System.out.println("7 - сортировка введённого масива обменом");
        System.out.println("8 - ханойские башни");

        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextInt()) {
            case 1:
                System.out.println("Введите число");
                Scanner number = new Scanner(System.in);
                System.out.println("Сумма цифр числа = " + Summing.summline(number.nextInt()));
                break;
            case 2:
                System.out.println("Введите требуемую длину последовательности чисел Фибоначчи");
                Scanner fib = new Scanner(System.in);
                System.out.println("Последовательность числе Фибоначчи = " + Fibonachi.numbers(fib.nextInt()));
                break;
            case 3:
                System.out.println("Введите верхний предел размера пачки билетов(макс. 999999)");
                Scanner tic = new Scanner(System.in);
                System.out.println("Количество \"счастливых\" билетов в пачке = " + Tickets.LuckyTicketsCount(tic.nextInt()));
                break;
            case 4:
                int a = Luckytime.count();
                System.out.println("Всего в сутках - " + a + " симетричных часов");
                break;
            case 5:
                System.out.println("Сгенерированный массив \n");
                int randomarray[][]= ArrayGenerate.generateRandomArray(5,8,10,99);
                arrayPrinter(randomarray);
                break;
            case 6:
                System.out.println("Сгенерированный массив");
                int randomarray2[][]=ArrayGenerate.generateRandomArray(7,4,-5,5);
                arrayPrinter(randomarray2);
                int randomarrayresult[]=arraySumming.maxCompositionLine(randomarray2);
                System.out.println("\nС максимальным по модулю произведением стока №"+randomarrayresult[0]);
                System.out.println("Её произведение равно - "+randomarrayresult[1]);
                break;
            case 7:
                System.out.println("Введите длинну случайно генерируемого массива ");
                Scanner randarr = new Scanner(System.in);
                int b[] = ArrayGenerate.generateRandomArray(randarr.nextInt(), 0, 100);
                arrayPrinter(b);
                System.out.println(" - Массив до сотрировки");
                int c = Bottle.arraySorter(b);
                arrayPrinter(b);
                System.out.println(" - Массив после сортировки обменом");
                System.out.println("Количество итерраций - " + c);
                break;
            case 8:
                System.out.println("Area under construction :(");
                break;
            default:
                break;

        }
    }
}
