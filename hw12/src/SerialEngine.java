import Airplanes.AutonomusMilitaryAircraft;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class SerialEngine {

    private String path;
    private ArrayList <AutonomusMilitaryAircraft> list = new ArrayList<>();
    private Random rnd = new Random();
    File text_file;

    public ArrayList<AutonomusMilitaryAircraft> getList() {
        return list;
    }

    public SerialEngine(String path) {
        this.path = path;
        text_file = new File(path);
        run();
    }

    private void run() {
        if (text_file.exists() && text_file.canRead() && text_file.length() != 0) {
            readFromFile();
        } else {
            writeInFile();
        }
    }

    private void readFromFile() {
        try {
            openReadStreamFromFile();
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Wrong file or corrupted");
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not exist or some by protected");
        }
    }

    private void writeInFile() {
        generateAirplanes(5);
        if (text_file.exists() && !text_file.canWrite()) {
            text_file.delete();
        }
        if (!text_file.exists()) {
            try {
                createFile();
            } catch (IOException e) {
                throw new IllegalArgumentException("Can't create new file, you'r may have no permition");
            }
        }
        openWriteStreamInFile();
    }

    private void createFile() throws IOException {
        text_file.createNewFile();
    }

    private void generateAirplanes(int number) {
        for (int i = 0; i < number; i++) {
            list.add(new AutonomusMilitaryAircraft(rnd.nextInt(10), rnd.nextInt(10), rnd.nextInt(10), rnd.nextInt(10), rnd.nextInt(10)));
        }
    }

    private void openReadStreamFromFile() throws ClassNotFoundException, FileNotFoundException {
        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (true) {
                try {
                    AutonomusMilitaryAircraft aircraft = (AutonomusMilitaryAircraft) ois.readObject();
                    list.add(aircraft);
                } catch (EOFException e) {
                    break;
                }
            }

        } catch (IOException e) {
            throw new FileNotFoundException("File not found in reading");
        }
    }

    private void openWriteStreamInFile() {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (Object a : list) {
                oos.writeObject(a);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("File not found in writing");
        }

    }

}
