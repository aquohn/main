package duke.command.impression;

import duke.DukeCore;
import duke.command.ArgCommand;
import duke.command.ArgSpec;
import duke.data.Impression;
import duke.data.Investigation;
import duke.data.Medicine;
import duke.data.Plan;
import duke.data.Treatment;
import duke.exception.DukeException;
import duke.exception.DukeHelpException;

import java.util.List;

public class ImpressionStatusCommand extends ArgCommand {

    @Override
    protected ArgSpec getSpec() {
        return ImpressionStatusSpec.getSpec();
    }

    @Override
    public void execute(DukeCore core) throws DukeException {
        Impression impression = ImpressionUtils.getImpression(core);
        Treatment treatment = (Treatment) ImpressionUtils.getData(null, null, getArg(), impression);
        List<String> statusList;
        Class targetClass = treatment.getClass(); //statics don't play nice with polymorphism
        if (targetClass == Medicine.class) {
            statusList = Medicine.getStatusArr();
        } else if (targetClass == Investigation.class) {
            statusList = Investigation.getStatusArr();
        } else if (targetClass == Plan.class) {
            statusList = Plan.getStatusArr();
        } else {
            throw new DukeException("This is not a recognised treatment!");
        }

        int status;
        String statusStr = getSwitchVal("set");
        if (statusStr == null) {
            status = treatment.getStatusIdx() + 1;
            if (status >= statusList.size()) {
                throw new DukeHelpException("This treatment cannot progress any further!", this);
            }
        } else {
            status = ImpressionUtils.processStatus(statusStr, statusList);
        }
        treatment.setStatusIdx(status);

        core.writeJsonFile();
        core.ui.print("Status of '" + treatment.getName() + "' updated to '" + statusList.get(status) + "'");
    }
}
