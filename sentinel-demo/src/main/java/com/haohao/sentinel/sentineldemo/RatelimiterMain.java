package com.haohao.sentinel.sentineldemo;

import com.google.common.util.concurrent.RateLimiter;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/***
 *  这个适是用来测试 Guava-RateLimiter 的单击限流的
 *      需要引入 guava 的包
 */

public class RatelimiterMain {

    // 令牌通的实现
    RateLimiter rateLimiter = RateLimiter.create(10); // 10 表示每秒钟可以接受 10 个请求;

    public void doTest() {
        // 这里就是获得一个令牌
        if (rateLimiter.tryAcquire()) {
            System.out.println("允许通过进行访问.");
        } else {
            System.out.println("被限流了.");
        }
    }

    public static void main(String[] args) throws IOException {
        RatelimiterMain ratelimiterMain = new RatelimiterMain();

        // 模拟并发请求
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                try {
                    countDownLatch.await();
                    Thread.sleep(random.nextInt(1000));
                    ratelimiterMain.doTest();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        countDownLatch.countDown();
        System.in.read();
    }


}
