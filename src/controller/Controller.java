package controller;

import business.*;
import composants.*;
import exceptions.*;

import java.io.*;
import java.util.*;

/** <b>classe du controller de l'interface</b>
*
* @author Antoine Lambert et Nathan Surquin
* @version 3.1
*
*/

public class Controller {

  protected static Business businesslayer = new Business(); // cette variable est utilisée dans le cadre d'interactions avec la couchebusiness

  protected Client [] clients;
  private ArrayList<Locality> bufferlocalitiesToDeliver = new ArrayList<>();
  private Order newOrder = new Order();

  private BusinessUnit [] businessBuffer = null;

  public String[][]  getstock(boolean onlyLow){
    ArrayList<Beer> beers = null;
    try{
      if (onlyLow){
        beers = businesslayer.getLowQuantityBeers();
      }else{
        beers = businesslayer.getAllBeers();
      }
    }catch(Exception e){//TODO
      System.out.println(e.getMessage());
    }
    if (beers == null){//TODO
    }
    int totalBieres = beers.size();
    String [][] out = new String[totalBieres][2];
    for(int i=0;i<totalBieres;i++){
          out[i][0] = beers.get(i).getName();
          out[i][1] = Integer.toString(beers.get(i).getQtInStock());
    }
    return out;
  }
  public String[] getAllPostCodeToDeliverTo() throws ProgramErrorException{
    if (bufferlocalitiesToDeliver != null){
      ArrayList<String> allPostCodes = new ArrayList<>();
      for(int i=0;i<bufferlocalitiesToDeliver.size();i++){
        String currentPostCode =  bufferlocalitiesToDeliver.get(i).getPostCode();
        boolean isInArray = false;
        for (int j=0;j<allPostCodes.size();j++){
          if (currentPostCode.equals(allPostCodes.get(j))){
            isInArray = true;
          }
        }
        if (isInArray == false){
          allPostCodes.add(currentPostCode);
        }
      }
      String [] out = new String[allPostCodes.size()];
      for(int i=0; i<allPostCodes.size();i++){
        out[i] = allPostCodes.get(i);
      }
      return out;
    }else{
      throw new ProgramErrorException("OOPS something went wrong");
    }
  }
  public String[] getLocalitesToDeliverWithPostCode(String postCode)throws ProgramErrorException{
    ArrayList<Locality> localities = businesslayer.localitiesWithPostCode(postCode);
    if (localities!=null){
      String [] out = new String[localities.size()];
      for(int i=0; i<localities.size();i++){
        out[i] = localities.get(i).getName();
      }
      return out;
    }
    throw new ProgramErrorException("locality Loading Error");
  }

  public ArrayList<Order> getOrdersToDeliver()throws ProgramErrorException{
      try{
        return businesslayer.getOrdersToDeliver();
      }catch(Exception e){
        throw new ProgramErrorException("impossible de charger les Commandes à livrer");
      }
  }

  public ArrayList<Client> getAllClients()throws ProgramErrorException{
    try{
      return businesslayer.getAllClientsArray();
    }catch(Exception error){
      throw new ProgramErrorException(error.getMessage());
    }
  }

  public ArrayList<BusinessUnit> getBusinessOf(int clientId)throws ProgramErrorException{
    try{
      return businesslayer.getArrayBusinessOf(clientId);
    }catch(Exception error){
      throw new ProgramErrorException(error.getMessage());
    }
  }

  public Order getOrder(int idCommande){
    return businesslayer.getOrder(idCommande);
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

/*  public void addBeer(int index, int quantity)throws UserInputErrorException, ProgramErrorException{
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
  }*/

  /*public void removeLastBeer(){
    newOrder.removeLastOrderLine();
  }*/

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

    public int saveOrder(Order orderToSave)throws UserInputErrorException,ProgramErrorException{
      validateOrder(orderToSave);
      try {
        return businesslayer.saveOrder(orderToSave);
      }
      catch(Exception e){
        throw new ProgramErrorException("Ajout impossible " + e.getMessage());
      }
    }

    public void validateOrder(Order orderToSave)throws UserInputErrorException{
      if (orderToSave.getClient() == null){
        throw new UserInputErrorException("Veuillez selectionner un client");
      }
      if(orderToSave.getOrderDate() == null){//TODO
        throw new UserInputErrorException("La date de la commande est invalide");
      }
      //TODO date de commande est inférieure à la date actuelle
      if(orderToSave.getTimeLimit()<0){
        throw new UserInputErrorException("Le nombre de jours pour effectuer la commande est invalide");
      }
      /*if(orderToSave.getOrderLinesSize() == 0){
        throw new UserInputErrorException("Commande Vide");
      }*/
    }

    public void deleteOrder(int id)throws ProgramErrorException{
      try{
        businesslayer.deleteOrder(id);
      }catch(Exception error){
        throw new ProgramErrorException("Il y a eu un problème lors de la suppression de la commande");
      }
    }

    public void modifyOrder(Order order)throws ProgramErrorException, UserInputErrorException{
      validateOrder(orderToSave);
      try{
        businesslayer.modifyOrder(order);
      }catch(Exception e){
        throw new ProgramErrorException("Erreur lors de la sauvegarde des données modifiées");
      }
    }
  }
