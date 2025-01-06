package danil.yudin.arguments;

import java.util.ArrayList;
import java.util.List;

public class ArgumentsModel {
    private boolean appendMode;
    private String prefix;
    private String outputPath;
    private String statistics;
    private List<String> inputFiles;

    private ArgumentsModel(Builder builder) {
        this.appendMode = builder.appendMode;
        this.prefix = builder.prefix;
        this.outputPath = builder.outputPath;
        this.statistics = builder.statistics;
        this.inputFiles = builder.inputFiles;
    }

    public boolean isAppendMode() {
        return appendMode;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public String getStatistics() {
        return statistics;
    }

    public List<String> getInputFiles() {
        return inputFiles;
    }

    public static class Builder {
        private boolean appendMode = false;
        private String prefix;
        private String outputPath;
        private String statistics;
        private List<String> inputFiles = new ArrayList<>();

        public Builder setAppendMode(boolean appendMode) {
            this.appendMode = appendMode;
            return this;
        }

        public Builder setPrefix(String prefix) {
            this.prefix = prefix;
            return this;
        }

        public Builder setOutputPath(String outputPath) {
            this.outputPath = outputPath;
            return this;
        }

        public Builder setStatistics(String statistics) {
            this.statistics = statistics;
            return this;
        }

        public Builder setInputFiles(List<String> inputFiles) {
            this.inputFiles = inputFiles;
            return this;
        }

        public ArgumentsModel build() {
            return new ArgumentsModel(this);
        }
    }

    @Override
    public String toString() {
        return "ArgumentsModel{" +
                "appendMode=" + appendMode +
                ", prefix='" + prefix + '\'' +
                ", outputPath='" + outputPath + '\'' +
                ", statistics='" + statistics + '\'' +
                ", inputFiles=" + inputFiles +
                '}';
    }
}