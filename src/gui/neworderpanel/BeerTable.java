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
    /*
    table=new JTable(controller.getOrderLines(),column);
    table.setEnabled(false);;
    sp=new JScrollPane(table);
    this.add(sp);*/

  }

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
