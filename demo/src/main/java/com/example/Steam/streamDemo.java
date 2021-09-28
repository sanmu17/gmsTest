package com.example.Steam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 类描述：
 *
 * @ClassName streamDemo
 * @Description TODO
 * @Author 1537414333@qq.com
 * @Date 2021/9/10 20:26
 * @Version 1.0
 */
public class streamDemo {

    public static void main(String[] args) {
        List<String> items =
                Arrays.asList("apple", "apple", "banana",
                        "apple", "orange", "banana", "papaya");
        Map<String, Long> result =
                items.stream().collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );
        System.out.println(result);
        Integer a = new Integer(1000);
        Integer b = new Integer(1000);
        System.out.println((a==1000)+","+(a==b));

    }
}
