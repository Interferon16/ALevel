package task1;

public class TestNewArrayList {

    public static void run () {
        NewArrayList<String> test = new NewArrayList<>();

        System.out.println("start length - "+test.length());
        System.out.println("start size - "+test.size());
        test.addElement("1");
        test.addElement("2");
        test.add("3",2);
        test.add("5",3);
        test.add("4",3);
        test.addElement("6");
        test.addElement("7");
        test.addElement("8");
        System.out.println("test array growth, new length - "+test.length());
        test.addElement("9");
        test.addElement("10");

        String rem = test.remove(8);

        for(int i = 0;i<test.length();i++){
            System.out.println(test.get(i));
        }
        System.out.println("remove element - "+rem);
    }

}
