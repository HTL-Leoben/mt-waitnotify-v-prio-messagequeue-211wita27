package at.htlle.pos4.prio_messagequeue;

import java.util.Random;

public class Consumer extends Thread {
    private static final Random RND = new Random();
    private final PriorityMessageQueue queue;

    public Consumer(String name, PriorityMessageQueue queue) {
        super(name);
        this.queue = queue;
    }

    @Override public void run() {
        while (!isInterrupted()) {
            try {
                Message m = queue.receiveMessage();
                System.out.printf("%s CONSUMED %s%n", getName(), m);
                Thread.sleep(300 + RND.nextInt(700));      // 300-1000 ms
            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }
}