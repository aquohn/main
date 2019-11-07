package duke.ui.window;

import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXScrollPane;
import duke.data.DukeObject;
import duke.data.Patient;
import duke.exception.DukeFatalException;
import duke.ui.card.PatientCard;
import duke.ui.card.UiCard;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;

import java.util.ArrayList;
import java.util.List;

/**
 * UI window for the Home context.
 */
public class HomeContextWindow extends ContextWindow {
    private static final String FXML = "HomeContextWindow.fxml";

    @FXML
    private JFXMasonryPane patientListPanel;
    @FXML
    private ScrollPane scrollPane;

    private ArrayList<Patient> patientList;
    private List<DukeObject> indexedPatientList;

    /**
     * Constructs the Home UI window.
     *
     * @param patientList List of {@code Patient} objects.
     */
    public HomeContextWindow(ArrayList<Patient> patientList) throws DukeFatalException {
        super(FXML);
        this.patientList = patientList;
        JFXScrollPane.smoothScrolling(scrollPane);
        updateUi();
    }

    /**
     * Fills {@code indexedPatientList} and {@code patientListPanel}.
     */
    private void fillPatientList() throws DukeFatalException {
        indexedPatientList = new ArrayList<>(patientList);
        patientListPanel.getChildren().clear();
        indexedPatientList.forEach(patient -> {
            PatientCard patientCard = new PatientCard((Patient) patient);
            patientCard.setIndex(patientList.indexOf(patient) + 1);
            patientListPanel.getChildren().add(patientCard);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateUi() throws DukeFatalException {
        fillPatientList();
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public List<DukeObject> getIndexedList(String type) {
        return indexedPatientList;
    }

    /**
     * Attaches a listener to the patient hashmap.
     * This listener updates the {@code patientListPanel} whenever the patient hashmap is updated.
     */
    //private void attachPatientListListener() {
    //   patientObservableMap.addListener((MapChangeListener<String, Patient>) change -> {
    //       fillPatientList();
    //   });
    //}
}
