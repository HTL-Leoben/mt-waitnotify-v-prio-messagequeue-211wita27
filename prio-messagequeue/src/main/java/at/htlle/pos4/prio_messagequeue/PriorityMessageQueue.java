package at.htlle.pos4.prio_messagequeue;

import java.util.LinkedList;

public class PriorityMessageQueue {
    private final LinkedList<Message> prioQueue    = new LinkedList<>();
    private final LinkedList<Message> normalQueue  = new LinkedList<>();
    private final int capacity;

    public PriorityMessageQueue(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity > 0");
        this.capacity = capacity;
    }

    private int size() {
        return prioQueue.size() + normalQueue.size();
    }

    public synchronized void sendMessage(Message msg) throws InterruptedException {
        while (size() >= capacity) {
            wait();                       // voll → Producer warten
        }
        (msg.isPriority() ? prioQueue : normalQueue).addLast(msg);
        notifyAll();                      // evtl. wartende Consumer aufwecken
    }

    public synchronized Message receiveMessage() throws InterruptedException {
        while (size() == 0) {
            wait();                       // leer → Consumer warten
        }
        Message m = !prioQueue.isEmpty()
                ? prioQueue.removeFirst() // FIFO zuerst bei Prio-Liste
                : normalQueue.removeFirst();
        notifyAll();                      // evtl. wartende Producer aufwecken
        return m;
    }
}