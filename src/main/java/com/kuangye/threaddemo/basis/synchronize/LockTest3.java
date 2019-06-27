package com.kuangye.threaddemo.basis.synchronize;

/**
 * 不能被同时访问。
 * 共用一个类同步锁
 */
public class LockTest3 {

    Something x = new Something();
    Something y = new Something();

    private void test3() {
        Thread t31 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        x.cSyncA();
                    }
                }, "t31");

        Thread t32 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        y.cSyncB();
                    }
                }, "t32");


        t31.start();  // 启动t31
        t32.start();  // 启动t32
    }

    public static void main(String[] args) {
        LockTest3 demo = new LockTest3();
        demo.test3();
    }

    static class Something {
        public static synchronized void cSyncA() {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100); // 休眠100ms
                    System.out.println(Thread.currentThread().getName() + " : cSyncA");
                }
            } catch (InterruptedException ie) {
            }
        }

        public static synchronized void cSyncB() {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100); // 休眠100ms
                    System.out.println(Thread.currentThread().getName() + " : cSyncB");
                }
            } catch (InterruptedException ie) {
            }
        }
    }
}
