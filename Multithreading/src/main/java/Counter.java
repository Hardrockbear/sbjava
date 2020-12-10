
public class Counter {
    private String lastThreadName = "";

    public static void main(String[] args) {
        Counter counter = new Counter();
        new Thread(() -> counter.count("thread0")).start();
        new Thread(() -> counter.count("thread1")).start();
    }

    private synchronized void count(String threadName) {
        for (int i = 1; i < 20; i++) {
            try {
                while (lastThreadName.equals(threadName)) {
                    this.wait();
                }

                System.out.println(String.format("%s prints %s", Thread.currentThread().getName(), i <= 10 ? i : 20 - i));

                lastThreadName = threadName;
                sleep();
                notifyAll();

            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(ex);
            }
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
