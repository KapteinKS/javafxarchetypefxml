package org.openjfx;

public class Person {
    private String navn;
    private int alder;
    private Dato fDato;
    private String ePost;
    private String telefon;

    public Person(String navn, int alder, int dag, int måned, int år, String ePost, String telefon) {
        this.navn = navn;
        this.alder = alder;
        this.fDato = new Dato(dag, måned, år);
        this.ePost = ePost;
        this.telefon = telefon;
    }

    public String getNavn(){
        return navn;
    }

    public int getAlder(){
        return alder;
    }

    public String getePost() {
        return ePost;
    }

    public String getTelefon() {
        return telefon;
    }

    public Dato getfDato() {
        return fDato;
    }

    @Override
    public String toString() {
        return "Navn; " + navn + ". Alder; " + alder + ". Fødselsdato; " + fDato +
                ". \n\tE-post; " + ePost + ". Tlf; " + telefon +".";
    }
}