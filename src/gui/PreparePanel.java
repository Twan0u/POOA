package gui;

import controller.*;
import exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PreparePanel extends Container{

  private JPanel base;
  private JScrollPane sp;

  private JTable table;

  public PreparePanel(Color colBackground,Color colText) {
    this.setBackground(colBackground);
    this.setLayout(new GridLayout(1,2));

    base = new JPanel();
    base.add(new JLabel("Onglet PREPARATION DE COMMANDE"));

    /*Tableau des Commandes à préparer*/
  /*  String column[]={"","Quantité","Prix Unit","Total"};
    table=new JTable(null,column);
    table.setEnabled(false);
    sp=new JScrollPane(table);
    base.add(sp);*/

    this.add(base);
  }
}
