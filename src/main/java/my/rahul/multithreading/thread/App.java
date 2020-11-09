package my.rahul.multithreading.thread;

class MyTask {
    void run() {
        for (int doc = 1; doc <= 10; doc++) {
            System.out.println("Printing from printer 2 document #" + doc);
        }
    }
}

class SequentialExecutor {
    void go() {
        MyTask task = new MyTask();
        task.run();
    }
}

public class App {
    public static void main(String[] args) {
        System.out.println("== Main Thread started ==");

        SequentialExecutor executor = new SequentialExecutor();
        executor.go();

        for (int doc = 1; doc <= 10; doc++) {
            System.out.println("Printing from printer 1 \uD83D\uDDA8 document #" + doc);
        }

        System.out.println("== Main Thread ended ==");
    }
}
