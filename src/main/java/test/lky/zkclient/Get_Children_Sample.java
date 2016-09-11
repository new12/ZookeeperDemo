package test.lky.zkclient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

/**
 * Created by kylong on 2016/9/3.
 */
public class Get_Children_Sample {
    public static void main(String[] args) throws InterruptedException {
        String path = "/test";
        ZkClient zkClient = new ZkClient("10.3.8.88:2181,10.3.8.89:2181,10.2.5.54:2181",5000);
        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                System.out.println(parentPath+"'s child changed, currentChilds:" + currentChilds);
            }
        });
        Thread.sleep(Integer.MAX_VALUE);
    }
}
