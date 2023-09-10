package lk.ijse.controller;

//import com.jfoenix.controls.JFXButton;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Alert;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//import lk.ijse.bo.BOFactory;
//import lk.ijse.bo.custom.RoomBO;
//import lk.ijse.model.UserDTO;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//
public class SignupPageController {
//
//    public TextField txtPasswordHint;
//    public AnchorPane pane;
//    @FXML
//    private JFXButton btnRegister;
//
//    @FXML
//    private TextField txtUsername;
//
//    @FXML
//    private TextField txtPassword;
//
//    @FXML
//    private TextField txtRptPassword;
//
//    @FXML
//    private TextField TxtPasswordHint;
//    private UserService userService;
//    private RegExFactory regExFactory;
//    RoomBO roomBO= (RoomBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.ROOM);
//
//    @FXML
//    void btnRegisterOnAction(ActionEvent event) {
//        Stage window = (Stage) pane.getScene().getWindow();
//        try {
//            window.setAlwaysOnTop(false);
//            if (checkRegEx()) {
//                UserDTO userDto = new UserDTO(txtUsername.getText(), txtPassword.getText());
//                userBO.save(userDto);
//                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Registration Success! ");
//                alert.showAndWait();
//                clear();
//            } else {
//                new Alert(Alert.AlertType.INFORMATION, "Pattern not match!").showAndWait();
//            }
//        } catch (RuntimeException e) {
//            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).showAndWait();
//        }
//        window.setAlwaysOnTop(true);
//    }
//
//    private void clear() {
//        txtUsername.clear();
//        txtPassword.clear();
//        txtRptPassword.clear();
//        txtPasswordHint.clear();
//    }
//

}
