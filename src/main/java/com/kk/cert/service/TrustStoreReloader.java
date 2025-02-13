package com.kk.cert.service;

import com.kk.cert.pojo.DefaultTrustStoreFile;
import com.kk.cert.util.LoggerUtil;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.logging.Logger;

public class TrustStoreReloader {
    private static final Logger logger = LoggerUtil.getDefaultLogger(TrustStoreReloader.class);

    public boolean reloadTrustStore(DefaultTrustStoreFile trustStoreFile) {
        try {
            // Load default trust store
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            try (FileInputStream trustStoreStream = new FileInputStream(trustStoreFile.getPath())) {
                trustStore.load(trustStoreStream, trustStoreFile.getTrustStorePassword().toCharArray());
            }

            // Initialize the TrustManagerFactory with the trust store
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(trustStore);

            // Create a new SSL context
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

            // Set the default SSL context with the newly created SSLContext
            SSLContext.setDefault(sslContext);
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return false;
        }

        return true;
    }
}
