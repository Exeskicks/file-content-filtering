package danil.yudin;

import danil.yudin.arguments.ArgumentsModel;
import danil.yudin.arguments.JVMArguments;
import danil.yudin.file.FileWriterExample;
import danil.yudin.file.ReadFile;
import danil.yudin.service.FileProcessingService;
import danil.yudin.statistic.Statistic;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ApplicationConsole {


    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(
                    ApplicationConsole.class.getClassLoader().getResourceAsStream("logging.properties")
            );
        } catch (Exception e) {
            System.err.println("Не удалось загрузить logging.properties: " + e.getMessage());
        }

        // Parse arguments
        final JVMArguments jvmArguments = new JVMArguments(args);
        final ArgumentsModel arguments = jvmArguments.getArguments();

        // Create dependencies
        ReadFile readFile = new ReadFile();
        FileWriterExample writeFile = new FileWriterExample();
        Statistic statistic = new Statistic();

        // Inject dependencies into the service
        FileProcessingService fileProcessingService = new FileProcessingService(readFile, writeFile, statistic);

        // Process files
        fileProcessingService.processFiles(arguments);
    }

}