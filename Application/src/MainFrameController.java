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

    //Mine Økter
    public ArrayList<TreningsØkt> økter = new ArrayList<TreningsØkt>();
    @FXML private TableView _Økter;
    @FXML private Slider _ØktSlider;
    @FXML private Label Antallokter;
    @FXML private TableView _Øktoversikt;
    @FXML private Button Slett;

    //Ny Økt
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

    //Mine Øvelser
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

    //Mine Apparater
    public ArrayList<Apparat> apparater = new ArrayList<Apparat>();
    @FXML private TextField ApparatNavn;
    @FXML private TextArea Funksjon;
    @FXML private Button RegistrerApparat;
    @FXML private TableView MineApparater;
    @FXML private Label ApparatBeskrivelse;

    //Mine Øvelsegrupper
    public ArrayList<ØvelsesGruppe> øvelsesgrupper = new ArrayList<ØvelsesGruppe>();
    @FXML private TableView _ØvelsesGrupper;
    @FXML private TableView Oversikt;
    @FXML private TableView _Øvelser;
    @FXML private TextField GruppeNavn;
    @FXML private Button LagGruppe;
    @FXML private Button LeggTilIGruppe;
    @FXML private Button FjernØvelse;
}