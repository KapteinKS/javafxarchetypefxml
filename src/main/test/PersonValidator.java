public class PersonValidator {
    public static boolean email(String epost) {
        String pat = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        if(epost.matches(pat)){
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean phone(String tlf){
        String pat = "^(\\+|00){0,2}(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}$";
        if(tlf.matches(pat)){
            return true;
        }
        else {
            return false;
        }
    }
}
