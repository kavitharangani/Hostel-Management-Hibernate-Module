//package lk.ijse.controller;
//
//import com.jfoenix.controls.JFXComboBox;
//import com.jfoenix.controls.JFXTextField;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import lk.ijse.bo.BOFactory;
//import lk.ijse.bo.custom.ReservationBO;
//import lk.ijse.dao.DAOFactory;
//import lk.ijse.dao.custom.ReservationDAO;
//import lk.ijse.entity.Room;
//import lk.ijse.entity.Student;
//import lk.ijse.model.ReservationDTO;
//import lk.ijse.model.tm.ReservationTM;
//
//import java.net.URL;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.Date;
//import java.util.List;
//import java.util.ResourceBundle;
//
//public class ReservationController implements Initializable {
//
//    @FXML
//    private Label lblReservationId;
//
//    @FXML
//    private JFXComboBox<String> cmbStdId;
//
//    @FXML
//    private JFXComboBox<String> cmbRoomTypeId;
//
//    @FXML
//    private Label lblQty;
//
//    @FXML
//    private DatePicker date;
//
//    @FXML
//    private Label lblStatus;
//
//    @FXML
//    private Label lblPricePerRoom;
//
//    @FXML
//    private JFXTextField txtResId;
//
//    @FXML
//    private TableView<ReservationDTO> tblReservation;
//
//    @FXML
//    private TableColumn<?, ?> clmResId;
//
//    @FXML
//    private TableColumn<?, ?> clmDate;
//
//    @FXML
//    private TableColumn<?, ?> clmStdId;
//
//    @FXML
//    private TableColumn<?, ?> clmRoomId;
//
//    @FXML
//    private TableColumn<?, ?> clmStatus;
//
//    ReservationBO reservationBO= (ReservationBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.RESERVATION);
//    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
//    ObservableList<ReservationTM> observableList;
//
//    Student student = new Student();
//    Room room = new Room();
//
//    @FXML
//    void btnBookOnAction(ActionEvent event) throws SQLException {
//        String id = lblReservationId.getText();
//        LocalDate localDate = date.getValue();
//        Date resDate=java.sql.Date.valueOf(localDate);
//        String sid = cmbStdId.getValue();
//        String rid = cmbRoomTypeId.getValue();
//        String status = lblStatus.getText();
//
//        student.setSId(sid);
//        room.setRoom_type_id(rid);
//
//        if (reservationBO.addRevervation(new ReservationDTO(id, (java.sql.Date) resDate, status, room, student))) {
//            new Alert(Alert.AlertType.CONFIRMATION, "Saved!!").show();
//        } else {
//            new Alert(Alert.AlertType.ERROR, "Error!!").show();
//        }
//    }
//
//
//    @FXML
//    void btnPayOnAction(ActionEvent event) {
//
//    }
//
//    @FXML
//    void cmbRoomTypeOnAction(ActionEvent event) {
//
//    }
//
//    @FXML
//    void cmbStdIdOnAction(ActionEvent event) {
//
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        loadNextResId();
//        loadStudentID();
//        loadRoomID();
//        getAll();
//        setCellValueFactory();
//    }
//
//    private void loadRoomID() {
//        List<String> id = reservationDAO.loadRoomID();
//        ObservableList<String> obList = FXCollections.observableArrayList();
//
//        for (String un : id){
//            obList.add(un);
//        }
//        cmbRoomTypeId.setItems(obList);
//    }
//
//    private void loadStudentID() {
//        List<String> id = reservationDAO.loadStudentID();
//        ObservableList<String> obList = FXCollections.observableArrayList();
//
//        for (String un : id){
//            obList.add(un);
//        }
//        cmbStdId.setItems(obList);
//    }
//
//    private void loadNextResId(){
//            String nextReservationId = reservationBO.getNextResId();
//            lblReservationId.setText(nextReservationId);
//    }
//
//    @FXML
//    public void btnSearchOnAction(ActionEvent actionEvent) {
//        String id = txtResId.getText();
//
//        ReservationDTO reservation = null;
//        reservation = reservationBO.search(id);
//
//        if (reservation != null) {
//            lblReservationId.setText(reservation.getRes_id());
//            date.setValue(reservation.getDate().toLocalDate());
//            lblStatus.setText(reservation.getStatus());
//            cmbStdId.setValue(reservation.getStudent().getSId());
//            cmbRoomTypeId.setValue(reservation.getRoom().getRoom_type_id());
//        }
//    }
//    @FXML
//    public void btnUpdateOnAction(ActionEvent actionEvent) {
//        String id = lblReservationId.getText();
//        LocalDate localDate = date.getValue();
//        Date resDate=java.sql.Date.valueOf(localDate);
//        String sid = cmbStdId.getValue();
//        String rid = cmbRoomTypeId.getValue();
//        String status = lblStatus.getText();
//
//        student.setSId(sid);
//        room.setRoom_type_id(rid);
//
//        ReservationDTO reservation = new ReservationDTO(id, (java.sql.Date) resDate, status, room, student);
//        boolean isUpdate = false;
//        isUpdate = reservationBO.Update(reservation);
//        if (isUpdate) {
//            new Alert(Alert.AlertType.CONFIRMATION, "OK").show();
//        }
//    }
//    @FXML
//    public void btnClearOnAction(ActionEvent actionEvent) {
//        lblReservationId.setText(null);
//        cmbStdId.setValue(null);
//        cmbRoomTypeId.setValue(null);
//        lblQty.setText(null);
//        date.setValue(null);
//        txtResId.setText(null);
//        lblPricePerRoom.setText(null);
//    }
//    private void getAll(){
////        observableList = FXCollections.observableArrayList();
////        List<ReservationDTO> allRevervation = reservationBO.getAllRevervation();
////
////        for (ReservationDTO reservationDTO : allRevervation){
////            observableList.add(new ReservationTM(
////                    reservationDTO.getRes_id(),
////                    reservationDTO.getDate(),
////                    reservationDTO.getStatus(),
////                    reservationDTO.getRoom().getRoom_type_id(),
////                    reservationDTO.getStudent().getSId()
////            ));
////        }
////        tblReservation.setItems(observableList);
//    }
//    void setCellValueFactory(){
//        clmResId.setCellValueFactory(new PropertyValueFactory<>("id"));
//        clmDate.setCellValueFactory(new PropertyValueFactory<>("resDate"));
//        clmStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
//        clmRoomId.setCellValueFactory(new PropertyValueFactory<>("rid"));
//        clmStdId.setCellValueFactory(new PropertyValueFactory<>("sid"));
//    }
//    @FXML
//    public void btnDeleteOnAction(ActionEvent actionEvent) {
//        String id = lblReservationId.getText();
//        boolean isDelete = false;
//        isDelete = reservationBO.delete(id);
//        if (isDelete) {
//            new Alert(Alert.AlertType.CONFIRMATION, "OK").show();
//        }
//    }
//}

package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ReservationBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ReservationDAO;
import lk.ijse.model.ReservationDTO;
import lk.ijse.model.RoomDTO;
import lk.ijse.model.StudentDTO;
import lk.ijse.model.tm.ReservationTM;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {
    @FXML
    private AnchorPane pane;

    @FXML
    private Label lblReservationId;

    @FXML
    private JFXComboBox<String> cmbStdId;

    @FXML
    private JFXComboBox<String> cmbRoomTypeId;

    @FXML
    private DatePicker date;

    @FXML
    private Label lblPricePerRoom;

    @FXML
    private Label lblBalance;

    @FXML
    private JFXTextField txtResId;

    @FXML
    private TableView<ReservationTM> tblReservation;

    @FXML
    private TableColumn<?, ?> clmResId;

    @FXML
    private TableColumn<?, ?> clmDate;

    @FXML
    private TableColumn<?, ?> clmStdId;

    @FXML
    private TableColumn<?, ?> clmRoomId;

    @FXML
    private TableColumn<?, ?> clmStatus;

    @FXML
    private TableColumn<?, ?> clmStatus1;

    @FXML
    private JFXTextField txtPayAmount;

    ReservationBO reservationBO= (ReservationBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.RESERVATION);
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    ObservableList<ReservationTM> observableList;

        private void loadRoomID() {
        List<String> id = reservationDAO.loadRoomID();
        ObservableList<String> obList = FXCollections.observableArrayList();

        for (String un : id){
            obList.add(un);
        }
        cmbRoomTypeId.setItems(obList);
    }

    private void loadStudentID() {
        List<String> id = reservationDAO.loadStudentID();
        ObservableList<String> obList = FXCollections.observableArrayList();

        for (String un : id){
            obList.add(un);
        }
        cmbStdId.setItems(obList);
    }

    private void loadNextResId(){
            String nextReservationId = reservationBO.getNextResId();
            lblReservationId.setText(nextReservationId);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        lblReservationId.setText(null);
        date.setValue(null);
        cmbStdId.setValue(null);
        cmbRoomTypeId.setValue(null);
        txtResId.setText(null);
        lblBalance.setText(null);
        txtPayAmount.setText(null);
        lblPricePerRoom.setText(null);

    }
    @FXML
    void btnBookOnAction(ActionEvent event) {
        String id = lblReservationId.getText();
        LocalDate localDate = date.getValue();
        String sid = String.valueOf(cmbStdId.getValue());
        String rid = String.valueOf(cmbRoomTypeId.getValue());
        Double keyMoney = Double.valueOf(lblPricePerRoom.getText());
        Double balance = Double.valueOf(lblBalance.getText());

        RoomDTO roomDTO = new RoomDTO(rid);
        StudentDTO studentDTO = new StudentDTO(sid);

        boolean isReserved = reservationBO.addRevervation(new ReservationDTO(id, localDate, keyMoney, balance, roomDTO , studentDTO));
        if (isReserved){
            new Alert(Alert.AlertType.CONFIRMATION, "Saved!!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Error!!").show();
        }
    }

    @FXML
    void btnPayOnAction(ActionEvent event) {
            Double balanceText= Double.valueOf(lblBalance.getText());
            Double amountText= Double.valueOf(txtPayAmount.getText());

            Double newBalance = balanceText-amountText;

            boolean isUpdate = reservationBO.UpdatePayment(newBalance,txtResId.getText());

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id = txtResId.getText();

        ReservationDTO reservation = reservationBO.search(id);
        System.out.println(reservation.getBalance()+"  "+reservation.getKeyMoney());

        if (reservation != null) {
             lblPricePerRoom.setText(String.valueOf(reservation.getKeyMoney()));
             lblBalance.setText(String.valueOf(reservation.getBalance()));
         }
    }

    @FXML
    void cmbRoomTypeOnAction(ActionEvent event) {
        Double keymoney = reservationBO.getKeymoney(cmbRoomTypeId.getSelectionModel().getSelectedItem());
        lblPricePerRoom.setText(String.valueOf(keymoney));
    }

    @FXML
    void cmbStdIdOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadRoomID();
        loadNextResId();
        loadStudentID();
        getAll();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        clmResId.setCellValueFactory(new PropertyValueFactory<>("res_id"));
        clmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmRoomId.setCellValueFactory(new PropertyValueFactory<>("rid"));
        clmStdId.setCellValueFactory(new PropertyValueFactory<>("sid"));
        clmStatus.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        clmStatus1.setCellValueFactory(new PropertyValueFactory<>("balance"));
    }

    private void getAll() {
            observableList = FXCollections.observableArrayList();
            List<ReservationDTO> allReservation = reservationBO.getAllRevervation();

            for (ReservationDTO reservationDTO : allReservation){
                observableList.add(new ReservationTM(
                        reservationDTO.getRes_id(),
                        reservationDTO.getDate(),
                        reservationDTO.getStudentDTO().getSId(),
                        reservationDTO.getRoomDTO().getRoom_type_id(),
                        reservationDTO.getKeyMoney(),
                        reservationDTO.getBalance()
                ));
            }
            tblReservation.setItems(observableList);
    }

    public void getBalance(ActionEvent actionEvent) {
        Double payment = Double.valueOf(txtPayAmount.getText());
        Double keymoney = Double.valueOf(lblPricePerRoom.getText());
        Double balance = keymoney - payment;

        lblBalance.setText(String.valueOf(balance));
    }


}



