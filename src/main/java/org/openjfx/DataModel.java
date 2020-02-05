package org.openjfx;

// #shamelessripoff
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class DataModel {

    private SimpleStringProperty navn;
    private SimpleIntegerProperty alder;
    private SimpleObjectProperty fDato;
    private SimpleStringProperty tlf;
    private SimpleStringProperty ePost;

    public DataModel(String navn, int alder, Dato fDato, String tlf, String ePost) {
        if(alder < 0) {
            throw new IllegalArgumentException("intData cannot be negative");
        }

        this.navn = new SimpleStringProperty(navn);
        this.alder = new SimpleIntegerProperty(alder);
        this.fDato = new SimpleObjectProperty(fDato);
        this.tlf = new SimpleStringProperty(tlf);
        this.ePost = new SimpleStringProperty(ePost);
    }

    public String getNavn() {
        return navn.getValue();
    }

    public void setNavn(String navn) {
        this.navn.set(navn);
    }

    public int getAlder() {
        return alder.getValue();
    }

    public void setAlder(int alder) {
        if(alder < 0) {
            throw new IllegalArgumentException("Alder kan ikke være negativ");
        }

        this.alder.set(alder);
    }

    public Dato getFDato(){
        return (Dato) fDato.getValue();
    }

    public void setfDato(Dato fDato) {
        this.fDato.set(fDato);
    }

    public String getTlf(){
        return tlf.getValue();
    }

    public void setTlf(String tlf) {
        this.tlf.set(tlf);
    }

    public String getEPost(){
        return ePost.getValue();
    }

    public void setePost(String ePost) {
        this.ePost.set(ePost);
    }
}