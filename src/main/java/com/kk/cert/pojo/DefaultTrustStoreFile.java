package com.kk.cert.pojo;

public class DefaultTrustStoreFile {
    private String javaHome;
    private String caCertFile;
    private String path;
    private String trustStorePassword;
    private static final String defaultPassword = "changeit";

    public DefaultTrustStoreFile() {
        this(System.getProperty("java.home"), "/lib/security/cacerts");
    }

    public DefaultTrustStoreFile(String javaHome, String caCertFile) {
        this(javaHome, caCertFile, defaultPassword);
    }

    public DefaultTrustStoreFile(String javaHome, String caCertFile, String trustStorePassword) {
        this.javaHome = javaHome;
        this.caCertFile = caCertFile;
        this.path = String.format("%s/%s", javaHome, caCertFile);
        // TODO: validate if path exists

        this.trustStorePassword = trustStorePassword;
    }

    public static DefaultTrustStoreFile initializeWithDefault() {
        return new DefaultTrustStoreFile();
    }

    public static DefaultTrustStoreFile builder() {
        DefaultTrustStoreFile defaultTrustStoreFile = new DefaultTrustStoreFile();
        defaultTrustStoreFile.path = "";
        return defaultTrustStoreFile;
    }

    public DefaultTrustStoreFile addJavaHome(String javaHome) {
        this.javaHome = javaHome;
        return this;
    }

    public DefaultTrustStoreFile addCaCertFileName(String fileName) {
        this.caCertFile = fileName;
        return this;
    }

    public DefaultTrustStoreFile addTrustStorePassword(String password) {
        this.trustStorePassword = password;
        return this;
    }

    public DefaultTrustStoreFile build() {
        // Set Java home and cacert file, which will be overridden
        // by other builder methods
        // Validate if all fields are having the correct data
        // Validate if path exists

        return this;
    }

    public String getJavaHome() {
        return javaHome;
    }

    public String getCaCertFile() {
        return caCertFile;
    }

    public String getPath() {
        return path;
    }

    public String getTrustStorePassword() {
        return trustStorePassword;
    }
}
