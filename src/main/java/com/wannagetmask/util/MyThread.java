package com.wannagetmask.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThread {

    private final ExecutorService threadPool;

    private MyThread() {
        this.threadPool = Executors.newFixedThreadPool(5);
    }

    private static class LazyHolder {
        private static final MyThread INSTANCE = new MyThread();
    }

    public static MyThread getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void run(Runnable task) {
        this.threadPool.submit(task);
    }

    public void shutdown() {
        this.threadPool.shutdownNow();
    }

}
