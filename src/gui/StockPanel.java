package gui;

import controller.*;
import exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StockPanel extends Container{

  private JScrollPane sp;
  private JTable table;
  private JCheckBox checkLow;
  private JPanel base;
  private static String column[]={"Bière","QuantitéStock"};
  Controller controller= new Controller();

  public StockPanel(Color colBackground,Color colText) {

    this.setLayout(new BorderLayout());
    this.setBackground(colBackground);
    checkLow = new JCheckBox("Afficher Uniquement les Quantités faibles");
    this.add(checkLow, BorderLayout.PAGE_START);

    /*Tableau du Stock actuel*/
    table=new JTable(controller.getstock(false),column);
    table.setEnabled(false);
    sp=new JScrollPane(table);
    base = new JPanel();
    base.add(sp);

    this.add(base, BorderLayout.CENTER );
    checkLow.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
          reloadTable(e.getStateChange() == ItemEvent.SELECTED);//recharge le tableau en fonction de la checkbox
      }
    });
  }

  private void reloadTable(boolean onlyLow){
    base.remove(sp);
    table=new JTable(controller.getstock(onlyLow),column);
    table.setEnabled(false);
    sp=new JScrollPane(table);
    base.add(sp);
    base.updateUI();
  }

}
