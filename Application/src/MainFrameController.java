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

    private DBController;

    public MainFrameController() {
        this.DBController = new DatabaseController();
    }

//--------------------Mine Økter--------------------

    public ArrayList<TreningsØkt> økter = new ArrayList<TreningsØkt>();
    @FXML private TableView _Økter;
    @FXML private Slider _ØktSlider;
    @FXML private Label Antalløkter;
    @FXML private TableView _Øktoversikt;
    @FXML private Button Slett;

    @FXML
    public void settAntall(){
        int i = _ØktSlider.getValue();
        //Bruk i til noe
    }

    @FXML
    public void updateAntallLabel(){
        int i = _ØktSlider.getValue();
        Antalløkter.setValue(i);
    }

    @FXML
    public void updateØkter(){
        //Dew It!
    }

    @FXML
    public void updateØktoversikt(){
        //Dew It!
    }

    @FXML
    public void delete(){
        //Dew It!
    }

//--------------------Ny Økt--------------------

    public String _økt = "";
    @FXML private ComboBox VelgØvelsesgruppe;
    @FXML private TableView _ØvelseTabell;
    @FXML private Button VelgØvelse;
    @FXML private TextField StartTid;
    @FXML private TextField SluttTid;
    @FXML private DatePicker Dato;
    @FXML private Button RegistrerØkt;
    @FXML private TableView _ØktOversikt;
    @FXML private Slider Dagsform;
    @FXML private Slider Prestasjon;
    @FXML private TextArea Notat;
    @FXML private Button SlettØvelse;

    @FXML
    public void updateØktOversikt();
        //Dew It!

    @FXML
    public void settDagsform(){
        int i = Dagsform.getValue();
        _økt += Integer.toString(i);
        _økt +=",";
    }

    @FXML
    public void settPrestasjon(){
        int i = Prestasjon.getValue();
        _økt += Integer.toString(i);
        _økt +=",";
    }

    @FXML
    public void registrer_økt(_økt){
        //Datetime objekt???
        _økt +=",";
        _økt += StartTid.getValue();
        _økt +=",";
        _økt += SluttTid.getValue();
        _økt +=",";
        _økt += Notat.getValue();
        _økt +=",";
        //Send _økt til parsing i databasekontroller?
        _økt = "";
        Dagsform.setValue(0);
        Prestasjon.setValue(0);
        Notat.clear();
        Dato.clear();
        StartTid.clear();
        SluttTid.clear();
        //combobox reset
        this.updateØkter();
        this.updateØktoversikt();
        this.updateØktOversikt();
    }

//--------------------Mine Øvelser--------------------

    public ArrayList<Øvelse> øvelser = new ArrayList<Øvelse>();
    @FXML private TableView MineØvelser;
    @FXML private TableView _ØvelseResultat;
    @FXML private TextField _ØvelseNavn;
    @FXML private ComboBox VelgApparat;
    @FXML private Button RegistrerØvelse;
    @FXML private TextArea Beskrivelse;
    @FXML private TextField Kilo;
    @FXML private TextField Sett;
    @FXML private CheckBox ApparatAVPÅ;

//--------------------Mine Apparater--------------------

    public ArrayList<Apparat> apparater = new ArrayList<Apparat>();
    @FXML private TextField ApparatNavn;
    @FXML private TextArea Funksjon;
    @FXML private Button RegistrerApparat;
    @FXML private TableView MineApparater;
    @FXML private Label ApparatBeskrivelse;

//--------------------Mine Øvelsegrupper--------------------

    public ArrayList<ØvelsesGruppe> øvelsesgrupper = new ArrayList<ØvelsesGruppe>();
    @FXML private TableView _ØvelsesGrupper;
    @FXML private TableView Oversikt;
    @FXML private TableView _Øvelser;
    @FXML private TextField GruppeNavn;
    @FXML private Button LagGruppe;
    @FXML private Button LeggTilIGruppe;
    @FXML private Button FjernØvelse;
}