package exceptions;

/**
 * <b> Cette classe est une exception liée aux erreurs du programme. </b>
 *
 * @author Antoine Lambert & Nathan Surquin
 * @version 1.0
 */
public class ProgramErrorException extends Exception{
  /**
  * Message lié à la création de l'exception
  */
  private String message;

  /**
  * @param message description de l'erreur rencontrée par le programme
  * @since 1.0
  */
  public ProgramErrorException(String message){
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
