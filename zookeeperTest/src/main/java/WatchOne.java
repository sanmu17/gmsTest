import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

public class WatchOne {

    private static final String CONNECTSTRING = "192.168.159.129:2181";

    private static final String PATH = "/ggg";
    private static final int SESSION_TIMEOUT = 20 * 1000;
    private ZooKeeper zk;

    public ZooKeeper getZk() {
        return zk;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    public ZooKeeper startZK() throws IOException {
        return new ZooKeeper(CONNECTSTRING, SESSION_TIMEOUT, event -> {
        });
    }

    public void stopZK() throws InterruptedException {
        if (zk != null) {
            zk.close();
        }
    }
    public void createZNode(String path, String nodeValue) throws KeeperException, InterruptedException {
        zk.create(path, nodeValue.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public String getZNode(String path) throws KeeperException, InterruptedException {
        String result="";
        byte[] byteArray = zk.getData(path, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                try {
                    TriggerValue(path);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, new Stat());
        result = new String(byteArray);
        return result;
    }
    public void TriggerValue(String path) throws KeeperException, InterruptedException {
        byte[] byteArray = zk.getData(path, false, new Stat());
        System.out.println("TriggerValue="+new String(byteArray));
    }

    public static void main(String[] args) throws Exception{
        WatchOne watchOne = new WatchOne();
        watchOne.setZk(watchOne.startZK());
        if(watchOne.getZk().exists(PATH,false)==null){
            watchOne.createZNode(PATH,"aaaa");
            String zNode = watchOne.getZNode(PATH);
            System.out.println("node==="+zNode);
        }else {
            System.out.println("node is already existed");
        }
        Thread.sleep(Long.MAX_VALUE);
    }
}
