package EvgenUlianov.HomeTask_1_1.General;

import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class CompletionControl {
    private boolean mustBeCompleted = false;
}
