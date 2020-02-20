package java8nf;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FuncionalInterface {
    public static void main(String[] args) {
        List<Integer> arrs = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        eval(arrs, i -> i % 2 == 0);
        eval(arrs, i -> i % 3 == 0);
        eval(arrs, i -> i % 10 == 0);
    }

    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        list.forEach(i -> {
            if (predicate.test(i)) {
                System.out.print(i + ",");
            }
        });
        System.out.println();
    }
}
