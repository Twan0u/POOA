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

  public String[][] getOrdersToDeliver()throws ProgramErrorException{
      ArrayList<Order> orders;
      try{
        orders = businesslayer.getOrdersToDeliver();
      }catch(Exception e){
        throw new ProgramErrorException("impossible de charger les Commandes à livrer");
      }
      if (orders.size()==0){
        throw new ProgramErrorException("Il n'y a aucune Commandes a livrer"); //TODO changer par un message d'info
      }else{
        String [][] out = new String[orders.size()][6];
        for(int i = 0; i<out.length;i++){
          out[i][0] = Integer.toString(orders.get(i).getId());
          out[i][1] = orders.get(i).getClient().getName();
          out[i][2] = Boolean.toString(orders.get(i).getHasPriority());
          out[i][3] = orders.get(i).getBusinessUnitId().getStreetName();
          out[i][4] = orders.get(i).getBusinessUnitId().getLocality().getPostCode();
          bufferlocalitiesToDeliver.add(orders.get(i).getBusinessUnitId().getLocality());
          out[i][5] = orders.get(i).getBusinessUnitId().getLocality().getName();
        }
        return out;
      }
  }

  /** Recupération de tous les clients
  * @return un tableau contenant chaque client sous la forme d'un string
  * @since 1.1
  */
  public String[] getClients()throws ProgramErrorException{
    try{
      clients = businesslayer.getAllClients();
    }catch(Exception error){
      throw new ProgramErrorException(error.getMessage());
    }
    if (clients == null){
      throw new ProgramErrorException("Il y a eu une erreur dans le chargement des clients");
    }
    if (clients.length == 0){
      throw new ProgramErrorException("Il n'y a pas de clients dans la base de donnée");
    }
    String [] out = new String[clients.length];
    for(int i = 0; i<out.length; i++){
      out[i] = clients[i].getName() + "-" + clients[i].getId();
    }
    return out;
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


}
