package org.openjfx;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.MenuItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PrimaryController implements Initializable {
    FileChooser fileChooser = new FileChooser();
    Stage mainStage = new Stage();
    File selectedFile;
    Avvik avvik = new Avvik();
    public IntegerStringConverter intStrConverter = new IntegerStringConverter();
    private PersonRegister collection = new PersonRegister();
    String fileType = "";

    @FXML
    private Button btsVisReg;

    @FXML
    private Button btnRegistrer;

    @FXML
    private TextField lblNavn;

    @FXML
    private TextField lblAlder;

    @FXML
    private TextField lblMM;

    @FXML
    private TextField lblYYYY;

    @FXML
    private TextField lblDD;

    @FXML
    private TextField txtEPost;

    @FXML
    private TextField txtTelefon;

    @FXML
    private Label warninglbl;

    @FXML
    private MenuBar fileMenu;

    @FXML
    private MenuItem openFile;

    @FXML
    private MenuItem lukkFil;

    @FXML
    private MenuItem lagreFilSom;

    @FXML
    private ChoiceBox<?> filterBox;

    @FXML
    private TextField txtFilter;

    @FXML
    private TableView<Person> tableView;

    @FXML
    private TableColumn<Person, String> colNavn;

    @FXML
    private TableColumn<Person, Integer> colAlder;

    @FXML
    private TableColumn<Person, String> colFødselsdag;

    @FXML
    private TableColumn<Person, String> colTlf;

    @FXML
    private TableColumn<Person, String> colMail;

    @Override
    /*
    Fester tableview til Personregisteret, setter lovlige filtyper
    til .txt og .jobj, fester IntegerStringConverter til alder
    */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        collection.attachTableView(tableView);
        colAlder.setCellFactory(TextFieldTableCell.forTableColumn(intStrConverter));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Object Files", "*.jobj"));
    }

    @FXML
    /*
    får tak i verdien i choiceBox(filterBox) og filterstrengen,
    sjekker om et filter er skrevet inn, og filtrerer listen
    etter navn, alder, telefon eller epost. hvis filterfeltet er
    tomt resettes tableview til standard
    */
    void btnFiltrer(ActionEvent event){
        String choiceBoxValue = (String) filterBox.getValue();
        String filterInput = txtFilter.getText();

        if(!filterInput.isEmpty()) {
            ObservableList<Person> newList =
                    PersonRegister.filter(choiceBoxValue, filterInput);

            tableView.setItems(newList);
        } else {
            tableView.setItems(collection.getList());
        }

    }

    @FXML
    /*
    "Lagre Som"-metode, åpner alltid filvindu hvor du kan opprette
    ny fil eller overskrive en annen fil. Skiller mellom å lagre som .txt eller
    .jobj
     */
    void saveRegistryAs(ActionEvent event) throws IOException {
        fileChooser.setTitle("Save to which file?");
        selectedFile = fileChooser.showSaveDialog(mainStage);
        if(selectedFile != null){
            String path = selectedFile.getAbsolutePath();
            Path paths = Paths.get(path);
            String stringPath = paths.toString();
            String [] pathArray = stringPath.split("\\.");
            fileType = pathArray[pathArray.length-1];
            if(fileType.equals("txt")){
                FileSaverTxt.skrivTilFil(collection.getList(), paths);
            } else if(fileType.equals("jobj")){
                FileSaverJobj.srivTilFil(collection.getList(), paths);
            } else {
                org.openjfx.Dialogs.showErrorDialog("Feil filformat valgt!");
            }
        }
    }

    @FXML
    /*
    Mye det samme som forrige metode, men hvis en fil allerede er valgt
    lagres register til den valgte fila
     */
    void saveRegistry(ActionEvent event) throws IOException {
        if(selectedFile == null) {
            fileChooser.setTitle("Save to which file?");
            selectedFile = fileChooser.showSaveDialog(mainStage);
        }
        if (selectedFile != null) {
            String path = selectedFile.getAbsolutePath();
            Path paths = Paths.get(path);
            String stringPath = paths.toString();
            String[] pathArray = stringPath.split("\\.");
            fileType = pathArray[pathArray.length - 1];
            if (fileType.equals("txt")) {
                FileSaverTxt.skrivTilFil(collection.getList(), paths);
            } else if (fileType.equals("jobj")) {
                FileSaverJobj.srivTilFil(collection.getList(), paths);
            } else {
                org.openjfx.Dialogs.showErrorDialog("Feil filformat valgt!");
            }
        }

    }

    @FXML
    /*
    metode for å åpne fil, åpner filvindu for å velge fil
    og laster inn register
     */
    void chooseFile(ActionEvent event) throws IOException, ClassNotFoundException {
        fileChooser.setTitle("Velg en fil som inneholder register");
        selectedFile = fileChooser.showOpenDialog(mainStage);
        if(selectedFile != null) {
            String path = selectedFile.getAbsolutePath();
            Path paths = Paths.get(path);
            String stringPath = paths.toString();
            String [] pathArray = stringPath.split("\\.");
            fileType = pathArray[pathArray.length-1];
            if(fileType.equals("jobj")){
                ObservableList<Person> list = FileOpenerJobj.lesFil(paths);

                for(Person dm : list){
                    collection.addElement(dm);
                }
            } else if(fileType.equals("txt")){
                ArrayList<Person> list = FileOpenerTxt.lesFil(paths);

                for(Person dm : list){
                    collection.addElement(dm);
                }
            } else {
                org.openjfx.Dialogs.showErrorDialog("Feil filformat valgt");
            }

        }
    }

    @FXML
    /*
    Registrerer en Person til tableview og resetter tekstfelter
     */
    void regPers(ActionEvent event) {
        if(!lblNavn.getText().isEmpty()) {
            Person obj = createDataModelObjectFromGUI();
            if (obj != null) {
                resetTxtFields();
                collection.addElement(obj);
            }

        }
    }

    /*
    prøver å lage Person-objekt fra GUI, parser tallverdier og gir
    passende feilmelding dersom noe er galt
     */
    private Person createDataModelObjectFromGUI() {
        try {
            String navn = avvik.sjekkNavn(lblNavn.getText());
            int alder = avvik.alder(Integer.parseInt(lblAlder.getText()));
            int dag = avvik.dag(Integer.parseInt(lblDD.getText()));
            int måned = avvik.måned(Integer.parseInt(lblMM.getText()));
            int år = avvik.år(Integer.parseInt(lblYYYY.getText()));
            String tlf = avvik.sjekkTelefon(txtTelefon.getText());
            String ePost = avvik.sjekkEpost(txtEPost.getText());
            avvik.antallDager(dag, måned, år);
            Dato fDato = new Dato(dag, måned, år);
            return new Person(navn, alder, fDato, tlf, ePost);
        } catch (InvalidNameException e) {
            warninglbl.setText(e.getMessage());
        } catch (NumberFormatException n){
            warninglbl.setText("Kun heltall kan skrives inn i alder og datofelt");
            return null;
        } catch (InvalidAgeException | InvalidDateException | InvalidEmailException | InvalidPhoneException e){
            warninglbl.setText(e.getMessage());
            return null;
        }
        return null;
    }

    //resetter tekstfelter
    private void resetTxtFields() {
        lblAlder.setText("");
        lblNavn.setText("");
        lblDD.setText("");
        lblMM.setText("");
        lblYYYY.setText("");
        txtEPost.setText("");
        txtTelefon.setText("");
        warninglbl.setText("");
    }

    //on-change metoder for å oppdatere Person-objekter fra tabellen
    public void nameDataEdited(TableColumn.CellEditEvent<Person, String> event) {
        event.getRowValue().setNavn(event.getNewValue());
    }

    public void phoneDataEdited(TableColumn.CellEditEvent<Person, String> event) {
        event.getRowValue().setTlf(event.getNewValue());
    }

    public void emailDataEdited(TableColumn.CellEditEvent<Person, String> event) {
        event.getRowValue().setePost(event.getNewValue());
    }

    public void alderDataEdited(TableColumn.CellEditEvent<Person, Integer> event) {
        if(intStrConverter.wasSuccessful()) {
            try {
                event.getRowValue().setAlder(event.getNewValue());
            } catch (IllegalArgumentException e){
                Dialogs.showErrorDialog("Du kan ikke taste inn et negativt tall");
            }
        }

        tableView.refresh();
    }
}