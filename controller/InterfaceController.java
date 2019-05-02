package controller;
import business.*;
import composants.*;
import composants.exceptions.*;

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
  * @throws ClientException erreur renvoyée si il y a eu une erreur dans la création d'un objet client
  * @since 1.1
  */
  String[] getClients()throws ClientException;

  /** Recupération d'une courte description d'un client sur base de son index
  * @param index
  *             index dans le tableau des clients du client à afficher
  * @return une courte description du client
  * @since 1.0
  */
  String getInfoClient(int index);

  /** Récupération de toutes les bières de la base de donnée
  * @return un tableau des différentes bières que vends l'entreprise
  * @throws BeerException en cas d'erreur dans la création d'une des bières
  * @since 1.0
  */
  String[] getBeers()throws BeerException;

  /** récupère tous les BusinessUnit d'un client sur base de son index dans le tableau
  * @param index
  *             index du client
  * @return une liste de business
  * @throws BusinessUnitException en cas de création d'un BusinessUnit incorrect
  * @throws LocalityException en cas de création d'une Locality incorrecte
  * @since 1.0
  */
  String[] getBusinessOf(int index)throws BusinessUnitException,LocalityException;
}
