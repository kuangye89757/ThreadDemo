package com.kuangye.threaddemo.basis.synchronize;

/**
 * 可以被同时访问。
 *
 * cSyncA()访问获取的是类锁
 * isSyncA()访问获取的是x对象锁
 */
public class LockTest4 {

    Something x = new Something();
    Something y = new Something();

    private void test4() {
        Thread t41 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        x.isSyncA();
                    }
                }, "t41");

        Thread t42 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        Something.cSyncA();
                    }
                }, "t42");


        t41.start();  // 启动t41
        t42.start();  // 启动t42
    }

    public static void main(String[] args) {
        LockTest4 demo = new LockTest4();

        demo.test4();
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