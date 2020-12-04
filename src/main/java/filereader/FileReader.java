package filereader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public static List<String> readFile(String pathToFile) {
        List<String> file = null;
        try {
            file =  Files.readAllLines(Paths.get(pathToFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static List<String> readWithEmptyLine(String pathToFile) {
        List<String> file = readFile(pathToFile);
        List<String> input = new ArrayList<>();
        String currentString = "";
        for (String s : file) {
            if (s.isEmpty()) {
                input.add(currentString);
                currentString = "";
            } else {
                currentString += s;
                currentString += " ";
            }
        }
        input.add(currentString);
        return input;
    }
}
