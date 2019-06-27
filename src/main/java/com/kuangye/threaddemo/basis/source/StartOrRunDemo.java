package com.kuangye.threaddemo.basis.source;

/**
 * start() : 它的作用是启动一个新线程，新线程会执行相应的run()方法。start()不能被重复调用。
    run()  :普通的成员方法一样，可以被重复调用。单独调用run()的话，会在当前线程中执行run()，而并不会启动新线程！
 *
 * 结果说明：
     (01) Thread.currentThread().getName()是用于获取“当前线程”的名字。当前线程是指正在cpu中调度执行的线程。
     (02) mythread.run()是在“主线程main”中调用的，该run()方法直接运行在“主线程main”上。
     (03) mythread.start()会启动“线程mythread”，“线程mythread”启动之后，会调用run()方法；此时的run()方法是运行在“线程mythread”上。
 */
public class StartOrRunDemo {
    public static void main(String[] args) {
        Thread mythread = new MyThread("mythread");

        // java并没有禁止将不同线程名称属性设置为相同
        // Thread.currentThread().setName("mythread");
        System.out.println(Thread.currentThread().getName() + " call mythread.run()");
        mythread.run();//mythread.run()则会直接在当前线程中运行run()方法，并不会启动一个新线程来运行run()

        System.out.println(Thread.currentThread().getName() + " call mythread.start()");
        mythread.start();//mythread.start()会启动一个新线程，并在新线程中运行run()方法。
    }

    static class MyThread extends Thread {
        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is running");
        }
    }
}
