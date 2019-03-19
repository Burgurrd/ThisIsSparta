import java.sql.*;
import java.util.ArrayList;
import java.awt.*;
import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;



public class JDBCInsert {

    public static boolean debug = true;

    public ArrayList<Object> createApparat(String navn, String funksjon) throws java.lang.ClassNotFoundException{
        ArrayList<Object> ApparatListe = new ArrayList<Object>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/treningsdagbok?useSSL=false", "root", "Fittehull2014");

            PreparedStatement stmt = conn.prepareStatement("insert into apparat(Navn,Funksjon) values(?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, navn);
            stmt.setString(2, funksjon);
            int id = stmt.executeUpdate();
            ApparatListe.add(id);
            ApparatListe.add(navn);
            ApparatListe.add(funksjon);
            stmt.close();
            conn.close();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return ApparatListe ;
    }



    public void createØktØvelserRelasjon(int id, ArrayList<Øvelse> a, ArrayList<Øvelse> k){
       for (Øvelse ø : a){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/treningsdagbok?useSSL=false", "root", "Fittehull2014");

                PreparedStatement stmt = conn.prepareStatement("insert into apparatøvelseiøkt(øktid, apparatøvelseid) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, id);
                stmt.setInt(2, ø.getID());
                if (debug) {
                    System.out.println(id +  ø.getID());
                }
                stmt.close();
                conn.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
        for (Øvelse ø : k){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/treningsdagbok?useSSL=false", "root", "Fittehull2014");

                PreparedStatement stmt = conn.prepareStatement("insert into kroppsøvelseiøkt(øktid, KroppsØvelseID) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, id);
                stmt.setInt(2, ø.getID());
                if (debug) {
                    System.out.println(id +  ø.getID());
                }
                stmt.close();
                conn.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
    }

    public TreningsØkt createØkt(String dato, String starttid, String sluttid, String form, String prestasjon, String notat, ArrayList<Øvelse> a, ArrayList<Øvelse> k) throws java.lang.ClassNotFoundException{
        int id = 0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/treningsdagbok?useSSL=false", "root", "Fittehull2014");

            PreparedStatement stmt = conn.prepareStatement("insert into treningsøkt(dato, starttid, slutttid, form, prestasjon, notat) values (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS );
            Date date = new SimpleDateFormat("yyyy-mm-dd").parse(dato);
            java.sql.Date _date = new java.sql.Date(date.getTime());
            stmt.setDate(1, _date);
            Date startTime = new SimpleDateFormat("hh:mm").parse(starttid);
            java.sql.Time _time = new java.sql.Time(startTime.getTime());
            Date endTime = new SimpleDateFormat("hh:mm").parse(starttid);
            java.sql.Time _time1 = new java.sql.Time(startTime.getTime());
            stmt.setTime(2, _time);
            stmt.setTime(3, _time1);
            int _form = Integer.parseInt(form);
            stmt.setInt(4, _form);
            int _prest = Integer.parseInt(prestasjon);
            stmt.setInt(5, _prest);
            stmt.setString(6, notat);
            //ØktID returneres etter at "insert"-spørringen utføres.
            id = stmt.executeUpdate();
            ArrayList<Øvelse> øvelser = new ArrayList<>();
            øvelser.addAll(a);
            øvelser.addAll(k);
            createØktØvelserRelasjon(id, a, k);
            TreningsØkt t_økt = new TreningsØkt(id,_date, _time, _time1, _form, _prest, notat, øvelser);

            if (debug){
                System.out.println(t_økt);
                System.out.println("a:\t" + a + "\tk:\t" + k);
            }
            stmt.close();
            conn.close();
            return  t_økt;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        return null;
    }

    public static void main(String[] args)  {
        JDBCInsert jdbci = new JDBCInsert();
        try{
            ArrayList liste = jdbci.createApparat("navn","funksjon");
            System.out.println(liste);
        } catch(ClassNotFoundException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}

