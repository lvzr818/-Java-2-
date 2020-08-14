package com.crazymakercircle.basic.demo.cas;

import com.crazymakercircle.util.Print;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderVSAtomicLongTest
{
    // 每条线程的执行轮数
    final int TURNS = 100000000;

    @org.junit.Test
    public void testAtomicLong()
    {
        // 线程数
        final int THREADS = 10;

        //线程池，用于多线程模拟测试
        ExecutorService pool = Executors.newFixedThreadPool(THREADS);

        //
        AtomicLong atomicLong = new AtomicLong(0);

        // 线程同步器
        CountDownLatch countDownLatch = new CountDownLatch(THREADS);
        long start = System.currentTimeMillis();
        for (int i = 0; i < THREADS; i++)
        {
            pool.submit(() ->
            {
                try
                {
                    for (int j = 0; j < TURNS; j++)
                    {
                        atomicLong.incrementAndGet();
                    }
                    // Print.tcfo("本线程累加完成");
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                //等待所有线程结束
                countDownLatch.countDown();

            });
        }
        try
        {
            countDownLatch.await();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        float time = (System.currentTimeMillis() - start) / 1000F;
        //输出统计结果
        Print.tcfo("运行的时长为：" + time);
        Print.tcfo("累加结果为：" + atomicLong.get());
    }

    @org.junit.Test
    public void testLongAdder()
    {
        // 线程数
        final int THREADS = 10;

        //线程池，用于多线程模拟测试
        ExecutorService pool = Executors.newFixedThreadPool(THREADS);

        //
        LongAdder longAdder = new LongAdder();

        // 线程同步器
        CountDownLatch countDownLatch = new CountDownLatch(THREADS);
        long start = System.currentTimeMillis();
        for (int i = 0; i < THREADS; i++)
        {
            pool.submit(() ->
            {
                try
                {
                    for (int j = 0; j < TURNS; j++)
                    {
                        longAdder.add(1);
                    }
                    // Print.tcfo("本线程累加完成");
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                //等待所有线程结束
                countDownLatch.countDown();

            });
        }
        try
        {
            countDownLatch.await();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        float time = (System.currentTimeMillis() - start) / 1000F;
        //输出统计结果
        Print.tcfo("运行的时长为：" + time);
        Print.tcfo("累加结果为：" + longAdder.longValue());
    }


}