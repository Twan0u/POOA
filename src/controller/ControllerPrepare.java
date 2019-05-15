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
public class ControllerPrepare extends Controller {

    private Client [] bufferClients = null;
    /** Recupération de tous les clients
    * @return un tableau contenant chaque client sous la forme d'un string
    * @since 1.1
    */
    public String[] getClients()throws ProgramErrorException{
      try{
        bufferClients = businesslayer.getAllClients();
      }catch(Exception exception){
        throw new ProgramErrorException("Erreur lors de l'access aux données client :" + exception.getMessage());
      }
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
    public void getOrdersWithClient(int index)throws ProgramErrorException{
      //TODO verifier index client valide
      //TODO verifier client non null
      Client client = businesslayer.getClient(bufferClients[index].getId());
    }

}
