package org.openjfx;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileSaverTxt implements FileSaver{
    public static String DELIMITER = ";";

    public static void skrivTilFil(List<Person> personList, Path path) throws IOException {

        StringBuffer str = new StringBuffer();
        for(Person p : personList) {
            str.append(formatPerson(p));
            str.append("\n");
        }
        writeToFile(path, str.toString());
    }

    public static void writeToFile(Path path, String str) throws IOException {
        Files.write(path, str.getBytes());
    }

    public static String formatPerson(Person p) {
        String ut = p.getNavn() + DELIMITER + p.getAlder() + DELIMITER;
        Dato fDato = p.getfDato();
        ut += fDato.getDag() + DELIMITER + fDato.getMåned() + DELIMITER +
                fDato.getÅr() + DELIMITER + p.getePost() + DELIMITER + p.getTelefon();
        return ut;

    }
}