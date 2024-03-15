package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardFormController {
    @FXML
    private AnchorPane adminDashboardPane;

    @FXML
    private AnchorPane allAdminPane;

    @FXML
    void btnBooksOnAction(ActionEvent event) {
        Parent rootNode = null;
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/view/adminbookManage_form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.allAdminPane.getChildren().clear();
        this.allAdminPane.getChildren().add(rootNode);
    }

    @FXML
    void btnBranchersOnAction(ActionEvent event) {
        Parent rootNode = null;
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/view/branchers_form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.allAdminPane.getChildren().clear();
        this.allAdminPane.getChildren().add(rootNode);
    }

    @FXML
    void btnCheckBarrowsOnAction(ActionEvent event) {
        Parent rootNode = null;
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/view/adminBarrowManage_form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.allAdminPane.getChildren().clear();
        this.allAdminPane.getChildren().add(rootNode);
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {

    }
}
