package service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

public class AuditService {

    private static AuditService instance = null;

    private final BufferedWriter buffer;

    private AuditService() {
        try {
            String path = "files/Audit.csv";

            new FileWriter(path, false).close();

            buffer = new BufferedWriter(new FileWriter(path, true));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static synchronized AuditService getInstance() {
        if (instance == null) {
            instance = new AuditService();
        }

        return instance;
    }

    public void logAction(String action) {
        try {
            Timestamp timestamp = Timestamp.from(Instant.now());

            buffer.write(action + "," + timestamp + "\n");
            buffer.flush();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
