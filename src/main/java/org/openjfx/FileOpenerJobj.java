package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileOpenerJobj implements FileOpener{
    /*
    leser inn fra .jobj fil, returnerer en ObservableArrayList
     */
    public static ObservableList<Person> lesFil(Path path) {
        try {
            InputStream in = Files.newInputStream(path);
            ObjectInputStream oin = new ObjectInputStream(in);
            List<Person> dmList = (ArrayList<Person>) oin.readObject();
            return FXCollections.observableArrayList(dmList);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
