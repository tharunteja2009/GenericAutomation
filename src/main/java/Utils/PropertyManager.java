package Utils;

public class PropertyManager {
    public static final String browser = ConfigurationLoader.getProperty("browser");
    public static final String applicationURL = ConfigurationLoader.getProperty("applicationURL");
    public static final String oppenheimerServiceUrl = ConfigurationLoader.getProperty("oppenheimerServiceUrl");
    public static final String dbHost = ConfigurationLoader.getProperty("dbhost");
    public static final String dbPort = ConfigurationLoader.getProperty("dbport");
    public static final String dbUser = ConfigurationLoader.getProperty("dbuser");
    public static final String dbPassword = ConfigurationLoader.getProperty("dbpassword");
}
