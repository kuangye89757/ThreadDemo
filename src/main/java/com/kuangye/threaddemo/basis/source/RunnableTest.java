package com.kuangye.threaddemo.basis.source;

/**
 * Runnable的多线程示例.
 *      结果说明：
         (01) 和上面“MyThread继承于Thread”不同；这里的MyThread实现了Runnable接口。
         (02) 主线程main创建并启动3个子线程，而且这3个子线程都是基于“mt这个Runnable对象”而创建的。
                运行结果是这3个子线程一共卖出了10张票。这说明它们是共享了MyThread接口的。
 */
public class RunnableTest {
    public static void main(String[] args) {
        MyThread mt = new MyThread();

        // 启动3个线程t1,t2,t3(它们共用一个Runnable对象)，这3个线程一共卖10张票！
        Thread t1 = new Thread(mt);
        Thread t2 = new Thread(mt);
        Thread t3 = new Thread(mt);
        t1.start();
        t2.start();
        t3.start();
    }

    static class MyThread implements Runnable {
        private int ticket = 10;

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                if (this.ticket > 0) {
                    System.out.println(Thread.currentThread()
                            .getName() + " 卖票：ticket" + this.ticket--);
                }
            }
        }
    }
}
