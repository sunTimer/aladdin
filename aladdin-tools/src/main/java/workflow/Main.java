package workflow;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        EventRunnable eventTask = new EventRunnable(new Runnable() {
            @Override
            public void run() {
                System.out.println("========任务开始");
                System.out.println("========任务执行ing");
                System.out.println("========任务结束");

            }
        }, "test", "channel");

        System.out.println("创建任务：");
        System.out.println("任务名称：" + eventTask.getName());
        System.out.println("任务组别：" + eventTask.getGroup());
        System.out.println("任务状态：" + eventTask.getState().name());


        List<EventTaskHandler> eventHandlers = new ArrayList<>();
        eventHandlers.add(new AuditEventTaskHandler());
        eventHandlers.add(new ExecuteEventTaskHandler());

        for (EventTaskHandler eventHandler : eventHandlers) {
            eventHandler.handler(eventTask);
        }
    }

    public void demo() {
        new Thread();
    }
}
