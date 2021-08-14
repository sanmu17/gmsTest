import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{
    private int ticket = 30;
    private Lock lock = new ReentrantLock();

    public void sale(){
        lock.lock();
        try {
            if(ticket>0){
                System.out.println(Thread.currentThread().getName()+"\t---卖了"+(30-ticket)+"\t剩余"+ticket--);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                ticket.sale();
            }
        },"AAA").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                ticket.sale();
            }
        },"BBB").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                ticket.sale();
            }
        },"CCC").start();
    }

}
