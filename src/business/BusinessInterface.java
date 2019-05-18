package business;

import dataccess.*;
import composants.*;
import exceptions.*;

import java.io.*;
import java.util.*;

/**
* <b>classe de la couche Business</b>
*
* @author Antoine Lambert et Nathan Surquin
* @version 1.5
*
*/
public interface BusinessInterface {

  /** Recupération de tous les clients depuis la couche data
  * @throws ProgramErrorException erreur renvoyée si la couche base de donnée ne parviens pas à charger les données
  * @return un tableau les clients de l'entreprise
  * @since 1.1
  */
  Client[] getAllClients()throws ProgramErrorException;

  /** Récupération de toutes les bières de la base de donnée
  * @return un arraylist des différentes bières que vends l'entreprise
  * @throws ProgramErrorException en cas d'erreur de réception des bières depuis la base de donnée
  * @since 1.2
  */
  ArrayList<Beer> getAllBeers()throws ProgramErrorException;

  /** récupère tous les BusinessUnit d'un client sur base de l'id d'un client
  * @param id
  *             id du client
  * @return une liste de business ou null si il n'y a pas de business associé à ce client
  * @throws ProgramErrorException en cas d'erreurs de lecture depuis la database ou de liste vide
  * @since 1.2
  */
  BusinessUnit[] getBusinessOf(int id)throws ProgramErrorException;

  /** Recuperation de toutes les bières dont le stock est en dessous de leur seuil minimum
  * @return une liste de business ou null si il n'y a pas de business associé à ce client
  * @throws ProgramErrorException en cas d'erreurs de lecture depuis la database ou de liste vide
  * @since 1.2
  */
  ArrayList<Beer> getLowQuantityBeers()throws ProgramErrorException;

  /** Sauvegarde une commande dans la base de donnée
  * @throws ProgramErrorException erreur renvoyée si il n'y a pas d'order ou si la couche data n'a pas réussi à ajouter l'order à la base de donnée
  * @param order
          La commande à Sauvegarder dans la base de donneé
  * @return l'id de la commande sauvegardée
  * @since 1.4
  */
  int saveOrder(Order order) throws ProgramErrorException;

  /** Recupération de toutes les commandes stockées dans la base de donnée
  * @return un tableau contenant toutes les orders de la base de donnée
  * @throws ProgramErrorException erreur renvoyée si la couche base de donnée ne parviens pas à charger les données
  * @since 1.1
  */
  Order[] getAllOrders()throws ProgramErrorException;

  Order getOrder(int orderId);

  ArrayList<Order> getOrdersToDeliver()throws ProgramErrorException;

  ArrayList<Locality> localitiesWithPostCode(String postCode)throws ProgramErrorException;

  ArrayList<BusinessUnit> getArrayBusinessOf(int id)throws ProgramErrorException;
}
