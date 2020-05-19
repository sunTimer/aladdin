package workflow;

public class EventRunnable implements Runnable {

    private Runnable target;

    private State state;

    private String name;

    private String group;

    public EventRunnable(Runnable target, String name, String group) {
        this.target = target;
        this.state = State.NEW;
        this.name = name;
        this.group = group;
    }

    @Override
    public void run() {
        target.run();
    }

    public enum State {
        NEW,
        EXECUTABLE,
        EXECUTED,
        TERMINAL
    }


    public Runnable getTarget() {
        return target;
    }

    public void setTarget(Runnable target) {
        this.target = target;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}

