package src;

import modules.DependencyParser;
import modules.FileConcatenator;
import modules.FileScanner;
import modules.TopologicalSorter;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/*
* Не знаю, стоит ли писать комментарии к коду и принято ли это, но, пожалуй, напишу
*/

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // Ввод пути к рабочей директории и пути к выходному файлу
        System.out.println("Enter path to root directory:");
        Path rootDirectory = Paths.get(input.nextLine());
        System.out.println("Enter path to output file:");
        Path outputFile = Paths.get(input.nextLine());

        try {
            // Сканирование директорий на наличие файлов
            FileScanner scanner = new FileScanner(rootDirectory);
            List<Path> textFiles = scanner.getAllTextFiles();

            // Парсинг зависимостей в файлах
            DependencyParser parser = new DependencyParser();
            Map<Path, List<Path>> dependencies = parser.parseDependencies(textFiles, rootDirectory);

            // Топологическая сортировка
            TopologicalSorter sorter = new TopologicalSorter();
            List<Path> sortedFiles = sorter.sortFiles(dependencies);

            // Конкатенация файлов
            FileConcatenator concatenator = new FileConcatenator();
            concatenator.concatenateFiles(sortedFiles, outputFile);

            System.out.println("Files has been contacinated successfully!");
            System.out.println("File structure:");
            for (Path file : sortedFiles){
                System.out.println(file);
            }

        } catch (Exception e) { // Вывод сообщений об ошибке
            System.err.println("Error: " + e.getMessage());
            System.out.println("Aborting..");
        }
    }
}
