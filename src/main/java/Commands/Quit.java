package Commands;

import General.CompletionControl;

public class Quit implements Command{

    @Override
    public String getName() {
        return "quit";
    }

    @Override
    public String getDescription() {
        return "quit";
    }

    @Override
    public void accept(String argumentCommandWord) {
        CompletionControl completionControl = CompletionControl.get();
        completionControl.setMustBeCompleted(true);
        System.out.println("Программа завершена");
    }
}
