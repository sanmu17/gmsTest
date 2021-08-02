package com.example;

public class JVMTest {
    /*
     * 运行参数：-Xms1024m -Xmx1024m -XX:+PrintGCDetails
     * @param args
     *
     *
     */
    public static void main(String[] args) {
        long maxMemory = Runtime.getRuntime().maxMemory();//字节数 java虚拟机驶入使用的最大内存量
        long totalMemory = Runtime.getRuntime().totalMemory();//java虚拟机的内存总量
        System.out.println("MAX_MEMORY:"+maxMemory+"字节，"+(maxMemory/1024/1024)+"MB");
        System.out.println("TOTAL_MEMORY:"+totalMemory+"字节，"+(totalMemory/1024/1024)+"MB");
    }
}
//结果：
//MAX_MEMORY:1029177344字节，981MB
//TOTAL_MEMORY:1029177344字节，981MB
//Heap
// PSYoungGen      total 305664K, used 20971K [0x00000000eab00000, 0x0000000100000000, 0x0000000100000000)
//  eden space 262144K, 8% used [0x00000000eab00000,0x00000000ebf7afb8,0x00000000fab00000)
//  from space 43520K, 0% used [0x00000000fd580000,0x00000000fd580000,0x0000000100000000)
//  to   space 43520K, 0% used [0x00000000fab00000,0x00000000fab00000,0x00000000fd580000)
// ParOldGen       total 699392K, used 0K [0x00000000c0000000, 0x00000000eab00000, 0x00000000eab00000)
//  object space 699392K, 0% used [0x00000000c0000000,0x00000000c0000000,0x00000000eab00000)
// Metaspace       used 3519K, capacity 4498K, committed 4864K, reserved 1056768K
//  class space    used 387K, capacity 390K, committed 512K, reserved 1048576K