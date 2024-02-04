    package sample;

    import com.sun.prism.PixelFormat;
    import com.sun.source.util.ParameterNameProvider;
    import javafx.collections.FXCollections;
    import sample.UserData;
    import javafx.collections.ObservableList;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.fxml.Initializable;
    import javafx.scene.Node;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.*;
    import javafx.scene.control.Button;
    import javafx.scene.control.TextArea;
    import javafx.scene.control.TextField;
    import javafx.scene.control.cell.PropertyValueFactory;
    import javafx.scene.image.ImageView;
    import javafx.scene.text.Text;
    import javafx.stage.Stage;

    import javafx.scene.control.DatePicker;
    import javafx.stage.StageStyle;


    import java.awt.*;
    import java.io.IOException;
    import java.net.URI;
    import java.net.URISyntaxException;
    import java.net.URL;
    import java.sql.*;
    import java.util.ResourceBundle;
    import java.net.URISyntaxException;
    import java.net.URI;
    import java.time.LocalDate;
    import java.util.logging.Level;
    import java.util.logging.Logger;

    import static com.sun.beans.introspect.ClassInfo.clear;


    public class Controller implements Initializable {
        public TextField usernamefeild;
        private Parent root;
        private Scene scene;
        private Stage stage;
        String query = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        @FXML
        private TextField FBM;
        @FXML
        private TextField username;

        @FXML
        private TextField password;

        @FXML
        private Button loginbtn;

        @FXML
        private Button register;
        @FXML
        private TextField fname;

        @FXML
        private TextField lname;

        @FXML
        private TextField Rusername;

        @FXML
        private TextField email;

        @FXML
        private TextField age;

        @FXML
        private Button registerbutton;

        @FXML
        private PasswordField rpass;


        @FXML
        private TextField pnumber;


        @FXML
        private TextField gender;
        @FXML
        private DatePicker datepicker;

        @FXML
        private TextArea textrarea;

        @FXML
        private Button done;

        @FXML
        private Button aback;

        @FXML
        private Button back;

        @FXML
        private Button pback;
        @FXML
        private Button registerback;
        @FXML
        private Object Vaccine;
        @FXML
        private TextField timepicker;
        @FXML
        private Button fbsubmit;

        @FXML
        private TextField hospitpicker;
        @FXML
        private Button appointmentbutton;


        @FXML
        public void pickdateaction(ActionEvent event) {
        }

        @FXML
        private Button feedback;


        public void PickHospital(ActionEvent event) {
        }

        public void PickTime(ActionEvent event) {
        }

        @FXML
        private TextArea FB;
        @FXML
        private Button reset;

        @FXML
        void resettingaction(ActionEvent event) {
            LocalDate thedate = datepicker.getValue();
            String thetime = timepicker.getText();
            String thehospit = hospitpicker.getText();
            datepicker.getEditor().clear();
            timepicker.clear();
            hospitpicker.clear();

        }

        @FXML
        void registerback(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        @FXML
        void Back(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        PreparedStatement pst = null;
        ResultSet rs = null;
        private static Connection conn = null;
        String user;

        @FXML
        void loginbtn(ActionEvent event) throws IOException, SQLException {
            conn = dbconnection.getConnect();

            String username1 = username.getText();
            user = username1;
            System.out.println(user);
            String password1 = password.getText();


            String Qu3 = "select * from login where (USERNAME = ? and PASSWORD = ?)";
            System.out.println(Qu3);

            pst = conn.prepareStatement(Qu3);
            pst.setString(1, username.getText());
            pst.setString(2, password.getText());
            rs = pst.executeQuery();
            if (rs.next() == true) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/Homepage.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
                }

                Controller controller = loader.getController();
                controller.setUser(user);
                Parent parent = loader.getRoot();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(parent));
                stage.show();


            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText(" Failed!  ");
                alert.showAndWait();

            }
        }


        @FXML
        void registerbtn(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("Registeration.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        @FXML
        void Registeration(ActionEvent event) throws IOException {
            Connection conn = dbconnection.getConnect();
            String ID = null;
            String firstname = fname.getText();
            String lastname = lname.getText();
            String rusername = Rusername.getText();
            String Email = email.getText();
            String rpassword = rpass.getText();
            String Age = age.getText();
            String phone = pnumber.getText();
            String Gender = gender.getText();


            String Qu = "insert into `login` values (" + ID + ",'" + firstname + "','" + lastname + "','" + rusername + "','" + Email + "','" + rpassword + "','" + Age + "','" + phone + "','" + Gender + "')";
            System.out.println(Qu);

            if (firstname.isEmpty() | lastname.isEmpty() | rusername.isEmpty() | Email.isEmpty() | rpassword.isEmpty() | Age.isEmpty() | phone.isEmpty() | Gender.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please Fill all the Fields");
                alert.showAndWait();
                return;
            }

            if (dbconnection.execAction(Qu)) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/Login.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
                }

                Controller controller = loader.getController();
                controller.setUser(user);
                Parent parent = loader.getRoot();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(parent));
                stage.show();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Registered Successfully");
                alert.showAndWait();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Failed");
                alert.showAndWait();
            }
        }


        @FXML
        void donebtn(ActionEvent event) throws IOException, SQLException {
            Connection conn = dbconnection.getConnect();
            LocalDate thedate = datepicker.getValue();
            String thetime = timepicker.getText();
            String thehospit = hospitpicker.getText();

            String Qu4 = "insert into `appointments` values (" + null + ",'" + thedate + "','" + thetime + "','" + thehospit + "','" + getUser() + "')";
            System.out.println(Qu4);


            if (thedate == null | thetime.isEmpty() | thehospit.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please Fill all the Fields");
                alert.showAndWait();
                return;
            }

            if (dbconnection.execAction(Qu4)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Successfully Completed");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Failed");
                alert.showAndWait();
            }

        }

        @FXML
        void feedbackbtn(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("FeedBack.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }

        @FXML
        void appointmentbtn(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/Appointment.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
            }

            Controller controller = loader.getController();
            controller.setUser(user);
            Parent parent = loader.getRoot();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.show();

        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        @FXML
        void hospitalsbtn(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("Hospitals.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        @FXML
        void logoutbtutton(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        @FXML
        void profilebtn(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/Profile.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, e);
            }

            TableController controller = loader.getController();
            Parent parent = loader.getRoot();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.show();
        }

        @FXML
        void appointback(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        @FXML
        void profileback(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        @FXML
        void vaccinelink(ActionEvent event) throws URISyntaxException, IOException {
            Desktop browser = Desktop.getDesktop();
            try {
                browser.browse(new URI("https://news.google.com/covid19/map?hl=en-US&state=7&mid=%2Fm%2F0jdd&gl=US&ceid=US%3Aen"));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

        }

        @FXML
        void submitbtn(ActionEvent event) throws SQLException {

            Connection conn = dbconnection.getConnect();
            String feedbacktext = FBM.getText();

            String Qu5 = "INSERT INTO `feedback`(`fbcol`) VALUES ('?')";
            System.out.println(Qu5);

            if (feedbacktext.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please provide your feedback then submit");
                alert.showAndWait();
            } else {dbconnection.execAction(Qu5);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Your feedback has been recorded!");
                alert.showAndWait();

            }
        }


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

        }
    }







