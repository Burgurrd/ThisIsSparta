public class Øvelse {

    public boolean isApparatØvelse;
    public int ID;
    public Øvelse(int id,boolean isApparat){
        if (isApparat){ this.isApparatØvelse = true; }
        else{ this.isApparatØvelse = false; }
        this.ID = id;

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
