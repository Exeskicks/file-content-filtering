package danil.yudin.statistic;

import java.util.Comparator;
import java.util.List;

public class Statistic {

    public void shotsStatic(List<String> stringList, List<Integer> integerList, List<Float> floatList) {
        printListSize("Integer", integerList.size());
        printListSize("Float", floatList.size());
        printListSize("String", stringList.size());
    }

    public void fullStatic(List<String> stringList, List<Integer> integerList, List<Float> floatList) {
        processIntegerList(integerList);
        processFloatList(floatList);

        processStringList(stringList);
    }

    private void processIntegerList(List<Integer> integerList) {
        printListSize("Integer", integerList.size());

        printMinValue("Integer", integerList.stream().min(Comparator.naturalOrder()));
        printMaxValue("Integer", integerList.stream().max(Comparator.naturalOrder()));

        int sumInt = integerList.stream()
                .mapToInt(Integer::intValue)
                .sum();

        printSum("Integer", sumInt);
    }

    private void processFloatList(List<Float> floatList) {
        printListSize("Float", floatList.size());

        printMinValue("Float", floatList.stream().min(Comparator.naturalOrder()));
        printMaxValue("Float", floatList.stream().max(Comparator.naturalOrder()));

        double sumFloat = floatList.stream()
                .mapToDouble(Float::floatValue)
                .sum();

        printSum("Float", sumFloat);
    }

    private void processStringList(List<String> stringList) {
        printListSize("String", stringList.size());

        printMaxValue("Longest String", stringList.stream().max(Comparator.comparingInt(String::length)));
        printMinValue("Shortest String", stringList.stream().min(Comparator.comparingInt(String::length)));
    }


    private void printListSize(String listType, int size) {
        System.out.println(listType + " number of items: " + size);
    }

    private <T> void printMinValue(String valueType, java.util.Optional<T> minOptional) {
        minOptional.ifPresent(min -> System.out.println(valueType + " min: " + min));
    }

    private <T> void printMaxValue(String valueType, java.util.Optional<T> maxOptional) {
        maxOptional.ifPresent(max -> System.out.println(valueType + " max: " + max));
    }

    private void printSum(String listType, Number sum) {
        System.out.println("Sum of " + listType + ": " + sum);
    }

}
