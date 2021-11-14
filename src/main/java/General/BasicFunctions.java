package General;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BasicFunctions {
    private BasicFunctions(){};

    public static boolean checkName (String name) {
        if (name.length() == 0) {
            printAndLog("Не указано наименование");
            return true;
        }
        return false;
    }

    public static Integer getNumber(String stringNumber) {
        if (stringNumber.length() == 0) {
            printAndLog("Не указан номер");
            return null;
        }

        int number;
        try {
            number = Integer.parseInt(stringNumber);
        } catch (NumberFormatException ex) {
            log.error(ex.getMessage(), ex);
            ex.printStackTrace();
            return null;
        }
        return number;
    }

    public static void printAndLog(String msg){
        System.out.println(msg);
        log.error(msg);
        /*
        может-быть пригодится когда-нибудь
        if (Main.isLoggingEnabled()) {

            StackTraceElement[] trace = Thread.currentThread().getStackTrace();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(msg);
            if (trace.length > 2) {
                stringBuilder.append("\n");
                IntStream.range(2, trace.length)
                        .forEach((index) -> {
                            String traceText = trace[index].toString();
                            stringBuilder.append("\t\t");
                            stringBuilder.append(traceText);
                            stringBuilder.append("\n");
                        });
            }
        }*/
    }

}
