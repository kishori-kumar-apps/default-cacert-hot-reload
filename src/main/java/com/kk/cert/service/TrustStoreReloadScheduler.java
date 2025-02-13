package com.kk.cert.service;

import com.kk.cert.pojo.DefaultTrustStoreFile;
import com.kk.cert.util.LoggerUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class TrustStoreReloadScheduler {
    private static Logger logger = LoggerUtil.getDefaultLogger(TrustStoreReloadScheduler.class);

    public static void initiateSchedule() {
        TrustStoreChecker trustStoreChecker = new TrustStoreChecker();
        TrustStoreReloader trustStoreReloader = new TrustStoreReloader();
        DefaultTrustStoreFile defaultTrustStoreFile = DefaultTrustStoreFile.initializeWithDefault();

        try(ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1)) {
            scheduledExecutorService.scheduleAtFixedRate(() -> {
                if (trustStoreChecker.hasCaCertChanged()) {
                    logger.info("Trust store change is detected");
                    trustStoreReloader.reloadTrustStore(defaultTrustStoreFile);
                    logger.info("Trust store is reloaded successfully");
                }
            }, 0, 1, TimeUnit.MINUTES);
        }
    }
}
