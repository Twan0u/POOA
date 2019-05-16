import business.Business;
import dataccess.*;
import gui.Gui;

import composants.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
      //new Gui("src\\ressources\\");
        InterfaceData dbAccess = new DBAccess();
        try {
            System.out.println(OrderDBAccess.getOrderWithID(2));
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}