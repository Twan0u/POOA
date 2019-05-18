package gui;

import controller.*;
import exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DeliveryPanel extends Container{

  Controller controller = new Controller();

  private static String column[]={"ID-COMMANDE","Client","Prioritaire","Rue","Code Postal","Localite"};
  private String [] codePostalDataCB = null;

  private JComboBox comboBoxLocality, comboBoxCodePostal;

  private JScrollPane sp;
  private JTable table;

  public DeliveryPanel() {
    this.setLayout(new BorderLayout());
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new FlowLayout());
    String [][] allOrdersToDeliver = null;
    try{
      allOrdersToDeliver = controller.getOrdersToDeliver();
    }catch(ProgramErrorException error){
      JOptionPane.showMessageDialog (null, "Erreur du chargement des Commandes à livrer","FATAL_ERROR", JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    }

    try{
      codePostalDataCB = controller.getAllPostCodeToDeliverTo();
      comboBoxCodePostal = new JComboBox(codePostalDataCB);
    }catch(ProgramErrorException error){
      JOptionPane.showMessageDialog (null, "Erreur du chargement des Codes Postaux","FATAL_ERROR", JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    }

    comboBoxCodePostal.setMaximumRowCount(5);
    comboBoxCodePostal.setSelectedIndex(-1);
    topPanel.add(comboBoxCodePostal);

    comboBoxLocality = new JComboBox();
    comboBoxLocality.setMaximumRowCount(5);
    comboBoxLocality.setSelectedIndex(-1);
    comboBoxLocality.setEnabled(false);
    topPanel.add(comboBoxLocality);

    JButton search = new JButton("Rechercher");
    topPanel.add(search);

    this.add(topPanel,BorderLayout.NORTH);


    JTable table = new JTable(allOrdersToDeliver,column);
    table.setEnabled(false);
    sp=new JScrollPane(table);

    this.add(sp,BorderLayout.CENTER);

    PostCodeComboBoxListener listener = new PostCodeComboBoxListener();
    comboBoxCodePostal.addItemListener(listener);

  }
  public class PostCodeComboBoxListener implements ItemListener {
      public void itemStateChanged(ItemEvent event){
        try{
          String [] localities = controller.getLocalitesToDeliverWithPostCode(codePostalDataCB[comboBoxCodePostal.getSelectedIndex()]);
          for(int i = 0; i<localities.length; i++){
            comboBoxLocality.addItem(localities[i]);
            //TODO Reload Table with new data selected ORDERS WITH POSTCODE == POSTCODE;
          }
        }catch(ProgramErrorException error){
          JOptionPane.showMessageDialog (null, "Erreur du chargement des localités depuis les codes Postaux","ERROR", JOptionPane.ERROR_MESSAGE);
        }
      }
  }

}
