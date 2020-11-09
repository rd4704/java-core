package my.rahul.multithreading.thread;

import java.util.concurrent.*;

class Printer {
    // Synchronize the resource
    synchronized void print(int copies, String name) {
        for (int i = 1; i <= copies; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(">> Printing document " + name + " copy #" + i);
        }
    }
}

class AppleThread extends Thread {
    Printer pref;

    AppleThread(Printer p) {
        pref = p;
    }

    @Override
    public void run() {
        pref.print(5, "\uD83C\uDF4E Apple.pdf");

        /* This is an another way to synchronize the thread directly
        synchronized (pref) {
            pref.print(10, "\uD83C\uDF4E Apple.pdf");
        }
        */
    }
}

class MangoThread extends Thread {
    Printer pref;

    MangoThread(Printer p) {
        pref = p;
    }

    @Override
    public void run() {
        pref.print(10, "\uD83E\uDD6D Mango.pdf");
    }
}

public class MultiThreadedApp {
    public static void main(String[] args) {
        System.out.println("== Main Thread started ==");

        Printer printer = new Printer();

        // Multiple threads working on a shared resource Printer

        AppleThread apple = new AppleThread(printer);
        MangoThread mango = new MangoThread(printer);

        runThreads(apple, mango);

        runThreadPool(apple, mango);

        runCachedThreadPool(apple, mango);

        runScheduledThreadPool(apple, mango);

        /* Synchronise the thread
        try {
            apple.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        System.out.println("== Main Thread ended ==");
    }

    private static void runThreads(Thread t1, Thread t2) {
        t1.start();
        t2.start();
    }

    private static void runThreadPool(Runnable t1, Runnable t2) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        executor.submit(t1);
        executor.submit(t2);
    }

    private static void runCachedThreadPool(Runnable t1, Runnable t2) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        executor.submit(t1);
        executor.submit(t2);
    }

    private static void runScheduledThreadPool(Runnable t1, Runnable t2) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        executor.schedule(t1, 500, TimeUnit.MILLISECONDS);
        executor.schedule(t2, 500, TimeUnit.MILLISECONDS);
    }
}
