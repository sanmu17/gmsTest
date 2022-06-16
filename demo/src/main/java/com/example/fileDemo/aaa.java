package com.example.fileDemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * 类描述：
 *
 * @ClassName aaa
 * @Description TODO
 * @Author 1537414333@qq.com
 * @Date 2021/9/14 20:16
 * @Version 1.0
 */
public class aaa {



    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        String path = "D:\\JAVA_STUDY";
        File file = new File(path);
        Map<String, Integer> map = new HashMap<>();
        getFileNumsss(file,map);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("文件类型:"+entry.getKey()+",\t文件数量"+entry.getValue());
        }

//        System.out.println("开始执行");
//        CountDownLatch countDownLatch = new CountDownLatch(5);
//        for (int i = 0; i < 5; i++) {
//            new Thread(()->{
//                System.out.println(Thread.currentThread().getName()+"线程执行");
//                countDownLatch.countDown();
//            }, String.valueOf(i)).start();
//        }
//        countDownLatch.await();
//        System.out.println("主线程====执行完毕----------");


    }
    public static void getFileNumsss(File file, Map<String,Integer> map){
         File[] files = file.listFiles();
        assert files != null;
        for (File file1 : files) {
            if(file1.isFile()){
                String name = file1.getName();
                String[] split = name.split("\\.");
                String f = split[split.length-1];
                if(map.containsKey(f)){
                    map.put(f,map.get(f)+1);
                }else{
                    map.put(f,1);
                }
            }else{
                getFileNumsss(file1,map);
            }
        }
    }
}
