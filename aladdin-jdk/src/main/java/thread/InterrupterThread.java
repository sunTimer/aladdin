package thread;

public class InterrupterThread {

    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    System.out.println("inddd");
                    return;
                }
            }
        });

        thread.start();

        thread.interrupt();
    }
}
