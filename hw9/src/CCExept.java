public class CCExept {

    private static Object numbers = new Integer(10000);
    private static Object array[] = new Object[100];
    private static Object a = array[1];



    public static void main(String[] args) {
        try {
            System.out.println((String) numbers);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(a.toString());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}