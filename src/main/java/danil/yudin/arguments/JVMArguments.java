package danil.yudin.arguments;

import java.util.*;
import java.util.function.Consumer;


public class JVMArguments {
    private static final String appendMode = "-a";
    private static final String prefix = "-p";
    private static final String outputPath = "-o";
    private static final String shortStatistic = "-s";
    private static final String fullStatistic = "-f";

    private final Map<String, Consumer<String>> argumentHandlers = new HashMap<>();
    private final ArgumentsModel.Builder argumentsBuilder = new ArgumentsModel.Builder();
    private final List<String> inputFiles = new ArrayList<>();

    private final String[] args;

    public JVMArguments(String[] args) {
        this.args = args;
        argumentHandlers.put(appendMode, value -> argumentsBuilder.setAppendMode(true)); // Флаг
        argumentHandlers.put(prefix, value -> argumentsBuilder.setPrefix(value)); // Аргумент с значением
        argumentHandlers.put(outputPath, value -> argumentsBuilder.setOutputPath(value)); // Аргумент с значением
        argumentHandlers.put(shortStatistic, value -> argumentsBuilder.setStatistics("short")); // Флаг
        argumentHandlers.put(fullStatistic, value -> argumentsBuilder.setStatistics("full")); // Флаг
    }

    public ArgumentsModel getArguments() {
        for (int i = 0; i < args.length; i++) {
            final String argument = args[i];

            if (argumentHandlers.containsKey(argument)) {
                // Если аргумент является флагом (не требует значения)
                if (argument.equals(appendMode) || argument.equals(shortStatistic) || argument.equals(fullStatistic)) {
                    argumentHandlers.get(argument).accept(null);
                }
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
            System.err.println(e.getMessage());
            return null;
        }
    }

}
