package Commands;


//Pattern strategy for abstract class Command
public interface Command {

    public abstract String getName();

    public abstract String getDescription();

    public abstract void accept(String argumentCommandWord);

}
