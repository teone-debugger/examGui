package test.java.saveManager;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Blob;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FirebaseInitializerTest {

    private static FirebaseInitializerTest instance = null;
    private boolean initialized = false;

    // Metodo costruttore
    private FirebaseInitializerTest() {
        try {
            FileInputStream serviceAccount = new FileInputStream("resources/firebase/key/examgui-4a40b-35ff981a418b.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket("examgui-4a40b.appspot.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.initialized = true;
    }

    @BeforeAll
    public static void setUp() {
        if (instance == null) {
            instance = new FirebaseInitializerTest();
        }
    }

    @Test
    public void testInitialization() {
        assertNotNull(instance);
        assertTrue(instance.initialized);
    }

    @Test
    public void testStorageClient() {
        Bucket bucket = StorageClient.getInstance().bucket();
        assertNotNull(bucket);
    }

    @Test
    public void testBlobOperations() throws IOException {
        Bucket bucket = StorageClient.getInstance().bucket();
        String blobName = "test-blob";
        String content = "Hello, Firebase!";
        Blob blob = bucket.create(blobName, content.getBytes());

        assertNotNull(blob);
        assertEquals(blobName, blob.getName());

        Blob readBlob = bucket.get(blobName);
        assertNotNull(readBlob);
        assertEquals(content, new String(readBlob.getContent()));

        readBlob.delete();
        assertNull(bucket.get(blobName));
    }
}