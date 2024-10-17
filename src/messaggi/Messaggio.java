package messaggi;

public class Messaggio {

    private static String messaggio;

    public  Messaggio(){

    }

    public Messaggio(String m) {
        messaggio = m;
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

}