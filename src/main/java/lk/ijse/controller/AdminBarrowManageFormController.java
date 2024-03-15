package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AdminBarrowManageFormController {
    @FXML
    private TableView<?> tblHistory;

    @FXML
    private TableColumn<?, ?> collUserName;

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
}
