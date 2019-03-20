public class Øvelse {

    public boolean isApparatØvelse;
    public int ID;
    public String navn;

    public Øvelse(int id,boolean isApparat, String navn){
        if (isApparat){ this.isApparatØvelse = true; }
        else{ this.isApparatØvelse = false; }
        this.ID = id;
        this.navn = navn;

    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean getIsApparatØvelse(){
        return this.isApparatØvelse;
    }

}
