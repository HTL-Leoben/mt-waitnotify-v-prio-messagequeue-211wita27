package at.htlle.pos4.prio_messagequeue;

import java.util.Random;

public class Producer extends Thread {
    private static final Random RND = new Random();
    private final PriorityMessageQueue queue;

    public Producer(String name, PriorityMessageQueue queue) {
        super(name);
        this.queue = queue;
    }

    @Override public void run() {
        int counter = 1;
        while (!isInterrupted()) {
            boolean prio = RND.nextBoolean();              // 50 % Chance
            String  txt  = String.format("%s-Msg-%d", getName(), counter++);
            Message m    = new Message(prio, txt);
            try {
                queue.sendMessage(m);
                System.out.printf("%s PRODUCED %s%n", getName(), m);
                Thread.sleep(200 + RND.nextInt(800));     //irgendwas zischen 200 und 1000ms
            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }
}