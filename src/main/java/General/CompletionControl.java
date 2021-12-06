package General;

import lombok.Data;

@Data
public class CompletionControl {

    //Encapsulation
    private boolean mustBeCompleted;

    // SingleTone ++

    private CompletionControl(){

        mustBeCompleted = false;
    }

    private static class Holder {
        public static final CompletionControl COMPLETION_CONTROL = new CompletionControl();
    }

    public static CompletionControl get()  {
        return CompletionControl.Holder.COMPLETION_CONTROL;
    }

    // SingleTone --
}
