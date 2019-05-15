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
public class Controller {

    static Business businesslayer = new Business(); // cette variable est utilisée dans le cadre d'interactions avec la couchebusiness

  public String[][]  getstock(boolean onlyLow){
    Beer [] beers = null;
    try{
      if (onlyLow){
        beers = businesslayer.getLowQuantityBeers();
      }else{
        beers = businesslayer.getAllBeers();
      }
    }catch(Exception e){//TODO
      System.out.println(e.getMessage());
      //throw new ProgramErrorException("Erreur du chargement des bières disponibles " + e.getMessage());
    }
    if (beers == null){//TODO
      //throw new ProgramErrorException("Erreur du chargement : Pas de bières disponibles ");
    }
    int totalBieres = beers.length;
    String [][] out = new String[totalBieres][2];
    for(int i=0;i<totalBieres;i++){
          out[i][0] = beers[i].getName();
          out[i][1] = Integer.toString(beers[i].getQtInStock());
    }
    return out;
  }
}
