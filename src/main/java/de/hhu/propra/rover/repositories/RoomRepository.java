package de.hhu.propra.rover.repositories;

import org.springframework.stereotype.Repository;
import de.hhu.propra.rover.data.Room;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomRepository {

    private List<Room> rooms = new ArrayList<>();

    public List<Room> findAll() {return rooms;}

    public void save(Room room){
        for(int i=0;i<rooms.size();++i){
            if(rooms.get(i).getRaumNummer().equals(room.getRaumNummer())){
                rooms.set(i,room);
                return;
            }
        }
        rooms.add(room);
    }

    public Room findbyName(String suche){
        for (Room room : rooms){
            if (room.getRaumNummer().equals(suche))
                return room;
        }
        return null;
    }
    public void deletebyName(String Nummer){
        for(int i = 0; i < rooms.size(); i++){
            if(rooms.get(i).getRaumNummer().equals(Nummer)){
                rooms.remove(i);
                return;
            }
        }
    }
    public void deletebyPlatznummer(String Nummer, Integer platznummer){
        for (int i = 0; i < rooms.size(); i++){
            if(rooms.get(i).getRaumNummer().equals(Nummer)){
                rooms.get(i).deletePlatzByPlatznumber(platznummer);
                return;
            }
        }
    }

    public void addPlatz(String Nummer, Integer platznummer, String ausstattung){
        for(int i = 0; i < rooms.size(); i++){
            if(rooms.get(i).getRaumNummer().equals(Nummer)){
                if(rooms.get(i).getPlatzWithPlatznumber(platznummer)){
                    rooms.get(i).editPlatz(platznummer, ausstattung);
                } else {
                    rooms.get(i).addPlatz(Nummer, platznummer, ausstattung);
                    rooms.get(i).setAnzahlPlaetze(rooms.get(i).getAnzahlPlaetze() + 1);
                }
            }
        }
    }
    public void buchen(String nummer, Integer platznummer){
        for(int i = 0; i < rooms.size(); i++){
            if(rooms.get(i).getRaumNummer().equals(nummer)) {
                if(rooms.get(i).getPlatzWithPlatznumber(platznummer)){
                    rooms.get(i).platzbuchen(platznummer);
                }
            }
        }
    }
}
