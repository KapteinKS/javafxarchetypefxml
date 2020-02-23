package org.openjfx;

// #anothershamelessripoff
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.List;
import java.util.stream.Collectors;

public class PersonRegister {

    private static ObservableList<Person> list = FXCollections.observableArrayList();

    public void attachTableView(TableView tv) {
        tv.setItems(list);
    }

    public void addElement(Person obj) {
        if(!erBrukt(obj)) {
            list.add(obj);
        }
    }

    public void clearRegister(){
        list.removeAll();
    }

    public ObservableList<Person> getList(){
        return list;
    }

    public boolean erBrukt(Person obj){
        for(Person d : list){
            if(d.getTlf().equals(obj.getTlf())){
                return true;
            }
        }
        return false;
    }
    // Filter method
    public static ObservableList<Person> filter(String choiceBoxValue, String filterInput){
        //  Here the new, filtered lists are returned
        switch (choiceBoxValue) {
            case "Navn": {
                List<Person> filteredList =
                        list.parallelStream()
                                .filter(p -> (p.getNavn().startsWith(filterInput)))
                                .collect(Collectors.toCollection(FXCollections::observableArrayList));
                list = FXCollections.observableArrayList(filteredList);
                return list;
            }
            case "Alder": {
                List<Person> filteredList =
                        list.parallelStream()
                                .filter(p -> ((p.getAlder()) == (Integer.parseInt(filterInput))))
                                .collect(Collectors.toCollection(FXCollections::observableArrayList));
                list = FXCollections.observableArrayList(filteredList);
                return list;

            }
            case "Telefonnr": {
                List<Person> filteredList =
                        list.parallelStream()
                                .filter(p -> (p.getTlf().startsWith(filterInput)))
                                .collect(Collectors.toCollection(FXCollections::observableArrayList));
                list = FXCollections.observableArrayList(filteredList);
                return list;
            }
            case "Epost": {
                List<Person> filteredList =
                        list.parallelStream()
                                .filter(p -> (p.getEPost().startsWith(filterInput)))
                                .collect(Collectors.toCollection(FXCollections::observableArrayList));
                list = FXCollections.observableArrayList(filteredList);
                return list;
            }
            default:
                return list;
        }
    }
}