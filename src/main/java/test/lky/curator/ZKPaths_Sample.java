package test.lky.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.ZooKeeper;

/**
 * Created by kylong on 2016/9/4.
 */
public class ZKPaths_Sample {
    static String path = "/curator_zkpath_sample";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("10.3.8.88:2181,10.3.8.89:2181,10.2.5.54:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000,3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        ZooKeeper zooKeeper = client.getZookeeperClient().getZooKeeper();
        System.out.println(ZKPaths.fixForNamespace(path,"/sub"));
        System.out.println(ZKPaths.makePath(path,"/sub"));
        System.out.println(ZKPaths.getNodeFromPath("/curator_zkpath_sample/sub1"));
        ZKPaths.PathAndNode pn = ZKPaths.getPathAndNode("/curator_zkpath_sample/sub1");
        System.out.println(pn.getPath());
        System.out.println(pn.getNode());

        String dir1 = path + "/child1";
        String dir2 = path + "/child2";
        ZKPaths.mkdirs(zooKeeper, dir1);
        ZKPaths.mkdirs(zooKeeper, dir2);
        System.out.println(ZKPaths.getSortedChildren(zooKeeper,path));
    }
}
