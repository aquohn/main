package duke.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class DoWithinPeriodTask extends EventTask {



    public DoWithinPeriodTask(String name, LocalDateTime at, LocalDateTime endTime) {
        super(name, at, endTime);
        type = 'B';
    }

    @Override
    public String toString() throws DateTimeException {
        return super.toString() + " (between: " + getTime() + " and " + getEndTime() + ")";
    }
}
