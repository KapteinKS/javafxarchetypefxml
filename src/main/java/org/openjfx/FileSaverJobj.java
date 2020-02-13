package org.openjfx;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileSaverJobj implements FileOpener {
    public static void srivTilFil(List<DataModel> dmList, Path path){
        try (OutputStream os = Files.newOutputStream(path);
             ObjectOutputStream out = new ObjectOutputStream(os)) {
            out.writeObject(new ArrayList<> (dmList));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
