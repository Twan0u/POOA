/*
Programme de Gestion d'une brasserie
écrit par Antoine Lambert et Nathan Surquin
dans le cadre du cours de Programation avancée Orientée Objets
*/
import composants.*;
import composants.exceptions.*;

import javax.swing.*;
import java.io.*;


// ajouter une discount par order line

public class BrassiGestion {

    public static void main(String[] args) {

      // test de création d'interface
      //new Interface("Titre","Boutton"); // Gui

      //test de création de client

      try{
        Client bob = new Client (12, "Bob", "0032498194975", 30, "TVA_062232095251");
        Locality local = new Locality (1, "Localité du petit bois", "7911");
        BusinessUnit bubu = new BusinessUnit (3, bob, local, "Rue de la faim", "12");
        BusinessUnit blyat = new BusinessUnit (16, bob, local, "Rue des potiers", "3");
        Order order1 = new Order(1, bubu, bob, false, "10-12-19", "Paid", 365);
        Order order2 = new Order(2, bubu, bob, true, "12-04-19", "Paid", 365);
        Beer trappe = new Beer("Trappe triple",2.80,100);
        Beer bucheBlonde = new Beer("Bûche Blonde",2.10,10);
        Beer beer1 = new Beer("Bûche Blonde",2.20,10);
        Beer beer2 = new Beer("Blonde ambrée",1.50,2);
        Beer beer3 = new Beer("Bûche double",2.50,16);
        Beer beer4 = new Beer("Bûche triple",3.10,11);
        Beer beer5 = new Beer("Triple Karmeliet",1.00,10);
        OrderLine orderline1 = new OrderLine(bucheBlonde,order1,10);
        OrderLine orderline2 = new OrderLine(trappe,order2,12);
        OrderLine orderline3 = new OrderLine(beer1,order1,3);
        OrderLine orderline4 = new OrderLine(beer2,order1,12);
        OrderLine orderline5 = new OrderLine(beer3,order1,45);
        OrderLine orderline6 = new OrderLine(beer4,order1,42);
        OrderLine orderline7 = new OrderLine(beer5,order1,700);
        System.out.println(order1.toString());
        System.out.println(order2.toString());
        new Interface("Titre","bouton",bob.getBusiness(),bob.getBusinessCount());
        //System.out.println(orderline1.toString());
        //System.out.println(orderline2.toString());
        //System.out.println(bubu.toString());
      }
      catch(BeerException beerException){
        JOptionPane.showMessageDialog (null, beerException.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
      }
      catch(OrderException OrderException){
        JOptionPane.showMessageDialog (null, OrderException.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
      }
      catch(BusinessUnitException e){
        JOptionPane.showMessageDialog (null, e.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
      }
      catch(ClientException e){
        JOptionPane.showMessageDialog (null, e.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
      }
      catch(LocalityException localityException){
        JOptionPane.showMessageDialog (null, localityException.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
      }
      //initialisation();//Lance un thread qui initialise l'application
      //System.exit(0);
      //JOptionPane.showMessageDialog (null, "Message","INFO", JOptionPane.PLAIN_MESSAGE);
    }

    /*public static void initialisation(){
      ThreadInit threadInit = new ThreadInit();
      threadInit.start();
      try{
        threadInit.join();
      }
      catch(InterruptedException err){
        System.out.println(err);
      }
    }*/

}
