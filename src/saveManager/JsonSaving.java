package saveManager;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import game.Posizione;
import game.character.enemies.Drago;
import game.character.player.Giocatore;
import game.character.player.Inventario;
import game.enums.Classe;
import game.enums.Razza;
import game.items.view.Arma;

public class JsonSaving {


    //Metodo per salvare i dati di gioco su file
    public static void saveToFile(Giocatore dungeon, String filename) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

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

    public static void saveToFileInventario(Inventario dungeon, String filename) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

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

    public static void saveToFileClasse(Classe dungeon, String filename) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

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

    public static void saveToFileRazza(Razza dungeon, String filename) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

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
    public static void saveToFileDrago(Drago dungeon, String filename) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

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
    public static void saveToFileArma(Arma dungeon, String filename) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

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
    public static Giocatore loadFromFile(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            return mapper.readValue(new File(filename), Giocatore.class);
        } catch (JsonGenerationException e) {
            System.out.println("Error generating JSON from GameData");
            e.printStackTrace();
        } catch (JsonMappingException e) {
            System.out.println("Error mapping JSON from GameData");
            e.printStackTrace();
        }

        return null;
    }

    //Metodo per caricare i file da file
    public static Inventario loadFromFileInventoy(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            return mapper.readValue(new File(filename), Inventario.class);
        } catch (JsonGenerationException e) {
            System.out.println("Error generating JSON from GameData");
            e.printStackTrace();
        } catch (JsonMappingException e) {
            System.out.println("Error mapping JSON from GameData");
            e.printStackTrace();
        }

        return null;
    }

    //Metodo per caricare i file da file
    public static Classe loadFromFileClasse(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            return mapper.readValue(new File(filename), Classe.class);
        } catch (JsonGenerationException e) {
            System.out.println("Error generating JSON from GameData");
            e.printStackTrace();
        } catch (JsonMappingException e) {
            System.out.println("Error mapping JSON from GameData");
            e.printStackTrace();
        }

        return null;
    }

    //Metodo per caricare i file da file
    public static Razza loadFromFileRazza(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            return mapper.readValue(new File(filename), Razza.class);
        } catch (JsonGenerationException e) {
            System.out.println("Error generating JSON from GameData");
            e.printStackTrace();
        } catch (JsonMappingException e) {
            System.out.println("Error mapping JSON from GameData");
            e.printStackTrace();
        }

        return null;
    }

    //Metodo per caricare i file da file
    public static Drago loadFromFileDrago(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            return mapper.readValue(new File(filename), Drago.class);
        } catch (JsonGenerationException e) {
            System.out.println("Error generating JSON from GameData");
            e.printStackTrace();
        } catch (JsonMappingException e) {
            System.out.println("Error mapping JSON from GameData");
            e.printStackTrace();
        }

        return null;
    }
    //Metodo per caricare i file da file
    public static Arma loadFromFileArma(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            return mapper.readValue(new File(filename), Arma.class);
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
