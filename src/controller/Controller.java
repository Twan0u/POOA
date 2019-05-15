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
}
