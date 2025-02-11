package com.kk.cert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class CaCertChecker {
    private final DefaultCaCertFile caCertFile;
    private byte[] previousHash;

    public CaCertChecker(){
        this.caCertFile = DefaultCaCertFile.initializeWithDefault();
    }

    public CaCertChecker(DefaultCaCertFile defaultCaCertFile) {
        this.caCertFile = defaultCaCertFile;
    }

    public boolean hasCaCertChanged() {
        try {
            byte[] currentHash = computeFileHash(caCertFile.getPath());
            if(previousHash == null) {
                previousHash = currentHash;
                return false;
            }

            boolean hasChanged = !Arrays.equals(previousHash, currentHash);
            previousHash = currentHash;
            return hasChanged;
        } catch (NoSuchAlgorithmException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] computeFileHash(String filePath) throws NoSuchAlgorithmException, IOException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        try (FileInputStream fis = new FileInputStream(filePath)){
            byte[] byteArray = new byte[1024];
            int byteCount;
            while ((byteCount = fis.read(byteArray)) != -1) {
                digest.update(byteArray, 0, byteCount);
            }
        }

        return digest.digest();
    }
}
