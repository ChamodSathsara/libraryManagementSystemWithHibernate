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

public class SignupFormController {
    @FXML
    private AnchorPane signUpPane;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtpassword;

    @FXML
    private TextField txtComfirmPassword;

    private static String position;

    @FXML
    void btnAdmin(ActionEvent event) {
        position = "Admin";
    }

    @FXML
    void btnSignup(ActionEvent event) {

        String userName = txtUsername.getText();
        String eMail = txtEmail.getText();
        String password = txtpassword.getText();
        String confirmPassword = txtComfirmPassword.getText();

        if(password.equals(confirmPassword)){
            if (position.equals("Admin")){
                AdminDto adminDto = new AdminDto(userName,eMail,password);

                AdminService adminService = AdminServiceImpl.getInstance();
                boolean isSave = adminService.saveAdmin(adminDto);

                if (isSave){

                    Parent rootNode= null;
                    try {
                        rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Scene scene = new Scene(rootNode);

                    Stage stage = (Stage)this.signUpPane.getScene().getWindow();
                    stage.setTitle("LogIn");
                    stage.centerOnScreen();
                    stage.setScene(scene);

                }
                else {
                    new Alert(Alert.AlertType.ERROR,"Signup Failed").show();
                }

            } else if (position.equals("User")) {
                UserDto userDto = new UserDto(userName,eMail,password);

                UserService userService = UserServiceImpl.getInstance();
                boolean isSave = userService.saveUser(userDto);

                if (isSave){

                    Parent rootNode= null;
                    try {
                        rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Scene scene = new Scene(rootNode);

                    Stage stage = (Stage)this.signUpPane.getScene().getWindow();
                    stage.setTitle("LogIn");
                    stage.centerOnScreen();
                    stage.setScene(scene);

                }
                else {
                    new Alert(Alert.AlertType.ERROR,"Signup Failed").show();
                }

            }else {
                new Alert(Alert.AlertType.ERROR,"please select your Position").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Password Confirmation Failed").show();
        }
    }

    @FXML
    void btnUser(ActionEvent event) {
        position = "User";
    }

}
