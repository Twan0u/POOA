import composants.Beer;
import dataccess.DBAccess;
import dataccess.InterfaceData;
import gui.OrderGui;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
      //new  OrderGui();
        InterfaceData dbAccess = new DBAccess();
        try{
            ArrayList<Beer> beers = dbAccess.getAllBeers();
            for(Beer beer : beers){
                System.out.println(beer);
            }
        }
        catch(Exception e){
            System.out.println("Echec dans le main");
            System.exit(0);
        }

    }
}