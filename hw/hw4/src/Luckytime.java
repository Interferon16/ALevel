public class Luckytime {

    public static int count() {
        int count = 0;
        for (int hours = 0; hours < 24; hours++) {
            for (int minuts = 0; minuts < 60; minuts++) {
                int a = hours / 10;
                int b = hours % 10;
                int c = minuts / 10;
                int d = minuts % 10;
                if (a == d && b == c) {
                    System.out.println("Time " + a + "" + b + " : " + c + "" + d);
                    count++;
                }
            }
        }
        return count++;
    }
}
