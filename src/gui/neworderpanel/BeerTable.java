package gui.neworderpanel;

import controller.*;
import exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BeerTable extends JPanel{

ControllerNewOrder controller;

  private JTable table;
  private JScrollPane sp;
  private String column[]={"Bière","Quantité","Prix Unit","Total"};

  public BeerTable(ControllerNewOrder controller){
    this.controller = controller;
    this.setLayout(new FlowLayout());

    //Tableau Commande
    table=new JTable(controller.getOrderLines(),column);
    tableStyle();
    sp=new JScrollPane(table);
    this.add(sp);

  }

      public void refreshTable(){
        this.remove(sp);
        String column[]={"Bière","Quantité","Prix Unit","Total"};
        table=new JTable(controller.getOrderLines(),column);
        tableStyle();
        sp=new JScrollPane(table);
        this.add(sp);
        this.updateUI();
      }



    public void tableStyle(){
          table.setEnabled(false);
          //table.setBackground(colBackground);
          //table.setGridColor(colText);
          //table.setForeground(colText);
        }
}
