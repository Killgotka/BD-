package com.example.zaema;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.mysql.cj.xdevapi.Table;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private TableColumn<?, ?> FirstName;

    @FXML
    private TableColumn<?, ?> Post;

    @FXML
    private TableView<?> Table;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox DataBaseView;

    @FXML
    private Button Registr_On;

    @FXML
    private Button Reset;

    @FXML
    private Button Search;

    @FXML
    private TextField SearchField;
    @FXML
    private TextArea Textfield;
    @FXML
    private Button delete;
    @FXML
    private Button load;
    @FXML
    private Button DeletUser;
    @FXML
    private Button SortBy;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();


        Search.setOnAction(actionEvent ->{
            String SearchTask = SearchField.getText().trim();
            System.out.println(SearchTask);
            if (!SearchTask.equals("")) {
                try {
                    String SearchResult;
                    SearchResult = String.join("\n", databaseHandler.SearchUser(SearchTask));
                    Textfield.clear();
                    Textfield.setText(SearchResult);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }else{
                System.out.println("Empty");
            }

        });
        Registr_On.setOnAction(actionEvent -> {
            Registr_On.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("2 space.fxml"));
            try{
                loader.load();
            }catch (IOException e){
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
        Reset.setOnAction(actionEvent -> {
            SearchField.clear();
        });
        delete.setOnAction(actionEvent -> {
            Textfield.clear();
        });
        load.setOnAction(actionEvent -> {
            String listString;

            try {
                listString = String.join("\n", databaseHandler.LoadDB());

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            Textfield.clear();
            Textfield.setText(listString);
        });
        DeletUser.setOnAction(actionEvent -> {
            try {

                databaseHandler.DeletUsers(SearchField.getText().trim());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        SortBy.setOnAction(actionEvent -> {
            String SearchResult = null;
            String ST = SearchField.getText().trim();
            try {

                SearchResult = String.join("\n", databaseHandler.Order_By(ST));
                System.out.println(SearchResult);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            Textfield.clear();
            Textfield.setText(SearchResult);
        });




    }
/*
    private void loginUser(String lastname,String firstname,String post,
                           String BirthDate,String PassportSN,String PassportBy,String PassportCode
            ,String Married,String gender) throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet result = dbHandler.SearchUser(lastname,firstname,post,
                BirthDate,PassportSN,PassportBy,PassportCode
                ,Married,gender);
         int count = 0;
          try{
              while (result.next()){
                  count++;
              }
          }catch (SQLException e){
              e.printStackTrace();
          }
          if(count >= 1){
              System.out.println("Success");
          }
    }*/
//String[] ArlistString = listString.split("\n");
//            listString2 = String.join("",ArlistString);
//            UserTable = listString2.split(" ");
//            for(int i = 0;i < UserTable.length;i++){
//                TableColumn c = null;
//                c = new TableColumn<>(UserTable[i]);
//                Table.getItems().add(UserTable[i]);
//            }
}
