package test.lky.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by kylong on 2016/9/3.
 */
public class Create_Session_Sample {
    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("10.3.8.88:2181,10.3.8.89:2181,10.2.5.54:2181",5000,3000,retryPolicy);
        client.start();
        client.create().forPath("/zz","123".getBytes());
        Thread.sleep(Integer.MAX_VALUE);
    }
}
