package composants.exceptions;

public class BusinessUnitException extends Exception{
  private String message;
  public BusinessUnitException(String message){
    this.message = message;
  }
  public String getMessage(){
    return this.message;
  }
}
