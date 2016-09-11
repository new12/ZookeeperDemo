package test.lky.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * Created by kylong on 2016/9/3.
 */
public class Get_Data_Sample {
    public static void main(String[] args) throws InterruptedException {
        String path = "/zk";
        ZkClient zkClient = new ZkClient("10.3.8.88:2181,10.3.8.89:2181,10.2.5.54:2181",5000);
        if (!zkClient.exists(path)){
        zkClient.createPersistent(path,"123");}
        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("Node " + dataPath + " changed, new data:" + data);
            }

            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("Node " + dataPath + " deleted.");
            }
        });
        System.out.println(zkClient.readData(path));
        Thread.sleep(1000);
        zkClient.writeData(path,"23");
        Thread.sleep(Integer.MAX_VALUE);
    }
}
