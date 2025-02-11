package com.kk.cert;

public class DefaultCaCertFile {
    private String javaHome;
    private String caCertFile;
    private String path;

    public DefaultCaCertFile() {
        this.javaHome = System.getProperty("java.home");
        this.caCertFile = "/lib/security/cacerts";
        this.path = String.format("%s/%s", javaHome, caCertFile);
    }

    public DefaultCaCertFile(String javaHome, String caCertFile) {
        this.javaHome = javaHome;
        this.caCertFile = caCertFile;
        this.path = String.format("%s/%s", javaHome, caCertFile);
        // Validate if path exists
    }

    public static DefaultCaCertFile initializeWithDefault() {
        String javaHome = System.getProperty("java.home");
        String defaultCaCertPath = "/lib/security/cacerts";
        return new DefaultCaCertFile(javaHome, defaultCaCertPath);
    }

    public static DefaultCaCertFile builder() {
        DefaultCaCertFile defaultCaCertFile = new DefaultCaCertFile();
        defaultCaCertFile.path = "";
        return defaultCaCertFile;
    }

    public DefaultCaCertFile addJavaHome(String javaHome) {
        this.javaHome = javaHome;
        return this;
    }

    public DefaultCaCertFile addCaCertFileName(String fileName) {
        this.caCertFile = fileName;
        return this;
    }

    public DefaultCaCertFile build() {
        this.path = String.format("%s/%s", this.javaHome, this.caCertFile);

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
}
