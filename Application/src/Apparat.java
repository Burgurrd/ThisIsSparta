public class Apparat {

    private int apparatID;
    private String navn, funksjon;

    public Apparat(int ID, String navn, String funksjon){
        this.apparatID = ID;
        this.navn = navn;
        this.funksjon = funksjon;
    }

    public int getApparatID() {
        return apparatID;
    }

    public void setApparatID(int apparatID) {
        this.apparatID = apparatID;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getFunksjon() {
        return funksjon;
    }

    public void setFunksjon(String funksjon) {
        this.funksjon = funksjon;
    }
}
