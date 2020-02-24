package org.openjfx;

import javafx.scene.control.Alert;

public class Dialogs {

    //metoode for feilmeling
    public static void showErrorDialog(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Feil!");
        alert.setHeaderText("Ugyldig data!");
        alert.setContentText(msg);

        alert.showAndWait();
    }

}
