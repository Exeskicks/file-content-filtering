package danil.yudin.statistic;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class Statistic {

    /**
     * Логирование
     */
    private static final Logger log = Logger.getLogger(Statistic.class.getName());

    /**
     * Метод для короткой статистики
     *
     * @param stringList  - список строк
     * @param integerList - для целых чисел
     * @param floatList   - список чисел с плавающей точкой
     */
    public void shotsStatic(List<String> stringList, List<Integer> integerList, List<Float> floatList) {
        printListSize("Integer", integerList.size());
        printListSize("Float", floatList.size());
        printListSize("String", stringList.size());
    }

    /**
     * Метод для полной статистики
     *
     * @param stringList  - список строк
     * @param integerList - для целых чисел
     * @param floatList   - список чисел с плавающей точкой
     */
    public void fullStatic(List<String> stringList, List<Integer> integerList, List<Float> floatList) {
        processIntegerList(integerList);
        processFloatList(floatList);
        processStringList(stringList);
    }

    /**
     * Метод для статистики целых чисел
     *
     * @param integerList - для целых чисел
     */
    private void processIntegerList(List<Integer> integerList) {
        if (integerList.isEmpty()) {
            log.warning("Integer list is empty");
            return;
        }

        printListSize("Integer", integerList.size());

        printMinValue("Integer", integerList.stream().min(Comparator.naturalOrder()));
        printMaxValue("Integer", integerList.stream().max(Comparator.naturalOrder()));

        int sumInt = integerList.stream()
                .mapToInt(Integer::intValue)
                .sum();

        printSum("Integer", sumInt);
    }

    /**
     * Метод для статистики чисел с плавающей точкой
     *
     * @param floatList - чисел с плавающей точкой
     */
    private void processFloatList(List<Float> floatList) {
        if (floatList.isEmpty()) {
            log.warning("Float list is empty");
            return;
        }

        printListSize("Float", floatList.size());

        printMinValue("Float", floatList.stream().min(Comparator.naturalOrder()));
        printMaxValue("Float", floatList.stream().max(Comparator.naturalOrder()));

        double sumFloat = floatList.stream()
                .mapToDouble(Float::floatValue)
                .sum();

        printSum("Float", sumFloat);
    }

    /**
     * Метод для статистики чисел с плавающей точкой
     *
     * @param stringList - список строк
     */
    private void processStringList(List<String> stringList) {
        if (stringList.isEmpty()) {
            log.warning("String list is empty");
            return;
        }

        printListSize("String", stringList.size());

        printMaxValue("Longest String", stringList.stream().max(Comparator.comparingInt(String::length)));
        printMinValue("Shortest String", stringList.stream().min(Comparator.comparingInt(String::length)));
    }

    /**
     * Метод размер списка
     *
     * @param listType - список
     * @size размер
     */
    private void printListSize(String listType, int size) {
        log.info(String.format("%s number of items: %d", listType, size));
    }


    /**
     * Метод минимальное значение
     *
     * @param valueType   - тип
     * @param minOptional - минимум
     */
    private <T> void printMinValue(String valueType, Optional<T> minOptional) {
        minOptional.ifPresent(min -> log.info(String.format("%s min: %s", valueType, min)));
    }

    /**
     * Метод максимального значение
     *
     * @param valueType   - тип
     * @param maxOptional - максимум
     */
    private <T> void printMaxValue(String valueType, Optional<T> maxOptional) {
        maxOptional.ifPresent(max -> log.info(String.format("%s max: %s", valueType, max)));
    }

    /**
     * Метод суммы
     *
     * @param listType - тип
     * @param sum      - сумма
     */
    private void printSum(String listType, Number sum) {
        log.info(String.format("Sum of %s: %s", listType, sum));
    }
}