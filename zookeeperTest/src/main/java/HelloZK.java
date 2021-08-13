import org.apache.log4j.Logger;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

public class HelloZK {
    private static final String CONNECTSTRING = "192.168.159.129:2181";
    private static final Logger logger = Logger.getLogger(HelloZK.class);

    private static final String PATH = "/atguigu";
    private static final int SESSION_TIMEOUT = 20 * 1000;


    public ZooKeeper startZK() throws IOException {
        return new ZooKeeper(CONNECTSTRING, SESSION_TIMEOUT, event -> {
        });
    }

    public void stopZK(ZooKeeper zk) throws InterruptedException {
        if (zk != null) {
            zk.close();
        }
    }

    public void createZNode(ZooKeeper zk, String path, String nodeValue) throws KeeperException, InterruptedException {
        zk.create(path, nodeValue.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public String getZNode(ZooKeeper zk, String path) throws KeeperException, InterruptedException {
        byte[] byteArray = zk.getData(path, false, new Stat());
        return new String(byteArray);
    }

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        HelloZK hello = new HelloZK();

        ZooKeeper zk = hello.startZK();

        Stat stat = zk.exists(PATH, false);

        if (stat == null) {
            hello.createZNode(zk, PATH, "zk1014");
            String result = hello.getZNode(zk, PATH);
            System.out.println("**********result: " + result);
        } else {
            System.out.println("***********znode has already ok***********");
        }
        hello.stopZK(zk);
    }
}
