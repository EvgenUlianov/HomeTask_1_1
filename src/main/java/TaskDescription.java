public class TaskDescription {
    private String name;
    private boolean completed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public TaskDescription(String name) {
        this.name = name;
        this.completed = false;
    }

    public void toggle() {
        completed = !(completed);
    }
}
