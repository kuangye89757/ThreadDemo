package com.kuangye.threaddemo.basis.synchronize;

/**
 * synchronized代码块可以更精确的控制冲突限制访问区域，有时候表现更高效率。
 * 执行结果: 每次结果不同有可能是JVM调优 理论上代码块效率高
 * synMethod() : 11
 * synBlock() : 3
 */
public class Demo4 {

    public synchronized void synMethod() {
        for (long i = 0; i < 1000_0000_00; i++) ;
    }

    public void synBlock() {
        synchronized (this) {
            for (long i = 0; i < 1000_0000_00; i++) ;
        }
    }

    public static void main(String[] args) {
        Demo4 demo = new Demo4();

        long start, diff;
        start = System.currentTimeMillis();                // 获取当前时间(millis)
        demo.synMethod();                                // 调用“synchronized方法”
        diff = System.currentTimeMillis() - start;        // 获取“时间差值”
        System.out.println("synMethod() : " + diff);

        start = System.currentTimeMillis();                // 获取当前时间(millis)
        demo.synBlock();                                // 调用“synchronized方法块”
        diff = System.currentTimeMillis() - start;        // 获取“时间差值”
        System.out.println("synBlock()  : " + diff);
    }
}
