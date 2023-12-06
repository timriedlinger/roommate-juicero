package de.hhu.propra.rover.service;

import de.hhu.propra.rover.data.Room;
import de.hhu.propra.rover.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    RoomRepository roomRepository;

    RoomService(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }
    public List<Room> getRooms() {return roomRepository.findAll(); }

    public Room getByNumber(String search) {return roomRepository.findbyName(search);}

    public void addRoom(Room room) {
        roomRepository.save(room);
    }

    public void deleteRoom(String delete){
        roomRepository.deletebyName(delete);
    }

    public void deletePlatz(String roomNumber, Integer platznummer){
        roomRepository.deletebyPlatznummer(roomNumber, platznummer);
    }
    public void addPlatz(String roomNumber, Integer platznummer, String ausstattung){
        roomRepository.addPlatz(roomNumber, platznummer, ausstattung);
    }
    public void buchen(String roomNumber, Integer platznummer){ roomRepository.buchen(roomNumber, platznummer);}
}
