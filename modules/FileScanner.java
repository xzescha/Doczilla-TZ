package modules;

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

    public List<Path> getAllTextFiles() throws IOException {
        List<Path> textFiles = new ArrayList<>();
        Files.walk(rootDirectory)
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".txt"))
                .forEach(textFiles::add);
        return textFiles;
    }
}
