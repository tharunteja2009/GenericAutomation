package Utils;

import java.io.*;
import java.util.Properties;

public class ConfigurationLoader {
    static Properties prop = new Properties();
    static InputStream inputStream;

    public static String getProperty(String property) {
        loadProperties();
        return prop.getProperty(property);
    }

    public static void loadProperties() {
        if (prop.isEmpty()) {
            try {
                inputStream = ConfigurationLoader.class.getClassLoader().getResourceAsStream("config.properties");
                prop.load(inputStream);
            } catch (FileNotFoundException e) {
                System.out.println("no config file found");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException io) {
                    System.out.println("unable to close input stream of config file");
                }
            }
        }
    }


}
