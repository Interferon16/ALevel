import java.util.Random;


public class Maze {

    private static int smallest = Integer.MAX_VALUE, smallest_exit_i = 0, smallest_exit_j = 0, wave, counter;

    public static void run() {
        int[][] tempArray = cloneArray(MAZE);
        int startpoint[] = generateStartPoint(MAZE);
        int sp_i = startpoint[0];
        int sp_j = startpoint[1];
        waveOnWater(tempArray, sp_i, sp_j);
        checkExits(tempArray);
        int backroad[][] = ShortestRoad(tempArray, smallest_exit_i, smallest_exit_j);
        System.out.println("");
        System.out.println("Начальная точка - "+sp_i+"."+sp_j);
        System.out.println("");
        System.out.print("Путь от выхода к начальной точке");
        for (int i = 0; i < counter; i++) {
            System.out.print(" -> " + backroad[i][0] + "." + backroad[i][1]);
        }
        System.out.print(" -> " + sp_i + "." + sp_j + " Нашел выход. Ураааа!!!");
    }

    private static int[][] MAZE = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 0, 0, 0, 1, 1, 0, 0, 1},
            {1, 0, 0, 1, 0, 0, 0, 0, 1, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 1, 0, 1, 0, 1, 1, 1},
            {1, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 1, 0, 1, 0, 0, 0, 1},
            {1, 1, 0, 1, 0, 0, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    private static void waveOnWater(int[][] m, int sp_i, int sp_j) {
        wave = 2;
        m[sp_i][sp_j] = wave;
        int count = 1;
        while (count > 0) {
            count = 0;
            wave++;
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m[i].length; j++) {
                    if (m[i][j] == wave - 1) {
                        count++;
                        if (i + 1 < m.length && m[i + 1][j] == 0) {
                            m[i + 1][j] = wave;
                        }
                        if (i - 1 >= 0 && m[i - 1][j] == 0) {
                            m[i - 1][j] = wave;
                        }
                        if (j + 1 < m[i].length && m[i][j + 1] == 0) {
                            m[i][j + 1] = wave;
                        }
                        if (j - 1 >= 0 && m[i][j - 1] == 0) {
                            m[i][j - 1] = wave;
                        }
                    }
                }
            }
        }
        /* Прверка логики волнообразного заолненияя массива
        for (int i = 0; i < m.length; i++) {
            System.out.println("");
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] < 10) {
                    System.out.print(" " + m[i][j]);
                } else {
                    System.out.print(m[i][j]);
                }
            }
        }
        */
        System.out.println("");
        for (int i = 0; i < MAZE.length; i++) {
            System.out.println("");
            for (int j = 0; j < MAZE[i].length; j++) {
                if (MAZE[i][j] < 10) {
                    System.out.print(" " + MAZE[i][j]);
                } else {
                    System.out.print(MAZE[i][j]);
                }
            }
        }
        System.out.println("");
    }

    private static int[][] cloneArray(int[][] m) {
        int tempArray[][] = new int[m.length][m[1].length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                tempArray[i][j] = m[i][j];
            }
        }
        return tempArray;
    }

    private static int[] generateStartPoint(int[][] m) {
        int startpoint[] = new int[2];
        while (true) {
            Random rnd = new Random();
            int i = rnd.nextInt(m.length - 1);
            int j = rnd.nextInt(m[0].length - 1);
            if (m[i][j] == 0) {
                startpoint[0] = i;
                startpoint[1] = j;
                break;
            }
        }
        return startpoint;
    }

    private static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

    private static void checkExits(int[][] m) {
        int check = 0;
        for (int i = 0, j = 0; i < m.length; i++) {
            if (m[i][j] > 1) {
                check++;
                if (m[i][j] < smallest) {
                    smallest_exit_i = i;
                    smallest_exit_j = j;
                    smallest = m[i][j];
                }
            }
        }
        for (int i = 0, j = m[i].length - 1; i < m.length; i++) {
            if (m[i][j] > 1) {
                check++;
                if (m[i][j] < smallest) {
                    smallest_exit_i = i;
                    smallest_exit_j = j;
                    smallest = m[i][j];
                }
            }
        }
        for (int i = 0, j = 0; j < m[i].length; j++) {
            if (m[i][j] > 1) {
                check++;
                if (m[i][j] < smallest) {
                    smallest_exit_i = i;
                    smallest_exit_j = j;
                    smallest = m[i][j];
                }
            }
        }
        for (int i = m.length - 1, j = 0; j < m[i].length; j++) {
            if (m[i][j] > 1) {
                check++;
                if (m[i][j] < smallest) {
                    smallest_exit_i = i;
                    smallest_exit_j = j;
                    smallest = m[i][j];
                }
            }
        }
        if (check == 0) {
            System.out.println("");
            System.out.println("Из лабиринта нет выхода");
            System.exit(0);
        }
    }

    private static int[][] ShortestRoad(int[][] m, int i, int j) {
        int[][] backroad = new int[m.length * m[0].length][m.length * m[0].length];
        int count = 0, cor = 0, wave = 2;
        while (count < 1) {
            smallest--;
            if (i + 1 < m.length && m[i + 1][j] == smallest) {
                counter++;
                backroad[cor][0] = i;
                backroad[cor][1] = j;
                cor++;
                i++;
                if (m[i][j] == wave) {
                    count++;
                }
            }
            if (i - 1 >= 0 && m[i - 1][j] == smallest) {
                counter++;
                backroad[cor][0] = i;
                backroad[cor][1] = j;
                cor++;
                i--;
                if (m[i][j] == wave) {
                    count++;
                }
            }
            if (j + 1 < m[i].length && m[i][j + 1] == smallest) {
                counter++;
                backroad[cor][0] = i;
                backroad[cor][1] = j;
                cor++;
                j++;
                if (m[i][j] == wave) {
                    count++;
                }
            }
            if (j - 1 >= 0 && m[i][j - 1] == smallest) {
                counter++;
                backroad[cor][0] = i;
                backroad[cor][1] = j;
                cor++;
                j--;
                if (m[i][j] == wave) {
                    count++;
                }
            }

        }
        return backroad;
    }
}
