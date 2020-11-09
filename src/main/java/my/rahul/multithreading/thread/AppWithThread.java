package my.rahul.multithreading.thread;

class ThreadTask extends Thread {
    @Override
    public void run() {
        for (int doc = 1; doc <= 10; doc++) {
            System.out.println("Printing from printer 2 document #" + doc);
        }
    }
}

class RunnableThread implements Runnable {
    @Override
    public void run() {
        for (int doc = 1; doc <= 10; doc++) {
            System.out.println("Printing from printer 2 document #" + doc);
        }
    }
}

class ParallelExecutor {

    // uses Thread Extended class
    void go() {
        ThreadTask task = new ThreadTask();
        task.start();  // internally executes run()
    }

    // uses implemented Runnable interface
    void goWithRunnable() {
        Runnable r = new RunnableThread();
        Thread task = new Thread(r);
        task.start();
    }
}

public class AppWithThread {
    public static void main(String[] args) {
        System.out.println("== Main Thread started ==");

        ParallelExecutor executor = new ParallelExecutor();
        executor.goWithRunnable();

        for (int doc = 1; doc <= 10; doc++) {
            System.out.println("Printing from printer 1 \uD83D\uDDA8 document #" + doc);
        }

        System.out.println("== Main Thread ended ==");
    }
}
