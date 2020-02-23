package org.openjfx;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileSaverTxt implements FileSaver{
    public static String DELIMITER = ";";

    public static void skrivTilFil(List<Person> dmList, Path path) throws IOException {

        StringBuffer str = new StringBuffer();
        for(Person dm : dmList) {
            str.append(formatPerson(dm));
            str.append("\n");
        }
        writeToFile(path, str.toString());
    }

    public static void writeToFile(Path path, String str) throws IOException {
        Files.write(path, str.getBytes());
    }

    public static String formatPerson(Person dm) {
        String ut = dm.getNavn() + DELIMITER + dm.getAlder() + DELIMITER;
        Dato fDato = dm.getFDato();
        ut += fDato.getDag() + DELIMITER + fDato.getMåned() + DELIMITER +
                fDato.getÅr() + DELIMITER +   dm.getTlf() + DELIMITER + dm.getEPost();
        return ut;

    }
}