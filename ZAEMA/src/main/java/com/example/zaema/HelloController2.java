package com.example.zaema;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class HelloController2 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackToView;

    @FXML
    private DatePicker BirthDate;

    @FXML
    private Button Enter_On;

    @FXML
    private CheckBox Female;

    @FXML
    private TextField FirstName;

    @FXML
    private TextField LastName;

    @FXML
    private CheckBox Male;

    @FXML
    private TextField Married;

    @FXML
    private TextField PassportBy;

    @FXML
    private TextField PassportCode;

    @FXML
    private TextField PassportSN;

    @FXML
    private TextField Post;

    @FXML
    void initialize() {

        DatabaseHandler dbHandler = new DatabaseHandler();
        BackToView.setOnAction(actionEvent -> {
            BackToView.getScene().getWindow().hide();

            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(getClass().getResource("hello-view.fxml"));
            try{
                loader2.load();
            }catch (IOException ex){
                ex.printStackTrace();
            }
            Parent root = loader2.getRoot();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root));
            stage2.showAndWait();
        });

        Enter_On.setOnAction(actionEvent -> {
            try {
                String gender = "not selected";
                if (Male.isSelected() && !Female.isSelected()){
                    gender = "Male";
                }else if(Female.isSelected() && !Male.isSelected()){
                    gender = "Female";
                }else if(Female.isSelected() && Male.isSelected()) {
                    gender = "IT";
                }else if(!Female.isSelected() && !Male.isSelected()) {
                    gender = "not selected";
                }
                String finalGender = gender;
                System.out.println("Почти Заебись");
                dbHandler.signUpUser(LastName.getText(),FirstName.getText(),Post.getText(),
                        BirthDate.getValue().toString(),PassportSN.getText(),PassportBy.getText(),
                        PassportCode.getText(),Married.getText(), finalGender);
                System.out.println("Заебись");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Прошел");
        });


    }

}
