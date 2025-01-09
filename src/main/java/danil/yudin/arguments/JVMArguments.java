package danil.yudin.arguments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Logger;


/**
 * Класс для обработки аргументов командной строки и их преобразования в объект ArgumentsModel.
 */
public class JVMArguments {

    private static final Logger log = Logger.getLogger(JVMArguments.class.getName());

    /** Флаг для дополнения файлы */
    private static final String appendMode = "-a";
    /** Флаг для префикса */
    private static final String prefix = "-p";
    /** Флаг для вывода файла */
    private static final String outputPath = "-o";
    /** Флаг для короткой статистики файла */
    private static final String shortStatistic = "-s";
    /** Флаг для короткой полной файла */
    private static final String fullStatistic = "-f";

    /** Мапа для хранения обработчиков аргументов */
    private final Map<String, Consumer<String>> argumentHandlers = new HashMap<>();
    /**  Объект Builder для создания экземпляра {@link ArgumentsModel}. */
    private final ArgumentsModel.Builder argumentsBuilder = new ArgumentsModel.Builder();
    /** Список путей к файлам, переданных в качестве аргументов командной строки */
    private final List<String> inputFiles = new ArrayList<>();

    private final String[] args;

    /**
     * Конструктор класса.
     *
     * @param args Аргументы командной строки.
     */
    public JVMArguments(String[] args) {
        this.args = args;
        argumentHandlers.put(appendMode, value -> argumentsBuilder.setAppendMode(true));
        argumentHandlers.put(prefix, value -> argumentsBuilder.setPrefix(value));
        argumentHandlers.put(outputPath, value -> argumentsBuilder.setOutputPath(value));
        argumentHandlers.put(shortStatistic, value -> argumentsBuilder.setStatistics("short"));
        argumentHandlers.put(fullStatistic, value -> argumentsBuilder.setStatistics("full"));
    }

    /**
     * Метод для получения объекта ArgumentsModel на основе переданных аргументов.
     *
     * @return Объект ArgumentsModel или null, если произошла ошибка.
     */
    public ArgumentsModel getArguments() {
        if (args.length == 0) {
            log.warning("No arguments provided.");
        }

        for (int i = 0; i < args.length; i++) {
            final String argument = args[i];

            if (argumentHandlers.containsKey(argument)) {
                // Если аргумент является флагом (не требует значения)
                if (argument.equals(appendMode) || argument.equals(shortStatistic) || argument.equals(fullStatistic)) {
                    argumentHandlers.get(argument).accept(null);
                }
                // Если аргумент требует значения
                else if (i + 1 < args.length && !argumentHandlers.containsKey(args[i + 1])) {
                    argumentHandlers.get(argument).accept(args[i + 1]);
                    i++;
                } else {
                    throw new IllegalArgumentException("Argument " + argument + " is missing a value");
                }
            } else {
                inputFiles.add(argument);
            }
        }

        argumentsBuilder.setInputFiles(inputFiles);

        try {
            return argumentsBuilder.build();
        } catch (IllegalArgumentException e) {
            log.severe("Error while building ArgumentsModel: " + e.getMessage());
            return null;
        }
    }
}
