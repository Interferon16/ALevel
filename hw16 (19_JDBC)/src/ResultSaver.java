import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResultSaver {

    private String result;
    private String generated_name;
    Path path;

    public ResultSaver (String path,String result){
        genName();
        this.path = Paths.get(path,generated_name);
        this.result=result;
        run();
    }

    private void genName(){
        generated_name=System.currentTimeMillis()+".csv";
    }

    private void run(){
        createFile();
        writeInFile();
    }

    private void createFile(){
        try {
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeInFile(){
        try(BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"));) {
            writer.write(result);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Path getFileName() {
        return path.getFileName();
    }
}
