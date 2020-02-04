package org.openjfx;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.MenuItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class PrimaryController {
    Register register = new Register();
    FileChooser fileChooser = new FileChooser();
    Stage mainStage = new Stage();
    File selectedFile;
    DataCollection collection = new DataCollection();
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
    private Label txtRegister;

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
    void chooseFile(ActionEvent event) throws IOException {
        fileChooser.setTitle("Open resource file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        selectedFile = fileChooser.showOpenDialog(mainStage);
        if(selectedFile != null) {
            String path = selectedFile.getAbsolutePath();
            Path paths = Paths.get(path);
            List<Person> personer = FileOpenerTxt.lesFil(paths);
            for (Person p : personer) {
                register.leggTil(p);
            }
            warninglbl.setText("Person(er) lagt inn fra fil");
            txtRegister.setText(register.skrivUtListe());
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
    void regPers(ActionEvent event) {
        if(!lblNavn.getText().isEmpty()) {
            warninglbl.setText(register.registrerPerson(lblNavn.getText(), lblAlder.getText(),
                    lblDD.getText(), lblMM.getText(), lblYYYY.getText(), txtEPost.getText(), txtTelefon.getText()));
            txtRegister.setText(register.skrivUtListe());

        }
    }
}

