package com.kuangye.threaddemo.basis.synchronize;

/**
 * t1 synMethod loop 0 -- t1获取到对象锁 先执行 t2阻塞
 * t1 synMethod loop 1
 * t1 synMethod loop 2
 * t1 synMethod loop 3
 * t1 synMethod loop 4 -- t1执行synchronized代码块结束，释放锁
 *
 * t2 synMethod_2 loop 0 -- t2运行获取到对象锁 执行
 * t2 synMethod_2 loop 1
 * t2 synMethod_2 loop 2
 * t2 synMethod_2 loop 3
 * t2 synMethod_2 loop 4 -- t2执行synchronized代码块结束，释放锁
 */
public class Demo3 {

    public static void main(String[] args) {
        final Count count = new Count();
        // 新建t1, t1会调用“count对象”的synMethod()方法
        Thread t1 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        count.synMethod();
                    }
                }, "t1");

        // 新建t2, t2会调用“count对象”的nonSynMethod()方法
        Thread t2 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        count.synMethod_2();
                    }
                }, "t2");


        t1.start();  // 启动t1
        t2.start();  // 启动t2
    }

    static class Count {

        // 含有synchronized同步块的方法
        public void synMethod() {
            synchronized (this) {
                try {
                    for (int i = 0; i < 5; i++) {
                        Thread.sleep(100); // 休眠100ms
                        System.out.println(Thread.currentThread()
                                .getName() + " synMethod loop " + i);
                    }
                } catch (InterruptedException ie) {
                }
            }
        }

        // 也包含synchronized同步块的方法
        public void synMethod_2() {
            synchronized (this) {
                try {
                    for (int i = 0; i < 5; i++) {
                        Thread.sleep(100);
                        System.out.println(Thread.currentThread()
                                .getName() + " synMethod_2 loop " + i);
                    }
                } catch (InterruptedException ie) {
                }
            }
        }
    }
}