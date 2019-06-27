package com.kuangye.threaddemo;

/**
 *   thread1: test1 4
     thread1: test1 3
     thread1: test1 2
     thread1: test1 1
     thread1: test1 0  --- 锁已释放
     thread2: test2 4  --- 线程2获取锁执行test2方法
     thread1: test1 finish -- 线程1继续执行剩下的部分
     thread2: test2 3
     thread2: test2 2
     thread2: test2 1
     thread2: test2 0
 */
public class ThreadTest3 {

    public void test1(){
        synchronized (this){
            int i = 5;
            while (i-- > 0){
                System.out.println(Thread.currentThread().getName() + ": test1 " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        //同步块结束就释放锁,故不用执行到下面代码就可以执行test2
        for (int i = 0; i < 100000; i++) {

        }
        System.out.println(Thread.currentThread().getName() + ": test1 finish");
    }

    public synchronized void test2(){
        int i = 5;
        while (i-- > 0){
            System.out.println(Thread.currentThread().getName() + ": test2 " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void test3(){
        synchronized (ThreadTest3.class){
            int i = 5;
            while (i-- > 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args){
        final ThreadTest3 threadTest3 = new ThreadTest3();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadTest3.test1();
            }
        },"thread1");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadTest3.test2();
//                ThreadTest3.test3();
            }
        },"thread2");

        thread2.start();
        thread1.start();
    }
}
