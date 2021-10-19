package Pr3.Server;

import java.util.LinkedList;
import java.util.List;

public class MessageBuffer {
    public static List<String> messages = new LinkedList<>();

    public static void addMessage(String m) {
        synchronized (messages) {
            messages.add(m);
        }
    }

    public static List<String> injectMessages() {
        List<String> ret;
        synchronized (messages) {
            ret = messages;
            messages = new LinkedList<>();
        }
        return ret;
    }
}
