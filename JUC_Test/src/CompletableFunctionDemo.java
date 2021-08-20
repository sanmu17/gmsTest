import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFunctionDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //同步，异步，异步回调
        //MQ消息中间件
        //同步
//        CompletableFuture<Void> cf = CompletableFuture.runAsync(()->{
//            System.out.println("CompletableFuture.runAsync");
//        });
//        cf.get();

         //异步回调
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(()->{
            System.out.println("CompletableFuture.runAsync");
//            int a = 11/0;
            return 111;
        });
        cf1.whenComplete((t,u)->{//Function函数型接口
            System.out.println("===="+t);
            System.out.println("===="+u);//====java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
        }).exceptionally(f->{
            System.out.println(f.getMessage());
            return 500;
        });
    }
}
