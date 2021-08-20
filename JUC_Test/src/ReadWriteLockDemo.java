import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyRW{
    private volatile Map<String,String> map = new HashMap<>();
    private ReadWriteLock rw = new ReentrantReadWriteLock();

    public void put(String key,String value){
        rw.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"准备写入数据,key="+key);
            TimeUnit.SECONDS.sleep(3);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"已经放入数据,key="+key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rw.writeLock().unlock();
        }
    }
    public void get(String key){
        rw.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"准备取出数据");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("取出的数据："+map.get(key));
            System.out.println(Thread.currentThread().getName()+"取出数据");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rw.readLock().unlock();
        }
    }

}


public class ReadWriteLockDemo {

    public static void main(String[] args) throws InterruptedException {
        MyRW myRW = new MyRW();
        for (int i = 1; i <=  4; i++) {
            String key = String.valueOf(i);
            new Thread(()->{
               myRW.put(key, UUID.randomUUID().toString().substring(0,10));
            },String.valueOf(i)).start();

        }
        TimeUnit.SECONDS.sleep(2);

        for (int i = 1; i <=  4; i++) {
            String key = String.valueOf(i);
            new Thread(()->{
               myRW.get(key);
            },String.valueOf(i)).start();
        }
    }


}
