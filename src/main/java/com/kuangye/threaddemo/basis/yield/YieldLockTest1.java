package com.kuangye.threaddemo.basis.yield;

/**
 * “线程t1”在能被4整数的时候，并没有切换到“线程t2”。
 * 这表明，yield()虽然可以让线程由“运行状态”进入到“就绪状态”；
 * 但是，它不一定会让其它线程获取CPU执行权(即，其它线程进入到“运行状态”)，
 * 即使这个“其它线程”与当前调用yield()的线程具有相同的优先级
 *
 *  结果:
 *       t2 [5]:0     ---对象锁t2
         t1 [5]:0     ---对象锁t1   t1在运行状态又让出CPU t1在就绪状态又抢到了CPU

         t1 [5]:1
         t1 [5]:2
         t1 [5]:3

         t1 [5]:4                   t1在运行状态又让出CPU t1在就绪状态又抢到了CPU
         t1 [5]:5
         t1 [5]:6
         t1 [5]:7
         t1 [5]:8                   t1在运行状态又让出CPU t2在就绪状态终于抢上了CPU

         t2 [5]:1
         t2 [5]:2
         t2 [5]:3
         t2 [5]:4                   t2在运行状态又让出CPU t2在就绪状态又抢到了CPU

         t2 [5]:5
         t2 [5]:6
         t2 [5]:7
         t2 [5]:8                   t2在运行状态又让出CPU t2在就绪状态又抢到了CPU

         t2 [5]:9                   ...终于t2没给t1机会执行完毕了
         t1 [5]:9                   t1获取锁 进行执行
 *
 * 虽然t1和t2是两个对象 同步锁不一样 但是 t1让出又占CPU
 */
public class YieldLockTest1 {

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

        @Override
        public synchronized void run() {
            for (int i = 0; i < 10; i++) {
                System.out.printf("%s [%d]:%d\n", this.getName(), this.getPriority(), i);
                if (i % 4 == 0) {
                    Thread.yield();
                }
            }
        }
    }
}
