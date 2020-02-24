package org.openjfx;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//Serialiserbar Personklasse
public class Person implements Serializable{

    private transient SimpleStringProperty navn;
    private transient SimpleIntegerProperty alder;
    private transient SimpleObjectProperty fDato;
    private transient SimpleStringProperty tlf;
    private transient SimpleStringProperty ePost;

    //konstruktør
    public Person(String navn, int alder, Dato fDato, String tlf, String ePost) {
        if(alder < 0) {
            throw new IllegalArgumentException("intData cannot be negative");
        }

        this.navn = new SimpleStringProperty(navn);
        this.alder = new SimpleIntegerProperty(alder);
        this.fDato = new SimpleObjectProperty(fDato);
        this.tlf = new SimpleStringProperty(tlf);
        this.ePost = new SimpleStringProperty(ePost);
    }

    //get/set metoder
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

    @Override
    public String toString() {
        return String.format("%s er %s år, født %s, epost: %s, Tlf: %s", navn.getValue(), alder.getValue(), fDato.getValue(),
                ePost.getValue(), tlf.getValue());
    }

    /*
    metode for å skrive til fil
     */
    private void writeObject(ObjectOutputStream s) throws IOException {
        s.writeUTF(navn.getValue());
        s.writeInt(alder.getValue());
        s.writeObject(fDato.getValue());
        s.writeUTF(tlf.getValue());
        s.writeUTF(ePost.getValue());
    }

    /*
    metode for å lese inn fra fil
     */
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException{
        String name = s.readUTF();
        int age = s.readInt();
        Dato date = (Dato) s.readObject();
        String phone = s.readUTF();
        String email = s.readUTF();

        this.navn = new SimpleStringProperty(name);
        this.alder = new SimpleIntegerProperty(age);
        this.fDato = new SimpleObjectProperty(date);
        this.tlf = new SimpleStringProperty(phone);
        this.ePost = new SimpleStringProperty(email);
    }
}