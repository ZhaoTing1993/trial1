package java8nf;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FuncionalInterface {
    public static void main(String[] args) {
        List<Integer> arrs = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        eval(arrs, i -> i % 2 == 0);
        eval(arrs, i -> i % 3 == 0);
        eval(arrs, i -> i % 10 == 0);

        //consumer
        consume("to consume", System.out::println);

        //biFunction 得到两数之和的平方
        int square = biCalculate(3, 4, Integer::sum, r -> r * r);
        consume(String.valueOf(square), System.out::println);


    }

    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        list.forEach(i -> {
            if (predicate.test(i)) {
                System.out.print(i + ",");
            }
        });
        System.out.println();
    }

    public static void consume(String something, Consumer<String> consumer) {
        consumer.accept(something);
    }

    public static int biCalculate(int i, int j, BiFunction<Integer, Integer, Integer> biFunction, Function<Integer, Integer> after) {
        return biFunction.andThen(after).apply(i, j);
    }


}
