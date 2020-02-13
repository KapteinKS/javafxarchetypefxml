package org.openjfx;

// #anothershamelessripoff
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class DataCollection {
    // List of Persons
    private ObservableList<DataModel> list = FXCollections.observableArrayList();

    public void attachTableView(TableView tv) {
        tv.setItems(list);
    }

    public void addElement(DataModel obj) {
        if(!erBrukt(obj)) {
            list.add(obj);
        }
    }

    public ObservableList<DataModel> getList(){
        return list;
    }

    public boolean erBrukt(DataModel obj){
        for(DataModel d : list){
            if(d.getTlf().equals(obj.getTlf())){
                return true;
            }
        }
        return false;
    }

    /* metode som konverterer 'list' stream, og filtrerer streamen basert på choicebox */
    /* Som så kalles i primarykontroller på knappen (*1) */

}