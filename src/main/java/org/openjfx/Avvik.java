package org.openjfx;

//klasse for å håndtere avvik under innlesning fra GUI
public class Avvik {

    //sjekker at alder er lovlig
    public int alder(int innAlder) throws InvalidAgeException {
        if(innAlder < 0 || innAlder > 120){
            throw new InvalidAgeException("Alder må være mellom 0 og 120");
        }
        return innAlder;
    }

    //sjekker at fødselsdag er lovlig 
    public int dag(int innDag) throws InvalidAgeException{
        if(innDag < 1 || innDag > 31){
            throw new InvalidDateException("Fødselsdag må være mellom 1 og 31");
        }
        return innDag;
    }

    //sjekker at fødselsmåned er lovlig
    public int måned(int innMåned) throws InvalidAgeException{
        if(innMåned < 1 || innMåned > 12){
            throw new InvalidDateException("Fødslesmåned må være mellom 1 og 12");
        }
        return innMåned;
    }

    //sjekker at fødselsår er lovlig
    public int år(int innÅr) throws InvalidAgeException{
        if(innÅr < 1900 || innÅr > 2020){
            throw new InvalidDateException("Fødselsår må være mellom 1900 og 2020");
        }
        return innÅr;
    }

    //metode som sjekker at fødselsdag er lovlig i forhold til måned og skuddår
    public void antallDager(int dag, int måned, int år) throws InvalidDateException{
        if((!(år%4 == 0)) && måned == 2 && dag > 28){
            throw new InvalidDateException(år + " er ikke skuddår, 2. måned har kun 28 dager");
        } else if ((måned == 4 || måned == 6 || måned == 9 || måned == 11) && dag == 31){
            throw new InvalidDateException(måned + ". måned har kun 30 dager");
        } else if(måned == 2 && år%4 == 0 && dag > 29){
            throw new InvalidDateException(år + " har kun 29 dager i et skuddår");
        }
    }

    //metode for å sørge for lovlig navn, eller gi passende feilmelding
    public String sjekkNavn(String navn) throws InvalidNameException {
        if(!navn.matches("[a-zæøåA-ZÆØÅ][^#&<>\"~;$^%{}?]{1,40}$")){
            throw new InvalidNameException("Ulovlig karakter i navn");
        } else {
            return navn;
        }
    }

    //metode for å sørge for lovlig epost, eller gi passende feilmelding
    public String sjekkEpost(String epost) throws InvalidEmailException {
        String pat = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        if(!epost.matches(pat)){
            throw new InvalidEmailException("Ulovlig karakter i e-post");
        }
        else {
            return epost;
        }
    }

    //metode for å sørge for lovlig telefon, eller gi passende feilmelding
    public String sjekkTelefon(String telefon) throws InvalidPhoneException {
        String pat = "^(\\+|00){0,2}(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}$";
        if(!telefon.matches(pat)){
            throw new InvalidPhoneException("Ulovlig karakter i telefonnr (Mellomrom, parantes eller bindestrek)");
        }
        else {
            return telefon;
        }
    }
}
