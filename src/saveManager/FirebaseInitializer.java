package saveManager;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseInitializer {

    private boolean initialized = false;

    public FirebaseInitializer() {
        initializeFirebase();
        this.initialized = true;
    }

    public static void initializeFirebase() {
        try {
            FileInputStream serviceAccount = new FileInputStream("src/key/examgui-4a40b-35ff981a418b.json");

            FirebaseOptions options = new FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket("examgui-4a40b.appspot.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean saveToCloud(String filename) {
        if (!this.initialized) return false;

        Bucket bucket = StorageClient.getInstance().bucket();

        try {
            File file = new File();
            FileInputStream fis = new FileInputStream("salvataggi/" + filename);

            byte[] data = new byte[(int) file.length()];

            fis.read(data);
            fis.close();

            bucket.create(filename.replace("salvataggi/", ""), data, "application/json");
            return true;

        } catch (IOException e) {
            System.out.println("Error opening file " + filename + " for reading");
            e.printStackTrace();
            return false;
        }

    }

    public boolean deleteFromCloud(String filename) {
        if (!this.initialized) return false;
        
        if (filename == null) return false;
        Bucket bucket = StorageClient.getInstance().bucket();
        Blob blob = bucket.get(filename.replace("salvataggi/", ""));
        if (blob != null) blob.delete();

        return true;
    }
}
