package controller;
import business.*;
import composants.*;
import exceptions.*;

/**
* <b>classe du controller de l'interface</b>
*
* @author Antoine Lambert et Nathan Surquin
* @version 1.1
*
*/
public interface InterfaceController {

  /** Recupération de tous les clients
  * @return un tableau contenant chaque client sous la forme d'un string
  * @since 1.1
  */
  public String[] getClients()throws ProgramErrorException;

  /** Enregistre quel client est sélectionné
  * @param index index du client selectionné dans le tableau des clients
  * @since 1.2
  */
  public void selectClient(int index)throws ProgramErrorException;

  /** Enregistre quel Business est sélectionné (null si pas de livraison à effectuer(defaut))
  * @param index index du client selectionné dans le tableau des clients  (négatif si pas de livraison à effectuer)
  * @since 1.2
  */
  public void selectBusiness(int index);

  /** Récupération de toutes les bières de la base de donnée
  * @return un tableau des différentes bières que vends l'entreprise
  * @since 1.0
  */
  public String[] getBeers()throws ProgramErrorException;

  /** Ajoute une bière à la commande
  * @param index
  *         index de la bière dans la table des bières disponnibles
  * @param quantity
  *         quantité de bière à commander
  * @throws UserInputErrorException quand les valeurs d'entrées sont erronnées ou que la création des objets n'a pas pu aboutir
  * @since 1.2
  */
  public void addBeer(int index, int quantity)throws UserInputErrorException, ProgramErrorException;

  /** récupère tous les BusinessUnit d'un client sur base de son index dans le tableau
  * @param index
  *             index du client
  * @return une liste de business si il y en a et null sinon
  * @since 1.0
  */
  public String[] getBusiness()throws ProgramErrorException;

  public void removeLastBeer();


  /**
  * TODO
  */
  public String[][] getOrderLines();
}
