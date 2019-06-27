package com.kuangye.threaddemo.basis.suspend;

import java.util.concurrent.TimeUnit;

public class StopThreadTest {
    public static void main(String[] args) {
        Task task = new Task();
        new Thread(task).start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        task.stop();
        System.out.println("main end...");
    }


    static class Task implements Runnable{
        volatile boolean stop = false;

        public void stop() {
            this.stop = true;
        }

        @Override
        public void run() {
            int i = 0;
            while (!stop){
                System.out.println(i++);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
