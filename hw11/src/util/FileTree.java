package util;

import java.io.*;
import java.util.Date;

public class FileTree {
    public FileTree(String path, String listOfFiles) throws IOException {
        createListOfFiles(listOfFiles);
        browseFileTreeModifiead(path, -1);
        writeToFile(listOfFilesBuffer);
    }

    public FileTree(String path) throws IOException {
        this(path, path);
    }

    private File logfile;
    private StringBuffer listOfFilesBuffer = new StringBuffer();

    private void createListOfFiles(String logFileDir) throws IOException {
        String listOfFilesPath = logFileDir + "\\ListOfFiles.txt";
        logfile = new File(listOfFilesPath);
        if (logfile.exists()) {
            logfile.delete();
        } else {
            logfile.createNewFile();
        }
    }

    private void browseFileTreeModifiead(String path, int whiteSpaceCount) throws IOException {
        whiteSpaceCount++;
        StringBuilder whiteSpace = new StringBuilder("");
        for (int i = 0; i < whiteSpaceCount; i++) {
            whiteSpace.append("  ");
        }
        File file = new File(path);
        File[] a = file.listFiles();
        String prefix = "[]";
        if (a != null) {
            for (File s : a) {
                Date last_Modified = new Date(s.lastModified());
                if (s.isFile()) {
                    prefix = "[FILE] ";
                }
                if (s.isDirectory()) {
                    prefix = "[DIR] ";
                }
                listOfFilesBuffer.append("\r\n" + whiteSpace + prefix + "" + s.getName() + " :    " + last_Modified);
                browseFileTreeModifiead(s.getPath(), whiteSpaceCount);
                if (listOfFilesBuffer.length() > 40000) {
                    writeToFile(listOfFilesBuffer);
                    listOfFilesBuffer.delete(0, listOfFilesBuffer.length());
                }
            }
        }
    }

    private void writeToFile(StringBuffer listOfFilesBuffer) throws IOException {
        FileWriter writer = null;
        try {
            writer = new FileWriter(logfile, true);
        } catch (IOException e) {
            throw new IOException("Не могу записать в лог файл");
        }
        try {
            writer.write(listOfFilesBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
