import java.util.concurrent.*;

public class MyThreadPoolDemo {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2,
                5,
                5,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy()//抛出异常
//                new ThreadPoolExecutor.CallerRunsPolicy()//到哪来回哪去
//                new ThreadPoolExecutor.DiscardOldestPolicy()//抛弃等待最久的
                new ThreadPoolExecutor.DiscardPolicy()//抛弃最新到来的
        );
        for (int i = 1; i <= 10; i++) {
            String value = String.valueOf(i);
            threadPool.execute(()->{
                System.out.println(Thread.currentThread().getName()+"业务员办理业务,"+value);
            });
        }
    }
}
