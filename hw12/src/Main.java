/*
Реализовать класс-оболочку для просмотра текстового файла. В конструктор класса должно передаваться имя файла.
Реализовать возможность построчного просмотра содержимого файла циклом for.
 */

import Airplanes.AutonomusMilitaryAircraft;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        //textReader();
        task2();

    }

    private static void textReader() {
        MyTextFileReader s = new MyTextFileReader("C:\\Program Files\\ListOfFiles.txt", 5, 15);
        for (String read : s) {
            System.out.println(read);
        }
    }

    private static void task2() throws ClassNotFoundException {

        SerialEngine test = new SerialEngine("D:\\testObjectToFile.txt");
        //for test
        for(AutonomusMilitaryAircraft o:test.getList()){
            System.out.println(o.getSpeed()+" "+o.getVektor()+" "+o.hashCode());
        }
    }
}
