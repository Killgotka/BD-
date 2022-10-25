package com.example.zaema;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler extends Configs {
    private Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName/* + "?autoReconnect=true&useSSL=false"*/;

        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void signUpUser(String lastname, String firstname, String post,
                           String BirthDate, String PassportSN, String PassportBy, String PassportCode
            , String Married, String gender) throws SQLException, ClassNotFoundException {
        String insert = "INSERT " + Constant.USER_TABLE + "(" +
                Constant.USERS_FIRSTNAME + "," + Constant.USERS_LASTNAME + "," + Constant.USERS_POST
                + "," + Constant.USERS_BIRTHDATE + "," + Constant.USERS_PASSPORTSERIESNUMBER
                + "," + Constant.USERS_PASSPORTBY + "," + Constant.USERS_PASSPORTCODE
                + "," + Constant.USERS_MARRIED + "," + Constant.USERS_GENDER + ")" + "VALUES(?,?,?,?,?,?,?,?,?)";

        PreparedStatement prST = getDbConnection().prepareStatement(insert);
        prST.setString(1, firstname);
        prST.setString(2, lastname);
        prST.setString(3, post);
        prST.setString(4, BirthDate);
        prST.setString(5, PassportSN);
        prST.setString(6, PassportBy);
        prST.setString(7, PassportCode);
        prST.setString(8, Married);
        prST.setString(9, gender);

        prST.executeUpdate();

    }

    public ArrayList<String> SearchUser(String Search) throws SQLException, ClassNotFoundException {
        ResultSet resSet = null;
        ArrayList<String> SearchUser = new ArrayList<>();
        String select = "SELECT * FROM " + Constant.USER_TABLE + " WHERE firstname LIKE ? OR lastname" +
                " LIKE ? OR post LIKE ? OR BirthDate LIKE ? OR PassportSN LIKE ? OR PassportBy LIKE ? " +
                "OR PassportCode LIKE ? OR Married LIKE ? OR gender LIKE ?";

        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        prSt.setString(1, "%" + Search + "%");
        prSt.setString(2, "%" + Search + "%");
        prSt.setString(3, "%" + Search + "%");
        prSt.setString(4, "%" + Search + "%");
        prSt.setString(5, "%" + Search + "%");
        prSt.setString(6, "%" + Search + "%");
        prSt.setString(7, "%" + Search + "%");
        prSt.setString(8, "%" + Search + "%");
        prSt.setString(9, "%" + Search + "%");
        System.out.println(select);
        System.out.println(prSt);
        resSet = prSt.executeQuery();

        while (resSet.next()) {

            String fn = resSet.getString("firstname");
            String ln = resSet.getString("lastname");
            String p = resSet.getString("post");
            String bd = resSet.getString("BirthDate");
            String psn = resSet.getString("PassportSN");
            String pb = resSet.getString("PassportBy");
            String pc = resSet.getString("PassportCode");
            String m = resSet.getString("Married");
            String g = resSet.getString("gender");

            System.out.println(fn + " " + ln + " " + p + " " + bd + " " + psn + " " + pb + " " + pc + " " + m + " " + g);
            SearchUser.add(fn + " " + ln + " " + p + " " + bd + " " + psn + " " + pb + " " + pc + " " + m + " " + g);
        }


        return SearchUser;

    }

    public ArrayList<String> LoadDB() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM " + Constant.USER_TABLE;
        ArrayList<String> user = new ArrayList<>();
        String fn;String ln;String p;String bd;String psn;String pb;String pc;String m;String g;
        ResultSet resSet = null;
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        resSet = prSt.executeQuery();
        while (resSet.next()) {
            fn = resSet.getString("firstname");
            ln = resSet.getString("lastname");
            p = resSet.getString("post");
            bd = resSet.getString("BirthDate");
            psn = resSet.getString("PassportSN");
            pb = resSet.getString("PassportBy");
            pc = resSet.getString("PassportCode");
            m = resSet.getString("Married");
            g = resSet.getString("gender");

            System.out.println(fn + " " + ln + " " + p + " " + bd + " " + psn + " " + pb + " " + pc + " " + m + " " + g);
            user.add(fn + " " + ln + " " + p + " " + bd + " " + psn + " " + pb + " " + pc + " " + m + " " + g);
        }
        return (user);
    }
    public ArrayList<String> Order_By(String Search) throws SQLException, ClassNotFoundException {
        ArrayList<String> user = new ArrayList<>();
        System.out.println(Search);
        if(Search.equals("firstname") || Search.equals("фамилия") || Search.equals("Фамилия") ){
            System.out.println(Search);
            String sql = new String("SELECT * FROM " + Constant.USER_TABLE + " ORDER BY firstname ")  ;
            String fn;String ln;String p;String bd;String psn;String pb;String pc;String m;String g;
            ResultSet resSet = null;
            PreparedStatement prSt = getDbConnection().prepareStatement(sql);
            String k = Search;
            //prSt.setString(1,  k );
            resSet = prSt.executeQuery();
            while (resSet.next()) {
                fn = resSet.getString("firstname");
                ln = resSet.getString("lastname");
                p = resSet.getString("post");
                bd = resSet.getString("BirthDate");
                psn = resSet.getString("PassportSN");
                pb = resSet.getString("PassportBy");
                pc = resSet.getString("PassportCode");
                m = resSet.getString("Married");
                g = resSet.getString("gender");

                //System.out.println(fn + " " + ln + " " + p + " " + bd + " " + psn + " " + pb + " " + pc + " " + m + " " + g);
                user.add(fn + " " + ln + " " + p + " " + bd + " " + psn + " " + pb + " " + pc + " " + m + " " + g);
            }

        }else if(Search.equals("post") || Search.equals("должность") || Search.equals("Должность")){
            System.out.println(Search);
            String sql = new String("SELECT * FROM " + Constant.USER_TABLE + " ORDER BY post ")  ;
            String fn;String ln;String p;String bd;String psn;String pb;String pc;String m;String g;
            ResultSet resSet = null;
            PreparedStatement prSt = getDbConnection().prepareStatement(sql);
            String k = Search;
            //prSt.setString(1,  k );
            resSet = prSt.executeQuery();
            while (resSet.next()) {
                fn = resSet.getString("firstname");
                ln = resSet.getString("lastname");
                p = resSet.getString("post");
                bd = resSet.getString("BirthDate");
                psn = resSet.getString("PassportSN");
                pb = resSet.getString("PassportBy");
                pc = resSet.getString("PassportCode");
                m = resSet.getString("Married");
                g = resSet.getString("gender");

                //System.out.println(fn + " " + ln + " " + p + " " + bd + " " + psn + " " + pb + " " + pc + " " + m + " " + g);
                user.add(fn + " " + ln + " " + p + " " + bd + " " + psn + " " + pb + " " + pc + " " + m + " " + g);
            }
        }else if(Search.equals("BirthDate") || Search.equals("Дата рождения") || Search.equals("дата рождения")){
            System.out.println(Search);
            String sql = new String("SELECT * FROM " + Constant.USER_TABLE + " ORDER BY BirthDate ")  ;
            String fn;String ln;String p;String bd;String psn;String pb;String pc;String m;String g;
            ResultSet resSet = null;
            PreparedStatement prSt = getDbConnection().prepareStatement(sql);
            String k = Search;
            //prSt.setString(1,  k );
            resSet = prSt.executeQuery();
            while (resSet.next()) {
                fn = resSet.getString("firstname");
                ln = resSet.getString("lastname");
                p = resSet.getString("post");
                bd = resSet.getString("BirthDate");
                psn = resSet.getString("PassportSN");
                pb = resSet.getString("PassportBy");
                pc = resSet.getString("PassportCode");
                m = resSet.getString("Married");
                g = resSet.getString("gender");

                //System.out.println(fn + " " + ln + " " + p + " " + bd + " " + psn + " " + pb + " " + pc + " " + m + " " + g);
                user.add(fn + " " + ln + " " + p + " " + bd + " " + psn + " " + pb + " " + pc + " " + m + " " + g);
            }
        }else if(Search.equals("PassportSN") || Search.equals("серия и номер паспорта") || Search.equals("Серия и номер паспорта")){
            System.out.println(Search);
            String sql = new String("SELECT * FROM " + Constant.USER_TABLE + " ORDER BY  PassportSN")  ;
            String fn;String ln;String p;String bd;String psn;String pb;String pc;String m;String g;
            ResultSet resSet = null;
            PreparedStatement prSt = getDbConnection().prepareStatement(sql);
            String k = Search;
            //prSt.setString(1,  k );
            resSet = prSt.executeQuery();
            while (resSet.next()) {
                fn = resSet.getString("firstname");
                ln = resSet.getString("lastname");
                p = resSet.getString("post");
                bd = resSet.getString("BirthDate");
                psn = resSet.getString("PassportSN");
                pb = resSet.getString("PassportBy");
                pc = resSet.getString("PassportCode");
                m = resSet.getString("Married");
                g = resSet.getString("gender");

                //System.out.println(fn + " " + ln + " " + p + " " + bd + " " + psn + " " + pb + " " + pc + " " + m + " " + g);
                user.add(fn + " " + ln + " " + p + " " + bd + " " + psn + " " + pb + " " + pc + " " + m + " " + g);
            }
        }else if(Search.equals("PassportBy") || Search.equals("кем выдан паспорт") || Search.equals("Кем выдан паспорт")){
            System.out.println(Search);
            String sql = new String("SELECT * FROM " + Constant.USER_TABLE + " ORDER BY  PassportBy")  ;
            String fn;String ln;String p;String bd;String psn;String pb;String pc;String m;String g;
            ResultSet resSet = null;
            PreparedStatement prSt = getDbConnection().prepareStatement(sql);
            String k = Search;
            //prSt.setString(1,  k );
            resSet = prSt.executeQuery();
            while (resSet.next()) {
                fn = resSet.getString("firstname");
                ln = resSet.getString("lastname");
                p = resSet.getString("post");
                bd = resSet.getString("BirthDate");
                psn = resSet.getString("PassportSN");
                pb = resSet.getString("PassportBy");
                pc = resSet.getString("PassportCode");
                m = resSet.getString("Married");
                g = resSet.getString("gender");

                //System.out.println(fn + " " + ln + " " + p + " " + bd + " " + psn + " " + pb + " " + pc + " " + m + " " + g);
                user.add(fn + " " + ln + " " + p + " " + bd + " " + psn + " " + pb + " " + pc + " " + m + " " + g);
            }
        }else if(Search.equals("PassportCode") || Search.equals("код подразделения") || Search.equals("Код подразделения")){
            System.out.println(Search);
            String sql = new String("SELECT * FROM " + Constant.USER_TABLE + " ORDER BY  PassportCode")  ;
            String fn;String ln;String p;String bd;String psn;String pb;String pc;String m;String g;
            ResultSet resSet = null;
            PreparedStatement prSt = getDbConnection().prepareStatement(sql);
            String k = Search;
            //prSt.setString(1,  k );
            resSet = prSt.executeQuery();
            while (resSet.next()) {
                fn = resSet.getString("firstname");
                ln = resSet.getString("lastname");
                p = resSet.getString("post");
                bd = resSet.getString("BirthDate");
                psn = resSet.getString("PassportSN");
                pb = resSet.getString("PassportBy");
                pc = resSet.getString("PassportCode");
                m = resSet.getString("Married");
                g = resSet.getString("gender");

                //System.out.println(fn + " " + ln + " " + p + " " + bd + " " + psn + " " + pb + " " + pc + " " + m + " " + g);
                user.add(fn + " " + ln + " " + p + " " + bd + " " + psn + " " + pb + " " + pc + " " + m + " " + g);
            }
        }else if(Search.equals("Married") || Search.equals("семейное положение") || Search.equals("Семейное положение")){
            System.out.println(Search);
            String sql = new String("SELECT * FROM " + Constant.USER_TABLE + " ORDER BY Married ")  ;
            String fn;String ln;String p;String bd;String psn;String pb;String pc;String m;String g;
            ResultSet resSet = null;
            PreparedStatement prSt = getDbConnection().prepareStatement(sql);
            String k = Search;
            //prSt.setString(1,  k );
            resSet = prSt.executeQuery();
            while (resSet.next()) {
                fn = resSet.getString("firstname");
                ln = resSet.getString("lastname");
                p = resSet.getString("post");
                bd = resSet.getString("BirthDate");
                psn = resSet.getString("PassportSN");
                pb = resSet.getString("PassportBy");
                pc = resSet.getString("PassportCode");
                m = resSet.getString("Married");
                g = resSet.getString("gender");

                //System.out.println(fn + " " + ln + " " + p + " " + bd + " " + psn + " " + pb + " " + pc + " " + m + " " + g);
                user.add(fn + " " + ln + " " + p + " " + bd + " " + psn + " " + pb + " " + pc + " " + m + " " + g);
            }
        }else if(Search.equals("gender") || Search.equals("пол") || Search.equals("Пол")){
            System.out.println(Search);
            String sql = new String("SELECT * FROM " + Constant.USER_TABLE + " ORDER BY gender ")  ;
            String fn;String ln;String p;String bd;String psn;String pb;String pc;String m;String g;
            ResultSet resSet = null;
            PreparedStatement prSt = getDbConnection().prepareStatement(sql);
            String k = Search;
            //prSt.setString(1,  k );
            resSet = prSt.executeQuery();
            while (resSet.next()) {
                fn = resSet.getString("firstname");
                ln = resSet.getString("lastname");
                p = resSet.getString("post");
                bd = resSet.getString("BirthDate");
                psn = resSet.getString("PassportSN");
                pb = resSet.getString("PassportBy");
                pc = resSet.getString("PassportCode");
                m = resSet.getString("Married");
                g = resSet.getString("gender");

                //System.out.println(fn + " " + ln + " " + p + " " + bd + " " + psn + " " + pb + " " + pc + " " + m + " " + g);
                user.add(fn + " " + ln + " " + p + " " + bd + " " + psn + " " + pb + " " + pc + " " + m + " " + g);
            }
        }else if(Search.equals("lastname") || Search.equals("имя") || Search.equals("Имя")){
            System.out.println(Search);
            String sql = new String("SELECT * FROM " + Constant.USER_TABLE + " ORDER BY  lastname")  ;
            String fn;String ln;String p;String bd;String psn;String pb;String pc;String m;String g;
            ResultSet resSet = null;
            PreparedStatement prSt = getDbConnection().prepareStatement(sql);
            String k = Search;
            //prSt.setString(1,  k );
            resSet = prSt.executeQuery();
            while (resSet.next()) {
                fn = resSet.getString("firstname");
                ln = resSet.getString("lastname");
                p = resSet.getString("post");
                bd = resSet.getString("BirthDate");
                psn = resSet.getString("PassportSN");
                pb = resSet.getString("PassportBy");
                pc = resSet.getString("PassportCode");
                m = resSet.getString("Married");
                g = resSet.getString("gender");

                //System.out.println(fn + " " + ln + " " + p + " " + bd + " " + psn + " " + pb + " " + pc + " " + m + " " + g);
                user.add(fn + " " + ln + " " + p + " " + bd + " " + psn + " " + pb + " " + pc + " " + m + " " + g);
            }
        }


        return user;
    }

    public ArrayList<String> DeletUsers(String Search) throws SQLException, ClassNotFoundException {
        String delete = "DELETE FROM "+ Constant.USER_TABLE + " WHERE firstname LIKE ? OR lastname LIKE ? " +
                "OR post LIKE ? OR BirthDate LIKE ? OR PassportSN LIKE ? OR PassportBy LIKE ? OR PassportCode " +
                "LIKE ? OR Married LIKE ? OR gender LIKE ?";
        PreparedStatement prST = getDbConnection().prepareStatement(delete);
        ResultSet resSet = null;
        prST.setString(1, "%" + Search + "%");
        prST.setString(2, "%" + Search + "%");
        prST.setString(3, "%" + Search + "%");
        prST.setString(4, "%" + Search + "%");
        prST.setString(5, "%" + Search + "%");
        prST.setString(6, "%" + Search + "%");
        prST.setString(7, "%" + Search + "%");
        prST.setString(8, "%" + Search + "%");
        prST.setString(9, "%" + Search + "%");
        System.out.println(delete);
        System.out.println(prST);
        prST.executeUpdate();
        return null;

    }
}
