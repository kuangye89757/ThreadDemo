package com.kuangye.threaddemo.basis.suspend;

/**
 * Created by shijie9 on 2018/4/14.
 */

public class Run {
    public static void main(String[] args){
        try {
            final SynchronizedObject object = new SynchronizedObject();
            Thread thread1 = new Thread(){
                @Override
                public void run() {
                    object.printString();
                }
            };
            thread1.setName("a");
            thread1.start();
            Thread.sleep(1000);
            
            Thread thread2 = new Thread(){
                @Override
                public void run() {
                    System.out.println("thread2启动,但进入不了printString()方法");
                    object.printString();
                }
            };
            thread2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
