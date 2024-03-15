package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.BranchDto;
import lk.ijse.dto.tm.BranchTm;
import lk.ijse.service.BranchService;
import lk.ijse.service.impl.BranchServiceImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BranchersFormController implements Initializable {

    @FXML
    private AnchorPane branchPane;

    @FXML
    private TextField txtBranchName;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtNextId;

    @FXML
    private TableView<BranchTm> tblBranchers;

    @FXML
    private TableColumn<?, ?> colBranchNo;

    @FXML
    private TableColumn<?, ?> BranchName;

    @FXML
    private TableColumn<?, ?> CollDescription;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String name = txtBranchName.getText();

        BranchService branchService = BranchServiceImpl.getInstance();
        int pk = branchService.getPk(name);
        BranchDto branchDto = branchService.getBranch(pk);

        boolean isDelete = branchService.deleteBranch(branchDto);
        if (isDelete){
            new Alert(Alert.AlertType.CONFIRMATION,"Successfully Delete").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Something wrong").show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String name = txtBranchName.getText();
        String description = txtDescription.getText();
        BranchDto branchDto = new BranchDto(1,name,description);

        BranchService branchService = BranchServiceImpl.getInstance();
        boolean isSave = branchService.saveBranch(branchDto);

        if (isSave){
            new Alert(Alert.AlertType.CONFIRMATION,"Successfully Adding").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Something wrong").show();
        }
        tblBranchers.refresh();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String name = txtBranchName.getText();
        String description = txtDescription.getText();

        BranchService branchService = BranchServiceImpl.getInstance();
        int pk = branchService.getPk(name);
        BranchDto branchDto = new BranchDto(pk,name,description);

        boolean isUpdate = branchService.updateBranch(branchDto);
        if (isUpdate){
            new Alert(Alert.AlertType.CONFIRMATION,"Successfully Updating").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Something wrong").show();
        }
        tblBranchers.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllItems();
    }

    private void loadAllItems() {
        BranchService branchService = BranchServiceImpl.getInstance();
        try{
            List<BranchDto> branchDtoList = branchService.getAllBranch();
            ObservableList<BranchTm> observableList = FXCollections.observableArrayList();

            for (BranchDto branchDto : branchDtoList){
                BranchTm branchTm = new BranchTm(
                        branchDto.getBranchId()
                        ,branchDto.getBranchName()
                        ,branchDto.getDescription()
                );
                observableList.add(branchTm);
            }
            tblBranchers.setItems(observableList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setCellValueFactory() {
        colBranchNo.setCellValueFactory(new PropertyValueFactory<>("branchId"));
        BranchName.setCellValueFactory(new PropertyValueFactory<>("branchName"));
        CollDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    }
}
