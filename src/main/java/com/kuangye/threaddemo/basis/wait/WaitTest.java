package com.kuangye.threaddemo.basis.wait;

import java.util.concurrent.TimeUnit;

/**
 * wait(long timeout)会让当前线程处于“等待(阻塞)状态”，
 * “直到其他线程调用此对象的 notify() 方法或 notifyAll() 方法，或者超过指定的时间量”，
 * 当前线程被唤醒(进入“就绪状态”)。
 */
public class WaitTest {

    /**
     * threadA和this这里都是同一个对象锁  所以当前线程（main） 调用完wait之后释放锁 threadA线程才能获取锁执行
     *
     * 所以不会发生 先执行notify的情况
     */
    public static void main(String[] args) {
        ThreadA threadA = new ThreadA("threadA");

        synchronized (threadA) {
            try {
                System.out.println(Thread.currentThread().getName() + " call start");
                threadA.start();

                TimeUnit.SECONDS.sleep(5);

                System.out.println(Thread.currentThread().getName() + " call wait");
                threadA.wait();//这里虽然是ThreadA调用 但是当前线程是主线程  所以是主线程wait 释放锁
                
                System.out.println(Thread.currentThread().getName() + " continue");
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }


    static class ThreadA extends Thread {

        public ThreadA(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + " call notify");
                // 唤醒当前的wait线程
                notify();
            }

        }
    }
}
