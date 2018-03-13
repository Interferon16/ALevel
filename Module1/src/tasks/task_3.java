package tasks;

public class Task_3 {

    private static int[]temp_array;
    private static int checker;
    private static int temp_array_iterrator=0;

    public static void run(int[]array){
        temp_array=new int[array.length];
        for(int i=0;i<array.length;i++){
            if(checkTempArray(array[i])){
                checker++;
                temp_array[temp_array_iterrator++]=array[i];
            }
        }
        System.out.println("Количество различных элеменов массива - "+checker);
    }
    private static boolean checkTempArray(int check_num){
        for(int i =0;i<temp_array.length;i++){
            if(check_num==temp_array[i]){
                return false;
            }
        }
        return true;
    }
}
