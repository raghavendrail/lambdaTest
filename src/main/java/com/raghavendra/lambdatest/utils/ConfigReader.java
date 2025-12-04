package com.raghavendra.lambdatest.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Reads LambdaTest configuration from lt-config.json and decrypts sensitive values.
 */
public class ConfigReader {

    private static final String CONFIG_FILE = "lt-config.json";
    private static LtConfig cachedConfig;

    private ConfigReader() {
        // utility
    }

    private static LtConfig loadConfig() {
        if (cachedConfig != null) {
            return cachedConfig;
        }

        try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (is == null) {
                throw new RuntimeException("Could not find " + CONFIG_FILE + " in classpath (src/main/resources)");
            }

            ObjectMapper mapper = new ObjectMapper();
            LtConfig rawConfig = mapper.readValue(is, LtConfig.class);

            // Get AES key from env var
            String secretKeyBase64 = System.getenv("LT_SECRET_KEY");
            if (secretKeyBase64 == null || secretKeyBase64.isEmpty()) {
                throw new RuntimeException("Environment variable LT_SECRET_KEY is not set");
            }

            // Decrypt username & access key
            String username = CryptoUtil.decrypt(rawConfig.getUsernameEnc(), secretKeyBase64);
            String accessKey = CryptoUtil.decrypt(rawConfig.getAccessKeyEnc(), secretKeyBase64);

            rawConfig.setUsername(username);
            rawConfig.setAccessKey(accessKey);

            cachedConfig = rawConfig;
            return cachedConfig;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read " + CONFIG_FILE, e);
        }
    }

    // Public getters

    public static String getUsername() {
        return loadConfig().getUsername();
    }

    public static String getAccessKey() {
        return loadConfig().getAccessKey();
    }

    public static String getAppId() {
        return loadConfig().getAppId();
    }

    public static String getPlatformName() {
        return loadConfig().getPlatformName();
    }

    public static String getDeviceName() {
        return loadConfig().getDeviceName();
    }

    public static String getPlatformVersion() {
        return loadConfig().getPlatformVersion();
    }

    /**
     * Internal DTO class mapping lt-config.json.
     */
    public static class LtConfig {
        private String usernameEnc;
        private String accessKeyEnc;
        private String appId;
        private String platformName;
        private String deviceName;
        private String platformVersion;

        // Decrypted values (not stored in JSON)
        private transient String username;
        private transient String accessKey;

        public String getUsernameEnc() {
            return usernameEnc;
        }

        public void setUsernameEnc(String usernameEnc) {
            this.usernameEnc = usernameEnc;
        }

        public String getAccessKeyEnc() {
            return accessKeyEnc;
        }

        public void setAccessKeyEnc(String accessKeyEnc) {
            this.accessKeyEnc = accessKeyEnc;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getPlatformName() {
            return platformName;
        }

        public void setPlatformName(String platformName) {
            this.platformName = platformName;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getPlatformVersion() {
            return platformVersion;
        }

        public void setPlatformVersion(String platformVersion) {
            this.platformVersion = platformVersion;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAccessKey() {
            return accessKey;
        }

        public void setAccessKey(String accessKey) {
            this.accessKey = accessKey;
        }
    }
}
