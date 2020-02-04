package org.openjfx;

// #anothershamelessripoff
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class DataCollection {

    private ObservableList<DataModel> list = FXCollections.observableArrayList();

    public void attachTableView(TableView tv) {
        tv.setItems(list);
    }

    public void addElement(DataModel obj) {
        list.add(obj);
    }

}