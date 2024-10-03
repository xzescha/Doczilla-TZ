package modules;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class TopologicalSorter {
    public List<Path> sortFiles(Map<Path, List<Path>> dependencies) throws Exception {
        List<Path> sortedFiles = new ArrayList<>();
        Set<Path> visited = new HashSet<>();
        Set<Path> stack = new HashSet<>();

        for (Path file : dependencies.keySet()) { // Инициация поиска и обработка циклической зависимости
            if (!visited.contains(file)) {
                if (dfs(file, dependencies, visited, stack, sortedFiles)) {
                    throw new IOException("Cycle dependency has been found");
                }
            }
        }


        return sortedFiles;
    }

    private boolean dfs(Path file, Map<Path, List<Path>> dependencies, Set<Path> visited, Set<Path> stack, List<Path> sortedFiles) {
        visited.add(file);
        stack.add(file);

        for (Path requiredFile : dependencies.getOrDefault(file, Collections.emptyList())) {
            if (stack.contains(requiredFile)) {
                System.out.println("Cycle dependency based on: " + requiredFile);  // Логика поиска циклической зависимости и первичная обработка ее
                return true; // Цикл обнаружен
            }
            if (!visited.contains(requiredFile)) {
                if (dfs(requiredFile, dependencies, visited, stack, sortedFiles)) {
                    return true;
                }
            }
        }

        stack.remove(file); // Если зависимость не циклическая, файл добавляется в готовую отсортированную последовательность
        sortedFiles.add(file);
        return false;
    }
}
