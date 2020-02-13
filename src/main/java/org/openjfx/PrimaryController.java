package org.openjfx;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.Observable;
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
    private DataCollection collection = new DataCollection();
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
    private TableView tableView;

    @FXML
    private TableColumn<DataModel, String> colNavn;

    @FXML
    private TableColumn<DataModel, Integer> colAlder;

    @FXML
    private TableColumn<DataModel, String> colFødselsdag;

    @FXML
    private TableColumn<DataModel, String> colTlf;

    @FXML
    private TableColumn<DataModel, String> colMail;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        collection.attachTableView(tableView);
        colAlder.setCellFactory(TextFieldTableCell.forTableColumn(intStrConverter));
    }

    @FXML
    void btnFilter(ActionEvent event){

    }

    @FXML
    void saveRegistryAs(ActionEvent event) throws IOException {
        fileChooser.setTitle("Save to which file?");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Object Files", "*.jobj"));
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
    void saveRegistry(ActionEvent event) throws IOException {
        if(selectedFile == null) {
            fileChooser.setTitle("Save to which file?");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                    new FileChooser.ExtensionFilter("Object Files", "*.jobj"));
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
    void chooseFile(ActionEvent event) throws IOException, ClassNotFoundException {
        fileChooser.setTitle("Velg en fil som inneholder register");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Object Files", "*.jobj"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        selectedFile = fileChooser.showOpenDialog(mainStage);
        if(selectedFile != null) {
            String path = selectedFile.getAbsolutePath();
            Path paths = Paths.get(path);
            String stringPath = paths.toString();
            String [] pathArray = stringPath.split("\\.");
            fileType = pathArray[pathArray.length-1];

            if(fileType.equals("jobj")){
                ObservableList<DataModel> list = FileOpenerJobj.lesFil(paths);

                for(DataModel dm : list){
                    collection.addElement(dm);
                }
            } else if(fileType.equals("txt")){
                ArrayList<DataModel> list = FileOpenerTxt.lesFil(paths);

                for(DataModel dm : list){
                    collection.addElement(dm);
                }
            } else {
                org.openjfx.Dialogs.showErrorDialog("Feil filformat valgt");
            }

        }
    }

    @FXML
    void regPers(ActionEvent event) {
        if(!lblNavn.getText().isEmpty()) {
            DataModel obj = createDataModelObjectFromGUI();
            if (obj != null) {
                resetTxtFields();
                collection.addElement(obj);
            }
        }
    }

    private DataModel createDataModelObjectFromGUI() {
        try {
            String navn = avvik.sjekkNavn(lblNavn.getText());
            int alder = avvik.alder(Integer.parseInt(lblAlder.getText()));
            int dag = avvik.dag(Integer.parseInt(lblDD.getText()));
            int måned = avvik.måned(Integer.parseInt(lblMM.getText()));
            int år = avvik.år(Integer.parseInt(lblYYYY.getText()));
            String tlf = avvik.sjekkTelefon(txtTelefon.getText());
            String ePost = avvik.sjekkEpost(txtEPost.getText());

            Dato fDato = new Dato(dag, måned, år);
            return new DataModel(navn, alder, fDato, tlf, ePost);
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

    public void nameDataEdited(TableColumn.CellEditEvent<DataModel, String> event) {
        event.getRowValue().setNavn(event.getNewValue());
    }

    public void phoneDataEdited(TableColumn.CellEditEvent<DataModel, String> event) {
        event.getRowValue().setTlf(event.getNewValue());
    }

    public void emailDataEdited(TableColumn.CellEditEvent<DataModel, String> event) {
        event.getRowValue().setePost(event.getNewValue());
    }

    public void alderDataEdited(TableColumn.CellEditEvent<DataModel, Integer> event) {
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