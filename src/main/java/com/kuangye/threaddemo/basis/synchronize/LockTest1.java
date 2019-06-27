package com.kuangye.threaddemo.basis.synchronize;

/**
 * 不能被同时访问。因为isSyncA()和isSyncB()都是访问同一个对象(对象x)的同步锁！
 */


public class LockTest1 {

    Something x = new Something();
    Something y = new Something();

    private void test1() {
        Thread t11 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        x.isSyncA();
                    }
                }, "t11");

        Thread t12 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        x.isSyncB();
                    }
                }, "t12");


        t11.start();  // 启动t11
        t12.start();  // 启动t12
    }

    public static void main(String[] args) {
        LockTest1 demo = new LockTest1();
        demo.test1();
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
