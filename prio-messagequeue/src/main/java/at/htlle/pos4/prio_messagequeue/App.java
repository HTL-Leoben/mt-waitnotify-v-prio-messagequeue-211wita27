package at.htlle.pos4.prio_messagequeue;

// Mario Ziegerhofer 08.05.2025


public class App {
    public static void main(String[] args) throws InterruptedException {
        PriorityMessageQueue queue = new PriorityMessageQueue(10);


        Thread p1 = new Producer("Producer-1", queue);
        Thread p2 = new Producer("Producer-2", queue);
        Thread c1 = new Consumer("Consumer-1", queue);
        Thread c2 = new Consumer("Consumer-2", queue);

        p1.start(); p2.start(); c1.start(); c2.start();

        // wie lage die app laufen soll (10sek)
        Thread.sleep(10_000);

        p1.interrupt(); p2.interrupt(); c1.interrupt(); c2.interrupt();
    }
}