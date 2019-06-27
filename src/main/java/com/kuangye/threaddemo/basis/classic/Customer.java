package com.kuangye.threaddemo.basis.classic;

/**
 * 消费者.
 */
public class Customer {
    private Depot depot;

    public Customer(Depot depot) {
        this.depot = depot;
    }

    public void consume(final int val){
        //开启线程去消费
        new Thread(){
            @Override
            public void run() {
                depot.consume(val);
            }
        }.start();
    }
}
