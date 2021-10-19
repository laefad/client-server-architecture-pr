package Pr3.Server.Services;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import Pr3.Server.MessageBuffer;

public class Printer implements Runnable {

    private List<BufferedWriter> consumers;

    public Printer() {
        consumers = new LinkedList<>();
    }

    public void addConsumer(BufferedWriter consumer) {
        synchronized (consumers) {
            consumers.add(consumer);
        }
    }

    public void removeConsumer(BufferedWriter consumer) {
        synchronized (consumers) {
            consumers.remove(consumer);
        }
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignore) {}

            List<String> messages = MessageBuffer.injectMessages();

            if (messages.size() == 0)
                continue;

            var res = messages.stream().collect(Collectors.joining("\n"));

            synchronized (consumers) {
                var consumersCopy = consumers;
                consumersCopy.stream().forEach(
                    (consumer) -> {
                        try {
                            consumer.write(res);
                            consumer.flush();
                        } catch (IOException ignore) {
                            System.out.println("Consumer removed");
                            consumers.remove(consumer);
                        }
                    }
                );
            }
        }
    }
    
}
