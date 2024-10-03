package modules;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileConcatenator {
    public void concatenateFiles(List<Path> sortedFiles, Path outputFile) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(outputFile)) {
            for (Path file : sortedFiles) {
                if (!file.toFile().exists()){
                    throw new IOException("File " + file +" does not exist");
                }
                List<String> lines = Files.readAllLines(file);
                for (String line : lines) {
                    if (!line.startsWith("require")) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
            }
        }

    }
}
