package danil.yudin;

import danil.yudin.arguments.ArgumentsModel;
import danil.yudin.arguments.JVMArguments;
import danil.yudin.file.FileWriterExample;
import danil.yudin.file.ReadFile;
import danil.yudin.statistic.Statistic;

public class Main {
    public static void main(String[] args) {

        final JVMArguments jvmArguments = new JVMArguments(args);
        final ArgumentsModel arguments = jvmArguments.getArguments();

        if (arguments != null) {
            System.out.println("Arguments processed successfully:");
            System.out.println(arguments);
        } else {
            System.out.println("Failed to process arguments.");
        }

        final ReadFile readFile = new ReadFile();
        final FileWriterExample writeFile = new FileWriterExample();

        readFile.readFiles(arguments.getInputFiles());
        writeFile.writeFile(
                readFile.getStringList(),
                readFile.getIntegerList(),
                readFile.getFloatListList(),
                arguments.getOutputPath(),
                arguments.getPrefix(),
                arguments.isAppendMode()
        );

        System.out.println();
        final Statistic statistic = new Statistic();

        switch (arguments.getStatistics()) {
            case "short":
                statistic.shotsStatic(
                        readFile.getStringList(),
                        readFile.getIntegerList(),
                        readFile.getFloatListList());
            case "full":
                statistic.fullStatic(
                        readFile.getStringList(),
                        readFile.getIntegerList(),
                        readFile.getFloatListList());
        }
    }

}