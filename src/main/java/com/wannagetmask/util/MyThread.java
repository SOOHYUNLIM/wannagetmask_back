package com.wannagetmask.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class MyThread {

    private final ExecutorService threadPool;

    public MyThread() {
        this.threadPool = Executors.newFixedThreadPool(10);
    }

    public void run(Runnable task) {
        this.threadPool.submit(task);
    }

    public void shutdown() {
        this.threadPool.shutdownNow();
    }

}
