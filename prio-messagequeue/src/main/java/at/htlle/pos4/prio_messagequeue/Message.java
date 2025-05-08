package at.htlle.pos4.prio_messagequeue;

public class Message {
    private final boolean priority;
    private final String content;

    public Message(boolean priority, String content) {
        this.priority = priority;
        this.content  = content;
    }

    public boolean isPriority() {
        return priority;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",
                priority ? "PRIO" : "NORMAL", content);
    }
}