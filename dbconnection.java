package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;

public class dbconnection {

    public static void main(String[] args) {
        getConnect();
    }

    private static final String dURL = "jdbc:mysql://localhost:3306/projectimmuno";
    private static final String dUser = "root";
    private static final String dPass = "";
    private static Connection conn = null;
    private static Statement statement = null;

    public static Connection getConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dURL, dUser, dPass);

        } catch (ClassNotFoundException ex) {
            System.out.println("Could not find database driver class");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }
        if (conn != null) {
            System.out.println("Database Connected successfully");
        } else {
            System.out.println("Database Connection failed");
        }
        return conn;
    }

    public static boolean execAction(String Qu) {
        try {
            statement = conn.createStatement();
            statement.execute(Qu);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.getMessage(), "Error Occured", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception at ExecuteQuery:DBConnect" + e.getLocalizedMessage());
            return false;
        }
    }

}

