package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.EventObject;
import java.util.ResourceBundle;

public class TableController implements Initializable {
    private Connection connection;

    @FXML
    private Button pback;

    @FXML
    private TableView<UserData> teatree;

    @FXML
    private TableColumn<UserData, Integer> ID;

    @FXML
    private TableColumn<UserData, java.sql.Date> Date;

    @FXML
    private TableColumn<UserData, java.sql.Time> Time;

    @FXML
    private TableColumn<UserData, String> Hospital;

    @FXML
    private TableColumn<UserData, String> Patient;
    private PreparedStatement preparedStatement;
    private Object root;
    private EventObject event;

    @FXML
    void profileback(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        getData();
        tableRefresh();
    }

    public void getData() {
        connection = dbconnection.getConnect();
        ID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        Date.setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));
        Time.setCellValueFactory(new PropertyValueFactory<>("appointmentTime"));
        Hospital.setCellValueFactory(new PropertyValueFactory<>("appointmentHospital"));
        Patient.setCellValueFactory(new PropertyValueFactory<>("user"));
    }


    public void tableRefresh(){

        ObservableList<UserData> userList = FXCollections.observableArrayList();
        userList.clear();
        String selectquery = "SELECT * FROM `appointments` ";
        System.out.println(selectquery);

        try {
            connection = dbconnection.getConnect();
            preparedStatement = connection.prepareStatement(selectquery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                userList.add(new UserData(
                        resultSet.getInt("appointmentID"),
                        resultSet.getDate("appointmentDate"),
                        resultSet.getTime("appointmentTime"),
                        resultSet.getString("appointmentHospital"),
                        resultSet.getString("user")));
            }
            teatree.setItems(userList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

}
