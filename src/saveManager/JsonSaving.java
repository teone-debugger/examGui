package saveManager;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import game.Dungeon;

public class JsonSaving {


    //Metodo per salvare i dati di gioco su file
    public static void saveToFile(Dungeon dungeon, String filename) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File(filename), dungeon);
        } catch (JsonGenerationException e) {
            System.out.println("Error generating JSON from GameData");
            e.printStackTrace();
        } catch (JsonMappingException e) {
            System.out.println("Error mapping JSON from GameData");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error opening file " + filename + " for writing");
            e.printStackTrace();
        } 
    }


    //Metodo per caricare i file da file
    public static Dungeon loadFromFile(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(new File(filename), Dungeon.class);
        } catch (JsonGenerationException e) {
            System.out.println("Error generating JSON from GameData");
            e.printStackTrace();
        } catch (JsonMappingException e) {
            System.out.println("Error mapping JSON from GameData");
            e.printStackTrace();
        }

        return null;
    }
}
