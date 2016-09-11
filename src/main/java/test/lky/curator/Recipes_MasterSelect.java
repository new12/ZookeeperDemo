package test.lky.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.Date;

/**
 * Created by kylong on 2016/9/4.
 */
public class Recipes_MasterSelect {
    static String master_path = "/curator_recipes_master_path";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("10.3.8.88:2181,10.3.8.89:2181,10.2.5.54:2181")
            .retryPolicy(new ExponentialBackoffRetry(1000,3))
            .sessionTimeoutMs(5000)
            .build();

    public static void main(String[] args) throws InterruptedException {
        client.start();
        LeaderSelector selector = new LeaderSelector(client, master_path, new LeaderSelectorListenerAdapter() {
            public void takeLeadership(CuratorFramework client) throws Exception {
                    System.out.println(new Date() + " 成为master角色");
                    Thread.sleep(3000);
                    System.out.println(new Date() + " 完成Master操作，釋放Master權利");
            }
        });
        selector.autoRequeue();
        selector.start();
        Thread.sleep(Integer.MAX_VALUE);

    }
}
