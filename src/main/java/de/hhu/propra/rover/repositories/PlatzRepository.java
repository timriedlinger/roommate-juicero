package de.hhu.propra.rover.repositories;

import de.hhu.propra.rover.data.Platz;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlatzRepository {

    private List<Platz> platzList = new ArrayList<>();

    public List<Platz> getPlatzList() {
        return platzList;
    }

    public void save(Platz platz) {
        for(int i=0;i<platzList.size();++i){
            if(platzList.get(i).getRaumNummer().equals(platz.getRaumNummer())){
                platzList.set(i,platz);
                return;
            }
        }
        platzList.add(platz);
    }
}
