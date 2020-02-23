package org.openjfx;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileOpenerTxt implements FileOpener {
    public static ArrayList<Person> lesFil(Path path) throws IOException {
        ArrayList<Person> plist = new ArrayList<>();
        try(var reader = Files.newBufferedReader(path)){
            String line;
            while ((line = reader.readLine()) != null){
                Person dm = parsePerson(line);
                if(dm != null) {
                    plist.add(dm);
                }
            }
        }
        return plist;
    }

    public static Person parsePerson(String line) throws InvalidPersonFormatException {

        String[] strings = line.split(FileSaverTxt.DELIMITER);
        if((strings.length) != 7){
            throw new InvalidPersonFormatException(("Feil bruk av spesialtegn"));
        }

        String navn = strings[0];
        try{
            int alder = Integer.parseInt(strings[1]);
            int dag = Integer.parseInt(strings[2]);
            int måned = Integer.parseInt(strings[3]);
            int år = Integer.parseInt(strings[4]);
            String epost = strings[5];
            String telefon = strings[6];
            Dato dato = new Dato(dag, måned, år);

            return new Person(navn, alder, dato, epost, telefon);
        } catch (NumberFormatException n){
            System.err.println(n.getMessage());
        }
        return null;
    }
}
