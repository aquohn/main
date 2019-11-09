package duke.ui.window;

import duke.data.Observation;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * UI window for the Observation context.
 */
public class ObservationContextWindow extends DukeDataContextWindow {
    private static final String FXML = "ObservationContextWindow.fxml";

    @FXML
    private Label objectivityLabel;

    private Observation observation;

    /**
     * Constructs a UI context window for an Observation object.
     *
     * @param observation Observation object.
     */
    public ObservationContextWindow(Observation observation) {
        super(FXML, observation);

        this.observation = observation;

        updateUi();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateUi() {
        super.updateUi();

        String objectivity = observation.isObjective() ? "Objective" : "Subjective";
        objectivityLabel.setText(objectivity);
    }
}
