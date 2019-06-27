package com.kuangye.threaddemo.basis.yield;

/**
 * yield()方法不会释放锁  所以已经获取锁的线程 让出来CPU只有可能让不需要该锁的线程可能获取到CPU
 *
 *
 * 结果:
 *       t2 [5]:0   --t2占了锁,让出CPU后 也没谁能跟它抢CPU了因为别人没锁  （同一个对象锁）

         t2 [5]:1
         t2 [5]:2
         t2 [5]:3
         t2 [5]:4   --t2占了锁,让出CPU后 也没谁能跟它抢CPU了因为别人没锁  （同一个对象锁）

         t2 [5]:5
         t2 [5]:6
         t2 [5]:7
         t2 [5]:8   --t2占了锁,让出CPU后 也没谁能跟它抢CPU了因为别人没锁  （同一个对象锁）

         t2 [5]:9   --霸道总裁终于执行完后 给别的小弟让出了锁
         t1 [5]:0
         t1 [5]:1
         t1 [5]:2
         t1 [5]:3
         t1 [5]:4
         t1 [5]:5
         t1 [5]:6
         t1 [5]:7
         t1 [5]:8
         t1 [5]:9
 *
 */
public class YieldLockTest {

    private static Object obj = new Object();

    public static void main(String[] args) {
        ThreadA t1 = new ThreadA("t1");
        ThreadA t2 = new ThreadA("t2");
        t1.start();
        t2.start();
    }

    static class ThreadA extends Thread {
        public ThreadA(String name) {
            super(name);
        }

        public void run() {
            // 获取obj对象的同步锁
            synchronized (obj) {
                for (int i = 0; i < 10; i++) {
                    System.out.printf("%s [%d]:%d\n", this.getName(), this.getPriority(), i);
                    // i整除4时，调用yield
                    if (i % 4 == 0)
                        Thread.yield();
                }
            }
        }
    }
}
