package CallCentr;

import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        //ATC
        new Thread(() -> {
            int i = 1;
            while (true) {
                try {
                    queue.put(i++);
                    System.out.println("ATC has generated: " + i);
                    queue.put(i++);
                    System.out.println("ATC has generated: " + i);
                    //System.out.println("The queue of clients: " + queue);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //expert1
        new Thread(() -> {
            if (queue.isEmpty()) {
                System.out.println("Waiting...");
            }
            while (true) {
                try {
                    int j = queue.take();
                    System.out.println("First expert has taken: " + j);
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //expert2
        new Thread(() -> {
            if (queue.isEmpty()) {
                System.out.println("Waiting...");
            }
            while (true) {
                try {
                    int k = queue.take();
                    System.out.println("Second expert has taken: " + k);
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
