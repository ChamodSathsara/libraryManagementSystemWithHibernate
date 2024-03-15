package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dto.AdminDto;
import lk.ijse.dto.UserDto;
import lk.ijse.service.AdminService;
import lk.ijse.service.UserService;
import lk.ijse.service.impl.AdminServiceImpl;
import lk.ijse.service.impl.UserServiceImpl;

import java.io.IOException;

public class LoginFormController {
    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtpassword;


    @FXML
    private AnchorPane loginPane;

    private static String position;

    @FXML
    void btnAdmin(ActionEvent event) {
        position = "Admin";
    }

    @FXML
    void btnForgatePassword(ActionEvent event) {

    }

    @FXML
    void btnLogin(ActionEvent event) {
        String userName = txtUsername.getText();
        String password = txtpassword.getText();
        if(position.equals("Admin")){
            AdminService adminService = AdminServiceImpl.getInstance();
            AdminDto adminDto = adminService.getAdmin(userName);

            if(adminDto.getPassword().equals(password)){

                Parent rootNode= null;
                try {
                    rootNode = FXMLLoader.load(this.getClass().getResource("/view/adminDashboard_form.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene scene = new Scene(rootNode);

                Stage stage = (Stage)this.loginPane.getScene().getWindow();
                stage.setTitle("Admin Dashboard");
                stage.centerOnScreen();
                stage.setScene(scene);

            }else{
                new Alert(Alert.AlertType.ERROR,"password Incorrect").show();
            }

        } else if (position.equals("User")) {
            UserService userService = UserServiceImpl.getInstance();
            UserDto userDto = userService.getUser(userName);

            if(userDto.getPassword().equals(password)){

                Parent rootNode= null;
                try {
                    rootNode = FXMLLoader.load(this.getClass().getResource("/view/userDashboard_form.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene scene = new Scene(rootNode);

                Stage stage = (Stage)this.loginPane.getScene().getWindow();
                stage.setTitle("Admin Dashboard");
                stage.centerOnScreen();
                stage.setScene(scene);

            }else {
                new Alert(Alert.AlertType.ERROR,"password Incorrect").show();
            }

        }else{
            new Alert(Alert.AlertType.ERROR,"please select your Position").show();
        }



    }

    @FXML
    void btnSignup(ActionEvent event) {

        Parent rootNode= null;
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/view/signup_form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(rootNode);

        Stage stage = (Stage)this.loginPane.getScene().getWindow();
        stage.setTitle("signUp");
        stage.centerOnScreen();
        stage.setScene(scene);

    }

    @FXML
    void btnUser(ActionEvent event) {
        position = "User";
    }

    @FXML
    void radioShowPassword(ActionEvent event) {

    }
}
