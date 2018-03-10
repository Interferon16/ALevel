public class NPE {

    private static Object array[]=new Object[100];
    private static Object a = array[1];

    public static void main(String[] args) {

        System.out.println(a.toString());

    }
}
