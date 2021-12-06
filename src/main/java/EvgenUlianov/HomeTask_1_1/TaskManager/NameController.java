package EvgenUlianov.HomeTask_1_1.TaskManager;

import EvgenUlianov.HomeTask_1_1.IO.IOStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NameController {

    @Autowired
    private IOStream ioStream;

    public boolean checkName (String name) {
        if (name.length() == 0) {
            String msg = "Не указано наименование";
            ioStream.println(msg);
            log.error(msg);
            return true;
        }
        return false;
    }
}
