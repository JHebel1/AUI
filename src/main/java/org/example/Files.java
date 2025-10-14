package org.example;

import java.io.*;
import java.util.List;
import java.util.Objects;

public class Files {
    public static void saveClubsToFile(List<Club> clubs, String filename){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))){
            out.writeObject(clubs);
            System.out.println("zapisano do pliku");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    public static List<Club> readClubsFromFile(String filename){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))){
            List<Club> clubs = (List<Club>) in.readObject();
            System.out.println("Odczytano z pliku");
            return clubs;
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}
