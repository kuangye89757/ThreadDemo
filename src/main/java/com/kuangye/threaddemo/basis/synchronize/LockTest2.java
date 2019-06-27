package com.kuangye.threaddemo.basis.synchronize;

/**
 * 可以同时被访问。因为访问的不是同一个对象的同步锁，x.isSyncA()访问的是x的同步锁，而y.isSyncA()访问的是y的同步锁。
 */
public class LockTest2 {

    Something x = new Something();
    Something y = new Something();

    private void test2() {
        Thread t21 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        x.isSyncA();
                    }
                }, "t21");

        Thread t22 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        y.isSyncA();
                    }
                }, "t22");


        t21.start();  // 启动t21
        t22.start();  // 启动t22
    }

    public static void main(String[] args) {
        LockTest2 demo = new LockTest2();

        demo.test2();
    }

    static class Something {
        public synchronized void isSyncA() {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100); // 休眠100ms
                    System.out.println(Thread.currentThread().getName() + " : isSyncA");
                }
            } catch (InterruptedException ie) {
            }
        }

        public synchronized void isSyncB() {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100); // 休眠100ms
                    System.out.println(Thread.currentThread().getName() + " : isSyncB");
                }
            } catch (InterruptedException ie) {
            }
        }


    }
}
