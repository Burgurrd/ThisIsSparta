import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class TreningsØkt {

    private int øktID, form, prestasjon;
    private Date dato;
    private Time startTid, sluttTid;
    private String notat;
    private ArrayList<Øvelse> øvelser;

    public TreningsØkt(int øktID, Date dato, Time startTid, Time sluttTid, int form, int prestasjon, String notat, ArrayList<Øvelse> øvelser) {
        this.øktID = øktID;
        this.dato = dato;
        this.startTid = startTid;
        this.sluttTid = sluttTid;
        this.form = form;
        this.prestasjon = prestasjon;
        this.notat = notat;
        this.øvelser = øvelser;
    }
    public TreningsØkt getTreningsøkt(){
        return this;
    }

    public int getØktID() {
        return øktID;
    }

    public void setØktID(int øktID) {
        this.øktID = øktID;
    }

    public int getForm() {
        return form;
    }

    public void setForm(int form) {
        this.form = form;
    }

    public int getPrestasjon() {
        return prestasjon;
    }

    public void setPrestasjon(int prestasjon) {
        this.prestasjon = prestasjon;
    }

    public Date getDato() {
        return dato;
    }

    public void setDato(Date dato) {
        this.dato = dato;
    }

    public Time getStartTid() {
        return startTid;
    }

    public void setStartTid(Time startTid) {
        this.startTid = startTid;
    }

    public Time getSluttTid() {
        return sluttTid;
    }

    public void setSluttTid(Time sluttTid) {
        this.sluttTid = sluttTid;
    }

    public String getNotat() {
        return notat;
    }

    public void setNotat(String notat) { this.notat = notat; }

    public ArrayList<Øvelse> getØvelser() {
        return øvelser;
    }

    public void setØvelser(ArrayList<Øvelse> øvelser) {
        this.øvelser = øvelser;
    }
}
