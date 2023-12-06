package de.hhu.propra.rover.data;
import java.util.Collections;
import java.util.List;

public class Room {
    private Integer anzahlPlaetze;
    private List<Platz> plaetze;
    private String raumNummer;

    public Room(Integer anzahlPlaetze, List<Platz> plaetze, String raumNummer) {
        this.anzahlPlaetze = anzahlPlaetze;
        this.plaetze = plaetze;
        this.raumNummer = raumNummer;
    }
    public void sortPlatzListe() {
        Collections.sort(plaetze);
    }
    public Integer getAnzahlPlaetze() {
        return anzahlPlaetze;
    }

    public List<Platz> getPlaetze() {
        return plaetze;
    }
    public boolean getPlatzWithPlatznumber(int platzNummer){
        for(int i = 0; i < plaetze.size(); i++){
            if (plaetze.get(i).getPlatzNummer() == platzNummer){
                return true;
            }
        }
        return false;
    }
    public void editPlatz(Integer platznummer, String ausstattung){
        if(platznummer > plaetze.size()){
            int o = plaetze.size() - 1;
            int offset = platznummer - o;
            plaetze.get(offset).setAusstattung(ausstattung);
        } else {
            plaetze.get(platznummer - 1).setAusstattung(ausstattung);
        }
    }
    public void addPlatz(String raumNummer, Integer platznummer, String ausstattung){
        if(platznummer > plaetze.size()){
            plaetze.add(new Platz(ausstattung, platznummer, true, raumNummer));
            sortPlatzListe();
        } else {
            plaetze.add(platznummer -1, new Platz(ausstattung, platznummer, true, raumNummer));
            sortPlatzListe();
        }
    }
    public void setPlatzWithPlatzNumber(Integer platzNumber){
    }

    public String getRaumNummer() {
        return raumNummer;
    }
    public void setAnzahlPlaetze(Integer anzahlPlaetze){
        this.anzahlPlaetze = anzahlPlaetze;
    }
    public void deletePlatzByPlatznumber(Integer platznumber){
        if (plaetze.removeIf(platz -> platz.getPlatzNummer() == platznumber)){
            anzahlPlaetze = anzahlPlaetze -1;
            sortPlatzListe();
        }
    }
    public void platzbuchen(Integer platznummer){
        if(platznummer > plaetze.size()){
            int o = plaetze.size() - 1;
            int offset = platznummer - o;
            plaetze.get(offset).setBuchbar();
            anzahlPlaetze--;
        } else {
            plaetze.get(platznummer - 1).setBuchbar();
            anzahlPlaetze--;
        }
    }
}
