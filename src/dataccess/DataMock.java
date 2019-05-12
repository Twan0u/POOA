package dataccess;

import composants.*;
import exceptions.*;

import java.io.*;
import java.util.*;

public class DataMock implements InterfaceData{

  private static Client [] clients= new Client[5];
  private static Beer [] beers = new Beer[4];
  private int load = 0;

  public Client[] getAllClients()throws ClientException{
          clients[0]=new Client (1, "Antoine", "0032498194975", 80, "TVA_062232095251");
          clients[1]=new Client (2, "Benjamin", "0032498191234", 50, "TVA_062232095223");
          clients[2]=new Client (3, "Corentin", "0032498195678", 10, "TVA_062232095654");
          clients[3]=new Client (4, "Emilie", "0032498198910", 25, "TVA_0622320956789");
          clients[4]=new Client (5, "BOB", "0032498191112", 66, "TVA_062232095765");
          return clients;
  }

  public Client getClient(int index){
    return clients[index];
  }

  private void loadbusiness()throws BusinessUnitException,LocalityException{
    if (load == 0){
      Locality local = new Locality (1, "Localité du petit bois", "7911");
      Locality local2 = new Locality (2, "Localité du saucisson", "7900");
      BusinessUnit bubu = new BusinessUnit (1, clients[0], local, "Rue de la faim", "12");
      BusinessUnit blyat = new BusinessUnit (2, clients[0], local2, "Rue des potiers", "3");
      BusinessUnit bubui = new BusinessUnit (1, clients[1], local, "Rue des marchands", "22");
      BusinessUnit blyati = new BusinessUnit (2, clients[1], local2, "Rue Raymond le gros", "16");
      load = 1;
    }
  }

  public BusinessUnit[] getBusinessOf(int index)throws BusinessUnitException,LocalityException{
    loadbusiness();
    return clients[index].getBusiness();
  }

  public Beer[] getAllBeers()throws BeerException{
    beers[0] = new Beer("Bûche Blonde",2.20,10);
    beers[1] = new Beer("Blonde ambrée",1.50,2);
    beers[2] = new Beer("Bûche double",2.50,16);
    beers[3] = new Beer("Bûche triple",3.10,11);
    return beers;
  }
}
