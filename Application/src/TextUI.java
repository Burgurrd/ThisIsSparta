import java.util.*;

public class TextUI{

    private DatabaseController DBC;
    private Scanner inp;

    public void initUI(){
        DBC = new DatabaseController();
        MineØkter_Main();
        inp = new Scanner(System.in);
    }
//--------------------Meny--------------------
    public void menyDirect() {//Her navigerer man mellom hovedmenyer for ulike tabs
        System.out.println("Naviger til: (Mine Økter/Ny Økt/Øvelser/Apparater/Øvelsesgrupper)");
        String cmd = inp.nextLine();
        if(cmd.equals("Mine Økter")){
            MineØkter_Main();
        }
        else if(cmd.equals("Ny Økt")){
            NyØkt_Main();
        }
        else if(cmd.equals("Øvelser")){
            Øvelser_Main();
        }
        else if(cmd.equals("Apparater")){
            Apparater_Main();
        }
        else if(cmd.equals("Øvelsesgrupper")){
            Øvelsesgrupper_Main();
        }
        else{
            System.out.println("ERROR: Ugyldig kommando");
            menyDirect();
        }
    }

    
    
// --------------------Mine Økter--------------------
    public void MineØkter_Main() {//Hovedmeny for denne taben. brukes til å navigere
        System.out.println("Jeg vil: (Meny/Sett Antall/Vis Økter)");
        String cmd = inp.nextLine();
        if(cmd.equals("Meny")){
            menyDirect();
        }
        else if(cmd.equals("Sett Antall")){
            MineØkter_SettAntall();
        }
        else if(cmd.equals("Vis Økter")){
            MineØkter_VisØkter();
        }
        else{
            System.out.println("ERROR: Ugyldig kommando");
            MineØkter_Main();
        }
    }
    public void MineØkter_SettAntall() {//Setter antall økter som skal vises
        System.out.println("Velg antall økter til visning");
        String x = inp.nextLine();
        if(x.equals("Tilbake")){
            MineØkter_Main();
        }
        int amount = Integer.parseInt(x);
        DBC.setAmount(amount);
        MineØkter_Main();
    }
    public void MineØkter_VisØkter() {//Viser et gitt antall økter med dato og navn
        ArrayList<TreningsØkt> Økter = new ArrayList<>();
        DBC.addØkterToList(Økter);
        for(TreningsØkt elem : Økter){
            System.out.println("---------------------------------------------------------------------");
            System.out.println(elem.dateString);
            System.out.println(elem.nameString);
        }
        System.out.println("");
        System.out.println("Jeg vil: (Vis Detaljer/Tilbake)");
        String cmd = inp.nextLine();
        if(cmd.equals("Vis Detaljer")){
            MineØkter_Detaljer(Økter);
        }
        else if(cmd.equals("Tilbake")){
            MineØkter_Main();
        }
    }
    public void MineØkter_Detaljer(ArrayList<TreningsØkt> Økter) {//Gir detaljer om økt spesifisert med navn
        System.out.println("Navn på økt: | ('Tilbake' for å gå tilbake)");
        String cmd = inp.nextLine();
        if(cmd.equals("Tilbake")){
            MineØkter_Main();
        }
        for(TreningsØkt elem : Økter){
            if(cmd.equals(elem.nameString)){
                System.out.println(elem.dateString);
                System.out.println(elem.nameString);
                System.out.println(elem.øvelserString);
                System.out.println(elem.resultatString);
                System.out.println("");
            }
        }
        MineØkter_Detaljer(Økter);
    }
//--------------------Ny Økt--------------------
    public void NyØkt_Main() {//Hovedmeny for denne taben. brukes til å navigere
        System.out.println("Jeg vil: (Meny/Registrer Økt)");
        String cmd = inp.nextLine();
        if(cmd.equals("Meny")){
            menyDirect();
        }
        else if(cmd.equals("Registrer Økt")){
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
        System.out.println("Dato: (dd.mm.åå) | ('Tilbake' for å gå tilbake)");
        String cmd = inp.nextLine();
        if(cmd.equals("Tilbake")){
            NyØkt_Main();
        }
        økt += cmd;
        økt += ",";
        System.out.println("Starttid: (hh.mm)");
        økt += inp.nextLine();
        økt += ",";
        System.out.println("Sluttid: (hh.mm)");
        økt += inp.nextLine();
        økt += ",";
        System.out.println("Dagsform: (1-10)");
        økt += inp.nextLine();
        økt += ",";
        System.out.println("Prestasjon: (1-10)");
        økt += inp.nextLine();
        økt += ",";
        System.out.println("Resultat: ");
        økt += inp.nextLine();
        System.out.println("Legg til øvelse: (Navn på øvelse, 'Ferdig' avslutter valget)");
        cmd = inp.nextLine();
        while(!cmd.equals("Ferdig")){
            for(Øvelse ø : DBC.registrerteØvelser){
                if(cmd.equals(ø.name)){
                    ValgteØvelser.add(ø);
                }
                else{
                    cnt += 1;
                }
            }
            if(cnt==DBC.registrerteØvelser.length()){
                System.out.println("Øvelse ikke funnet, ble ikke lagt til");
            }
            System.out.println("Legg til øvelse: (Navn på øvelse, 'Ferdig' avslutter valget)");
            cmd = inp.nextLine();
        }
        DBC.registrerØkt(økt, ValgteØvelser);
        NyØkt_Main();
    }

// --------------------Øvelser--------------------
    public void Øvelser_Main(){//Hovedmeny for denne taben. brukes til å navigere
        System.out.println("Jeg vil: (Meny/Registrer Øvelse)");
        String cmd = inp.nextLine();
        if(cmd.equals("Meny")){
            menyDirect();
        }
        else if(cmd.equals("Registrer Øvelse")){
            Øvelser_RegistrerØvelse();
        }
        else{
            System.out.println("ERROR: Ugyldig kommando");
            Øvelser_Main();
        }
    }

    private void Øvelser_RegistrerØvelse() {//Registrerer en ny øvelse
        String øvelse = "";
        Boolean apparatøvelse = null;
        Boolean idiot = true;
        System.out.println("Navn: | ('Tilbake' for å gå tilbake)");
        String cmd = inp.nextLine();
        if(cmd.equals("Tilbake")){
            Øvelser_Main();
        }
        øvelse += cmd;
        øvelse += ",";
        while(idiot == true){
            System.out.println("Apparatøvelse? (Ja/Nei)");
            String x = inp.nextLine();
            if(x.equals("Ja")){
                apparatøvelse = true;
                idiot = false;
            }
            else if(x.equals("Nei")){
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
                System.out.println("Apparat: ");
                String x = inp.nextLine();
                for(Apparat a : DBC.registrerteApparater){
                    if(x.equals(a.name)){
                        øvelse += x;
                        idiot = false;
                    }
                    else{
                        cnt += 1;
                    }
                }
                if(cnt == DBC.registrerteApparater.length()){
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
        System.out.println("Jeg vil: (Meny/Vis Apparater/Registrer Apparat)");
        String cmd = inp.nextLine();
        if(cmd.equals("Meny")){
            menyDirect();
        }
        else if(cmd.equals("Vis Apparater")){
            Apparater_VisApparater();
        }
        else if(cmd.equals("Registrer Apparat")){
            Apparater_RegistrerApparat();
        }
        else{
            System.out.println("ERROR: Ugyldig kommando");
            Apparater_Main();
        }
    }

    private void Apparater_VisApparater() {//Viser alle apparater med navn
        ArrayList<Apparat> Apparater = new ArrayList<>();
        DBC.addApparaterToList(Apparater);
        for(Apparat elem : Apparater){
            System.out.println("---------------------------------------------------------------------");
            System.out.println(elem.nameString);
        }
        System.out.println("");
        System.out.println("Jeg vil: (Vis Detaljer/Tilbake)");
        String cmd = inp.nextLine();
        if(cmd.equals("Vis Detaljer")){
            Apparater_Detaljer(Apparater);
        }
        else if(cmd.equals("Tilbake")){
            Apparater_Main();
        }
    }
    public void Apparater_Detaljer(ArrayList<Apparat> Apparater) {//Gir detaljer om apparat spesifisert med navn
        System.out.println("Navn på apparat: | ('Tilbake' for å gå tilbake)");
        String cmd = inp.nextLine();
        if(cmd.equals("Tilbake")){
            Apparater_Main();
        }
        for(Apparat elem : Apparater){
            if(cmd.equals(elem.nameString)){
                System.out.println(elem.nameString);
                System.out.println(elem.functionString);
                System.out.println("");
            }
        }
        Apparater_Detaljer(Apparater);
    }

    private void Apparater_RegistrerApparat() {//Registrerer et nytt apparat
        String apparat = "";
        System.out.println("Navn: | ('Tilbake' for å gå tilbake)");
        String cmd = inp.nextLine();
        if(cmd.equals("Tilbake")){
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
        System.out.println("Jeg vil: (Meny/Vis Øvelsesgrupper/Lag Ny Gruppe)");
        String cmd = inp.nextLine();
        if(cmd.equals("Meny")){
            menyDirect();
        }
        else if(cmd.equals("Vis Øvelsesgrupper")){
            Øvelsesgrupper_VisGrupper();
        }
        else if(cmd.equals("Lag Ny Gruppe")){
            Øvelsesgrupper_RegistrerGruppe();
        }
        else{
            System.out.println("ERROR: Ugyldig kommando");
            Øvelsesgrupper_Main();
        }
    }

    public void Øvelsesgrupper_VisGrupper(){//Viser alle grupper med navn
        ArrayList<ØvelsesGruppe> grupper = new ArrayList<>();
        DBC.addGrupperToList(grupper);
        for(ØvelsesGruppe elem : grupper){
            System.out.println("---------------------------------------------------------------------");
            System.out.println(elem.nameString);
        }
        System.out.println("");
        System.out.println("Jeg vil: (Vis Detaljer/Tilbake)");
        String cmd = inp.nextLine();
        if(cmd.equals("Vis Detaljer")){
            Øvelsesgrupper_Detaljer(grupper);
        }
        else if(cmd.equals("Tilbake")){
            Øvelsesgrupper_Main();
        }
    }

    public void Øvelsesgrupper_Detaljer(ArrayList<ØvelsesGruppe> grupper) {//Gir detaljer om gruppe spesifisert med navn
        System.out.println("Navn på gruppe: | ('Tilbake' for å gå tilbake)");
        String cmd = inp.nextLine();
        if(cmd.equals("Tilbake")){
            Øvelsesgrupper_Main();
        }
        for(ØvelsesGruppe elem : grupper){
            if(cmd.equals(elem.nameString)){
                System.out.println(elem.nameString);
                System.out.println(elem.øvelserString);
                System.out.println("");
            }
        }
        Øvelsesgrupper_Detaljer(grupper);
    }

    public void Øvelsesgrupper_RegistrerGruppe(){//Registrerer en ny gruppe
        String gruppe = "";
        System.out.println("Navn: | ('Tilbake' for å gå tilbake)");
        String cmd = inp.nextLine();
        if(cmd.equals("Tilbake")){
            Øvelsesgrupper_Main();
        }
        gruppe += cmd;
        DBC.registrerGruppe(gruppe); 
        Øvelsesgrupper_Main();
    }

    public void Øvelsesgrupper_LeggTilØvelse(){//Legger til øvelse til en gitt øvelsesgruppe
        ØvelsesGruppe activeGroup;
        int cnt = 0;
        System.out.println("Velg en øvelsesgruppe: (Navn på øvelsesgruppe) | ('Tilbake' for å gå tilbake)");
        String cmd = inp.nextLine();
        if(cmd.equals("Tilbake")){
            Øvelsesgrupper_Main();
        }
        for(ØvelsesGruppe øg : DBC.registrerteØvelsesgrupper){
            if(cmd.equals(øg.name)){
                activeGroup = øg;
            }
            else{
                cnt += 1;
            }
        }
        if(cnt == DBC.registrerteØvelsesgrupper.length()){
            System.out.println("ERROR: Øvelsesgruppe finnes ikke");
            Øvelsesgrupper_LeggTilØvelse();
        }
        System.out.println("Legg til øvelse: (Navn på øvelse, 'Ferdig' avslutter valget)");
        String cmd = inp.nextLine();
        while(!cmd.equals("Ferdig")){
            for(Øvelse ø : DBC.registrerteØvelser){
                if(cmd.equals(ø.name)){
                    activeGroup.add(ø);
                }
                else{
                    cnt += 1;
                }
            }
            if(cnt==DBC.registrerteØvelser.length()){
                System.out.println("ERROR: Øvelse ikke funnet, ble ikke lagt til");
            }
            System.out.println("Legg til øvelse: (Navn på øvelse, 'Ferdig' avslutter valget)");
            cmd = inp.nextLine();
        }
    }
    public static void main(String[] args) {//main metode
        TextUI txt = new TextUI();
        txt.initUI();
    }
}