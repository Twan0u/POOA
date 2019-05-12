package controller;
import business.*;
import composants.*;
import exceptions.*;


import java.io.*;
import java.util.*;


/**
* <b>classe du controller de l'interface</b>
*java.util.concurrent.TimeUnit
* @author Antoine Lambert et Nathan Surquin
* @version 1.2
*
*/
public class Controller implements InterfaceController {

    private static Order newOrder = new Order();

    private static BusinessUnit selectedBusiness = null;

    private static Client [] clients = null;
    private static Beer [] beers = null;

    private static BusinessUnit [] businessOfClient = null;

    private static Business businesslayer = new Business(); // cette variable est utilisée dans le cadre d'interactions avec la couchebusiness

    /** Constructeur de l'objet controller
    * Il initialise la liste des clients
    */
    public Controller(){

      //TODO Thread access aux données
      try{
        //TODO try connexion to Bdd
        clients = businesslayer.getAllClients();
        beers = businesslayer.getAllBeers();
      }catch(ClientException clientError){
        //TODO Affiche l'erreur dans le programme
      }catch(BeerException beerException){
        // TODO
      }catch(Exception e){
        // TODO
      }
    }

    /** Recupération de tous les clients
    * @return un tableau contenant chaque client sous la forme d'un string
    * @since 1.1
    */
    public String[] getClients(){
      if (clients == null){
        //Wait loading of clients
        // TODBusinessUnit [] listO erreur de chargement
        // TODO Retry Loading DataBase If Error plus timer sleep
      }
      String [] out = new String[clients.length];
      for(int i = 0; i<out.length; i++){
        out[i] = clients[i].getName() + "-" + clients[i].getId();
      }
      return out;
    }

    /** Enregistre quel client est sélectionnéjava.util.concurrent.TimeUnit
    * @param index index du client selectionné dans le tableau des clients
    * @since 1.2
    */
    public void selectClient(int index){
      try{ //TODO
        newOrder.setClient(clients[index]);
          businessOfClient = businesslayer.getBusinessOf(index);// TODO Mise à jour de la liste des business Via Thread
      }catch(Exception e){}
    }

    /** Enregistre quel Business est sélectionné (null si pas de livraison à effectuer(defaut))
    * @param index index du client selectionné dans le tableau des clients  (négatif ou égal à zero, pas de livraison à effectuer)
    * @since 1.2
    */
    public void selectBusiness(int index){
        if (clients == null || index <= 0 ){ // le cas ou il n'y a pas encore de clients chargés ou pas de livraison à effectuer
          newOrder.setBusinessUnitId(null);
        }else{
          newOrder.setBusinessUnitId(businessOfClient[index-1]);
        }
    }

    /** Recupération d'une courte description d'un client sur base de son index
    * @param index
    *             index dans le tableau des clients du client à affiche//TODO notify Veuillez selectionner un clientr
    * @return une courte description du client
    * @since 1.0
    */
    public String getInfoClient(int index){
      return businesslayer.getInfoClient(index).toString();
    }

    /** Récupération de toutes les bières de la base de donnée//TODO notify Veuillez selectionner un client
    * @return un tableau des différentes bières que vends l'entreprise
    * @since 1.0
    */
    public String[] getBeers(){
      if (beers == null){
        // TODO ERROR POPUP
        String [] out = new String[1];
        out[0] = "erreur de chargement";
        return out;
      }else{
        String [] out = new String[beers.length];
        for(int i=0;i<out.length;i++){
          out[i] = beers[i].getName();
        }
        return out;
      }
    }

    /** Ajoute une bière à la commande
    * @param index
    *         index de la bière dans la table des bières disponnibles
    * @param quantity
    *         quantité de bière à commander
    * @throws UserInputErrorException quand les valeurs d'entrées sont erronnées ou que la création des objets n'a pas pu aboutir
    * @since 1.2
    */
    public void addBeer(int index, int quantity)throws UserInputErrorException{
      // TODO verification de qauntité
      // TODO verification de l'index de beers
      if (beers == null){
        //TODO
      }
      else{
        try{// TODO
          Beer newBeer = beers[index];
          System.out.println(quantity);
          OrderLine nouveau = new OrderLine(newBeer,newOrder,quantity);
      }catch(OrderLineException e){
        throw new UserInputErrorException(e.getMessage());
      }
      }
    }

    /**

    * TODO

    */
    public String[][] getOrderLines(){
      int numItems = newOrder.getOrderLinesSize();
      String data[][] = new String[numItems+1][4];
      double total = 0;
      for(int i=0;i<numItems;i++){
        OrderLine current = newOrder.getOrderLine(i);

        data[i][0]= current.getBeer().getName();
        data[i][1]= Double.toString(current.getQuantity());
        data[i][2]= Double.toString(current.getPrice());
        data[i][3]= Double.toString(current.getQuantity()*current.getPrice()) + "€";

        /*data[i][1]= Integer.toString(quantity);
        data[i][2]= Double.toString(price) + "€";
        data[i][3]= Double.toString(price*quantity) + "€";*/
        //total += price * quantity;
      }
      data[numItems][0] = "---";
      data[numItems][1] = "---";
      data[numItems][2] = "---";
      data[numItems][3] = Double.toString(total) + "€";

      return data;
    }

    /** récupère tous les BusinessUnit d'un client sur base de son index dans le tableau
    * @param index
    *             index du client
    * @return une liste de business si il y en a et null sinon
    * @since 1.0
    */
    public String[] getBusiness(){
        if (newOrder.getClient()==null){
          String [] out = new String [1];
          out[0] = "Aucun Client Sélectionné";
          return out;
        }else{
          // TODO Wait loading of all the business of the client
          if (businessOfClient == null){
            String [] out = new String[1];
            out[0] = "Pas de Livraison";
            return out;
          }else{
            String [] out = new String[businessOfClient.length+1];
            out[0] = "Pas de Livraison";
            for(int i = 1; i<out.length; i++){
              out[i] = businessOfClient[i-1].getStreetName();
            }
            return out;
          }
        }
      }
}
