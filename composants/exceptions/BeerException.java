package composants.exceptions;

public class BeerException extends Exception{
  private String message;
  public BeerException(String message){
    this.message = message;
  }
  public String getMessage(){
    return this.message;
  }
}
