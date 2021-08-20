import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> bq = new ArrayBlockingQueue<>(2);
        //抛出异常
//        System.out.println(bq.add("a"));
//        System.out.println(bq.add("b"));
////        bq.add("c");//栈满抛异常
//        System.out.println(bq.remove());
//        System.out.println(bq.remove());

//        特殊值
//        System.out.println(bq.offer("a"));
//        System.out.println(bq.offer("b"));
//        System.out.println(bq.offer("c"));//false
//        System.out.println(bq.poll());
//        System.out.println(bq.poll());
//        System.out.println(bq.poll());//null


        //一直阻塞
//        bq.put("a");
//        bq.put("b");
////        bq.put("c");
//        System.out.println(bq.take());
//        System.out.println(bq.take());
////        System.out.println(bq.take());

        //超时
        System.out.println(bq.offer("a"));
        System.out.println(bq.offer("b"));
        System.out.println(bq.offer("c"));//false
        System.out.println(bq.poll());
        System.out.println(bq.poll());
        System.out.println(bq.poll(4, TimeUnit.SECONDS));//null
    }
}
