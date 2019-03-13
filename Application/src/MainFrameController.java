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

    public ArrayList<TreningsØkt> __økter = new ArrayList<TreningsØkt>();
    @FXML private TableView _Økter;
    @FXML private Slider _ØktSlider;
    @FXML private Label Antalløkter;
    @FXML private TableView _Øktoversikt;
    @FXML private Button Slett;

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
    public void updateØkter(){
        //Dew It!
    }

    @FXML
    public void updateØktoversikt(){
        //Dew It!
    }



//--------------------Ny Økt--------------------

    private ArrayList<Øvelse> valgteØvelser= new ArrayList<Øvelse>();
    private String _økt = "";
    private String _dagsform;
    private String _prestasjon;
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
    public void updateØktOversikt(){
        //Dew It!
    }


    @FXML
    public void update_ØvelseTabell(){
        //Dew It!
    }

    @FXML
    public void velgØvelsesgruppe(){
        //Dew It!
    }


    @FXML
    public void slettØvelse(){
        //Dew It!
    }

    @FXML
    public void velgØvelse(){
        //Dew It!
        this.updateØktOversikt();
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
        //Datetime objekt???
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
        //Send _økt og valgteØvelser til parsing i databasekontroller?
        _økt = "";
        Dagsform.setValue(5);
        Prestasjon.setValue(5);
        Notat.clear();
        Dato.setValue(null);
        StartTid.clear();
        SluttTid.clear();
        //combobox reset
        this.updateØkter();
        this.updateØktoversikt();
    }

//--------------------Mine Øvelser--------------------

    public ArrayList<Øvelse> __øvelser = new ArrayList<Øvelse>();
    private Boolean _apparatType = false;
    private String _øvelse = "";
    @FXML private TableView MineØvelser;
    @FXML private TableView _ØvelseResultat;
    @FXML private TextField _ØvelseNavn;
    @FXML private ComboBox VelgApparat;
    @FXML private Button RegistrerØvelse;
    @FXML private TextArea Beskrivelse;
    @FXML private TextField Kilo;
    @FXML private TextField Sett;
    @FXML private CheckBox ApparatAVPÅ;

    @FXML
    public void updateMineØvelser(){
        //Dew It!
    }

    @FXML
    public void update_ØvelseResultat(){
        //Dew It!
    }

    @FXML
    public void addApparat(){
        //Dew It!
    }

    @FXML
    public void toggleApparat(){
        if(_apparatType==false){
            _apparatType = true;
            VelgApparat.setDisable(false);
        }
        else{
            _apparatType = false;
            VelgApparat.setDisable(true);
        }
    }

    @FXML
    public void registrerØvelse(){
        _øvelse += _ØvelseNavn.getText();
        _øvelse += ",";
        _øvelse += Boolean.toString(_apparatType);
        _øvelse += ",";
        _øvelse += Kilo.getText();
        _øvelse += ",";
        _øvelse += Sett.getText();
        _øvelse += ",";
        _øvelse += Beskrivelse.getText();
        _øvelse += ",";
        //combobox ting
        _øvelse += ",";
        //Send _øvelse til parsing i databasekontroller?
        _øvelse = "";
        _ØvelseNavn.clear();
        //combobox reset
        _apparatType = false;
        ApparatAVPÅ.setSelected(false);
        VelgApparat.setDisable(true);
        Kilo.clear();
        Sett.clear();
        Beskrivelse.clear();
        this.updateMineØvelser();
        this.update_ØvelseTabell();
        this.update_Øvelser();
    }

//--------------------Mine Apparater--------------------

    public ArrayList<Apparat> apparater = new ArrayList<Apparat>();
    private String _apparat = "";
    @FXML private TextField ApparatNavn;
    @FXML private TextArea Funksjon;
    @FXML private Button RegistrerApparat;
    @FXML private TableView MineApparater;
    @FXML private Label ApparatBeskrivelse;

    @FXML
    public void updateMineApparater(){
        //Dew It!
    }

    @FXML
    public void registrerApparat(){
        _apparat += ApparatNavn.getText();
        _apparat += ",";
        _apparat += Funksjon.getText();
        //Send _apparat til parsing i databasekontroller?
        ApparatNavn.clear();
        Funksjon.clear();
        this.updateMineApparater();
        this.addApparat();
    }

//--------------------Mine Øvelsegrupper--------------------

    public ArrayList<ØvelsesGruppe> __øvelsesgrupper = new ArrayList<ØvelsesGruppe>();
    private ArrayList<Øvelse> __ØvelserIGruppe= new ArrayList<Øvelse>();
    @FXML private TableView _ØvelsesGrupper;
    @FXML private TableView Oversikt;
    @FXML private TableView _Øvelser;
    @FXML private TextField GruppeNavn;
    @FXML private Button LagGruppe;
    @FXML private Button LeggTilIGruppe;
    @FXML private Button FjernØvelse;

    @FXML
    public void update_Øvelser(){
        //Dew It!
    }

    @FXML
    public void update_Øvelsesgrupper(){
        //Dew It!
    }

    @FXML
    public void addØvelse(Øvelse ø){
        __ØvelserIGruppe.add(ø);
    }

    @FXML
    public void makeGroup(){
        //Send __ØvelserIGruppe til parsing i databasekontroller?
        __ØvelserIGruppe.clear();
        this.update_Øvelsesgrupper();
    }
}