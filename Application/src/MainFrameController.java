import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.effect.*;
import javafx.scene.text.*;
import java.lang.*;
import java.util.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MainFrameController{

    private DatabaseController DBController;

//--------------------Mine Økter--------------------

    @FXML private TableView _Økter;
    @FXML private TableColumn ColDateAndTime;

    public ArrayList<TreningsØkt> __økter = new ArrayList<TreningsØkt>();
    @FXML private Slider _ØktSlider;
    @FXML private Label Antalløkter;
    @FXML private TextArea _ØktBeskrivelse;

//--------------------Ny Økt--------------------

    @FXML private TableView ValgteØvelser;
    @FXML private TableColumn ColValgteØvelser;

    private ArrayList<Øvelse> valgteØvelser= new ArrayList<Øvelse>();
    private String _økt = "";
    private String _dagsform;
    private String _prestasjon;
    private Øvelse ValgtØvelse = null;
    @FXML private ComboBox VelgØvelse;
    public ObservableList<Øvelse> listØvelse = FXCollections.observableArrayList();
    @FXML private Button VelgAktivØvelse;
    @FXML private TextField StartTid;
    @FXML private TextField SluttTid;
    @FXML private DatePicker Dato;
    @FXML private Button RegistrerØkt;
    @FXML private Slider Dagsform;
    @FXML private Slider Prestasjon;
    @FXML private TextArea Notat;
    @FXML private TextField VisValgtØvelse;

//--------------------Øvelser--------------------

    public ArrayList<Øvelse> __øvelser = new ArrayList<Øvelse>();
    private Boolean _apparatType = false;
    private Boolean _Kilo = false;
    private Boolean _Sett = false;
    private Boolean _Beskrivelse = true;
    private String _øvelse = "";
    @FXML private TextField _ØvelseNavn;
    @FXML private ComboBox VelgApparat;
    public ObservableList<Apparat> listApparat = FXCollections.observableArrayList();
    @FXML private Button RegistrerØvelse;
    @FXML private TextArea Beskrivelse;
    @FXML private TextField Kilo;
    @FXML private TextField Sett;
    @FXML private CheckBox ApparatAVPÅ;

//--------------------Apparater--------------------

    @FXML private TableView MineApparater;
    @FXML private TableColumn ColMineApparater;

    public ArrayList<Apparat> apparater = new ArrayList<Apparat>();
    private String _apparat = "";
    @FXML private TextField ApparatNavn;
    @FXML private TextArea Funksjon;
    @FXML private Button RegistrerApparat;
    @FXML private Label ApparatBeskrivelse;

//--------------------Øvelsesgrupper--------------------

    @FXML private TableView _Øvelsesgrupper;
    @FXML private TableColumn ColMineØvelsesgrupper;
    @FXML private TableView _Øvelser;
    @FXML private TableColumn Col_Øvelser;

    public ArrayList<ØvelsesGruppe> __øvelsesgrupper = new ArrayList<ØvelsesGruppe>();
    @FXML private TextField GruppeNavn;
    @FXML private Button LagGruppe;
    @FXML private Button LeggTilIGruppe;
    @FXML private TextArea GruppeBeskrivelse;

//--------------------Konstruktør--------------------

//Konstruktøren oppretter tabellene og instanserer/kobler til databasekontrolleren
//De kommenterte linjene trengs kanskje
    public MainFrameController() {
        this.DBController = new DatabaseController();
        ColDateAndTime.setCellValueFactory(new PropertyValueFactory<>("dato"));
        //_Økter.getColumns().addAll(ColDateAndTime);
        ColValgteØvelser.setCellValueFactory(new PropertyValueFactory<>("øvelsesnavn"));
        //ValgteØvelser.getColumns().addAll(ColValgteØvelser);
        ColMineApparater.setCellValueFactory(new PropertyValueFactory<>("apparatnavn"));
        //MineApparater.getColumns().addAll(ColMineApparater);
        ColMineØvelsesgrupper.setCellValueFactory(new PropertyValueFactory<>("øvelsesgruppenavn"));
        //_Øvelsesgrupper.getColumns().addAll(ColMineØvelsesgrupper);
        Col_Øvelser.setCellValueFactory(new PropertyValueFactory<>("øvelsesnavn"));
        //_Øvelser.getColumns().addAll(Col_Øvelser);
    }

//--------------------Mine Økter--------------------

    @FXML
    public void settAntall(){//metode i databsekontroller for å sette antall økter som vises
        int i = (int) _ØktSlider.getValue();
        DBController.setAmount(i);  //denne metoden i DBkontroller må legges til!
    }

    @FXML
    public void updateAntallLabel(){//oppdaterer label i FXML
        int i = (int) _ØktSlider.getValue();
        Antalløkter.setText(Integer.toString(i)); 
    }

    @FXML
    public void updateØkter(TreningsØkt t){//Legger til lagd økt i tabell
        _Økter.getItems().add(t); 
    }

    @FXML
    public void displayActiveØkt(){//Viser beskrivelse av økt i form av øktens tostring metode
        TreningsØkt t = _Økter.getSelectionModel().getSelectedItem();
        _ØktBeskrivelse.setText(t.toString()); 
    }
    


//--------------------Ny Økt--------------------

    @FXML
    public void updateØktOversikt(TreningsØkt t){//Legger til lagd økt i tabell
        _ØktOversikt.getItems().add(t); 
    }

    @FXML
    public void _øvelseValgt(){//Setter label til å vise valgt øvelse
    ValgtØvelse = //Øvelse med øvelsesnavn: VelgØvelse.getValue();
    VisValgtØvelse.setText(x.øvelsesnavn); 
    }

    @FXML
    public void velgØvelse(){//knapp som legger til øvelse i liste tilhørende økt som skal registreres
        this.updateØktOversikt(ValgtØvelse);
        VisValgtØvelse.setText(null);
        ValgtØvelse = null;
    }

    @FXML
    public void _addØvelse(Øvelse t){//Tror ikke denne brukes lengre...
        listØvelse.add(t);
    }

    @FXML
    public void settDagsform(){//setter variabel til sliders verdi
        int i = (int) Dagsform.getValue();
        _dagsform= Integer.toString(i);
    }

    @FXML
    public void settPrestasjon(){//setter variabel til sliders verdi
        int i = (int) Prestasjon.getValue();
        _prestasjon= Integer.toString(i);
    }

    @FXML
    public void registrer_økt(){//registrerer økten og legger den til i tabell(er)
        _økt += Dato.toString();
        _økt +=",";
        _økt += StartTid.getText();
        _økt +=",";
        _økt += SluttTid.getText();
        _økt +=",";
        _økt += _dagsform;
        _økt +=",";
        _økt += _prestasjon;
        _økt +=",";
        _økt += Notat.getText();
        _økt +=",";
        t_økt = DBController.registrerØkt(_økt, valgteØvelser); //her kalles registreringsmetode som returnerer øktobjekt
        _økt = "";
        Dagsform.setValue(5);
        Prestasjon.setValue(5);
        Notat.clear();
        Dato.setValue(null);
        StartTid.clear();
        SluttTid.clear();
        VelgØvelse.setValue(null);
        this.updateØkter(t_økt);
    }

//--------------------Øvelser--------------------

    @FXML
    public void addApparat(Apparat a){//ubrukt metode kanskje
        listApparat.add(a);
    }

    @FXML
    public void toggleApparat(){//enabler/disabler felt basert på checkbox
        if(_apparatType==false){
            _apparatType = true;
            _Kilo = true;
            _Sett = true;
            _Beskrivelse = false;
            VelgApparat.setDisable(false);
            Kilo.setDisable(false);
            Sett.setDisable(false);
            Beskrivelse.setDisable(true);
        }
        else{
            _apparatType = false;
            _Kilo = false;
            _Sett = false;
            _Beskrivelse = true;
            VelgApparat.setDisable(true);
            Kilo.setDisable(true);
            Sett.setDisable(true);
            Beskrivelse.setDisable(false);
        }
    }

    @FXML
    public void registrerØvelse(){//registrerer øvelse og legger til i tabell(er)
        _øvelse += _ØvelseNavn.getText();
        _øvelse += ",";
        if(_apparatType){
            _øvelse += Kilo.getText();
            _øvelse += ",";
            _øvelse += Sett.getText();
            _øvelse += ",";
            _øvelse += VelgApparat.getValue();
        }
        else{
            _øvelse += Beskrivelse.getText();
        }
        t_øvelse = DBController.registrerØvelse(_øvelse); //her kalles registreringsmetode som returnerer øvelseobjekt
        _øvelse = "";
        _ØvelseNavn.clear();
        VelgApparat.setValue(null);
        _apparatType = false;
        ApparatAVPÅ.setSelected(false);
        VelgApparat.setDisable(true);
        Kilo.setDisable(true);
        Sett.setDisable(true);
        Beskrivelse.setDisable(false);
        Kilo.clear();
        Sett.clear();
        Beskrivelse.clear();
        this.update_Øvelser(t_øvelse);
        this._addØvelse(t_øvelse);
    }

//--------------------Apparater--------------------

    @FXML
    public void updateMineApparater(Apparat a){//Legger til apparat i tilhørende tabell
        MineApparater.getItems().add(t);
    }

    @FXML
    public void displayActiveApparat(){//Viser beskrivelse av apparat via apparatets tostring metode
        Apparat a = MineApparater.getSelectionModel().getSelectedItem();
        ApparatBeskrivelse.setText(a.toString());
    }

    @FXML
    public void registrerApparat(){//registrerer apparat og legger til i tabell(er)
        _apparat += ApparatNavn.getText();
        _apparat += ",";
        _apparat += Funksjon.getText();
        t_apparat = DBController.registrerApparat(_apparat);//her kalles registreringsmetode som returnerer apparatobjekt
        ApparatNavn.clear();
        Funksjon.clear();
        this.updateMineApparater(t_apparat);
        this.addApparat(t_apparat);
    }

//--------------------Øvelsesgrupper--------------------

    @FXML
    public void update_Øvelser(Øvelse t){//Legger til øvelse i tilhørende tabell
        _Øvelser.getItems().add(t);
    }

    @FXML
    public void update_Øvelsesgrupper(ØvelsesGruppe g){//Legger til øvelsesgruppe i tilhørende tabell
        _Øvelsesgrupper.getItems().add(g);
    }

    @FXML
    public void addØvelse(){//Legger til øvelse valgt i tabell til øvelsesgruppe
        __ØvelserIGruppe.add(_Øvelser.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void displayActiveGruppe(){//Viser beskrivelse av gruppe via gruppens tostring metode
        ØvelsesGruppe g = _Øvelsesgrupper.getSelectionModel().getSelectedItem();
        GruppeBeskrivelse.setText(g.toString());
    }

    @FXML
    public void makeGroup(){//oppretter gruppe med navn og oppdaterer tabell
        t_gruppe = DBController.registrerGruppe(GruppeNavn.getText());//her kalles registreringsmetode som returnerer gruppeobjekt
        this.update_Øvelsesgrupper(t_gruppe);
    }
}