package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;

public class BarrowHistoryFormController {

    @FXML
    private AnchorPane historyPane;

    @FXML
    private TableView<?> tblHistory;

    @FXML
    private TableColumn<?, ?> collBookId;

    @FXML
    private TableColumn<?, ?> collBookTopic;

    @FXML
    private TableColumn<?, ?> collBarrowDate;

    @FXML
    private TableColumn<?, ?> CollReturnDate;

    @FXML
    private TableColumn<?, ?> collReturnDays;

    @FXML
    private TableColumn<?, ?> collFullPayment;

    public BarrowHistoryFormController() {
    }
}
