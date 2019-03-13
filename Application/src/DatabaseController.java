import java.lang.reflect.Array;
import java.util.ArrayList;

public class DatabaseController{


    // hver av disse metodene skal parse strengen og opprette objekter av tilsvarende type, samt legge dem inn i databasen.
    public void registrerØkt(String s, ArrayList<Øvelse> a) {
        String[] parts = s.split(",");
        String dato = parts[0];
        String StartTid = parts[1];
        String SluttTid = parts[2];
        String Dagsform = parts[3];
        String Prestasjon = parts[4];
        String Notat = parts[5];
        ArrayList<Øvelse> _Øvelser = a;


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
    }



}