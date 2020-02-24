package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonRegister {

    private static ObservableList<Person> list = FXCollections.observableArrayList();

    public void attachTableView(TableView tv) {
        tv.setItems(list);
    }

    //sjekker om et objekt allerede finnes i list, legger kun til nye objekter
    public void addElement(Person obj) {
        if(!erBrukt(obj)) {
            list.add(obj);
        }
    }

    public ObservableList<Person> getList(){
        return list;
    }

    /*
    sjekker om et objekt finnes med samme telefonnummer, for å unngå at
    personer lagres flere ganger (ser på telefonnummer som primærnøkkel)
     */
    public boolean erBrukt(Person obj){
        for(Person d : list){
            if(d.getTlf().equals(obj.getTlf())){
                return true;
            }
        }
        return false;
    }
    // Filtermetode, returnerer en filtrert liste
    public static ObservableList<Person> filter(String choiceBoxValue, String filterInput){

        List<Person> filteredList;
        switch (choiceBoxValue) {
            case "Navn": {
                 filteredList =
                        list.parallelStream()
                                .filter(p -> (p.getNavn().startsWith(filterInput)))
                                .collect(Collectors.toCollection(FXCollections::observableArrayList));
                return (ObservableList<Person>) filteredList;
            }
            case "Alder": {
                    filteredList =
                            list.parallelStream()
                                    .filter(p -> ((p.getAlder()) == (Integer.parseInt(filterInput))))
                                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
                    return (ObservableList<Person>) filteredList;
            }
            case "Telefonnr": {
                filteredList =
                        list.parallelStream()
                                .filter(p -> (p.getTlf().startsWith(filterInput)))
                                .collect(Collectors.toCollection(FXCollections::observableArrayList));
                return (ObservableList<Person>) filteredList;
            }
            case "Epost": {
                 filteredList =
                        list.parallelStream()
                                .filter(p -> (p.getEPost().startsWith(filterInput)))
                                .collect(Collectors.toCollection(FXCollections::observableArrayList));
                return (ObservableList<Person>) filteredList;
            }
            default:
                return list;
        }
    }
}