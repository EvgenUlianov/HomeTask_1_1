package General;

import lombok.Data;

@Data
public class WordDelimiter {
    //Encapsulation
    private final String firstWord;
    //Encapsulation
    private final String secondWord;

    public WordDelimiter(String commandName) {
        final char SPACE = ' ';
        final int spacePosition = commandName.indexOf(SPACE);
        if (spacePosition > 0){
            this.firstWord = commandName.substring(0, spacePosition).trim().toLowerCase();
            this.secondWord = commandName.substring(spacePosition + 1).trim().toLowerCase();
        } else {
            this.firstWord = commandName.trim().toLowerCase();
            this.secondWord = "";
        }
    }

}
