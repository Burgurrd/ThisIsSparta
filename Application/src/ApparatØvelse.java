public class ApparatØvelse extends Øvelse {
    private int apparatØvelseID, apparatID, sett;
    private double kilo;
    private String navn;

    public ApparatØvelse(int ID, String navn, int sett, double kilo, int apparatID){
        super();
        this.apparatØvelseID = ID;
        this.navn = navn;
        this.sett = sett;
        this.kilo = kilo;
        this.apparatID = apparatID;
    }

    public int getApparatØvelseID() {
        return apparatØvelseID;
    }

    public void setApparatØvelseID(int apparatØvelseID) {
        this.apparatØvelseID = apparatØvelseID;
    }

    public int getApparatID() {
        return apparatID;
    }

    public void setApparatID(int apparatID) {
        this.apparatID = apparatID;
    }

    public int getSett() {
        return sett;
    }

    public void setSett(int sett) {
        this.sett = sett;
    }

    public double getKilo() {
        return kilo;
    }

    public void setKilo(double kilo) {
        this.kilo = kilo;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }
}
