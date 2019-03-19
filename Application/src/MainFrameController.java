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
    public void settAntall(){
        int i = (int) _ØktSlider.getValue();
        //Bruk i til noe
    }

    @FXML
    public void updateAntallLabel(){
        int i = (int) _ØktSlider.getValue();
        Antalløkter.setText(Integer.toString(i));
    }

    @FXML
    public void updateØkter(TreningsØkt t){
        _Økter.getItems().add(t);
    }

    @FXML
    public void displayActiveØkt(){
        TreningsØkt t = _Økter.getSelectionModel().getSelectedItem();
        _ØktBeskrivelse.setText(t.toString());
    }
    


//--------------------Ny Økt--------------------

    @FXML
    public void updateØktOversikt(TreningsØkt t){
        _ØktOversikt.getItems().add(t);
    }

    @FXML
    public void _øvelseValgt(){
    ValgtØvelse = //Øvelse med øvelsesnavn: VelgØvelse.getValue();
    VisValgtØvelse.setText(x.øvelsesnavn);
    }

    @FXML
    public void velgØvelse(){
        this.updateØktOversikt(ValgtØvelse);
        VisValgtØvelse.setText(null);
        ValgtØvelse = null;
    }

    @FXML
    public void _addØvelse(Øvelse t){
        listØvelse.add(t);
    }

    @FXML
    public void settDagsform(){
        int i = (int) Dagsform.getValue();
        _dagsform= Integer.toString(i);
    }

    @FXML
    public void settPrestasjon(){
        int i = (int) Prestasjon.getValue();
        _prestasjon= Integer.toString(i);
    }

    @FXML
    public void registrer_økt(){
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
        t_økt = DBController.registrerØkt(_økt, valgteØvelser);
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
    public void addApparat(Apparat a){
        listApparat.add(a);
    }

    @FXML
    public void toggleApparat(){
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
    public void registrerØvelse(){
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
        t_øvelse = DBController.registrerØvelse(_øvelse);
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
    public void updateMineApparater(Apparat a){
        MineApparater.getItems().add(t);
    }

    @FXML
    public void displayActiveApparat(){
        Apparat a = MineApparater.getSelectionModel().getSelectedItem();
        ApparatBeskrivelse.setText(a.toString());
    }

    @FXML
    public void registrerApparat(){
        _apparat += ApparatNavn.getText();
        _apparat += ",";
        _apparat += Funksjon.getText();
        t_apparat = DBController.registrerApparat(_apparat);
        ApparatNavn.clear();
        Funksjon.clear();
        this.updateMineApparater(t_apparat);
        this.addApparat(t_apparat);
    }

//--------------------Øvelsesgrupper--------------------

    @FXML
    public void update_Øvelser(Øvelse t){
        _Øvelser.getItems().add(t);
    }

    @FXML
    public void update_Øvelsesgrupper(ØvelsesGruppe g){
        _Øvelsesgrupper.getItems().add(g);
    }

    @FXML
    public void addØvelse(){
        __ØvelserIGruppe.add(_Øvelser.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void displayActiveGruppe(){
        ØvelsesGruppe g = _Øvelsesgrupper.getSelectionModel().getSelectedItem();
        GruppeBeskrivelse.setText(g.toString());
    }

    @FXML
    public void makeGroup(){
        t_gruppe = DBController.registrerGruppe(GruppeNavn.getText());
        this.update_Øvelsesgrupper(t_gruppe);
    }
}