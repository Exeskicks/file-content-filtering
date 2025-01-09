package danil.yudin.service;

import danil.yudin.arguments.ArgumentsModel;
import danil.yudin.file.FileWriterExample;
import danil.yudin.file.ReadFile;
import danil.yudin.statistic.Statistic;

import java.util.logging.Logger;

public class FileProcessingService {

    /**
     * Логирование
     */
    private static Logger log = Logger.getLogger(ReadFile.class.getName());

    /**
     * Чтение файлов
     */
    private final ReadFile readFile;
    /**
     * Запись файлов
     */
    private final FileWriterExample writeFile;
    /**
     * Статистика файлов
     */
    private final Statistic statistic;

    public FileProcessingService(ReadFile readFile, FileWriterExample writeFile, Statistic statistic) {
        this.readFile = readFile;
        this.writeFile = writeFile;
        this.statistic = statistic;
    }

    public void processFiles(ArgumentsModel arguments) {
        if (arguments == null) {
            log.info("Failed to process arguments.");
            return;
        }

        log.info("Arguments processed successfully:");
        log.info(arguments.toString());

        // Read files
        readFile.readFiles(arguments.getInputFiles());

        // Write files
        writeFile.writeFile(
                readFile.getStringList(),
                readFile.getIntegerList(),
                readFile.getFloatList(),
                arguments.getOutputPath(),
                arguments.getPrefix(),
                arguments.isAppendMode()
        );

        // Generate statistics
        switch (arguments.getStatistics()) {
            case "short":
                statistic.shotsStatic(
                        readFile.getStringList(),
                        readFile.getIntegerList(),
                        readFile.getFloatList());
                break;
            case "full":
                statistic.fullStatic(
                        readFile.getStringList(),
                        readFile.getIntegerList(),
                        readFile.getFloatList());
                break;
            default:
                log.info("No valid statistics option provided.");
        }
    }
}