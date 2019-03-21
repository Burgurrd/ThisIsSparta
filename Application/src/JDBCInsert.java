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
    public String url = "jdbc:mysql://mysql.stud.ntnu.no:3306/fs_tdt4145_1_gruppe300?useSSL=false";
    public String u = "sigurdra_root";
    public String pw = "root";


    public void createØktØvelserRelasjon(int id, ArrayList<Øvelse> a, ArrayList<Øvelse> k){
       for (Øvelse ø : a){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, u, pw);

                PreparedStatement stmt = conn.prepareStatement("insert into apparatøvelseiøkt(øktid, apparatøvelseid) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, id);
                stmt.setInt(2, ø.getID());
                if (debug) {
                    System.out.println(id +  ø.getID());
                }
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
        for (Øvelse ø : k){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, u, pw);

                PreparedStatement stmt = conn.prepareStatement("insert into kroppsøvelseiøkt(øktid, KroppsØvelseID) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, id);
                stmt.setInt(2, ø.getID());
                if (debug) {
                    System.out.println(id +  ø.getID());
                }
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
    }

    public TreningsØkt createØkt(String dato, String starttid, String sluttid, String form, String prestasjon, String notat, ArrayList<Øvelse> a, ArrayList<Øvelse> k) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, u, pw);

            PreparedStatement stmt = conn.prepareStatement("insert into treningsøkt(dato, starttid, slutttid, form, prestasjon, notat) values (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS );
            Date date = new SimpleDateFormat("dd.mm.yy").parse(dato);
            java.sql.Date _date = new java.sql.Date(date.getTime());
            stmt.setDate(1, _date);
            Date startTime = new SimpleDateFormat("hh.mm").parse(starttid);
            java.sql.Time _time = new java.sql.Time(startTime.getTime());
            Date endTime = new SimpleDateFormat("hh.mm").parse(starttid);
            java.sql.Time _time1 = new java.sql.Time(startTime.getTime());
            stmt.setTime(2, _time);
            stmt.setTime(3, _time1);
            int _form = Integer.parseInt(form);
            stmt.setInt(4, _form);
            int _prest = Integer.parseInt(prestasjon);
            stmt.setInt(5, _prest);
            stmt.setString(6, notat);
            //ØktID returneres etter at "insert"-spørringen utføres.
            int id = stmt.executeUpdate();
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
            e.printStackTrace();
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        return null;
    }

    public ApparatØvelse createApparatØvelse(String navn, String kilo, String sett, String apparat) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, u, pw);

            PreparedStatement stmt = conn.prepareStatement("insert into apparatøvelse(Navn, Kilo, Sett, ApparatID) values ( ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS );
            stmt.setString(1,navn);
            double _kilo = Double.parseDouble(kilo);
            stmt.setDouble(2, _kilo);
            int _sett = Integer.parseInt(sett);
            stmt.setInt(3, _sett);
            int _appID = Integer.parseInt(apparat);
            stmt.setInt(4, _appID);
            int id = stmt.executeUpdate();
            ApparatØvelse a_ø = new ApparatØvelse(id, navn, _sett, _kilo,_appID);
            if (debug){
                System.out.println(a_ø);
            }
            stmt.close();
            conn.close();
            return  a_ø;
        } catch (Exception e ) {
            e.printStackTrace();
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return null;
    }


    public KroppsØvelse createKroppsØvelse(String navn, String beskrivelse) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, u, pw);

            PreparedStatement stmt = conn.prepareStatement("insert into kroppsøvelse(Navn, Beskrivelse) values ( ?, ?)", Statement.RETURN_GENERATED_KEYS );
            stmt.setString(1,navn);
            stmt.setString(2, beskrivelse);
            int id = stmt.executeUpdate();
            KroppsØvelse k_ø = new KroppsØvelse(id, navn, beskrivelse);
            if (debug){
                System.out.println(k_ø);
            }
            stmt.close();
            conn.close();
            return  k_ø;
        } catch (Exception e ) {
            e.printStackTrace();
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return null;
    }


    public Apparat createApparat(String navn, String funksjon) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, u, pw);

            PreparedStatement stmt = conn.prepareStatement("insert into apparat(Navn, Funksjon) values ( ?, ?)", Statement.RETURN_GENERATED_KEYS );
            stmt.setString(1,navn);
            stmt.setString(2, funksjon);
            int id = stmt.executeUpdate();
            Apparat a = new Apparat(id, navn, funksjon);
            if (debug){
                System.out.println(a);
            }
            stmt.close();
            conn.close();
            return  a;
        } catch (Exception e ) {
            e.printStackTrace();
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return null;
    }

    public ØvelsesGruppe createGruppe(String navn) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, u, pw);

            PreparedStatement stmt = conn.prepareStatement("insert into øvelsesgruppe(Navn) values (?)", Statement.RETURN_GENERATED_KEYS );
            stmt.setString(1,navn);
            int id = stmt.executeUpdate();
            ØvelsesGruppe øg = new ØvelsesGruppe(id, navn);
            if (debug){
                System.out.println(øg);
            }
            stmt.close();
            conn.close();
            return  øg;
        } catch (Exception e ) {
            e.printStackTrace();
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return null;
    }

}

