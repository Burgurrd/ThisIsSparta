import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.*;

public class TextUI {

    private DatabaseController DBC;
    private Scanner inp;
    private ArrayList<TreningsØkt> _øktListe;

    public void initUI() {
        DBC = new DatabaseController();
        inp = new Scanner(System.in);
        menyDirect();
    }

    //--------------------Meny--------------------
    public void menyDirect() {//Her navigerer man mellom hovedmenyer for ulike tabs
        System.out.println("\nNaviger til: \nMine okter | Ny okt | ovelser | Apparater | ovelsesgrupper");
        String cmd = inp.nextLine();
        if (cmd.equalsIgnoreCase("Mine okter")) {
            MineØkter_Main();
        } else if (cmd.equalsIgnoreCase("Ny okt")) {
            NyØkt_Main();
        } else if (cmd.equalsIgnoreCase("ovelser")) {
            Øvelser_Main();
        } else if (cmd.equalsIgnoreCase("Apparater")) {
            Apparater_Main();
        } else if (cmd.equalsIgnoreCase("ovelsesgrupper")) {
            Øvelsesgrupper_Main();
        } else {
            System.out.println("ERROR: Ugyldig kommando");
            menyDirect();
        }
    }


    // --------------------Mine Økter--------------------
    public void MineØkter_Main() {//Hovedmeny for denne taben. brukes til å navigere
        System.out.println("Jeg vil: \nMeny | Sett Antall ("+ DBC._antallØkter + ") | Vis okter");
        String cmd = inp.nextLine();
        if (cmd.equalsIgnoreCase("Meny")) {
            menyDirect();
        } else if (cmd.equalsIgnoreCase("Sett Antall")) {
            MineØkter_SettAntall();
        } else if (cmd.equalsIgnoreCase("Vis okter")) {
            MineØkter_VisØkter();
        } else {
            System.out.println("ERROR: Ugyldig kommando");
            MineØkter_Main();
        }
    }

    public void MineØkter_SettAntall() {//Setter antall økter som skal vises
        System.out.println("Velg antall okter til visning");
        String x = inp.nextLine();
        if (x.equalsIgnoreCase("Tilbake")) {
            MineØkter_Main();
        }
        int amount = Integer.parseInt(x);
        if (!(DBC.setAmount(amount))){
            System.out.println("ERROR: Det er bare " + DBC._antallØkter + " antall okter registrert.");
            MineØkter_SettAntall();
        }
        else {
            MineØkter_Main();
        }
    }

    public void MineØkter_VisØkter() {//Viser et gitt antall økter med dato og navn
        for (int i = 0; i<DBC._antallØkter;i++){
            TreningsØkt elem = DBC._registrerteØkter.get(i);
            System.out.println("---------------------------------------------------------------------");
            System.out.println(elem.getDato());//elem.dateString);
            System.out.println(elem.getØktID());//elem.nameString);
        }
        System.out.println("");
        System.out.println("Jeg vil: \nVis Detaljer | Tilbake");
        String cmd = inp.nextLine();
        if (cmd.equalsIgnoreCase("Vis Detaljer")) {
            MineØkter_Detaljer();
        } else if (cmd.equalsIgnoreCase("Tilbake")) {
            MineØkter_Main();
        }
    }

    public void MineØkter_Detaljer() {//Gir detaljer om økt spesifisert med navn
        System.out.println("ID på okt: | \n'Tilbake' for å gå tilbake");
        String cmd = inp.nextLine();
        if (cmd.equalsIgnoreCase("Tilbake")) {
            MineØkter_Main();
        }
        try{
        ArrayList<Integer> oktIDs = new ArrayList<>();
        for (TreningsØkt ø : DBC._registrerteØkter){
            oktIDs.add(ø.getØktID());
        }
        if(!(oktIDs.contains(Integer.parseInt(cmd)))){
            System.out.println("ERROR: Ugyldig ID");
            MineØkter_Detaljer();
        }}
        catch (Exception e){
            System.out.println("ERROR: Ugyldig ID");
            MineØkter_Detaljer();
        }
        for (TreningsØkt elem : DBC._registrerteØkter) {
            if (Integer.parseInt(cmd) == (elem.getØktID())) {//elem.nameString)){
                System.out.println("Dato\t" + elem.getDato());//elem.dateString);
                for (Øvelse ø : elem.getØvelser()){
                    System.out.println("Ovelsestype:\t" + ø.getType() + "ovelse" + "\tOvelsesnavn:\t" + ø.getNavn());
                }
//                System.out.println(elem.getØvelser());//elem.øvelserString);
                System.out.println("Notat:\t" + elem.getNotat());//elem.resultatString);
                System.out.println("");
            }
        }
        MineØkter_Detaljer();
    }


//--------------------Ny Økt--------------------
    public void NyØkt_Main() {//Hovedmeny for denne taben. brukes til å navigere
        System.out.println("Jeg vil: \nMeny | Registrer okt");
        String cmd = inp.nextLine();
        if(cmd.equalsIgnoreCase("Meny")){
            menyDirect();
        }
        else if(cmd.equalsIgnoreCase("Registrer okt")){
            NyØkt_RegistrerØkt();
        }
        else{
            System.out.println("ERROR: Ugyldig kommando");
            NyØkt_Main();
        }
    }

    private void NyØkt_RegistrerØkt() {//Registrerer en ny økt
        ArrayList<Øvelse> ValgteØvelser = new ArrayList<>();
        String økt = "";
        int cnt = 0;
        System.out.println("Dato: \ndd.mm.åå | 'Tilbake' for å gå tilbake");
        String cmd = inp.nextLine();
        if(cmd.equalsIgnoreCase("Tilbake")){
            NyØkt_Main();
        }
        økt += cmd;
        økt += ",";
        System.out.println("Starttid: \nhh.mm");
        økt += inp.nextLine();
        økt += ",";
        System.out.println("Sluttid: \nhh.mm");
        økt += inp.nextLine();
        økt += ",";
        System.out.println("Dagsform: \n1-10");
        økt += inp.nextLine();
        økt += ",";
        System.out.println("Prestasjon: \n1-10");
        økt += inp.nextLine();
        økt += ",";
        System.out.println("Resultat: \nSkriftlig oppsummering");
        økt += inp.nextLine();
        for (Øvelse a :DBC._registrerteApparatØvelser){
            System.out.println("Apparatovelse:\t" + a.getNavn());
        }
        for (Øvelse a :DBC._registrerteKroppsØvelser){
            System.out.println("Kroppsovelse:\t" + a.getNavn());
        }
        System.out.println("Legg til ovelse: \nNavn på ovelse, 'Ferdig' avslutter valget");
        cmd = inp.nextLine();
        while(!cmd.equalsIgnoreCase("Ferdig")){
            for(Øvelse ø : DBC._registrerteKroppsØvelser){
                if(cmd.equals(ø.getNavn())){
                    ValgteØvelser.add(ø);
                    break;
                }
                else{
                    cnt += 1;
                }
            }
            for(Øvelse ø : DBC._registrerteApparatØvelser){
                if(cmd.equals(ø.getNavn())){
                    ValgteØvelser.add(ø);
                    break;
                }
                else{
                    cnt += 1;
                }
            }
            if(cnt == (DBC._registrerteApparatØvelser.size()+DBC._registrerteKroppsØvelser.size())){
                System.out.println("ovelse ikke funnet, ble ikke lagt til");
            }

            System.out.println("Legg til ovelse: \nNavn på ovelse, 'Ferdig' avslutter valget");
            cmd = inp.nextLine();
        }
        DBC.registrerØkt(økt, ValgteØvelser);
        NyØkt_Main();
    }

// --------------------Øvelser--------------------
    public void Øvelser_Main(){//Hovedmeny for denne taben. brukes til å navigere
        System.out.println("Jeg vil: \nMeny | Vis ovelser | Registrer ovelse");
        String cmd = inp.nextLine();
        if(cmd.equalsIgnoreCase("Meny")){
            menyDirect();
        }
        else if(cmd.equalsIgnoreCase("Vis ovelser")){
            Øvelser_VisØvelser();
        }
        else if(cmd.equalsIgnoreCase("Registrer ovelse")){
            Øvelser_RegistrerØvelse();
        }
        else{
            System.out.println("ERROR: Ugyldig kommando");
            Øvelser_Main();
        }
    }

    private void Øvelser_VisØvelser(){ //Viser Øvelser
        for (Øvelse ø : DBC._registrerteApparatØvelser){
            System.out.println("Ovelsestype:\t" + ø.getType() + "ovelse" + "\tOvelsesnavn:\t" + ø.getNavn());
        }
        for (Øvelse ø : DBC._registrerteKroppsØvelser){
            System.out.println("Ovelsestype:\t" + ø.getType() + "ovelse" + "\tOvelsesnavn:\t" + ø.getNavn());
        }
        Øvelser_Main();

    }
    private void Øvelser_RegistrerØvelse() {//Registrerer en ny øvelse
        String øvelse = "";
        Boolean apparatøvelse = null;
        Boolean idiot = true;
        System.out.println("Navn: | 'Tilbake' for å gå tilbake");
        String cmd = inp.nextLine();
        if(cmd.equalsIgnoreCase("Tilbake")){
            Øvelser_Main();
        }
        øvelse += cmd;
        øvelse += ",";
        while(idiot == true){
            System.out.println("Apparatovelse? \nJa | Nei");
            String x = inp.nextLine();
            if(x.equalsIgnoreCase("Ja")){
                apparatøvelse = true;
                idiot = false;
            }
            else if(x.equalsIgnoreCase("Nei")){
                apparatøvelse = false;
                idiot = false;
            }
            else{
                System.out.println("ERROR: Ugyldig kommando");
            }
        }
        if(apparatøvelse==true){
            idiot = true;
            int cnt = 0;
            System.out.println("Antall kilo: ");
            øvelse += inp.nextLine();
            øvelse += ",";
            System.out.println("Antall sett: ");
            øvelse += inp.nextLine();
            øvelse += ",";
            while(idiot==true){
                for (Apparat a :DBC._registrerteApparater){
                System.out.println("ApparatID\t" + a.getApparatID() + "\tApparatnavn:\t" + a.getNavn());
                }
                System.out.println("Velg Apparat: ");
                String x = inp.nextLine().toString();
//                System.out.println("x:\t" + x);
//                System.out.println("x.getclass:\t" + x.getClass());
                for(Apparat a : DBC._registrerteApparater){
//                    System.out.println(a.getNavn());
                    if(x.toString().equalsIgnoreCase(a.getNavn().toString())){

                        øvelse += a.getApparatID();

                        idiot = false;
                        break;
                    }
                    else{
                        cnt += 1;
                    }
                }
                if(cnt == DBC._registrerteApparater.size()){
                    System.out.println("ERROR: Dette apparatet finnes ikke");
                }
            }
        }
        else if(apparatøvelse==false){
            System.out.println("Beskrivelse: ");
            øvelse += inp.nextLine();
        }
        else{
            System.out.println("ERROR: Dette skal ikke kunne skje, hackerman...");
            menyDirect();
        }
        DBC.registrerØvelse(øvelse);
        Øvelser_Main();
    }

// --------------------Apparater--------------------
    public void Apparater_Main(){//Hovedmeny for denne taben. brukes til å navigere
        System.out.println("Jeg vil: \nMeny | Vis Apparater | Registrer Apparat");
        String cmd = inp.nextLine();
        if(cmd.equalsIgnoreCase("Meny")){
            menyDirect();
        }
        else if(cmd.equalsIgnoreCase("Vis Apparater")){
            Apparater_VisApparater();
        }
        else if(cmd.equalsIgnoreCase("Registrer Apparat")){
            Apparater_RegistrerApparat();
        }
        else{
            System.out.println("ERROR: Ugyldig kommando");
            Apparater_Main();
        }
    }

    private void Apparater_VisApparater() {//Viser alle apparater med navn
//        ArrayList<Apparat> Apparater = new ArrayList<>();
//        DBC.addApparaterToList();
        for(Apparat elem : DBC._registrerteApparater){
            System.out.println("---------------------------------------------------------------------");
            System.out.println(elem.getNavn());
        }
        System.out.println("");
        System.out.println("Jeg vil: \nVis Detaljer | Tilbake");
        String cmd = inp.nextLine();
        if(cmd.equalsIgnoreCase("Vis Detaljer")){
            Apparater_Detaljer();
        }
        else if(cmd.equalsIgnoreCase("Tilbake")){
            Apparater_Main();
        }
    }
    public void Apparater_Detaljer() {//Gir detaljer om apparat spesifisert med navn
        System.out.println("Navn på apparat: | 'Tilbake' for å gå tilbake");
        String cmd = inp.nextLine();
        if(cmd.equalsIgnoreCase("Tilbake")){
            Apparater_Main();
        }
        for(Apparat elem : DBC._registrerteApparater){
            if(cmd.equals(elem.getNavn())){
                System.out.println(elem.getNavn());
                System.out.println(elem.getFunksjon());
                System.out.println("");
            }
        }
        Apparater_Detaljer();
    }

    private void Apparater_RegistrerApparat() {//Registrerer et nytt apparat
        String apparat = "";
        System.out.println("Navn: | 'Tilbake' for å gå tilbake");
        String cmd = inp.nextLine();
        if(cmd.equalsIgnoreCase("Tilbake")){
            Apparater_Main();
        }
        apparat += cmd;
        apparat += ",";
        System.out.println("Funksjon: ");
        apparat += inp.nextLine();
        apparat += ",";
        DBC.registrerApparat(apparat);
        Apparater_Main();
    }

// --------------------Øvelsesgrupper--------------------
    public void Øvelsesgrupper_Main(){//Hovedmeny for denne taben. brukes til å navigere
        System.out.println("Jeg vil: \nMeny | Vis ovelsesgrupper | Lag Ny Gruppe | Legg til ovelse i gruppe");
        String cmd = inp.nextLine();
        if(cmd.equalsIgnoreCase("Meny")){
            menyDirect();
        }
        else if(cmd.equalsIgnoreCase("Vis ovelsesgrupper")){
            Øvelsesgrupper_VisGrupper();
        }
        else if(cmd.equalsIgnoreCase("Lag Ny Gruppe")){
            Øvelsesgrupper_RegistrerGruppe();
        }
        else if(cmd.equalsIgnoreCase("Legg til ovelse i gruppe")){
            Øvelsesgrupper_LeggTilØvelse();
        }
        else{
            System.out.println("ERROR: Ugyldig kommando");
            Øvelsesgrupper_Main();
        }
    }

    public void Øvelsesgrupper_VisGrupper(){//Viser alle grupper med navn
//        ArrayList<ØvelsesGruppe> grupper = new ArrayList<>();
//        DBC.addGrupperToList(grupper);
        for(ØvelsesGruppe elem : DBC._registrerteGrupper){
            System.out.println("---------------------------------------------------------------------");
            System.out.println(elem.getNavn());
        }
        System.out.println("");
        System.out.println("Jeg vil: \nVis Detaljer | Tilbake");
        String cmd = inp.nextLine();
        if(cmd.equalsIgnoreCase("Vis Detaljer")){
            Øvelsesgrupper_Detaljer();
        }
        else if(cmd.equalsIgnoreCase("Tilbake")){
            Øvelsesgrupper_Main();
        }
    }

    public void Øvelsesgrupper_Detaljer() {//Gir detaljer om gruppe spesifisert med navn
        System.out.println("Navn på gruppe: | 'Tilbake' for å gå tilbake");
        String cmd = inp.nextLine();
        if(cmd.equalsIgnoreCase("Tilbake")){
            Øvelsesgrupper_Main();
        }
        for(ØvelsesGruppe elem : DBC._registrerteGrupper){
            if(cmd.equals(elem.getNavn())){
                System.out.println(elem.getNavn());
                for (Øvelse ø : elem.getØvelser()){
                    System.out.println("ovelse:\t" + ø.getNavn());
                }
                System.out.println("");
            }
        }
        Øvelsesgrupper_Detaljer();
    }

    public void Øvelsesgrupper_RegistrerGruppe(){//Registrerer en ny gruppe
        String gruppe = "";
        System.out.println("Navn: | 'Tilbake' for å gå tilbake");
        String cmd = inp.nextLine();
        if(cmd.equalsIgnoreCase("Tilbake")){
            Øvelsesgrupper_Main();
        }
        gruppe += cmd;
        DBC.registrerGruppe(gruppe);
        Øvelsesgrupper_Main();
    }

    public void Øvelsesgrupper_LeggTilØvelse(){//Legger til øvelse til en gitt øvelsesgruppe
        ØvelsesGruppe activeGroup = null;
        int cnt = 0;
        for (ØvelsesGruppe g :DBC._registrerteGrupper){
            System.out.println("Gruppenavn:\t" + g.getNavn());
        }
        System.out.println("Velg en ovelsesgruppe: \nNavn på ovelsesgruppe | 'Tilbake' for å gå tilbake");
        String cmd = inp.nextLine();
        if(cmd.equalsIgnoreCase("Tilbake")){
            Øvelsesgrupper_Main();
        }
        for(ØvelsesGruppe øg : DBC._registrerteGrupper){
            if(cmd.equals(øg.getNavn())){
                activeGroup = øg;
            }
            else{
                cnt += 1;
            }
        }
        if(cnt == DBC._registrerteGrupper.size()){
            System.out.println("ERROR: ovelsesgruppe finnes ikke");
            Øvelsesgrupper_LeggTilØvelse();
        }
        System.out.println("Legg til ovelse: \nNavn på ovelse, 'Ferdig' avslutter valget");
        cmd = inp.nextLine();
        while(!cmd.equalsIgnoreCase("Ferdig")){
            for(Øvelse ø : DBC._registrerteKroppsØvelser){

                if(cmd.equals(ø.getNavn())){
                    activeGroup.addØvelser(ø);
                    break;
                }
                else{
                    cnt += 1;
                }
            }
            for(Øvelse ø : DBC._registrerteApparatØvelser){
                if(cmd.equals(ø.getNavn())){
                    activeGroup.addØvelser(ø);
                    break;
                }
                else{
                    cnt += 1;
                }
            }
            if(cnt==(DBC._registrerteKroppsØvelser.size()+DBC._registrerteApparatØvelser.size())){
                System.out.println("ERROR: ovelse ikke funnet, ble ikke lagt til");
            }
            System.out.println("Legg til ovelse: \nNavn på ovelse, 'Ferdig' avslutter valget");
            cmd = inp.nextLine();
        }
        Øvelsesgrupper_Main();
    }
    public static void main(String[] args) {//main metode
        TextUI txt = new TextUI();
        txt.initUI();
    }
}