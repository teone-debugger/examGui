package messaggi;

import java.util.Scanner;

public class Scn {
    
    private static Scanner instance = null;

    public static Scanner getInstance(){
        if(instance == null){
            instance = new Scanner(System.in);
        }

        return instance;
    }


}
