package test.lky.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by kylong on 2016/9/3.
 */
public class GetData_API_Sync_Usage implements Watcher {

    private static CountDownLatch connectedSemphore = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    private static Stat stat = new Stat();

    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        String path = "/zk-book";
        zk = new ZooKeeper("10.3.8.88:2181",5000,new GetData_API_Sync_Usage());
        connectedSemphore.await();
        System.out.println(new String(zk.getData(path,true,stat)));
        Thread.sleep(Integer.MAX_VALUE);
    }

    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()){
            if (Event.EventType.None == event.getType() && null == event.getPath()){
                connectedSemphore.countDown();
            }else if (event.getType() == Event.EventType.NodeDataChanged){
                try {
                    System.out.println(new String(zk.getData(event.getPath(),true,stat)));
                    System.out.println(stat.getCzxid()+","+stat.getMzxid()+"," + stat.getVersion());
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
