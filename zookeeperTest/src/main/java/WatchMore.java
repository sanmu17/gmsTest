import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

public class WatchMore {
      private static final String CONNECTSTRING = "192.168.159.129:2181";

    private static final String PATH = "/AAA";
    private static final int SESSION_TIMEOUT = 20 * 1000;
    private ZooKeeper zk;

    private String oldV=null;
    private String newV=null;

    public String getOldV() {
        return oldV;
    }

    public void setOldV(String oldV) {
        this.oldV = oldV;
    }

    public String getNewV() {
        return newV;
    }

    public void setNewV(String newV) {
        this.newV = newV;
    }

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

        byte[] byteArray = zk.getData(path, watchedEvent -> {
            try {
                triggerValue(path);
            } catch (KeeperException|InterruptedException  e) {
                e.printStackTrace();
            }
        }, new Stat());

        result = new String(byteArray);

        oldV=result;
        return result;
    }
    public boolean triggerValue(String path) throws KeeperException, InterruptedException {
        String res="";
        byte[] byteArray = zk.getData(path, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                try {
                    triggerValue(path);
                } catch (KeeperException|InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, new Stat());
        res = new String(byteArray);
        System.out.println("TriggerValue="+res);
        newV=res;
        if(oldV.equals(newV)){
            System.out.println("-------no-changes------------");
            return false;
        }else{
            System.out.println("--------oldV="+oldV+"-----newV="+newV);
            oldV=newV;
            return true;
        }
    }

    public static void main(String[] args) throws Exception{
        WatchMore watchMore = new WatchMore();
        watchMore.setZk(watchMore.startZK());
        if(watchMore.getZk().exists(PATH,false)==null){
            watchMore.createZNode(PATH,"AAAAA");
            String zNode = watchMore.getZNode(PATH);
            System.out.println("node==="+zNode);
        }else {
            System.out.println("node is already existed");
        }
        Thread.sleep(Long.MAX_VALUE);
    }
}
