package com.kuangye.threaddemo.basis.wait;

/**
 * 超时唤醒机制
 */
public class WaitTimeOutTest {

    public static void main(String[] args) {
        ThreadA threadA = new ThreadA("threadA");

        synchronized (threadA) {
            try {
                System.out.println(Thread.currentThread().getName() + " call start");
                threadA.start();

                System.out.println(Thread.currentThread().getName() + " call wait");
                //这里虽然是ThreadA调用 但是当前线程是主线程  所以是主线程wait 释放锁
                //超时唤醒
                threadA.wait(3000);

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
            System.out.println(Thread.currentThread().getName() + " run");
            while (true);
        }
    }
}
