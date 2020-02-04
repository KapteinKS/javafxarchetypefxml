package org.openjfx;

// #shamelessripoff
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DataModel {

    private SimpleStringProperty navn;
    private SimpleIntegerProperty alder;
    private SimpleIntegerProperty dag;
    private SimpleIntegerProperty måned;
    private SimpleIntegerProperty år;
    private SimpleStringProperty tlf;
    private SimpleStringProperty ePost;

    public DataModel(String navn, int alder, int dag, int måned, int år, String tlf, String ePost) {
        if(alder < 0) {
            throw new IllegalArgumentException("intData cannot be negative");
        }

        this.navn = new SimpleStringProperty(navn);
        this.alder = new SimpleIntegerProperty(alder);
        this.dag = new SimpleIntegerProperty(dag);
        this.måned = new SimpleIntegerProperty(måned);
        this.år = new SimpleIntegerProperty(år);
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

    public int getDag(){
        return dag.getValue();
    }

    public void setDag(int dag) {
        this.dag.set(dag);
    }

    public int getMåned(){
        return måned.getValue();
    }

    public void setMåned(int måned) {
        this.måned.set(måned);
    }

    public int getÅr(){
        return år.getValue();
    }

    public void setÅr(int år) {
        this.år.set(år);
    }

    public String getTLF(){
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