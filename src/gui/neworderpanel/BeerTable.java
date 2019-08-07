package gui.neworderpanel;

import controller.*;
import exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BeerTable extends JPanel{

Controller controller;

  private JTable table;
  private JScrollPane sp;
  private String column[]={"Bière","Quantité","Prix Unit","Total"};

  public BeerTable(Controller controller){
    this.controller = controller;
    this.setLayout(new FlowLayout());

    //Tableau Commande
    String [][] data = new String[1][4];
    data[0][0] = "1";
    data[0][1] = "2";
    data[0][2] = "3";
    data[0][3] = "4";//  beerTable.refreshTable();

    table=new JTable(data,column);
    //table=new JTable(controller.getOrderLines(),column);
    table.setEnabled(false);;
    sp=new JScrollPane(table);
    this.add(sp);

  }

  /**
  * TODO
  */
/*  public String[][] getOrderLines(){
    int numItems = newOrder.getOrderLinesSize();
    String data[][] = new String[numItems+1][4];
    double total = 0;
    for(int i=0;i<numItems;i++){
      OrderLine current = newOrder.getOrderLine(i);
      Double price = current.getPrice();
      int quantity = current.getQuantity();
      data[i][0]= current.getBeer().getName();
      data[i][1]= Integer.toString(quantity);
      data[i][2]= Double.toString(price) + "€";
      data[i][3]= Double.toString(price*quantity) + "€";
      total += price * quantity;
    }
    data[numItems][0] = "---";
    data[numItems][1] = "---";
    data[numItems][2] = "---";
    data[numItems][3] = Double.toString(total) + "€";

    return data;
  }*/

      public void refreshTable(){
        /*this.remove(sp);
        String column[]={"Bière","Quantité","Prix Unit","Total"};
        table=new JTable(controller.getOrderLines(),column);
        table.setEnabled(false);;
        sp=new JScrollPane(table);
        this.add(sp);
        this.updateUI();*/
      }

}
