package test.lky.curator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by kylong on 2016/9/4.
 */
public class Recipes_NoLock {
    public static void main(String[] args) {
        final CountDownLatch down = new CountDownLatch(1);
        for (int i=0;i<10;i++){
            new Thread(new Runnable() {
                public void run() {
                    try {
                        down.await();
                    } catch (InterruptedException e) {
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                    String orderNo = sdf.format(new Date());
                    System.out.println("生成的訂單號是：" + orderNo);
                }
            }).start();
        }
        down.countDown();
    }
}
