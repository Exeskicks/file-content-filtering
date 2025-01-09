package danil.yudin.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс запись файла со свойствами <b>nameForInteger</b>, <b>nameForFloat</b> и <b>nameForString</b>.
 * @author Данил Юдин
 * @version 1
 */
public class FileWriterExample {


    /**
     * Логирование
     */
    private static Logger log = Logger.getLogger(FileWriterExample.class.getName());

    /** Имя по умолчанию для целых чисел */
    private final String nameForInteger = "integers.txt";
    /** Имя по умолчанию для чисел с плавающей точкой */
    private final String nameForFloat = "floats.txt";
    /** Имя по умолчанию для строк */
    private final String nameForString = "strings.txt";


    /**
     * Метод - запись файла
     * @param stringList - список строк
     * @param integerList - список целых чисел
     * @param floatList - список чисел с плавающей точкой
     * @param out - путь записи файлов
     * @param prefix - префикс файлов
     * @param appendMode - дополнение в существующие файлы
     */

    public void writeFile(
            List<String> stringList,
            List<Integer> integerList,
            List<Float> floatList,
            String out,
            String prefix,
            boolean appendMode

    ) {

        String integerFile = constructFilePath(out, prefix, nameForInteger);
        String floatFile = constructFilePath(out, prefix, nameForFloat);
        String stringFile = constructFilePath(out, prefix, nameForString);

        if (!stringList.isEmpty()) {
            writeToFile(stringList, stringFile, appendMode);
        }

        if (!integerList.isEmpty()) {
            writeToFile(integerList, integerFile, appendMode);
        }
        if (!floatList.isEmpty()) {
            writeToFile(floatList, floatFile, appendMode);
        }

    }

    /**
     * Метод - создание полного пути
     * @param out - путь записи файлов
     * @param prefix - префикс файлов
     * @param fileName - имя файла
     */
    private String constructFilePath(String out, String prefix, String fileName) {

        String finalFileName = (prefix != null ? prefix : "") + fileName;

        return (out != null ? out + "/" + finalFileName : finalFileName);

    }


    /**

     * Метод - запись в файл
     * @param list - список для записи
     * @param filePath - полный путь
     * @param appendMode - дополнение в существующие файлы
     */
    private <T> void writeToFile(List<T> list, String filePath, boolean appendMode) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, appendMode))) {
            for (T item : list) {
                if (item == null) {
                    log.warning("Attempting to write a null item to file " + filePath);
                    continue; // Пропускаем null элементы
                }
                bw.write(item.toString());
                bw.newLine();
            }
            log.info("File " + filePath + " written successfully! Mode: "
                    + (appendMode ? "Append" : "Overwrite") + ". Total lines written: " + list.size());
        } catch (IOException ex) {
            log.log(Level.SEVERE, "Error writing to file " + filePath + ": " + ex.getMessage(), ex);
        } catch (Exception ex) {
            log.log(Level.SEVERE, "Unexpected error while writing to file " + filePath + ": " + ex.getMessage(), ex);
        }

    }
}
