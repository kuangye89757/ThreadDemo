package com.kuangye.threaddemo.atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * AtomicLong() // 构造函数
 * <p/>
 * AtomicLong(long initialValue) // 创建值为initialValue的AtomicLong对象
 * <p/>
 * final void set(long newValue) // 以原子方式设置当前值为newValue。
 * <p/>
 * final long get()// 获取当前值
 * <p/>
 * final long decrementAndGet()// 等价于“--num”
 * <p/>
 * final long getAndDecrement()// 等价于“num--”
 * <p/>
 * final long incrementAndGet()// 等价于“++num”
 * <p/>
 * final long getAndIncrement()// 等价于“num++”
 * <p/>
 * final long addAndGet(long delta) // 以原子方式将delta与当前值相加，并返回相加后的值。
 * <p/>
 * final long getAndAdd(long delta) // 以原子方式将delta添加到当前值，并返回相加前的值。
 * <p/>
 * // 如果是现在的值还是expect 就给我更新成update 修改成功就返回true 失败就返回false  (整个过程是原子方式)
 * final boolean compareAndSet(long expect, long update)
 * <p/>
 * final long getAndSet(long newValue)// 以原子方式设置当前值为newValue，并返回旧值。
 * <p/>
 * int intValue()// 返回当前值对应的int值
 * <p/>
 * long longValue() // 获取当前值对应的long值
 * <p/>
 * float floatValue() // 以 float 形式返回当前值
 * <p/>
 * double doubleValue()  // 以 double 形式返回当前值
 * <p/>
 * 不想立即读取设置的新值，允许在“后台”修改值，类似于启动一个后台线程去执行修改新值的任务，原线程就不等待修改结果立即返回（这种解释其实是不正确的，但是可以这么理解）。
 * final void lazySet(long newValue)
 */
public class AtomicLongTest {
    public static void main(String[] args) {

        /**
         * AtomicLong是作用是对长整形进行原子操作。
            在32位操作系统中，64位的long 和 double 变量由于会被JVM当作两个分离的32位来进行操作，
            所以不具有原子性。而使用AtomicLong能让long的操作保持原子型
         */
        AtomicLong mAtoLong = new AtomicLong();

        mAtoLong.set(0x0123456789ABCDEFL);
        System.out.printf("%20s : 0x%016X\n", "get()", mAtoLong.get());
        System.out.printf("%20s : 0x%016X\n", "intValue()", mAtoLong.intValue());
        System.out.printf("%20s : 0x%016X\n", "longValue()", mAtoLong.longValue());
        System.out.printf("%20s : %s\n", "doubleValue()", mAtoLong.doubleValue());
        System.out.printf("%20s : %s\n", "floatValue()", mAtoLong.floatValue());

        System.out.printf("%20s : 0x%016X\n", "getAndDecrement()", mAtoLong.getAndDecrement());
        System.out.printf("%20s : 0x%016X\n", "decrementAndGet()", mAtoLong.decrementAndGet());
        System.out.printf("%20s : 0x%016X\n", "getAndIncrement()", mAtoLong.getAndIncrement());
        System.out.printf("%20s : 0x%016X\n", "incrementAndGet()", mAtoLong.incrementAndGet());

        System.out.printf("%20s : 0x%016X\n", "addAndGet(0x10)", mAtoLong.addAndGet(0x10));
        System.out.printf("%20s : 0x%016X\n", "getAndAdd(0x10)", mAtoLong.getAndAdd(0x10));

        System.out.printf("\n%20s : 0x%016X\n", "get()", mAtoLong.get());

        System.out.printf("%20s : %s\n", "compareAndSet()", mAtoLong.compareAndSet(0x12345679L, 0xFEDCBA9876543210L));
        System.out.printf("%20s : 0x%016X\n", "get()", mAtoLong.get());
    }
}
