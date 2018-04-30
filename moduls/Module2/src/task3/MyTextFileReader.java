package task3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;


public class MyTextFileReader implements Iterable<String> {

    private String path;
    private int buffersize = 8 * 1024;
    private int start_line = 1;
    private int numbers_of_line = -1;
    private int check_lines = 0;

    public MyTextFileReader(String path) {
        this.path = path;
    }

    public MyTextFileReader(String path, int numbers_of_line) {
        this(path);
        if (numbers_of_line < 1) {
            this.numbers_of_line = -1;
        } else {
            this.numbers_of_line = numbers_of_line;
        }
    }

    public MyTextFileReader(String path, int start_line, int numbers_of_line) {
        this(path, numbers_of_line);
        if (start_line < 1) {
            this.start_line = 1;
        } else {
            this.start_line = start_line;
        }
        if (this.start_line > this.numbers_of_line) {
            int temp = this.start_line;
            this.numbers_of_line = this.start_line;
            this.start_line = temp;
        }
    }

    public void setBuffersize(int buffersize) {
        if (buffersize < 1) {
            this.buffersize = 8 * 1024;
        } else {
            this.buffersize = buffersize;
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new MyTextFileReaderIterator(start_line);
    }

    private class MyTextFileReaderIterator implements Iterator<String> {

        String nextline;
        BufferedReader buffer;


        private MyTextFileReaderIterator() {
            try {
                buffer = new BufferedReader(new FileReader(path), buffersize);
                nextline = buffer.readLine();
            } catch (IOException e) {
                throw new IllegalArgumentException("File empty or no exist");
            }
        }

        private MyTextFileReaderIterator(int start_line) {
            this();
            for (int i = 1; i < start_line; i++) {
                if (hasNext()) {
                    next();
                    check_lines--;
                }
            }
        }

        @Override
        public boolean hasNext() {
            if (numbers_of_line <= check_lines & numbers_of_line != -1) {
                return false;
            }
            return nextline != null;
        }

        @Override
        public String next() {
            try {
                String result = nextline;
                if (hasNext()) {
                    nextline = buffer.readLine();
                } else {
                    buffer.close();
                }
                check_lines++;
                return result;
            } catch (IOException e) {
                throw new IllegalArgumentException("File empty or no exist");
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not alowed to remove anything from file. File READONLY");
        }
    }

}
