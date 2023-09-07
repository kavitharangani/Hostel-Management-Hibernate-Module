package lk.ijse.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.RoomBO;
import lk.ijse.model.ReservationDTO;
import lk.ijse.model.RoomDTO;
import lk.ijse.model.tm.ReservationTM;
import lk.ijse.model.tm.RoomTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class RoomFormController implements Initializable {
    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField roomid;

    @FXML
    private JFXTextField roomqty;

    @FXML
    private JFXTextField roomkeymoney;

    @FXML
    private JFXTextField roomtype;

    @FXML
    private TableView<RoomTM> tableRoom;

    @FXML
    private TableColumn<?, ?> roomId;

    @FXML
    private TableColumn<?, ?> roomType;

    @FXML
    private TableColumn<?, ?> keyMny;

    @FXML
    private TableColumn<?, ?> qty;

    RoomBO roomBO= (RoomBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.ROOM);
    ObservableList<RoomTM> observableList;

    @FXML
    void btnClearOnAction(ActionEvent event) {
        roomid.setText("");
        roomtype.setText("");
        roomkeymoney.setText("");
        roomqty.setText("");

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = roomid.getText();
        boolean isDelete = false;
        try {
            isDelete = roomBO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (isDelete) {
            new Alert(Alert.AlertType.CONFIRMATION, "OK").show();
        }
        roomid.setText("");
        roomtype.setText("");
        roomkeymoney.setText("");
        roomqty.setText("");
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = roomid.getText();
        String type = roomtype.getText();
        Double keyMoney = Double.valueOf(roomkeymoney.getText());
        Integer qty = Integer.valueOf(roomqty.getText());


        RoomDTO room = new RoomDTO(id, type, keyMoney, qty);

        boolean isSave = false;
        try {
            isSave = roomBO.save(room);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (isSave) {
            new Alert(Alert.AlertType.CONFIRMATION, "ok").show();
        }
        roomid.setText("");
        roomtype.setText("");
        roomkeymoney.setText("");
        roomqty.setText("");
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id = roomid.getText();

        RoomDTO room = null;
        try {
            room = roomBO.search(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (room != null) {
            roomid.setText(room.getRoom_type_id());
            roomtype.setText(room.getType());
            roomkeymoney.setText(String.valueOf(room.getKey_money()));
            roomqty.setText(String.valueOf(room.getQty()));
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = roomid.getText();
        String type = roomtype.getText();
        Double keyMoney = Double.valueOf(roomkeymoney.getText());
        Integer qty = Integer.valueOf(roomqty.getText());

        RoomDTO room = new RoomDTO(id, type, keyMoney, qty);
        boolean isUpdate = false;
        try {
            isUpdate = roomBO.Update(room);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (isUpdate) {
            new Alert(Alert.AlertType.CONFIRMATION, "OK").show();
        }
        roomid.setText("");
        roomtype.setText("");
        roomkeymoney.setText("");
        roomqty.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        getAll();
//        setCellValueFactory();
    }

    private void setCellValueFactory() {
        roomId.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        roomType.setCellValueFactory(new PropertyValueFactory<>("type"));
        keyMny.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        qty.setCellValueFactory(new PropertyValueFactory<>("qty"));

    }

    private void getAll() {
        observableList = FXCollections.observableArrayList();
        List<RoomDTO> allRoom = roomBO.getAllRoom();

        for (RoomDTO roomDTO : allRoom){
            observableList.add(new RoomTM(
                    roomDTO.getRoom_type_id(),
                    roomDTO.getType(),
                    roomDTO.getKey_money(),
                    roomDTO.getQty()

            ));
        }
        tableRoom.setItems(observableList);
    }
}
