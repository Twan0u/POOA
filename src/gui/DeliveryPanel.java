package gui;

import controller.*;
import exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DeliveryPanel extends JPanel{

  Controller controller = new Controller();

  private static String column[]={"ID-COMMANDE","Client","Prioritaire","Rue","localite"};

  private JComboBox comboBoxLocality;

  private JScrollPane sp;
  private JTable table;
  private String[] localites;

  public DeliveryPanel() {
    this.setLayout(new BorderLayout());

    try{
      comboBoxLocality = new JComboBox(controller.getLocalitesToDeliver());
    }catch(ProgramErrorException error){
      JOptionPane.showMessageDialog (null, "Erreur du chargement des Localités","FATAL_ERROR", JOptionPane.ERROR_MESSAGE);
    }
    comboBoxLocality.setMaximumRowCount(5);
    comboBoxLocality.setSelectedIndex(-1);
    this.add(comboBoxLocality,BorderLayout.NORTH);
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
