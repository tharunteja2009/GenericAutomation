package Utils;

import Exceptions.GenericAutomationException;
import java.io.IOException;

public class WindowsUtils {

  private static final String KILL = "taskkill /IM ";

  public static void killProcess(String serviceName) {
    try {
      Runtime.getRuntime().exec(KILL + serviceName);
      System.out.println(serviceName + " killed successfully!");
    } catch (IOException e) {
      new GenericAutomationException("unable to kill process with name " + serviceName);
    }

  }

}
