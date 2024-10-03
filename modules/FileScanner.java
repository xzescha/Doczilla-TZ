package modules;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileScanner {
    private final Path rootDirectory;

    public FileScanner(Path rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public List<Path> getAllTextFiles() throws IOException { // Сканирование файлов в директории и проверка на существование директории
        List<Path> textFiles = new ArrayList<>();
        if (!rootDirectory.toFile().exists()){
            throw new IOException("Directory " + rootDirectory + " does not exist");
        }
        else if (isDirectoryEmpty(rootDirectory.toFile())){
            throw new IOException("Directory " + rootDirectory + " is empty!");
        }

        Files.walk(rootDirectory)
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".txt"))
                .forEach(textFiles::add);
        return textFiles;
    }
    public boolean isDirectoryEmpty(File directory) {
        String[] files = directory.list();
        return files.length == 0;
    }
}
