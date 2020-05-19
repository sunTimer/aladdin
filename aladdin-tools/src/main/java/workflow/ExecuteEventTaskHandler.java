package workflow;

public class ExecuteEventTaskHandler implements EventTaskHandler {
    @Override
    public void handler(EventRunnable task) throws Exception {
        if (task.getState() == EventRunnable.State.EXECUTABLE) {
            task.run();
            task.setState(EventRunnable.State.EXECUTED);
            System.out.println("执行完毕，当前状态：" + task.getState().name());
        } else {
            throw new IllegalAccessException("bad state " + task.getState());
        }
    }
}
