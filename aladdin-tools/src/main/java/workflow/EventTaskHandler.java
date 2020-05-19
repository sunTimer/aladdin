package workflow;

public interface EventTaskHandler {
    void handler(EventRunnable task) throws Exception;
}
