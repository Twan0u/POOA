package controller;

import business.*;
import composants.*;
import exceptions.*;

import java.io.*;
import java.util.*;


/**
* <b>classe du controller de l'interface</b>
*
* @author Antoine Lambert et Nathan Surquin
* @version 3.0
*
*/

public class Controller {

  static Business businesslayer = new Business(); // cette variable est utilisée dans le cadre d'interactions avec la couchebusiness



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



  public String[] getLocalitesToDeliver()throws ProgramErrorException{//TODO
    String [] out = new String[4];
    out[0] = "Riccardo";
    out[1] = "is";
    out[2] = "DAMN";
    out[3] = "SEXY";
    return out;
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
        String [][] out = new String[orders.size()][5];
        for(int i = 0; i<out.length;i++){
          out[i][0] = Integer.toString(orders.get(i).getId());
          out[i][1] = orders.get(i).getClient().getName();
          out[i][2] = Boolean.toString(orders.get(i).getHasPriority());
          out[i][3] = orders.get(i).getBusinessUnitId().getStreetName();
          out[i][4] = orders.get(i).getBusinessUnitId().getLocality().getName();
        }
        return out;
      }
  }
}
