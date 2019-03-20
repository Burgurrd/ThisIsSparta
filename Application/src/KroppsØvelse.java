public class KroppsØvelse extends Øvelse {

    private int kroppsØvelseID;
    private String navn, beskrivelse;

    public KroppsØvelse(int ID, String navn, String beskrivelse){
        super(ID,false, navn);
        this.kroppsØvelseID = ID;
//        this.navn = navn;
        this.beskrivelse = beskrivelse;

    }

    public int getKroppsØvelseID() {
        return kroppsØvelseID;
    }

    public void setKroppsØvelseID(int kroppsØvelseID) {
        this.kroppsØvelseID = kroppsØvelseID;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

}
