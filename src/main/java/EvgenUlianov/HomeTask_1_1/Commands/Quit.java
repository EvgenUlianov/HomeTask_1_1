package EvgenUlianov.HomeTask_1_1.Commands;

import EvgenUlianov.HomeTask_1_1.General.CompletionControl;
import EvgenUlianov.HomeTask_1_1.IO.IOStream;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Quit implements Command{

    final private CompletionControl completionControl;
    final private IOStream ioStream;

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
