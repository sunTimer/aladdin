package workflow;

public class AuditEventTaskHandler implements EventTaskHandler {

    @Override
    public void handler(EventRunnable task) throws IllegalAccessException {
        if (task.getState() == EventRunnable.State.NEW) {
            task.setState(EventRunnable.State.EXECUTABLE);
            System.out.println("审核通过，当前状态：" + task.getState().name());
        } else {
            throw new IllegalAccessException("bad state " + task.getState());
        }
    }
}
