package test.lky.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by kylong on 2016/9/3.
 */
public class Zookeeper_GetChildren_API_Sync_Usage implements Watcher {

    private  static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    private static ZooKeeper zk = null;


    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()){
            if (Event.EventType.None == event.getType() && null == event.getPath()){
                connectedSemaphore.countDown();
            }else if (event.getType() == Event.EventType.NodeChildrenChanged){
                try {
                    System.out.println("ReGet Child:"+zk.getChildren(event.getPath(),true));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }else if (event.getType() == Event.EventType.NodeDataChanged){

            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        String path = "/zk-book3";
        zk = new ZooKeeper("10.3.8.88:2181",5000,new Zookeeper_GetChildren_API_Sync_Usage());
        connectedSemaphore.await();
        zk.create(path,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        zk.create(path+"/c1","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);

        List<String> children = zk.getChildren(path, new Zookeeper_GetChildren_API_Sync_Usage());
        System.out.println(children);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
