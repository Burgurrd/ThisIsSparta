import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class DatabaseController{

    public int _antallØkter;
    public ArrayList<Øvelse> _registrerteKroppsØvelser = new ArrayList<Øvelse>();
    public ArrayList<Øvelse> _registrerteApparatØvelser = new ArrayList<Øvelse>();
    public ArrayList<Apparat> _registrerteApparater = new ArrayList<Apparat>();
    public ArrayList<ØvelsesGruppe> _registrerteGrupper = new ArrayList<ØvelsesGruppe>();
    public ArrayList<TreningsØkt> _registrerteØkter = new ArrayList<TreningsØkt>();
    public JDBCInsert iC;
    public JDBCSelect sC;


    public DatabaseController(){
        this.iC = new JDBCInsert(this);
        this.sC = new JDBCSelect(this);
        this._registrerteØkter.addAll(sC.getØktListe());
        Collections.sort(_registrerteØkter, øktC);
        this._antallØkter = this._registrerteØkter.size();
        this._registrerteApparater.addAll(sC.getApparatListe());
        this._registrerteGrupper.addAll(sC.getGrupperListe());
        this._registrerteApparatØvelser.addAll(sC.getApparatØvelser());
        this._registrerteKroppsØvelser.addAll(sC.getKroppsØvelser());

        //update all Lists



    }
    // hver av disse metodene skal parse strengen og opprette objekter av tilsvarende type, samt legge dem inn i databasen.
    public TreningsØkt registrerØkt(String s, ArrayList<Øvelse> ø){
        String[] parts = s.split(",");
        String dato = parts[0];
        String StartTid = parts[1];
        String SluttTid = parts[2];
        String Dagsform = parts[3];
        String Prestasjon = parts[4];
        String Notat = parts[5];
        ArrayList<Øvelse> a = new ArrayList<>();
        ArrayList<Øvelse> k = new ArrayList<>();
        for (Øvelse i : ø) {
            if (i.isApparatØvelse) {
                a.add(i);
            } else {
                k.add(i);
            }
        }
        TreningsØkt t_ø = iC.createØkt(dato, StartTid, SluttTid, Dagsform, Prestasjon, Notat, a,k);
        return t_ø;
    }

    public Øvelse registrerØvelse(String s) {
//        System.out.println("registrerer øvelse med streng:\n" + s);
        String[] parts = s.split(",");

        if (parts.length == 2){
            return registrerKroppsØvelse(s);
        }
        else{
            return registrerApparatØvelse(s);
        }

    }

    public ApparatØvelse registrerApparatØvelse(String s) {
        String[] parts = s.split(",");
        String navn = parts[0];
        String kilo = parts[1];
        String sett = parts[2];
        String apparat = parts[3];
        ApparatØvelse ø = iC.createApparatØvelse(navn,kilo,sett,apparat);
        this._registrerteApparatØvelser.add(ø);
        return ø;
    }

    public KroppsØvelse registrerKroppsØvelse(String s) {
        String[] parts = s.split(",");
        String navn = parts[0];
        String beskrivelse = parts[1];
        KroppsØvelse ø = iC.createKroppsØvelse(navn,beskrivelse);
        this._registrerteKroppsØvelser.add(ø);
        return ø;
    }

    public Apparat registrerApparat(String s) {
        String[] parts = s.split(",");
        String navn = parts[0];
        String funkjson = parts[1];
        Apparat a = iC.createApparat(navn,funkjson);
        this._registrerteApparater.add(a);
        return a;
    }

    public ØvelsesGruppe registrerGruppe(String s){
        String navn = s;
        ØvelsesGruppe ø = iC.createGruppe(navn);
        this._registrerteGrupper.add(ø);
        return ø;
    }

    public boolean setAmount(int i){
        if (i <= this._registrerteØkter.size()){
            this._antallØkter = i;
            return true;
        }
        else{
            this._antallØkter = this._registrerteØkter.size();
            return false;
        }
    }

    Comparator<TreningsØkt> øktC = new Comparator<TreningsØkt>() {
        @Override
        public int compare(TreningsØkt o1, TreningsØkt o2) {
            return o2.getDato().compareTo(o1.getDato());
        }
    };



}