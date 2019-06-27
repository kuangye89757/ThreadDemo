package com.kuangye.threaddemo.basis.classic;

/**
 * 生产者.
 */
public class Producer {
    private Depot depot;

    public Producer(Depot depot) {
        this.depot = depot;
    }

    public void produce(final int val){
        //开启线程去生产
        new Thread() {
            public void run() {
                depot.produce(val);
            }
        }.start();
    }
}
