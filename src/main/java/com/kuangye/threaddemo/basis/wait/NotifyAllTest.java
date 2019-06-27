package com.kuangye.threaddemo.basis.wait;

/**
 * t1 call wait  -- t1抢到了锁 睡... 释放锁
 * t3 call wait  -- t3抢到了锁 睡... 释放锁
 * main call sleep
 * t2 call wait  -- t2抢到了锁 睡... 释放锁
 *
 * main call notifyAll -- 主线程说： 全都醒醒吧
 *
 * t2 call continue -- t2抢到了锁 干活了干活了...
 * t2 call finish   -- t2干完了
 * t3 call continue -- t3抢到了锁 干活了干活了...
 * t3 call finish   -- t3干完了
 * t1 call continue -- t1抢到了锁 干活了干活了...
 * t1 call finish   -- t1干完了
 */
public class NotifyAllTest {

    private static Object obj = new Object();

    public static void main(String[] args) {

        ThreadA thread1 = new ThreadA("t1");
        ThreadA thread2 = new ThreadA("t2");
        ThreadA thread3 = new ThreadA("t3");
        thread1.start();
        thread2.start();
        thread3.start();

        System.out.println(Thread.currentThread().getName() + " call sleep");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (obj) {
            System.out.println(Thread.currentThread().getName() + " call notifyAll");
            obj.notifyAll();
        }
    }

    static class ThreadA extends Thread {

        public ThreadA(String threadName) {
            super(threadName);
        }

        @Override
        public void run() {
            synchronized (obj) {
                try {
                    System.out.println(Thread.currentThread().getName() + " call wait");
                    obj.wait();
                    System.out.println(Thread.currentThread().getName() + " call continue");
                    for (int i = 0; i < 1000_000_00; i++) {
                        //工作中...
                    }
                    System.out.println(Thread.currentThread().getName() + " call finish");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
