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
* @version 1.1
*
*/
public interface BusinessInterface {

  /** Recupération de tous les clients
  * @return un tableau contenant chaque client
  * @throws ClientException erreur renvoyée si il y a eu une erreur dans la création d'un objet client
  * @since 1.1
  */
  Client[] getAllClients()throws ClientException;

  /** Recupération d'un sur base de son index
  * @param index
  *             index dans le tableau des clients du client à retourner
  * @return une courte description du client
  * @since 1.0
  */
  Client getClient(int index);

  /** Récupération de toutes les bières de la base de donnée
  * @return un tableau des différentes bières que vends l'entreprise
  * @throws BeerException en cas d'erreur dans la création d'une des bières
  * @since 1.0
  */
  Beer[] getAllBeers()throws BeerException;

  /** récupère tous les BusinessUnit d'un client sur base de son index dans le tableau
  * @param index
  *             index du client
  * @return une liste de business
  * @throws BusinessUnitException en cas de création d'un BusinessUnit incorrect
  * @throws LocalityException en cas de création d'une Locality incorrecte
  * @since 1.0
  */
  BusinessUnit[] getBusinessOf(int id)throws BusinessUnitException,LocalityException;
}
