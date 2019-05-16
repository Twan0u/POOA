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
* @version 1.0
*
*/
public class ControllerNewOrder extends Controller {

    /**Variable contenant l'order actuellement en préparation*/
    private Order newOrder = new Order();

    private Client [] bufferClients = null;

    public ControllerNewOrder()throws ProgramErrorException{
      try{
        bufferClients = businesslayer.getAllClients();
      }catch(Exception exception){
        throw new ProgramErrorException( exception.getMessage());
      }
    }

    /** Recupération de tous les clients
    * @return un tableau contenant chaque client sous la forme d'un string
    * @since 1.1
    */
    public String[] getClients()throws ProgramErrorException{

      String [] out = new String[bufferClients.length];
      for(int i = 0; i<out.length; i++){
        out[i] = bufferClients[i].getName() + "-" + bufferClients[i].getId();
      }
      return out;
    }

    /** Enregistre quel client est sélectionné
    * @param index index du client selectionné dans le tableau des clients
    * @since 1.2
    */
    public void selectClient(int index)throws ProgramErrorException{
      try{
        newOrder.setClient(bufferClients[index]);
      }catch(OrderException e){
        throw new ProgramErrorException(e.getMessage());
      }

    }

    /** Enregistre quel Business est sélectionné (null si pas de livraison à effectuer(defaut))
    * @param index index du client selectionné dans le tableau des clients  (négatif ou égal à zero, pas de livraison à effectuer)
    * @since 1.2
    */
    public void selectBusiness(int index){
      if ( index <= 0 ){ // le cas ou il n'y a pas encore de clients chargés ou pas de livraison à effectuer
        newOrder.setBusinessUnitId(null);
      }else{
        BusinessUnit [] businessOfClient = null;
        try{
          Client client = newOrder.getClient();//TODO REMOVE
          businessOfClient = businesslayer.getBusinessOf(client.getId()); // TODO remove this shit and replace by a proper method getBuisness(Business)
        }catch(Exception e){
                System.out.println(e.getMessage());
          //TODO see later
        }
        newOrder.setBusinessUnitId(businessOfClient[index-1]);
      }
    }

    /** Récupération de toutes les bières de la base de donnée
    * @return un tableau des différentes bières que vends l'entreprise
    * @since 1.0
    */
    public String[] getBeers()throws ProgramErrorException{
      ArrayList<Beer> beers = null;
      try{
        beers = businesslayer.getAllBeers();
      }catch(Exception e){
        throw new ProgramErrorException("Erreur du chargement des bières disponibles " + e.getMessage());
      }
      if (beers == null){
        throw new ProgramErrorException("Erreur du chargement : Pas de bières disponibles ");
      }

        String [] out = new String[beers.size()];
        for(int i=0;i<out.length;i++){
          out[i] = beers.get(i).getName();
        }
        return out;
    }

    /** Ajoute une bière à la commande
    * @param index
    *         index de la bière dans la table des bières disponnibles
    * @param quantity
    *         quantité de bière à commander
    * @throws UserInputErrorException quand les valeurs d'entrées sont erronnées ou que la création des objets n'a pas pu aboutir
    * @since 1.2
    */
    public void addBeer(int index, int quantity)throws UserInputErrorException, ProgramErrorException{
      ArrayList<Beer> beers = null; //TODO getBeer(Beername)
      try{
        beers = businesslayer.getAllBeers();
      }catch(Exception e){
        throw new ProgramErrorException("Erreur du chargement des bières disponibles " + e.getMessage());
      }
      if (beers == null){
        throw new ProgramErrorException("Il y a eu un problème dans chargement de la bière");
      }else if(beers.size() < index || index < 0){
        throw new UserInputErrorException("L'index de la bière sélectionnée est invalide");
      }else{
        try{
          new OrderLine(beers.get(index),newOrder,quantity);
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
        Double price = current.getPrice();
        int quantity = current.getQuantity();
        data[i][0]= current.getBeer().getName();
        data[i][1]= Integer.toString(quantity);
        data[i][2]= Double.toString(price) + "€";
        data[i][3]= Double.toString(price*quantity) + "€";
        total += price * quantity;
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
    public String[] getBusiness()throws ProgramErrorException{
      Client client = newOrder.getClient();
        if (client==null){
          String [] out = new String [1];
          out[0] = "Aucun Client Sélectionné";
          return out;
        }else{
          BusinessUnit [] businessOfClient = null;
          try{
            businessOfClient = businesslayer.getBusinessOf(client.getId());
          }catch(Exception e){
            throw new ProgramErrorException(e.getMessage());
          }
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

      public void removeLastBeer(){
        newOrder.removeLastOrderLine();
      }

      public int saveOrder()throws UserInputErrorException{
      /*  if (numDays<0){
          throw new UserInputErrorException("Nombre de jours pour effectuer la livraison Invalide");
        }
        newOrder.setHasPriority(priority);
        newOrder.setTimeLimit(numDays);
        */

        if(newOrder.getClient() == null){
            throw new UserInputErrorException("Veuillez selectionner un client");
        }

        if(newOrder.getOrderLinesSize() == 0){
          throw new UserInputErrorException("Commande Vide");
        }

        int idOfNewOrder;
        try {
          idOfNewOrder = businesslayer.saveOrder(newOrder);
        }
        catch(Exception e){
          throw new UserInputErrorException("Ajout impossible " + e.getMessage());
        }
      return idOfNewOrder;

      }
}
