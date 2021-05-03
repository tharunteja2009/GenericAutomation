package Exceptions;

public class GenericAutomationException extends Exception {
  String message;

  public GenericAutomationException(String str){
    message = str;
  }

  public String toString() {
    return ("Framework Exception Occurred : " + message);
  }
}
