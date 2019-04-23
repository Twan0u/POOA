package dataccess;

import composants.*;
import composants.exceptions.*;

import java.io.*;
import java.util.*;

public class Data implements InterfaceData{
  private static ArrayList<Client> clients = new ArrayList<>();
  Client[] getAllClients(){

  }

  Beer[] getAllBeers();
  BusinessUnit[] getBusinessOf(int i){
    /*Locality local = new Locality (1, "Localité du petit bois", "7911");
    Locality local2 = new Locality (2, "Localité du saucisson", "7900");
    BusinessUnit bubu = new BusinessUnit (1, clients[0], local, "Rue de la faim", "12");
    BusinessUnit blyat = new BusinessUnit (2, clients[0], local2, "Rue des potiers", "3");
    BusinessUnit bubui = new BusinessUnit (1, clients[1], local, "Rue des marchands", "22");
    BusinessUnit blyati = new BusinessUnit (2, clients[1], local2, "Rue Raymond le gros", "16");*/
    return clients[index].getBusiness();
  }
}
