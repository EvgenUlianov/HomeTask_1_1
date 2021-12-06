package IO;


import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class IOStream{

    BufferedReader reader;

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

    // SingleTone ++

    private IOStream(){reader = new BufferedReader(new InputStreamReader(System.in));}

    private static class Holder {
        public static final IOStream IO_STREAM = new IOStream();
    }

    public static IOStream get()  {
        return IOStream.Holder.IO_STREAM;
    }

    // SingleTone --

}
