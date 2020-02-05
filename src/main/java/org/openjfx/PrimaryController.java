package org.openjfx;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.MenuItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class PrimaryController implements Initializable {
    Register register = new Register();
    FileChooser fileChooser = new FileChooser();
    Stage mainStage = new Stage();
    File selectedFile;
    private  DataCollection collection = new DataCollection();
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
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> colNavn;

    @FXML
    private TableColumn<?, ?> colAlder;

    @FXML
    private TableColumn<?, ?> colFødselsdag;

    @FXML
    private TableColumn<?, ?> colTlf;

    @FXML
    private TableColumn<?, ?> colMail;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        collection.attachTableView(tableView);
    }

    @FXML
    void saveRegistryAs(ActionEvent event) throws IOException {
        fileChooser.setTitle("Save to which file?");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        selectedFile = fileChooser.showSaveDialog(mainStage);
        if(selectedFile != null) {
            String paths = selectedFile.getAbsolutePath();
            var path = Paths.get(paths);
            FileSaverTxt.skrivTilFil(register.liste, path);
            warninglbl.setText("Register lagret");
        }
    }

    @FXML
    void saveRegistry(ActionEvent event) throws IOException {
        fileChooser.setTitle("Save to which file?");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        if(selectedFile == null) {
            selectedFile = fileChooser.showSaveDialog(mainStage);
        }
        if(selectedFile != null) {
            String paths = selectedFile.getAbsolutePath();
            var path = Paths.get(paths);
            FileSaverTxt.skrivTilFil(register.liste, path);
            warninglbl.setText("Register lagret");
        }

    }

    @FXML
    void chooseFile(ActionEvent event) throws IOException {
        fileChooser.setTitle("Velg en fil som inneholder register");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        selectedFile = fileChooser.showOpenDialog(mainStage);
        if(selectedFile != null) {
            String path = selectedFile.getAbsolutePath();
            Path paths = Paths.get(path);
            List<Person> personer = FileOpenerTxt.lesFil(paths);
            for (Person p : personer) {
                register.leggTil(p);
                DataModel dm = createDMfromPerson(p);
                collection.addElement(dm);
            }
            warninglbl.setText("Person(er) lagt inn fra fil");
        }
    }

    @FXML
    void regPers(ActionEvent event) {
        if(!lblNavn.getText().isEmpty()) {
                warninglbl.setText(register.registrerPerson(lblNavn.getText(), lblAlder.getText(),
                        lblDD.getText(), lblMM.getText(), lblYYYY.getText(), txtEPost.getText(), txtTelefon.getText()));

                DataModel obj = createDataModelObjectFromGUI();

                if(warninglbl.getText().equals("")){
                    if (obj != null) {
                        resetTxtFields();
                        collection.addElement(obj);
                    }
                }
        }
    }

    private DataModel createDMfromPerson(Person p){
        String navn = p.getNavn();
        int alder = p.getAlder();
        Dato fDato = p.getfDato();
        String tlf = p.getTelefon();
        String epost = p.getePost();
        return new DataModel(navn, alder, fDato, tlf, epost);
    }

    private DataModel createDataModelObjectFromGUI() {
        String navn = lblNavn.getText();
        String ePost = txtEPost.getText();
        String tlf = txtTelefon.getText();

        try {
            int alder = Integer.parseInt(lblAlder.getText());
            int dag = Integer.parseInt(lblDD.getText());
            int måned = Integer.parseInt(lblMM.getText());
            int år = Integer.parseInt(lblYYYY.getText());
            Dato fDato = new Dato(dag, måned, år);
            return new DataModel(navn, alder, fDato, tlf, ePost);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private void resetTxtFields() {
        lblAlder.setText("");
        lblNavn.setText("");
        lblDD.setText("");
        lblMM.setText("");
        lblYYYY.setText("");
        txtEPost.setText("");
        txtTelefon.setText("");
    }
}