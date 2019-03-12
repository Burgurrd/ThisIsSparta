import java.sql.Date;
import java.sql.Time;

public class TreningsØkt {

    private int øktID, form, prestasjon;
    private Date dato;
    private Time tid, varighet;
    private String notat;

    public TreningsØkt(int øktID, int form, int prestasjon, Date dato, Time tid, Time varighet, String notat) {
        this.øktID = øktID;
        this.dato = dato;
        this.tid = tid;
        this.varighet = varighet;
        this.form = form;
        this.prestasjon = prestasjon;
        this.notat = notat;
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

    public Time getTid() {
        return tid;
    }

    public void setTid(Time tid) {
        this.tid = tid;
    }

    public Time getVarighet() {
        return varighet;
    }

    public void setVarighet(Time varighet) {
        this.varighet = varighet;
    }

    public String getNotat() {
        return notat;
    }

    public void setNotat(String notat) {
        this.notat = notat;
    }
}
