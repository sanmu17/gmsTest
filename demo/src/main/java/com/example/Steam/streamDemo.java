package com.example.Steam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 类描述：测试Integer中默认缓存-128~127范围的数。
 *
 * @ClassName streamDemo
 * @Description TODO
 * @Author 1537414333@qq.com
 * @Date 2021/9/10 20:26
 * @Version 1.0
 */
public class streamDemo {

    public static void main(String[] args) {
//        String str1 = "abc";
//        String str2 = "abc";
//        String str3 = str1 + str2;
//        System.out.println(str3 == str1 + str2);


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
//        Integer a = new Integer(1);
        Integer a = -129;
//        Integer b = new Integer(1);
        Integer b = -129;

        System.out.println((a==-129)+","+(a==b));

    }
}
