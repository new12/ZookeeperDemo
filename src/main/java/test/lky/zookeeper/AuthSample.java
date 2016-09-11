package test.lky.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by kylong on 2016/9/3.
 */
public class AuthSample {
    final static String path = "/zk-book-auth_test";

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("10.3.8.88:2181",50000,null);
        zooKeeper.addAuthInfo("digest","foo:true".getBytes());
        if (zooKeeper.exists(path,false)!=null) {
            zooKeeper.delete(path,-1);
        }
        zooKeeper.create(path, "init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
        ZooKeeper zooKeeper2 = new ZooKeeper("10.3.8.88:2181",50000,null);
        zooKeeper2.addAuthInfo("digest","foo:true".getBytes());
        System.out.println(new String(zooKeeper2.getData(path,true,null)));
    }
}
