package com.figs.automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Loads values from config.properties with support for:
 * 1) System properties (-Dkey=value)
 * 2) Environment variables (dots -> underscores, uppercased)
 * 3) Property file (src/test/resources/config.properties by default)
 *
 * Handles relative paths (./testdata/...) by resolving against project root (user.dir).
 */
public class ConfigReader {
    private static final Properties props = new Properties();
    private static volatile boolean loaded = false;

    private ConfigReader() {}

    private static void loadProperties() {
        if (loaded) return;
        synchronized (ConfigReader.class) {
            if (loaded) return;

            // 1) Try classpath resource
            try (InputStream is = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("config.properties")) {
                if (is != null) {
                    props.load(is);
                    loaded = true;
                    return;
                }
            } catch (IOException ignored) {}

            // 2) Fallback to file path
            String filePath = System.getProperty("config.file", "src/test/resources/config.properties");
            try (FileInputStream fis = new FileInputStream(filePath)) {
                props.load(fis);
                loaded = true;
            } catch (IOException e) {
                throw new RuntimeException("❌ Failed to load config.properties: " + filePath, e);
            }
        }
    }

    /** Get property with system/env overrides */
    public static String get(String key) {
        loadProperties();

        // 1) JVM/system property override
        String sys = System.getProperty(key);
        if (sys != null && !sys.isEmpty()) return resolveIfPath(sys);

        // 2) Environment variable fallback
        String envKey = key.replace('.', '_').toUpperCase();
        String env = System.getenv(envKey);
        if (env != null && !env.isEmpty()) return resolveIfPath(env);

        // 3) From properties file
        String val = props.getProperty(key);
        return resolveIfPath(val);
    }

    public static String get(String key, String defaultVal) {
        String v = get(key);
        return (v == null) ? defaultVal : v;
    }

    public static int getInt(String key, int defaultVal) {
        String v = get(key);
        if (v == null) return defaultVal;
        try { return Integer.parseInt(v.trim()); }
        catch (NumberFormatException e) { return defaultVal; }
    }

    public static boolean getBoolean(String key, boolean defaultVal) {
        String v = get(key);
        if (v == null) return defaultVal;
        return Boolean.parseBoolean(v.trim());
    }

    public static void reload() {
        synchronized (ConfigReader.class) {
            props.clear();
            loaded = false;
            loadProperties();
        }
    }

    /** If value starts with ./ or ../, resolve against project root (user.dir) */
    private static String resolveIfPath(String value) {
        if (value == null) return null;
        if (value.startsWith("./") || value.startsWith("../")) {
            return new File(System.getProperty("user.dir"), value).getAbsolutePath();
        }
        return value;
    }
}
