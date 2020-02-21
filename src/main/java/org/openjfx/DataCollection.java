package org.openjfx;

// #anothershamelessripoff
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.List;
import java.util.stream.Collectors;

public class DataCollection {

    private static ObservableList<DataModel> list = FXCollections.observableArrayList();

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
    //Her har du metoden Henrik :)
//  public ObservableList filter (String choiceBoxValue, String filterInput){ ??????
    public static List filter (String choiceBoxValue, String filterInput){
        //List<DataModel> listOut = list;

        if(choiceBoxValue == "Navn"){
            List<DataModel> filteredList = list.stream()
                    .filter(p -> p.getNavn().equals(filterInput))
                    .collect(Collectors.toList());
            return filteredList;

        }else if(choiceBoxValue == "Alder"){
            List<DataModel> filteredList = list.stream()
                    .filter(p -> p.getAlder() == (Integer.parseInt(filterInput)))
                    .collect(Collectors.toList());
            return filteredList;
        }else if(choiceBoxValue == "Telefonnr"){
            List<DataModel> filteredList = list.stream()
                    .filter(p -> p.getTlf().equals(filterInput))
                    .collect(Collectors.toList());
            return filteredList;
        }else if(choiceBoxValue == "Epost"){
            List<DataModel> filteredList = list.stream()
                    .filter(p -> p.getEPost().equals(filterInput))
                    .collect(Collectors.toList());
            return filteredList;
        }else{
            return list;
        }
    }
}