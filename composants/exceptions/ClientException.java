package composants.exceptions;

public class ClientException extends Exception{
  private String message;
  public ClientException(String message){
    this.message = message;
  }
  public String getMessage(){
    return this.message;
  }
}
