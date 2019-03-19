import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseController{


    // hver av disse metodene skal parse strengen og opprette objekter av tilsvarende type, samt legge dem inn i databasen.
    public TreningsØkt registrerØkt(String s, ArrayList<Øvelse> ø) throws ClassNotFoundException{
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

    public void registrerØvelse(String s) {
        String[] parts = s.split(",");

        if (parts.length == 2){
            registrerKroppsØvelse(s);
        }
        else{
            registrerApparatØvelse(s);
        }
    }

    public void registrerApparatØvelse(String s) {
        String[] parts = s.split(",");
        String navn = parts[0];
        String kilo = parts[1];
        String sett = parts[2];
        String apparat = parts[3];

    }

    public void registrerKroppsØvelse(String s) {
        String[] parts = s.split(",");
        String navn = parts[0];
        String beskrivelse = parts[1];
    }

    public void registrerApparat(String s) {
        String[] parts = s.split(",");
        String navn = parts[0];
        String funkjson = parts[1];

    }

    public void registrerGruppe(String s){
        String navn = s;
        ØvelsesGruppe t_gruppe = new ØvelsesGruppe(id, navn);

        return t_gruppe
    }



}