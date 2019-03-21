import javax.print.DocFlavor;
import java.awt.*;
import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;



public class JDBCSelect{
    public static boolean debug = true;
    public String url = "jdbc:mysql://mysql.stud.ntnu.no:3306/fs_tdt4145_1_gruppe300?useSSL=false";
    public String u = "sigurdra_root";
    public String pw = "root";
    public DatabaseController dbc;

    public JDBCSelect(DatabaseController dbc){
        this.dbc = dbc;

        }

    public ArrayList<Apparat> getApparatListe() {
        ArrayList<Apparat> ApparatListe = new ArrayList<Apparat>();
//        DriverManager.registerDriver(new java.sql.Driver());
//        Class.forName("java.sql.Driver");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, u, pw);

//            PreparedStatement stmt = conn.prepareStatement("select * from apparat");
            PreparedStatement stmt = conn.prepareStatement("select * from Apparat");
//            stmt.setInt(1, Integer.parseInt(s[0]));
            ResultSet rs = stmt.executeQuery();
            while ( rs.next() ) {
                int apparatID = rs.getInt("ApparatID");
                String navn = rs.getString("Navn");
                String  funksjon = rs.getString("Funksjon");
                Apparat a = new Apparat(apparatID, navn, funksjon);
                ApparatListe.add(a);
//                ApparatListe.add(row);
                if (debug){
                    System.out.println("ApparatID: " + apparatID + "\tNavn: " + navn + "\tFunksjon: " + funksjon);
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch ( Exception e ) {
            e.printStackTrace();
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return ApparatListe;
    }

    public ArrayList getØktListe(){
        ArrayList<TreningsØkt> ØktListe = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, u, pw);
            PreparedStatement stmt = conn.prepareStatement("select * from Treningsøkt");
            ResultSet rs = stmt.executeQuery();
            int aøID = 0;
            while ( rs.next() ) {
                int øktID = rs.getInt("ØktID");
                Date dato = rs.getDate("Dato");
                Time tid = rs.getTime("StartTid");
                Time varighet = rs.getTime("SluttTid");
                int form = rs.getInt("Form");
                int prestasjon = rs.getInt("Prestasjon");
                String notat = rs.getString("Notat");
                ArrayList<Øvelse> øvelser = new ArrayList<>();
                PreparedStatement stmt1 = conn.prepareStatement("select * from ApparatØvelseIØkt where ØktID = (?)");
                stmt1.setInt(1,øktID);
                ResultSet rs1 = stmt1.executeQuery();
                if (rs1.next()) {
                    aøID = rs1.getInt("ApparatØvelseID");
                }
                PreparedStatement stmt4 = conn.prepareStatement("select * from ApparatØvelse where " +
                            "ApparatØvelseID = (?)");
                stmt4.setInt(1,aøID);
                ResultSet rs2 = stmt4.executeQuery();
                while(rs2.next()){
                    int aID = rs2.getInt("ApparatØvelseID");
                    String navn = rs2.getString("Navn");
                    Double kilo = rs2.getDouble("Kilo");
                    int sett = rs2.getInt("Sett");
                    int apparatID = rs2.getInt("ApparatID");
                    ApparatØvelse a = new ApparatØvelse(aøID, navn, sett, kilo, apparatID);
                    øvelser.add(a);
                    this.dbc._registrerteApparatØvelser.add(a);
                }
            rs2.close();

            rs1.close();
            PreparedStatement stmt2 = conn.prepareStatement("select * from KroppsØvelseIØkt where ØktID = (?)");
            stmt2.setInt(1,øktID);
            ResultSet rs3 = stmt2.executeQuery();
            while (rs3.next()){

                int køID = rs3.getInt("KroppsØvelseID");
                PreparedStatement stmt5 = conn.prepareStatement("select * from KroppsØvelse " +
                        "where KroppsØvelseID = (?)");
                stmt5.setInt(1, køID);
                ResultSet rs5 = stmt5.executeQuery();
                while (rs5.next()) {
                    int kID = rs5.getInt("KroppsØvelseID");
                    String navn = rs5.getString("Navn");
                    String beskrivelse = rs5.getString("Beskrivelse");
                    KroppsØvelse a = new KroppsØvelse(kID, navn, beskrivelse);
                    øvelser.add(a);
                    this.dbc._registrerteKroppsØvelser.add(a);
                }
                rs5.close();
            }
            rs3.close();
            TreningsØkt ø = new TreningsØkt(øktID, dato, tid, varighet, form, prestasjon, notat, øvelser);

//                ArrayList row = new ArrayList();
//                row.add(øktID);
//                row.add(dato);
//                row.add(tid);
//                row.add(varighet);
//                row.add(form);
//                row.add(prestasjon);
//                row.add(notat);
                ØktListe.add(ø);
                if (debug){
                    System.out.println(ø);
                    System.out.println(øvelser);
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch ( Exception e ) {
            e.printStackTrace();
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return ØktListe;
    }

    public ArrayList<ØvelsesGruppe> getGrupperListe() {
        ArrayList<ØvelsesGruppe> GruppeListe = new ArrayList<ØvelsesGruppe>();
//        DriverManager.registerDriver(new java.sql.Driver());
//        Class.forName("java.sql.Driver");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, u, pw);

//            PreparedStatement stmt = conn.prepareStatement("select * from apparat");
            PreparedStatement stmt = conn.prepareStatement("select * from ØvelsesGruppe");
//            stmt.setInt(1, Integer.parseInt(s[0]));
            ResultSet rs = stmt.executeQuery();
            while ( rs.next() ) {
                int gruppeID = rs.getInt("ØvelsesGruppeID");
                String navn = rs.getString("Navn");
                ØvelsesGruppe a = new ØvelsesGruppe(gruppeID, navn);
                GruppeListe.add(a);
//                ApparatListe.add(row);
                if (debug){
                    System.out.println("ØvelsesGruppeID: " + gruppeID + "\tNavn: " + navn);
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch ( Exception e ) {
            e.printStackTrace();
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return GruppeListe;
    }

}