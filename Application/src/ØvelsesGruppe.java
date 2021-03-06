import java.util.ArrayList;

public class ØvelsesGruppe {

    private int øvelsesgruppeID;
    private String navn;
    private ArrayList<Øvelse> øvelser;


    public ØvelsesGruppe(int ID, String navn){
        this.øvelsesgruppeID = ID;
        this.navn = navn;
        this.øvelser = new ArrayList<>();
    }

    public int getØvelsesgruppeID() {
        return øvelsesgruppeID;
    }

    public void setØvelsesgruppeID(int øvelsesgruppeID) {
        this.øvelsesgruppeID = øvelsesgruppeID;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public ArrayList<Øvelse> getØvelser() {
        return øvelser;
    }

    public void setØvelser(ArrayList<Øvelse> øvelser) {
        this.øvelser = øvelser;
    }

    public void addØvelser(Øvelse ø){
        this.øvelser.add(ø);
    }



}
