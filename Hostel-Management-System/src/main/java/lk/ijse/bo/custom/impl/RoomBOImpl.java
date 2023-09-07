package lk.ijse.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.bo.custom.RoomBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.RoomDAO;
import lk.ijse.entity.Reservation;
import lk.ijse.entity.Room;
import lk.ijse.model.ReservationDTO;
import lk.ijse.model.RoomDTO;
import lk.ijse.model.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {

    RoomDAO roomDAO= (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public boolean delete(String id) throws SQLException {
        return roomDAO.delete(id);
    }


    @Override
    public boolean save(RoomDTO dto) throws SQLException {
        return roomDAO.save(new Room(dto.getRoom_type_id(),dto.getType(),dto.getKey_money(),dto.getQty(),dto.getResList()));
    }

    @Override
    public RoomDTO search(String id) throws SQLException {
        Room room=roomDAO.search(id);
        return new RoomDTO(room.getRoom_type_id(),room.getType(),room.getKey_money(),room.getQty());
    }

    @Override
    public boolean Update(RoomDTO dto) throws SQLException {
        return roomDAO.Update(new Room(dto.getRoom_type_id(),dto.getType(),dto.getKey_money(),dto.getQty(),dto.getResList()));
    }

    @Override
    public ObservableList<RoomDTO> getAllRoom() {
        ObservableList<RoomDTO> allRoom = FXCollections.observableArrayList(); // Initialize ObservableList
        List<Room> all = roomDAO.getAll();
        for (Room room : all) {
            allRoom.add(new RoomDTO(room.getRoom_type_id(), room.getType(), room.getKey_money(), room.getQty()));
        }
        return allRoom;
    }
//        List<RoomDTO> allRoom= new ArrayList<>();
//        List<Room> all = roomDAO.getAll();
//        for (Room room : all) {
////            StudentDTO studentDTO=new StudentDTO(room.getStudent().getSId());
////            RoomDTO roomDTO = new RoomDTO(reservation.getRoom().getRoom_type_id());
//            allRoom.add(new RoomDTO(room.getRoom_type_id(),room.getType(),room.getKey_money(),room.getQty()));
//        }
//        return getAllRoom();
//    }
}
