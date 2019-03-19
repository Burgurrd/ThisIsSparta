import java.awt.*;
import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;



public class JDBCSelect{
    public static boolean debug = true;

    public ArrayList getApparatListe(String ... s) throws java.lang.ClassNotFoundException{
        ArrayList<ArrayList<Object>> ApparatListe = new ArrayList<ArrayList<Object>>();
//        DriverManager.registerDriver(new java.sql.Driver());
//        Class.forName("java.sql.Driver");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/treningsdagbok?useSSL=false", "root", "Fittehull2014");

//            PreparedStatement stmt = conn.prepareStatement("select * from apparat");
            PreparedStatement stmt = conn.prepareStatement("select * from apparat where ApparatID > ?");
            stmt.setInt(1, Integer.parseInt(s[0]));
            ResultSet rs = stmt.executeQuery();
            while ( rs.next() ) {
                int apparatID = rs.getInt("ApparatID");
                String navn = rs.getString("Navn");
                String  funksjon = rs.getString("Funksjon");
                ArrayList row = new ArrayList();
                row.add(apparatID);
                row.add(navn);
                row.add(funksjon);
                ApparatListe.add(row);
                if (debug){
                    System.out.println("ApparatID: " + apparatID + "\tNavn: " + navn + "\tFunksjon: " + funksjon);
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return ApparatListe;
    }

    public ArrayList getØktListe(String ... s) throws java.lang.ClassNotFoundException{
        ArrayList<ArrayList<String>> ØktListe = new ArrayList<ArrayList<String>>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/treningsdagbok?useSSL=false", "root", "Fittehull2014");
            // if ONE argument is passed to the function the query gets all info from the table.
            if (s.length == 1){


            }
            // else we can initiate other queries to select parts of the data.
            // i.e. the last 10 exercises...
            else if (s[1] == "1"){
                PreparedStatement stmt = conn.prepareStatement("select * from Treningsøkt where ØktID > ?");
                stmt.setInt(1, Integer.parseInt(s[0]));
                ResultSet rs = stmt.executeQuery();

            }
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
                ArrayList row = new ArrayList();
                row.add(øktID);
                row.add(dato);
                row.add(tid);
                row.add(varighet);
                row.add(form);
                row.add(prestasjon);
                row.add(notat);
                ØktListe.add(row);
                if (debug){
                    System.out.println(row);
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return ØktListe;
    }

    public static void main(String[] args)  {
        JDBCSelect jdbcsa = new JDBCSelect();
        try{
            ArrayList liste = jdbcsa.getApparatListe("1");
            ArrayList liste1 = jdbcsa.getØktListe("1");
            System.out.println(liste);
            System.out.println(liste1);
        } catch(ClassNotFoundException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}