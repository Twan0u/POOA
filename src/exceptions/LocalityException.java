package exceptions;

/**
 * <b> Cette classe est une exception liée a la classe <i>Locality</i> </b>
 *
 * @author Antoine Lambert
 * @version 1.0
 */
public class LocalityException extends Exception{

  /**
  * Message lié à la création de l'exception
  */
  private String message;

  /**
  * @param message description de l'erreur rencontrée par le programme
  * @since 1.0
  */
  public LocalityException(String message){
    this.message = message;
  }

  /**
  * @return le message décrivant l'erreur
  * @since 1.0
  */
  public String getMessage(){
    return this.message;
  }
}
