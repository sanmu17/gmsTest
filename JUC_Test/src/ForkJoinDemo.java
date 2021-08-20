import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

class MyTask extends RecursiveTask<Integer>{
    private static final Integer ADJAST_VALUE=10;
    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if(end-begin<=ADJAST_VALUE){
            for (int i = begin; i <= end; i++) {
                result = result+i;
            }
        }else {
            int mid = (begin+end)/2;
            MyTask myTask = new MyTask(begin, mid);
            MyTask myTask1 = new MyTask(mid+1, end);
            myTask.fork();
            myTask1.fork();
            result = myTask.join()+myTask1.join();
        }
        return result;
    }
}

/**
 * 分支合并例子
 * ForkJoinPool
 * ForkJoinTask
 * RecursiveTask
 */
public class ForkJoinDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask myTask = new MyTask(0,100);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask forkJoinTask = forkJoinPool.submit(myTask) ;
        System.out.println(forkJoinTask.get());
    }
}
