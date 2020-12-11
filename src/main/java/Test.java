import java.util.UUID;

public class Test{


    @org.junit.Test
    public void test(){
        System.out.println(UUID.randomUUID().toString());
    }

    public static void main(String[] args) {
        System.out.println(-9 % 5);
        Thread thread = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(100);
                        System.out.println(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(1/0);
            }
        });
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(e);
                System.out.println("--------------------");
            }
        });
        thread.start();
    }




    /*
    *
    * /*/

    /**/

    @org.junit.Test
    public void testArrayAddressInMem(){
        String key = "name";
        for (char c : key.toCharArray()) {
            byte c1 = (byte) c;;
            System.out.println(c1);
        }
    }


}