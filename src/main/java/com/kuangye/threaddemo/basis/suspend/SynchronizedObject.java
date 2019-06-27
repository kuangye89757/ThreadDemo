package com.kuangye.threaddemo.basis.suspend;

/**
 *
 * @author shijie9
 * @date 2018/4/14
 */
class SynchronizedObject {
    synchronized void printString(){
        System.out.println("begin");
        if(Thread.currentThread().getName().equals("a")){
            System.out.println("a线程永远suspend了!");
            Thread.currentThread().checkAccess();
        }
        System.out.println("end");
    }
}



