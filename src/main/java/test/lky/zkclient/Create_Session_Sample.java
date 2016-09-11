package test.lky.zkclient;

import org.I0Itec.zkclient.ZkClient;

/**
 * Created by kylong on 2016/9/3.
 */
public class Create_Session_Sample {
    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("10.3.8.88:2181,10.3.8.89:2181,10.2.5.54:2181",5000);
        System.out.println("zookeeper session established");
        String path = "/test/c1";
        zkClient.createPersistent(path,true);
    }
}
