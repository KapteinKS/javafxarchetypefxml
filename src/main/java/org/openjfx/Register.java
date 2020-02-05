package org.openjfx;

import java.util.ArrayList;

public class Register {
    ArrayList<Person> liste = new ArrayList();
    Avvik avvik = new Avvik();
    public String registrerPerson(String innNavn, String innAlder, String dd, String mm,
                                  String yyyy, String innEpost, String innTelefon) {

        if(!erBrukt(innNavn)) {
            try{
                int alder, dag, måned, år;
                alder = avvik.alder(Integer.parseInt(innAlder));
                dag = avvik.dag(Integer.parseInt(dd));
                måned = avvik.måned(Integer.parseInt(mm));
                år = avvik.år(Integer.parseInt(yyyy));
                avvik.antallDager(dag, måned, år);
                String navn = avvik.sjekkNavn(innNavn);
                String ePost = avvik.sjekkEpost(innEpost);
                String telefon = avvik.sjekkTelefon(innTelefon);

                Person p = new Person(navn, alder, dag, måned, år, ePost, telefon);
                liste.add(p);
            }
            catch (InvalidAgeException e){
                return e.getMessage();
            }
            catch (InvalidDateException e){
                return e.getMessage();
            } catch (InvalidNameException e) {
                return e.getMessage();
            } catch (InvalidEmailException e) {
                return e.getMessage();
            } catch (InvalidPhoneException e) {
                return e.getMessage();
            }
            return "";
        } else {
            return "Det finnes allerede en person med dette navnet i registeret";
        }
    }

    public void leggTil(Person p){
        if(!erBrukt(p.getNavn())) {
            liste.add(p);
        }
    }

    public boolean erBrukt(String navn){
        for(Person p : liste){
            if(p.getNavn().equalsIgnoreCase(navn)){
                return true;
            }
        }
        return false;
    }

    public String skrivUtListe(){
        String ut = "";
        for(Person p : liste){
            ut += p + "\n";
        }
        return ut;
    }
}



class Dato{
    private int dag, måned, år;

    public Dato(int dag, int måned, int år) {
        this.dag = dag;
        this.måned = måned;
        this.år = år;
    }

    public int getDag() {
        return dag;
    }

    public int getMåned() {
        return måned;
    }

    public int getÅr() {
        return år;
    }

    public String toString(){
        String ut = dag + "." + måned + "." + år;
        return ut;
    }
}