package controller;

import business.*;
import composants.*;
import exceptions.*;

import java.io.*;
import java.util.*;

/** <b>classe du controller de l'interface</b>
*
* @author Antoine Lambert et Nathan Surquin
* @version 4.2
*
*/

public class Controller {

  protected static Business businesslayer = new Business(); // cette variable est utilisée dans le cadre d'interactions avec la couchebusiness

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

  public ArrayList<Order> getOrdersToDeliver()throws ProgramErrorException{
      try{
        return businesslayer.getOrdersToDeliver();
      }catch(DataAccessException error){
        throw new ProgramErrorException("impossible de charger les Commandes à livrer : " + error.getMessage());
      }catch(CorruptedDataException error){
        throw new ProgramErrorException("Les données sont corrompues : " + error.getMessage());
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
      if(orderToSave.getOrderDate() == null){
        throw new UserInputErrorException("La date de la commande est invalide");
      }
      if(orderToSave.getOrderDate() == ""){
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
      validateOrder(order);
      try{
        businesslayer.modifyOrder(order);
      }catch(DataAccessException erreur){
        throw new ProgramErrorException("Erreur d'acces aux données : " + erreur.getMessage());
      }catch(DataModificationException erreur){
        throw new ProgramErrorException("Erreur de modification des données : " + erreur.getMessage());
      }
    }

    public ArrayList<Order> getOrdersToDeliverWithLocalityId(int idLocality) throws DataAccessException, CorruptedDataException{
      return businesslayer.getOrdersToDeliverWithLocalityId(idLocality);
    }

    public ArrayList<Order> getOrdersWithDates(String dateMin, String dateMax)throws ProgramErrorException {
      try {
        return businesslayer.getOrdersWithDates(dateMin, dateMax);
      } catch (Exception e){
        throw new ProgramErrorException("Problème lors de la récupération de données");
      }
    }

    public ArrayList<Order> getOrdersWithStateAndDates(String state, String dateMin, String dateMax) throws ProgramErrorException {
      try {
        return businesslayer.getOrdersWithStateAndDates(state, dateMin, dateMax);
      } catch (Exception e) {
        throw new ProgramErrorException("Problème lors de la récupération de données");
      }
    }
  }
