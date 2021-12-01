package EvgenUlianov.HomeTask_1_1.Commands;

import EvgenUlianov.HomeTask_1_1.General.CompletionControl;
import EvgenUlianov.HomeTask_1_1.IO.IOStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Quit implements Command{

    @Autowired
    private CompletionControl completionControl;

    @Autowired
    private IOStream ioStream;

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
        completionControl.setMustBeCompleted(true);
        ioStream.println("Программа завершена");
    }
}
