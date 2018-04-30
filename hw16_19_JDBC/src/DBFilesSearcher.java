import java.io.File;
import java.io.FilenameFilter;

public class DBFilesSearcher {
    private final String PATH;
    private File dbpath;
    private String[] csv_files_path;
    private String[] index_files_path;


    public DBFilesSearcher(String PATH) {
        this.PATH = PATH;
        this.dbpath = new File(PATH);
        if (!dbpath.isDirectory()) {
            System.out.println("Path is not directory");
            System.exit(0);
        }
        run();
    }

    private void run() {
        index_files_path = findFileByMask("index.txt");
        csv_files_path = findFileByMask(".csv");
    }


    private String[] findFileByMask(String filter) {
        String[] files_paths = dbpath.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(filter);
            }

        });
        return files_paths;
    }

    public String[] getCsv_files_path() {
        return csv_files_path;
    }

    public String[] getIndex_files_path() {
        return index_files_path;
    }
}
