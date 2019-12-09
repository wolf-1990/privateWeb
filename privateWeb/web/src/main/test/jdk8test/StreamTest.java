package jdk8test;


import org.junit.Test;

import java.util.stream.Stream;

/**
 * @Description TOO
 * @Author 赵赫智
 * @Date 2019/10/14 11:04
 **/
public class StreamTest {
    @Test
    public void test1(){
//        Stream.iterate(1, i -> i +1).limit(10).forEach(System.out::print);
//        Stream.concat(Stream.of("欢迎","关注"),Stream.of("程序新视界")).forEach(System.out::println);
//        Stream.of(1,1,1,2,2,2,3).distinct().forEach(System.out::println);
//        Stream.of(1, 2, 3, 4, 5).filter(i -> i >= 3).forEach(System.out::println);
//        Stream.of("a","b","c").map(item -> item.toUpperCase()).forEach(System.out::println);
//        Stream.of("a","b","c").map(String::toUpperCase).forEach(System.out::println);
//        Stream.of(1, 2, 3).flatMap(i -> Stream.of(i * 10)).forEach(System.out::println);
//        Stream.of(1, 2).peek(i -> System.out.println("peekCall:" + i)).forEach(System.out::println);
//        Stream.of(1, 2, 3).skip(2).forEach(System.out::println);
//        Stream.of(1, 3, 2).sorted().forEach(System.out::println);
//        Stream.of(1, 2, 3).limit(2).forEach(System.out::println);
//        List<Integer> collect = Stream.of(1, 2, 3).collect(Collectors.toList());
//        Set<Integer> collect1 = Stream.of(1, 2, 3).collect(Collectors.toSet());
//        Stream.of(1, 2, 3).count();
//        Optional<Integer> max = Stream.of(1, 2, 3).max(Comparator.comparingInt(o -> o));
//        System.out.println("max:" + max.get());
//        Optional<Integer> reduce = Stream.of(1, 2, 3).reduce(Integer::sum);
//        System.out.println(reduce.get());
//        Integer reduceOne = Stream.of(1, 2, 3).reduce(0, (a, b) -> a + b);
//        System.out.println(reduceOne);
//        boolean result = Stream.of(1, 2, 3).allMatch(i  -> i > 0);
//        System.out.println(result);
//        boolean anyResult = Stream.of(1, 2, 3).anyMatch(i  -> i > 2);
//        System.out.println(anyResult);
//        Optional<String> any = Stream.of("A", "B", "C").findAny();
//        Optional<String> any = Stream.of("A", "B", "C").findAny();
//        System.out.println(any.get());
//        Optional<String> first = Stream.of("A", "B", "C").findFirst();
//        System.out.println(first.get());
//        IntSummaryStatistics summaryStatistics = Stream.of(1, 2, 3).mapToInt((i) -> i).summaryStatistics();
//        System.out.println("max:" + summaryStatistics.getMax());
//        System.out.println("min:" + summaryStatistics.getMin());
//        System.out.println("sum:" + summaryStatistics.getSum());
//        System.out.println("average:" + summaryStatistics.getAverage());

        boolean noneMatch = Stream.of(1, 2, 3).noneMatch(i  -> i > 5);
        System.out.println(noneMatch);
    }
}
