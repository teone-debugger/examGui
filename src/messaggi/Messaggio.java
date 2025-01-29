package messaggi;

public class Messaggio {

    private static Messaggio instance = null;
    private static String messaggio = "";

    public  Messaggio(){
    }

    public static Messaggio getIstance(){
        if(instance == null){
            instance = new Messaggio();
        }
        
        return instance;
    }

    public static String getMessaggio() {
        return messaggio;
    }
    public static void setMessaggio(String m) {
        messaggio = m;
    }
    public static void addMessaggio(String m) {
        messaggio += m + "\n";
    }
    public static void clearMesaggio(){
        messaggio = "";
    }

}