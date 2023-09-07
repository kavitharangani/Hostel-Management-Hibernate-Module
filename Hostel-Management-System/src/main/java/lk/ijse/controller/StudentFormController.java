package lk.ijse.controller;


import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.StudentBO;
import lk.ijse.model.StudentDTO;
import lk.ijse.utill.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class StudentFormController implements Initializable {
    @FXML
    private JFXTextField studentid;

    @FXML
    private JFXTextField studentcontact;

    @FXML
    private JFXTextField studentaddress;

    @FXML
    private JFXTextField studentname;

    @FXML
    private DatePicker date;

    @FXML
    private JFXRadioButton male;

    @FXML
    private JFXRadioButton female;

    StudentBO studentBO= (StudentBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.STUDENT);

    @FXML
    public void btnClearOnAction(ActionEvent actionEvent) {
     studentid.setText("");
     studentname.setText("");
     studentaddress.setText("");
     studentcontact.setText("");
     date.setValue(null);
     male.setSelected(false);
     female.setSelected(false);

    }
    @FXML
    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = studentid.getText();
        String name = studentname.getText();
        String address=studentaddress.getText();
        String contact=studentcontact.getText();
        LocalDate localDate = date.getValue();
        Date DOB=java.sql.Date.valueOf(localDate);
        String gender =null;

        if(male.isSelected()){

            gender = "Male";

        }else if(female.isSelected()){

            gender = "Female";

        }
        StudentDTO student = new StudentDTO(id, name, address, contact, (java.sql.Date) DOB,gender);

        boolean isSave = false;
        try {
            isSave = studentBO.save(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (isSave) {
            new Alert(Alert.AlertType.CONFIRMATION, "ok").show();
        }
    }
    @FXML
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        if (studentid.getText().matches("^[S0-9]{4}$")) {
            String id = studentid.getText();
            boolean isDelete = false;
            try {
                isDelete = studentBO.delete(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "OK").show();
            }
        }
    }
    @FXML
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = studentid.getText();
        String name = studentname.getText();
        String address=studentaddress.getText();
        String contact=studentcontact.getText();
        LocalDate localDate = date.getValue();
        Date DOB=java.sql.Date.valueOf(localDate);
        String gender =null;

        if(male.isSelected()){

            gender = "Male";

        }else if(female.isSelected()){

            gender = "Female";

        }

        StudentDTO student = new StudentDTO(id, name, address, contact, (java.sql.Date) DOB, gender);
        boolean isUpdate = false;
        try {
            isUpdate = studentBO.Update(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (isUpdate) {
            new Alert(Alert.AlertType.CONFIRMATION, "OK").show();
        }
    }
    @FXML
    public void btnSearchOnAction(ActionEvent actionEvent) {
        String id = studentid.getText();

        StudentDTO student = null;
        try {
            student = studentBO.search(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (student != null) {
            studentid.setText(student.getSId());
            studentname.setText(student.getSName());
            studentaddress.setText(student.getSAddress());
            studentcontact.setText(student.getSContact());
            date.setValue(student.getDate().toLocalDate());
            String gender = student.getGender();

            if ("Male".equals(gender)) {
                male.setSelected(true);
            } else if ("Female".equals(gender)) {
                female.setSelected(true);
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        transaction.commit();
        session.close();
    }
}
