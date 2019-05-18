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
    private BusinessUnit [] businessBuffer = null;

    public String[] getBusinessOfSelectedClient(int index)throws ProgramErrorException{
      try{
        newOrder.setClient(clients[index]);
      }catch(OrderException e){
        throw new ProgramErrorException(e.getMessage());
      }
      return getBusinessOfClient(newOrder.getClient());
    }

    public String[] getBusinessOfClient(Client client)throws ProgramErrorException{
        if (client==null){
          String [] out = new String [1];
          out[0] = "Aucun Client Sélectionné";
          return out;
        }else{
          try{
            businessBuffer = businesslayer.getBusinessOf(client.getId());
          }catch(Exception e){
            throw new ProgramErrorException(e.getMessage());
          }
          if (businessBuffer == null){
            String [] out = new String[1];
            out[0] = "Pas de Livraison";
            return out;
          }else{
            String [] out = new String[businessBuffer.length+1];
            out[0] = "Pas de Livraison";
            for(int i = 1; i<out.length; i++){
              out[i] = businessBuffer[i-1].getStreetName();
            }
            return out;
          }
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

      public void removeLastBeer(){
        newOrder.removeLastOrderLine();
      }

      public int saveOrder(Client client,BusinessUnit business,String Date ,String timeLimit,boolean priority)throws UserInputErrorException{
        if (client == null){
          throw new UserInputErrorException("Veuillez selectionner un client");
        }else{
          try{
            newOrder.setClient(client);
          }catch(OrderException erreur) {
            throw new UserInputErrorException("Impossible d'ajouter ce client à la commande");
          }
        }

          newOrder.setBusinessUnitId(business);


        try{
          newOrder.setTimeLimit(Integer.parseInt(timeLimit));
        }catch(Exception error){
          throw new UserInputErrorException("Le nombre de jours supplémentaires autorisés pour effectuer la livraison est invalide");
        }
          newOrder.setHasPriority(priority);

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
