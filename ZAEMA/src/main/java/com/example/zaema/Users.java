package com.example.zaema;
import javafx.beans.property.*;


public class Users {
    private SimpleStringProperty firstname;
    private SimpleStringProperty lastname;
    private SimpleStringProperty post;
    private SimpleStringProperty BirthDate;
    private SimpleStringProperty PassportSN;
    private SimpleStringProperty PassportBy;
    private SimpleStringProperty PassportCode;
    private SimpleStringProperty Married;
    private SimpleStringProperty gender;

    Users(String firstname, String lastname, String post, String BirthDate
            , String PassportSN, String PassportBy, String PassportCode, String Married
            , String gender){
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.post = new SimpleStringProperty(post);
        this.BirthDate = new SimpleStringProperty(BirthDate);
        this.PassportSN = new SimpleStringProperty(PassportSN);
        this.PassportBy = new SimpleStringProperty(PassportBy);
        this.PassportCode = new SimpleStringProperty(PassportCode);
        this.Married = new SimpleStringProperty(Married);
        this.gender = new SimpleStringProperty(gender);
    }

    public String getFN(){ return firstname.get();}
    public void setFN(String value){ firstname.set(value);}

    public String getLN(){ return lastname.get();}
    public void setLN(String value){ lastname.set(value);}
}
