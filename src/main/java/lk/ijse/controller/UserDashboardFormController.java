package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dto.BranchDto;
import lk.ijse.dto.tm.BranchTm;
import lk.ijse.service.BookService;
import lk.ijse.service.BranchService;
import lk.ijse.service.impl.BookServiceImpl;
import lk.ijse.service.impl.BranchServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class UserDashboardFormController implements Initializable {
    @FXML
    private AnchorPane paneMany;

    @FXML
    private AnchorPane dashboardPane;

    @FXML
    private TableView<BranchTm> tblCatagories;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colCount;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colVisitBook;

    public static int cetegoryId;


    @FXML
    private Label lblDateAndTime;

    @FXML
    private Label lblUserName;

    @FXML
    void btnContinueOnAction(ActionEvent event) {
        Parent rootNode = null;
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/view/continueBooks_form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.paneMany.getChildren().clear();
        this.paneMany.getChildren().add(rootNode);
    }

    @FXML
    void btnHistoryOnAction(ActionEvent event) {
        Parent rootNode = null;
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/view/barrowHistory_form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.paneMany.getChildren().clear();
        this.paneMany.getChildren().add(rootNode);
    }

    @FXML
    void btnLibraryOnAction(ActionEvent event) {
        Parent rootNode= null;
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/view/userDashboard_form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(rootNode);

        Stage stage = (Stage)this.dashboardPane.getScene().getWindow();
        stage.setTitle("User Dashboard");
        stage.centerOnScreen();
        stage.setScene(scene);
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnResetPasswordOnAction(ActionEvent event) {

    }


    private void loadAllItems() {
        BranchService branchService = BranchServiceImpl.getInstance();
        try{
            List<BranchDto> BranchesList = branchService.getAllBranch();
            System.out.println("Ammoooo");
            ObservableList<BranchTm> obList = FXCollections.observableArrayList();
            AtomicInteger num = new AtomicInteger();
            for (BranchDto branchDto : BranchesList){
                System.out.println(branchDto);
                Button btn = new Button("Visit Books");
                btn.setCursor(Cursor.HAND);
                System.out.println("Ammoooo 2222222");

                btn.setOnAction((e) ->{
                    ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("no",ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION,"Are you sure to view Books? ",yes,no).showAndWait();

                    if (type.orElse(no) == yes){
                        int selectedIndex = tblCatagories.getSelectionModel().getSelectedIndex();
                        int id = (int)colId.getCellData(selectedIndex);
                        num.set(id);
                        visitBook(id);
                    }
                });
                BranchTm branchTm = new BranchTm(
                        branchDto.getBranchId()
                        ,branchDto.getBranchName()
                        ,getCount(num.get())
                        ,branchDto.getDescription()
                        ,btn
                );
                obList.add(branchTm);
            }
            System.out.println("Ammoooo 33333333333333");
            tblCatagories.setItems(obList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private int getCount(int id) {
        BookService bookService = BookServiceImpl.getInstance();

        int count = bookService.getCount(id);
        return count;
    }

    private void visitBook(int id) {
        cetegoryId = id;

        Parent rootNode = null;
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/view/books_form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.paneMany.getChildren().clear();
        this.paneMany.getChildren().add(rootNode);

    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("branchId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("branchName"));
        colCount.setCellValueFactory(new PropertyValueFactory<>("count"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colVisitBook.setCellValueFactory(new PropertyValueFactory<>("visitBooks"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllItems();
    }
}
