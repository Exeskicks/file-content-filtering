package danil.yudin.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileWriterExample {

    final String nameForInteger = "integers.txt";
    final String nameForFloat = "floats.txt";
    final String nameForString = "strings.txt";

    public void writeFile(List<String> stringList, List<Integer> integerList, List<Float> floatList, String out, String prefix, boolean appendMode) {
        // Construct file paths
        String integerFile = constructFilePath(out, prefix, nameForInteger);
        String floatFile = constructFilePath(out, prefix, nameForFloat);
        String stringFile = constructFilePath(out, prefix, nameForString);

        // Write strings to file
        if (!stringList.isEmpty()) {
            writeToFile(stringList, stringFile, appendMode);
        }

        // Write integers to file
        if (!integerList.isEmpty()) {
            writeToFile(integerList, integerFile, appendMode);
        }

        // Write floats to file
        if (!floatList.isEmpty()) {
            writeToFile(floatList, floatFile, appendMode);
        }
    }

    private String constructFilePath(String out, String prefix, String fileName) {
        // Add prefix to file name if provided
        String finalFileName = prefix != null ? prefix + "_" + fileName : fileName;

        // Construct full file path
        return out != null ? out + "/" + finalFileName : finalFileName;
    }

    private <T> void writeToFile(List<T> list, String filePath, boolean appendMode) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, appendMode))) {
            for (T item : list) {
                bw.write(item.toString());
                bw.newLine();
            }
            System.out.println("File " + filePath + " written successfully! Mode: " + (appendMode ? "Append" : "Overwrite"));
        } catch (IOException ex) {
            System.out.println("Error writing to file " + filePath + ": " + ex.getMessage());
        }
    }
}
