package controller;
import business.*;
import composants.*;
import composants.exceptions.*;

/**
* <b>classe de l'objet bière</b>
*
* @author Antoine Lambert et Nathan Surquin
* @version 1.1
*
* L'objet se compose de:
* <ul>
* <li>un nom</li>
* <li>un prix de stock</li>
* <li>une quantité en stock</li>
* <li>une quantité minimale d'alerte</li>
* </ul>
*
* <b>Important</b>
* La taille maximum du nom d'une bière est fixée avec la variable MAX_LONG_NOM
*
* <b> A implementer</b>
* <ul>
*<li>Verification que la biere n'existe pas deja</li>
*</ul>
*
*/

public class Controller implements InterfaceController {

    private Business businesslayer = new Business(); // cette variable est utilisée dans le cadre d'interactions avec la couchebusiness

    /** Recupération de tous les clients
    * @return un tableau contenant chaque client sous la forme d'un string
    * @throws ClientException erreur renvoyée si il y a eu une erreur dans la création d'un objet client
    * @since 1.1
    */
    public String[] getClients()throws ClientException{
      Client [] clients = businesslayer.getAllClients();
      String [] out = new String[clients.length];
      for(int i = 0; i<out.length; i++){
        out[i] = clients[i].getName() + "-" + clients[i].getId();
      }
      return out;
    }

    /** Recupération d'une courte description d'un client sur base de son index
    * @param index
    *             index dans le tableau des clients du client à afficher
    * @return une courte description du client
    * @since 1.0
    */
    public String getInfoClient(int index){
      return businesslayer.getInfoClient(index).toString();
    }

    /** Récupération de toutes les bières de la base de donnée
    * @return un tableau des différentes bières que vends l'entreprise
    * @throws BeerException en cas d'erreur dans la création d'une des bières
    * @since 1.0
    */
    public String[] getBeers()throws BeerException{
      Beer [] beers = businesslayer.getAllBeers();
      String [] out = new String[beers.length];
      for(int i=0;i<out.length;i++){
        out[i] = beers[i].getName();
      }
      return out;
    }

    /** récupère tous les BusinessUnit d'un client sur base de son index dans le tableau
    * @param index
    *             index du client
    * @return une liste de business
    * @throws BusinessUnitException en cas de création d'un BusinessUnit incorrect
    * @throws LocalityException en cas de création d'une Locality incorrecte
    * @since 1.0
    */
    public String[] getBusinessOf(int index)throws BusinessUnitException,LocalityException{
      BusinessUnit [] list = businesslayer.getBusinessOf(index);
      if (list == null){return null;}
      String [] out = new String[list.length];
      for(int i = 0; i<list.length; i++){
        out[i] = list[i].getStreetName();
      }
      return out;
    }
}
