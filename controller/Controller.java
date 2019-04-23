package controller;
import business.*;
import composants.*;
import composants.exceptions.*;

public class Controller implements InterfaceController {
    private Business businesslayer = new Business();

    public String[] getClients()throws ClientException{
      Client [] clients = businesslayer.getAllClients();
      String [] out = new String[clients.length];
      for(int i = 0; i<out.length; i++){
        out[i] = clients[i].getName() + "-" + clients[i].getId();
      }
      return out;
    }

    public String getInfoClient(int index){
      return businesslayer.getInfoClient(index).toString();
    }

    public String[] getBeers()throws BeerException{
      Beer [] beers = businesslayer.getAllBeers();
      String [] out = new String[beers.length];
      for(int i=0;i<out.length;i++){
        out[i] = beers[i].getName();
      }
      return out;
    }

    public String[] getBusinessOf(int num)throws BusinessUnitException,LocalityException{
      BusinessUnit [] list = businesslayer.getBusinessOf(num);
      if (list == null){return null;}
      String [] out = new String[list.length];
      for(int i = 0; i<list.length; i++){
        out[i] = list[i].getStreetName();
      }
      return out;
    }
}
