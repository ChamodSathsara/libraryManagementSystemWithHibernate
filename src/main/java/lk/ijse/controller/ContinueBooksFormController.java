package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ContinueBooksFormController {

    @FXML
    private AnchorPane returnBookPane;

    @FXML
    private TableView<?> tbtReturnBooks;

    @FXML
    private TableColumn<?, ?> collNo;

    @FXML
    private TableColumn<?, ?> collBookName;

    @FXML
    private TableColumn<?, ?> collBarrowDate;

    @FXML
    private TableColumn<?, ?> collReturnDate;

    @FXML
    private TableColumn<?, ?> collLatePayment;

    @FXML
    private TableColumn<?, ?> collFullPayment;

    @FXML
    private TableColumn<?, ?> collReturn;
}
