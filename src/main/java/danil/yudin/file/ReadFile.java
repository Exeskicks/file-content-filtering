package danil.yudin.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    private List<String> stringList = new ArrayList<>();
    private List<Integer> integerList = new ArrayList<>();
    private List<Float> floatList = new ArrayList<>();

    public List<String> getStringList() {
        return stringList;
    }

    public List<Integer> getIntegerList() {
        return integerList;
    }

    public List<Float> getFloatListList() {
        return floatList;
    }

    public void readFiles(List<String> filePaths) {
        for (String filePath : filePaths) {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    categorizeData(line.trim(), stringList, integerList, floatList);
                }
            } catch (IOException e) {
                System.err.println("Error reading the file: " + filePath + ": " + e.getMessage());
            }
        }
    }

    private static void categorizeData(String data, List<String> stringList, List<Integer> integerList, List<Float> doubleList) {
        try {
            Integer intValue = Integer.parseInt(data);
            integerList.add(intValue);
        } catch (NumberFormatException e1) {
            try {
                Float floatValue = Float.parseFloat(data);
                doubleList.add(floatValue);
            } catch (NumberFormatException e2) {
                stringList.add(data);
            }
        }
    }
}
