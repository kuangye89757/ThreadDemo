package com.kuangye.threaddemo.basis.source;

/**
 * Thread.run方法源码
 */
public class ThreadDemo {
    public static void main(String[] args){
        System.out.println(Thread.currentThread().getName() + "-- 执行  ");

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Run of Runnable");
                System.out.println(Thread.currentThread().getName() + "-- 执行 ");
            }
        }) {
            @Override
            public void run() {
                System.out.println("Run of Thread");
                System.out.println(Thread.currentThread().getName() + "-- 执行 ");
                /**
                 * 调用super.run(); 会执行Runnable的run
                 *
                 * if(target!=null){
                 *    target.run();
                 * }
                 */
                super.run();
            }
        }.start();


    }
}
