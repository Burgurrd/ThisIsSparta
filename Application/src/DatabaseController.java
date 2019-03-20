import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseController{

    public int _antallØkter;
    public ArrayList<Øvelse> _registrerteKroppsØvelser = new ArrayList<Øvelse>();
    public ArrayList<Øvelse> _registrerteApparatØvelser = new ArrayList<Øvelse>();
    public ArrayList<Apparat> _registrerteApparater = new ArrayList<Apparat>();
    public ArrayList<ØvelsesGruppe> _registrerteGrupper = new ArrayList<ØvelsesGruppe>();

    public DatabaseController(){
        //update all Lists


    }
    // hver av disse metodene skal parse strengen og opprette objekter av tilsvarende type, samt legge dem inn i databasen.
    public TreningsØkt registrerØkt(String s, ArrayList<Øvelse> ø){
        JDBCInsert iC = new JDBCInsert();
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
        String[] parts = s.split(",");

        if (parts.length == 2){
            return registrerKroppsØvelse(s);
        }
        else{
            return registrerApparatØvelse(s);
        }

    }

    public ApparatØvelse registrerApparatØvelse(String s) {
        JDBCInsert iC = new JDBCInsert();
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
        JDBCInsert iC = new JDBCInsert();
        String[] parts = s.split(",");
        String navn = parts[0];
        String beskrivelse = parts[1];
        KroppsØvelse ø = iC.createKroppsØvelse(navn,beskrivelse);
        this._registrerteKroppsØvelser.add(ø);
        return ø;
    }

    public Apparat registrerApparat(String s) {
        JDBCInsert iC = new JDBCInsert();
        String[] parts = s.split(",");
        String navn = parts[0];
        String funkjson = parts[1];
        Apparat a = iC.createApparat(navn,funkjson);
        this._registrerteApparater.add(a);
        return a;
    }

    public ØvelsesGruppe registrerGruppe(String s){
        JDBCInsert iC = new JDBCInsert();
        String navn = s;
        ØvelsesGruppe ø = iC.createGruppe(navn);
        this._registrerteGrupper.add(ø);
        return ø;
    }

    public void setAmount(int i){
        this._antallØkter = i;
    }

    public ArrayList<TreningsØkt> addØkterToList(){



        ArrayList<TreningsØkt> ø = new ArrayList<TreningsØkt>();
        return ø;
    }



}