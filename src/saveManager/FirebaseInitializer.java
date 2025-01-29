package saveManager;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Blob;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;

public class FirebaseInitializer {

    private Bucket bucket;
    private boolean initialized = false;

    //Metodo costruttore
    public FirebaseInitializer() {
        try {
            FileInputStream serviceAccount = new FileInputStream("resources/firebase/key/examgui-4a40b-35ff981a418b.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket("exam-gui.firebasestorage.app")
                    .build();

            FirebaseApp.initializeApp(options);

            bucket = StorageClient.getInstance().bucket();

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.initialized = true;
    }


    public boolean downloadFromCloud(String filename, String destinationPath) {
        if (!this.initialized) return false;

        try {
            Blob blob = bucket.get(filename);
            if (blob == null) {
                System.out.println("No such file exists in the bucket: " + filename);
                return false;
            }

            byte[] content = blob.getContent();
            try (FileOutputStream fos = new FileOutputStream(destinationPath)) {
                fos.write(content);
            }

            return true;
        } catch (IOException e) {
            System.out.println("Error downloading file " + filename + " from cloud");
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveToCloud(String filename) {
        if (!this.initialized) return false;

        try {
            File file = new File("resources/firebase/savesLogs/" + filename);
            FileInputStream fis = new FileInputStream("resources/firebase/savesLogs/" + filename);

            byte[] data = new byte[(int) file.length()];

            fis.read(data);
            fis.close();

            bucket.create(filename.replace("resources/firebase/savesLogs/", ""), data, "application/json");
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
        Blob blob = bucket.get(filename.replace("resources/firebase/savesLogs/", ""));
        if (blob != null) blob.delete();

        return true;
    }
}
