package com.kuangye.threaddemo.basis.join;

/**
 *     t1.join();  分析
 *
 *     public final void join() throws InterruptedException {
 //        join(0);
 //    }
 //
 //    public final synchronized void join(long millis) throws InterruptedException {
 //        long base = System.currentTimeMillis();
 //        long now = 0;
 //
 //        if (millis < 0) {
 //            throw new IllegalArgumentException("timeout value is negative");
 //        }
 //
 //        if (millis == 0) {
 //            while (isAlive()) {//t1线程若活着
 //                wait(0); // 当前线程(主线程Main)等待 这里参数为0 即超时唤醒机制
 //            }
 //        } else {
 //            while (isAlive()) {
 //                long delay = millis - now;
 //                if (delay <= 0) {
 //                    break;
 //                }
 //                wait(delay);
 //                now = System.currentTimeMillis() - base;
 //            }
 //        }
 //    }
 */
public class JoinTest {

    public static void main(String[] args) {
        try {
            ThreadA t1 = new ThreadA("t1"); // 新建“线程t1”
            // 启动“线程t1”
            t1.start();
            // 将“线程t1”加入到“主线程main”中，并且“主线程main()会等待它的完成”
            t1.join();
            System.out.printf("%s finish\n", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class ThreadA extends Thread {

        public ThreadA(String name) {
            super(name);
        }

        public void run() {
            System.out.printf("%s start\n", this.getName());
            // 延时操作
            for (int i = 0; i < 1000000; i++);
            System.out.printf("%s finish\n", this.getName());
        }
    }
}
