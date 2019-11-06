package duke.command;

import duke.DukeCore;
import duke.exception.DukeException;

public class BackSpec extends CommandSpec {
    private static final BackSpec spec = new BackSpec();

    public static BackSpec getSpec() {
        return spec;
    }

    @Override
    public void execute(DukeCore core) throws DukeException {
        core.uiContext.moveBackOneContext();
    }
}