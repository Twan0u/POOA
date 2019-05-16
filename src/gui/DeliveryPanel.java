package gui;

import controller.*;
import exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DeliveryPanel extends Container{

  Controller controller = new Controller();

  private static String column[]={"ID-COMMANDE","Client","Prioritaire","Rue","localite"};

  private JComboBox comboBoxLocality, comboBoxCodePostal;

  private JScrollPane sp;
  private JTable table;

  public DeliveryPanel() {
    this.setLayout(new BorderLayout());
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new FlowLayout());

    //try{
      String [] TODO = {"Select A PostCode","Please"};
      comboBoxCodePostal = new JComboBox(TODO);//TODO 
    //}catch(ProgramErrorException error){
    //  JOptionPane.showMessageDialog (null, "Erreur du chargement des Localités","FATAL_ERROR", JOptionPane.ERROR_MESSAGE);
    //}

    comboBoxCodePostal.setMaximumRowCount(5);
    comboBoxCodePostal.setSelectedIndex(-1);
    topPanel.add(comboBoxCodePostal);

    try{
      comboBoxLocality = new JComboBox(controller.getLocalitesToDeliver());
    }catch(ProgramErrorException error){
      JOptionPane.showMessageDialog (null, "Erreur du chargement des Localités","FATAL_ERROR", JOptionPane.ERROR_MESSAGE);
    }

    comboBoxLocality.setMaximumRowCount(5);
    comboBoxLocality.setSelectedIndex(-1);
    topPanel.add(comboBoxLocality);

    this.add(topPanel,BorderLayout.NORTH);
    try{
        JTable table = new JTable(controller.getOrdersToDeliver(),column);
        table.setEnabled(false);
        sp=new JScrollPane(table);
        this.add(sp,BorderLayout.CENTER);
    }catch(ProgramErrorException error){
      JOptionPane.showMessageDialog (null, error.getMessage(),"FATAL_ERROR", JOptionPane.ERROR_MESSAGE);
    }
    LocalityComboBoxListener listener = new LocalityComboBoxListener();
    comboBoxLocality.addItemListener(listener);
  }
  public class LocalityComboBoxListener implements ItemListener {
      public void itemStateChanged(ItemEvent event){
        try{
          System.out.println("table new");
          if (0==1){throw new ProgramErrorException("");};
        }catch(ProgramErrorException error){
            JOptionPane.showMessageDialog (null, error.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
        }
        //TODO refresh Table
      }
  }

}
