import java.awt.*;
import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;



public class JDBCSelect{
    public static boolean debug = true;
    public String url = "jdbc:mysql://localhost:3306/treningsdagbok?useSSL=false";
    public String u = "root";
    public String pw = "Fittehull2014";

    public ArrayList<Apparat> getApparatListe() {
        ArrayList<Apparat> ApparatListe = new ArrayList<Apparat>();
//        DriverManager.registerDriver(new java.sql.Driver());
//        Class.forName("java.sql.Driver");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, u, pw);

//            PreparedStatement stmt = conn.prepareStatement("select * from apparat");
            PreparedStatement stmt = conn.prepareStatement("select * from apparat");
//            stmt.setInt(1, Integer.parseInt(s[0]));
            ResultSet rs = stmt.executeQuery();
            while ( rs.next() ) {
                int apparatID = rs.getInt("ApparatID");
                String navn = rs.getString("Navn");
                String  funksjon = rs.getString("Funksjon");
                ArrayList row = new ArrayList();
                row.add(apparatID);
                row.add(navn);
                row.add(funksjon);
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
            while ( rs.next() ) {
                int øktID = rs.getInt("ØktID");
                Date dato = rs.getDate("Dato");
                Time tid = rs.getTime("StartTid");
                Time varighet = rs.getTime("SluttTid");
                int form = rs.getInt("Form");
                int prestasjon = rs.getInt("Prestasjon");
                String notat = rs.getString("Notat");
                ArrayList<Øvelse> øvelser = new ArrayList<>();
                PreparedStatement stmt1 = conn.prepareStatement("select * from apparatøvelseiøkt where ØktID = (?)");
                stmt1.setInt(1,øktID);
                ResultSet rs1 = stmt1.executeQuery();
                while (rs1.next()){
                    int aøID = rs1.getInt("ApparatØvelseID");
                    String navn = rs1.getString("Navn");
                    Double kilo = rs1.getDouble("Kilo");
                    int sett = rs1.getInt("Sett");
                    int apparatID = rs1.getInt("ApparatID");
                    ApparatØvelse a = new ApparatØvelse(aøID, navn, sett, kilo, apparatID);
                    øvelser.add(a);
                }
                rs1.close();
                PreparedStatement stmt2 = conn.prepareStatement("select * from kroppsøvelseiøkt where ØktID = (?)");
                stmt2.setInt(1,øktID);
                ResultSet rs2 = stmt2.executeQuery();
                while (rs2.next()){
                    int aøID = rs2.getInt("KroppsØvelseID");
                    String navn = rs2.getString("Navn");
                    String beskrivelse = rs2.getString("Beskrivelse");
                    KroppsØvelse a = new KroppsØvelse(aøID, navn, beskrivelse);
                    øvelser.add(a);
                }
                rs2.close();
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

}