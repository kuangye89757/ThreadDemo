package com.kuangye.threaddemo.basis.classic;

/**
 * 仓库.
 */
public class Depot {
    private int capacity;//仓库的容量
    private int size;//仓库实际数量

    public Depot(int capacity) {
        this.capacity = capacity;
        this.size = 0;
    }

    public synchronized void produce(int val) {
        try {
            int left;
            left = val;//想要生产的数量
            if (left > 0) {
                while (size >= capacity) {
                    wait();//实际数量大于仓库总量时等待
                }
                /**计算增量 若增加的量+实际量大于仓库总量时 实际增加的量为 仓库总量-实际数量 */
                int inc = (left + size) >= capacity ? capacity - size : left;
                size += inc;
                left -= inc;
                System.out.printf("%s produce(%3d) --> left=%3d, inc=%3d, size=%3d\n",
                        Thread.currentThread().getName(), val, left, inc, size);

                //通知消费者消费
                notifyAll();
            }
        } catch (InterruptedException e) {
        }

    }


    public synchronized void consume(int val) {
        try {
            int left;
            left = val;//想要消费的数量
            if (left > 0) {
                while (size <= 0) {
                    wait();//实际数量为0时等待
                }

                /**计算减量 若减少的量小于仓库实际数量时 实际减少的量为 仓库实际数量 */
                int dec = size <= left ? size : left;
                size -= dec;
                left -= dec;
                System.out.printf("%s consume(%3d) <-- left=%3d, dec=%3d, size=%3d\n",
                        Thread.currentThread().getName(), val, left, dec, size);

                //通知生产者消费
                notifyAll();
            }
        } catch (InterruptedException e) {
        }

    }
}
