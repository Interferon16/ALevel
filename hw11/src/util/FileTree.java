package util;

import java.io.*;
import java.util.Date;

public class FileTree {
    public FileTree(String path, String list_of_files) throws IOException {
        createListOfFiles(list_of_files);
        browseFileTreeModifiead(path, -1);
        writeToFile(list_of_files_buffer);
    }

    public FileTree(String path) throws IOException {
        this(path, path);
    }

    private File list_of_files;
    private StringBuffer list_of_files_buffer = new StringBuffer();

    private void createListOfFiles(String list_of_files_dir) throws IOException {
        String list_of_files_path = list_of_files_dir + "\\ListOfFiles.txt";
        list_of_files = new File(list_of_files_path);
        if (list_of_files.exists()) {
            list_of_files.delete();
        } else {
            list_of_files.createNewFile();
        }
    }

    private void browseFileTreeModifiead(String path, int white_space_count) throws IOException {
        white_space_count++;
        StringBuffer white_space = new StringBuffer("");
        for (int i = 0; i < white_space_count; i++) {
            white_space.append("  ");
        }
        File file = new File(path);
        File[] a = file.listFiles();
        String prefix = "[]";
        if (a != null) {
            for (File s : a) {
                Date last_modified = new Date(s.lastModified());
                if (s.isFile()) {
                    prefix = "[FILE] ";
                }
                if (s.isDirectory()) {
                    prefix = "[DIR] ";
                }
                list_of_files_buffer.append("\r\n" + white_space + prefix + "" + s.getName() + " :    " + last_modified);
                if (list_of_files_buffer.length() > 160000) {
                    writeToFile(list_of_files_buffer);
                    list_of_files_buffer.delete(0, list_of_files_buffer.length());
                }
                browseFileTreeModifiead(s.getPath(), white_space_count);

            }
        }
    }

    private void writeToFile(StringBuffer list_of_files_buffer) throws IOException {
        FileWriter writer = null;
        try {
            writer = new FileWriter(list_of_files, true);
        } catch (IOException e) {
            throw new IOException("Не могу записать в лог файл");
        }
        try {
            writer.write(list_of_files_buffer.toString());
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
