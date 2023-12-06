package de.hhu.propra.rover.data;

public class Platz implements Comparable<Platz> {

    private String ausstattung;

    private int platzNummer;

    private boolean buchbar;
    private String raumNummer;

    public Platz(String ausstattung, int platzNummmer, boolean buchbar, String raumNummer){
        this.ausstattung = ausstattung;
        this.platzNummer = platzNummmer;
        this.buchbar = buchbar;
        this.raumNummer = raumNummer;
    }

    @Override
    public int compareTo(Platz otherPlatz) {
        return Integer.compare(this.platzNummer, otherPlatz.platzNummer);
    }
    public String getRaumNummer(){
        return raumNummer;
    }

    public String getAusstattung(){ return ausstattung;}

    public int getPlatzNummer() {
        return platzNummer;
    }
    public void setAusstattung(String ausstattung){
        this.ausstattung = ausstattung;
    }
    public void setBuchbar(){ buchbar = false;}
    public boolean getBuchbar(){ return buchbar;}
}
