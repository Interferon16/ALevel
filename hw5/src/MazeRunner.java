public class MazeRunner {

    public static void run() {
        traedScissors(MAZE);
    }

    public static void run(int[][] i) {
        traedScissors(i);
    }

    private static int[][] MAZE = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 0, 0, 0, 1, 1, 0, 0, 1},
            {1, 0, 0, 1, 0, 0, 0, 0, 1, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 0, 1, 0, 1, 0, 1, 1, 1},
            {1, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 1, 0, 1, 0, 0, 0, 0},
            {1, 1, 0, 1, 0, 0, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    private static void traedScissors(int[][] m) {
        int count = 1;
        while (count > 0) {
            count = 0;
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m[i].length; j++) {
                    int up = 0, right = 0, down = 0, left = 0;
                    if (m[i][j] == 1) {
                        continue;
                    }
                    if ((i - 1) >= 0) {
                        up = m[i - 1][j];
                    }
                    if ((j + 1) < m[i].length) {
                        right = m[i][j + 1];
                    }
                    if ((i + 1) < m.length) {
                        down = m[i + 1][j];
                    }
                    if ((j - 1) >= 0) {
                        left = m[i][j - 1];
                    }
                    if ((up + right + down + left) > 2) {
                        m[i][j] = 1;
                        count++;
                    }
                }
            }
        }
        for (int i = 0; i < m.length; i++) {
            System.out.println("");
            for (int j = 0; j < m[i].length; j++) {
                System.out.print(m[i][j]);
            }
        }
        /*
        int start_i = 0, start_j = 0;
        int exit_i=7,exit_j=9;
        for (int i = 0; i < m.length; i++) {
            if (m[i][0] == 0) {
                start_i = i;
                start_j = 0;
            }
        }
        int finalwalktrought[][] = mouse(m, start_i, start_j, 0,exit_i,exit_j); //Вход в рекурсию поиска короткого пути
        */
    }
/*
    private static int[][] mouse(int[][] way, int i, int j, int count, int exit_i, int exit_j) {
        int walktrought[][] = new int[100][100];
        int steps = 1;
        int up, right, down, left;
        while (steps > 0) {
            steps=0;
            up = 1;
            right = 1;
            down = 1;
            left = 1;
            count++;
            if ((i - 1) >= 0) {
                up = way[i - 1][j];
                if (up == 0) {
                    walktrought[0][count]=i-1;
                    walktrought[1][count]=j;
                    steps++;
                }
            }
            if ((j + 1) < way[i].length) {
                right = way[i][j + 1];
                if(up!=0&&right==0){
                    walktrought[0][count]=i;
                    walktrought[1][count]=j+1;
                    steps++;
                }
                if(up==0&&right==0){
                    mouse(way,i,j+1,count,exit_i,exit_j);
                }
            }
            if ((i + 1) < way.length) {
                down = way[i + 1][j];
                if(up!=0&&right!=0&&down==0){
                    walktrought[0][count]=i+1;
                    walktrought[1][count]=j;
                    steps++;
                }
                if(up==0||right==0&&down==0){
                    mouse(way,i+1,j,count,exit_i,exit_j);
                }
            }
            if ((j - 1) >= 0) {
                left = way[i][j - 1];
                if(up!=0&&right!=0&&down==0){
                    walktrought[0][count]=i;
                    walktrought[1][count]=j-1;
                    steps++;
                }
                if(up==0||right==0||down==0&&left==0){
                    mouse(way,i,j-1,count,exit_i,exit_j);
                }
            }

        }
        return walktrought;
    }
    */
}
