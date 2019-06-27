package com.kuangye.threaddemo.basis.classic;

/**
 * 经典的生产者与消费者
 * 每次运行结果不同 5个线程同一把锁
 *   Thread-0 produce( 60) --> left=  0, inc= 60, size= 60
     Thread-3 consume(150) <-- left= 90, dec= 60, size=  0
     Thread-4 produce(110) --> left= 10, inc=100, size=100
     Thread-2 consume( 90) <-- left=  0, dec= 90, size= 10
     Thread-1 produce(120) --> left= 30, inc= 90, size=100

     Thread-1 produce(120) --> left= 20, inc=100, size=100
     Thread-3 consume(150) <-- left= 50, dec=100, size=  0
     Thread-0 produce( 60) --> left=  0, inc= 60, size= 60
     Thread-4 produce(110) --> left= 70, inc= 40, size=100
     Thread-2 consume( 90) <-- left=  0, dec= 90, size= 10
 */
public class Demo {
    public static void main(String[] args) {
        Depot mDepot = new Depot(100);
        Producer mPro = new Producer(mDepot);
        Customer mCus = new Customer(mDepot);

        mPro.produce(60);
        mPro.produce(120);
        mCus.consume(90);
        mCus.consume(150);
        mPro.produce(110);

    }
}
