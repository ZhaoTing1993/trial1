package pool.mythreadpool;

import java.util.LinkedList;

/**
 * 线程池实现类
 * Created by ZHAOTING001 on 2018/4/11.
 */
public class MyThreadPool implements Pool {

    private boolean isShut;
    private LinkedList pool;
    private int size;

    public MyThreadPool(int size) {
        System.out.println("MyThreadPool instance created, size: " + size);
        this.size = size;
        isShut = false;
        pool = new LinkedList();
        for (int i = 0; i < size; i++) {
            MyExecutor executor = new MyExecutorImpl();
            pool.add(executor);
            ((MyExecutorImpl) executor).start();
        }
    }

    @Override
    public MyExecutor getExecutor() {
        MyExecutor ret = null;
        synchronized (pool) {
            if (pool.size() > 0) {
                ret = (MyExecutor) pool.removeFirst();
            } else {
                synchronized (pool) {
                    try {
                        pool.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                ret = (MyExecutor) pool.removeFirst();
            }
        }
        return ret;
    }

    @Override
    public void destroy() {
        System.out.println("ThreadPool destroying...");
        synchronized (pool) {
            isShut = true;
            pool.notifyAll();
            pool.clear();
        }
        System.out.println("ThreadPool destroyed");
    }

    private class MyExecutorImpl extends Thread implements MyExecutor {

        private MyTask task;
        private Object lock = new Object();
        //private boolean loop = true;

        public MyExecutorImpl() {
        }

        @Override
        public void setTask(MyTask task) {
            this.task = task;
        }

        @Override
        public MyTask getTask() {
            return this.task;
        }

        @Override
        public void startTask() {
            synchronized (lock) {
                lock.notify();
            }
        }

        public void run() {
            System.out.println("an executor instance started");

            while (!isShut) {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                getTask().execute();
                synchronized (pool) {
                    pool.addFirst(this);
                    pool.notifyAll();
                }
            }
        }
    }
}
