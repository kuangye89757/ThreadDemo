package com.kuangye.threaddemo.basis.synchronize;

/**
 * synchronized(this)中的this代表的是MyThread对象，而t1和t2是两个不同的MyThread对象，
 * 因此t1和t2在执行synchronized(this)时，获取的是不同对象的同步锁。
 */


public class Demo1_2 {

    public static void main(String[] args) {
        Thread t1 = new MyThread("t1");  // 新建“线程t1”
        Thread t2 = new MyThread("t2");  // 新建“线程t2”
        t1.start();                          // 启动“线程t1”
        t2.start();                          // 启动“线程t2”
    }

    static class MyThread extends Thread {

        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (this) {
                try {
                    for (int i = 0; i < 5; i++) {
                        Thread.sleep(100); // 休眠100ms
                        System.out.println(Thread.currentThread().getName() + " loop " + i);
                    }
                } catch (InterruptedException ie) {
                }
            }
        }
    }
}
