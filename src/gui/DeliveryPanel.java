package gui;

import controller.*;
import exceptions.*;
import composants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.util.*;

public class DeliveryPanel extends Container{

  Controller controller = new Controller();

  private static String column[]={"ID-COMMANDE","Client","Prioritaire","Rue","Code Postal","Localite"};
  private String [] codePostalDataCB = null;

  private JComboBox comboBoxLocality, comboBoxCodePostal;

  private JScrollPane sp;
  private JTable table;
  private ArrayList<Order> allOrdersToDeliver;
  private ArrayList<Locality> bufferlocalitiesToDeliver;

  public DeliveryPanel() {
    this.setLayout(new BorderLayout());
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new FlowLayout());

    try{
      allOrdersToDeliver = controller.getOrdersToDeliver();
    }catch(ProgramErrorException e){
      JOptionPane.showMessageDialog (null, e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
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


    if (allOrdersToDeliver.size()==0){
      JOptionPane.showMessageDialog (null, "Il n'y a aucune Commande à livrer","Information", JOptionPane.INFORMATION_MESSAGE);
    }else{
      String [][] out = new String[allOrdersToDeliver.size()][6];
      for(int i = 0; i<out.length;i++){
        out[i][0] = Integer.toString(allOrdersToDeliver.get(i).getId());
        out[i][1] = allOrdersToDeliver.get(i).getClient().getName();
        out[i][2] = Boolean.toString(allOrdersToDeliver.get(i).getHasPriority());
        out[i][3] = allOrdersToDeliver.get(i).getBusinessUnitId().getStreetName();
        out[i][4] = allOrdersToDeliver.get(i).getBusinessUnitId().getLocality().getPostCode();
        bufferlocalitiesToDeliver.add(allOrdersToDeliver.get(i).getBusinessUnitId().getLocality());
        out[i][5] = allOrdersToDeliver.get(i).getBusinessUnitId().getLocality().getName();
      }
      table = new JTable(out,column);
    }

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
