package com.kuangye.threaddemo.basis.synchronize;

/**
 * 互不影响
 *
 * t1 synMethod loop 0
 * t2 nonSynMethod loop 0
 * t1 synMethod loop 1
 * t2 nonSynMethod loop 1
 * t1 synMethod loop 2
 * t2 nonSynMethod loop 2
 * t2 nonSynMethod loop 3
 * t1 synMethod loop 3
 * t1 synMethod loop 4
 * t2 nonSynMethod loop 4
 */
public class Demo2 {
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
                        count.nonSynMethod();
                    }
                }, "t2");
        t1.start();  // 启动t1
        t2.start();  // 启动t2
    }

    static class Count {
        void synMethod() {
            // 含有synchronized同步块的方法
            synchronized (this) {
                try {
                    for (int i = 0; i < 5; i++) {
                        Thread.sleep(100); // 休眠100ms
                        System.out.println(Thread.currentThread()
                                .getName() + " synMethod loop " + i);
                    }
                } catch (InterruptedException ignored) {
                }
            }
        }

        // 非同步的方法
        void nonSynMethod() {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread()
                            .getName() + " nonSynMethod loop " + i);
                }
            } catch (InterruptedException ignored) {
            }
        }
    }
}