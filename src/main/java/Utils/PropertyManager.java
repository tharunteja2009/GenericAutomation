package Utils;

public class PropertyManager {
    public static final String browser = ConfigurationLoader.getProperty("browser");
    public static final String applicationURL = ConfigurationLoader.getProperty("applicationURL");
    public static final String oppenheimerServiceUrl = ConfigurationLoader.getProperty("oppenheimerServiceUrl");
}
