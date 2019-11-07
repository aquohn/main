package duke.command.patient;

import duke.DukeCore;
import duke.command.ArgLevel;
import duke.command.ArgSpec;
import duke.command.Switch;
import duke.data.Patient;
import duke.exception.DukeException;

public class PatientDeleteSpec extends ArgSpec {
    private static final PatientDeleteSpec spec = new PatientDeleteSpec();

    public static PatientDeleteSpec getSpec() {
        return spec;
    }

    private PatientDeleteSpec() {
        cmdArgLevel = ArgLevel.REQUIRED;
        initSwitches(
                new Switch("critical", String.class, true, ArgLevel.OPTIONAL, "c"),
                new Switch("investigation", String.class, true, ArgLevel.OPTIONAL, "in"),
                new Switch("impression", String.class, true, ArgLevel.OPTIONAL, "im"),
        );
    }

    @Override
    protected void execute(DukeCore core) throws DukeException {
    super.execute(core);
        Patient patient = (Patient) core.uiContext.getObject();
        String searchCritical = cmd.getSwitchVal("critical");
        String searchInvestigation = cmd.getSwitchVal("investigation");
        String searchImpression = cmd.getSwitchVal("impression");

        if (searchCritical != null && (patient.getPrimaryDiagnosis()) != null) {
            if (patient.getPrimaryDiagnosis().getName().equals((searchCritical))) {
                patient.deletePriDiagnose();
                core.updateUi("Successfully deleted " + searchCritical);
            } else {
                core.updateUi("Unsuccessfully deleted patient's primary diagnosis does not match " + searchCritical);
            }
        } else if (searchCritical != null) {
            core.updateUi("Patient does not have a primary diagnosis.");
        }

        if (searchInvestigation != null) {
            // TODO
            core.updateUi("Not implemented yet, bug when adding treatments needs to be solved first");
            //core.ui.print("Successfully deleted " + searchInvestigation);
        }

        // TODO proper searching
        if (searchImpression != null) {
            if (patient.getImpression(searchImpression) != null) {
                patient.deleteImpression(searchImpression);
                core.updateUi("Successfully deleted " + searchImpression);
            } else {
                core.updateUi("Unsuccessfully deleted, none of patient's impressions match " + searchImpression);
            }
        }
    }
}
