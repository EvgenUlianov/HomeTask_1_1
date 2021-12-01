package EvgenUlianov.HomeTask_1_1.IO;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@Service
public class IOStream{

    BufferedReader reader;
    {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void println(String s){
        System.out.println(s);
    }

    public String readLine(){
        String inputString = null;
        try {
            inputString = reader.readLine();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return inputString;
    }
}
