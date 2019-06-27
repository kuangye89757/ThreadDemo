package com.kuangye.threaddemo.basis.interrupt;

/**
 * 结果说明：
 程序进入了死循环！
 因为t1在“等待(阻塞)状态”时，被interrupt()中断；
 此时，会清除中断标记[即isInterrupted()会返回false]，
 而且会抛出InterruptedException异常[该异常在while循环体内被捕获]。
 因此，t1理所当然的会进入死循环了。

 解决该问题
 在MyThread的catch(InterruptedException)中添加break 或 return就能解决该问题。
 */


public class InterruptDemo2 {

    public static void main(String[] args) {
        try {
            Thread t1 = new MyThread("t1");  // 新建“线程t1”
            System.out.println(t1.getName() + " (" + t1.getState() + ") is new.");

            t1.start();                      // 启动“线程t1”
            System.out.println(t1.getName() + " (" + t1.getState() + ") is started.");

            // 主线程休眠300ms，然后主线程给t1发“中断”指令。
            Thread.sleep(300);
            t1.interrupt();
            System.out.println(t1.getName() + " (" + t1.getState() + ") is interrupted.");

            // 主线程休眠300ms，然后查看t1的状态。
            Thread.sleep(300);
            System.out.println(t1.getName() + " (" + t1.getState() + ") is interrupted now.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class MyThread extends Thread {

        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            int i = 0;
            //阻塞状态下 收到中断之后就会立即清除 所以虽然catch到异常 但是isInterrupted()返回的是false所以还是在循环
            while (!isInterrupted()) {
                try {
//                    Thread.sleep(100); // 休眠100ms （阻塞状态）
                    for (int j = 0; j < 100000; j++) { //非阻塞状态 抛出中断是不会清除标识的
                        if(j == 99999){
                            throw new InterruptedException();
                        }
                    }
                } catch (InterruptedException ie) {
                    //需要break或return
                    System.out.println(Thread.currentThread()
                            .getName() + " (" + this.getState() + ") catch InterruptedException.");
                }
                i++;
                System.out.println(Thread.currentThread()
                        .getName() + " (" + this.getState() + ") loop " + i);
            }
        }
    }
}
