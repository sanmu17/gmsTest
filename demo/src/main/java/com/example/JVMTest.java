package com.example;

public class JVMTest {
    public static void main(String[] args) {
        long maxMemory = Runtime.getRuntime().maxMemory();//字节数 java虚拟机驶入使用的最大内存量
        long totalMemory = Runtime.getRuntime().totalMemory();//java虚拟机的内存总量
        System.out.println("MAX_MEMORY:"+maxMemory+"字节，"+(maxMemory/1024/1024)+"MB");
        System.out.println("TOTAL_MEMORY:"+totalMemory+"字节，"+(totalMemory/1024/1024)+"MB");
    }
}
