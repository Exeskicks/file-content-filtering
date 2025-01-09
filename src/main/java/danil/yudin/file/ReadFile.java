package danil.yudin.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Класс чтение файла со свойствами <b>stringList</b>, <b>integerList</b> и <b>floatList</b>.
 *
 * @author Данил Юдин
 * @version 1
 */

public class ReadFile {

    /**
     * Логирование
     */
    private static Logger log = Logger.getLogger(ReadFile.class.getName());

    /**
     * Список для строк
     */
    private List<String> stringList = new ArrayList<>();
    /**
     * Список для целых чисел
     */
    private List<Integer> integerList = new ArrayList<>();
    /**
     * Список для чисел с плавающей точкой
     */
    private List<Float> floatList = new ArrayList<>();


    /**
     * Метод получения списка строк {@link ReadFile#stringList}
     *
     * @return возвращает список строк
     */
    public List<String> getStringList() {
        return stringList;
    }

    /**
     * Метод получения списка целых чисел {@link ReadFile#integerList}
     *
     * @return возвращает список целых чисел
     */
    public List<Integer> getIntegerList() {
        return integerList;
    }


    /**
     * Метод получения списка для чисел с плавающей точкой {@link ReadFile#floatList}
     *
     * @return возвращает список чисел с плавающей точкой
     */
    public List<Float> getFloatList() {
        return floatList;
    }


    /**
     * Метод чтения файлов
     *
     * @param filePaths - список путей к файлам
     */
    public void readFiles(List<String> filePaths) {
        if (filePaths == null || filePaths.isEmpty()) {
            log.warning("No file paths provided.");
            return;
        }


        for (String filePath : filePaths) {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    categorizeData(line.trim(), stringList, integerList, floatList);
                }

            } catch (IOException e) {
                log.severe("Error reading the file: " + filePath + ": " + e.getMessage());
            }

        }
    }


    /**
     * Метод для определения типа данных
     *
     * @param data        - строка
     * @param stringList  - для строк
     * @param integerList - для целых чисел
     * @param floatList   - для чисел с плавающей точкой
     */

    private static void categorizeData(
            String data, List<String> stringList, List<Integer> integerList, List<Float> floatList

    ) {
        try {
            Integer intValue = Integer.parseInt(data);
            integerList.add(intValue);
        } catch (NumberFormatException e1) {
            try {
                Float floatValue = Float.parseFloat(data);
                floatList.add(floatValue);
            } catch (NumberFormatException e2) {
                stringList.add(data);
            }
        }

    }

}