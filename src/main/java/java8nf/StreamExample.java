package java8nf;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamExample {
    static List<String> strings = Arrays.asList("fhgwe", "fdkh", "2tbb", "hihb", "huebw", "vbxg", "", " ");
    static List<Integer> integers = Arrays.asList(14, 5, 56, 6, 1, 6, 3, 25);


    /**
     * 用于过滤，返回判断条件为true的元素到流中
     */
    public static void filter() {
        List<String> filtered = strings.stream().filter(s -> StringUtils.isNotBlank(s)).collect(Collectors.toList());
        filtered.forEach(System.out::println);
        System.out.println(filtered.stream().count());
    }

    public static void main(String[] args) {
        collectors();
    }

    /**
     * limit 用于获取指定数量的流
     * forEach 用于迭代
     */
    public static void forEach() {
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
    }

    /**
     * 返回的结果会映射到流中的元素
     */
    public static void map() {
        List<Integer> integers = Arrays.asList(1, 12, 5, 6, 78, 3, 673, 37, 7, 37, 3);
        List<Integer> squaredList = integers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        squaredList.forEach(System.out::println);
        integers.forEach(System.out::println);
    }

    /**
     * 默认升序
     */
    public static void sorted() {
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);
    }

    /**
     * 并行处理流，同时处理流中的所有元素
     */
    public static void parallel() {
        long count = integers.parallelStream().filter(integer -> integer > 5).count();
        System.out.println(count);
    }

    /**
     * Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串
     */
    public static void collectors() {
        String joining = strings.stream().filter(StringUtils::isNotBlank).collect(Collectors.joining(","));
        System.out.println(joining);
    }

    public static void statistic() {
        IntSummaryStatistics stats = integers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }

    public static void reduce() {
        //reduce 方法接收 binaryOperator ，binaryOperator 有两个参数一个返回值，返回值会作为下一次累加器计算的第一个参数。
        String s = strings.stream().reduce((acc, item) -> acc += item).get();
        System.out.println(s);
        //identity 累加器初始值
        Integer r = integers.stream().reduce(100, (sum, i) -> sum += i);
        System.out.println(r);
    }


}
