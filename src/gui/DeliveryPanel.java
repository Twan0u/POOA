package gui;

import controller.*;
import exceptions.*;
import composants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.util.*;

public class DeliveryPanel extends JPanel{

  Controller controller = new Controller();

  private static String column[]={"ID-COMMANDE","Client","Prioritaire","Rue","Code Postal","Localite"};

  private JPanel topPanel;
  private JComboBox comboBoxLocality, comboBoxCodePostal;
  private JScrollPane sp;
  private JTable table;

  private ArrayList<Order> allOrdersToDeliver;
  private ArrayList<Locality> bufferlocalitiesToDeliver = new ArrayList<>();
  private ArrayList<Locality> localitiesWithPostCode = new ArrayList<>();

  public DeliveryPanel() {
    this.setLayout(new BorderLayout());
    topPanel = new JPanel();
    topPanel.setLayout(new FlowLayout());

    try{
      allOrdersToDeliver = controller.getOrdersToDeliver();
    }catch(ProgramErrorException e){
      JOptionPane.showMessageDialog(null, e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
    }

    comboBoxCodePostal = new JComboBox();
    comboBoxCodePostal.setMaximumRowCount(5);

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
        Order current = allOrdersToDeliver.get(i);
        out[i][0] = Integer.toString(current.getId());
        out[i][1] = current.getClient().getName();
        out[i][2] = Boolean.toString(current.getHasPriority());
        out[i][3] = current.getBusinessUnitId().getStreetName();
        out[i][4] = current.getBusinessUnitId().getLocality().getPostCode();
        localitiesToDeliver(current.getBusinessUnitId().getLocality());
        out[i][5] = current.getBusinessUnitId().getLocality().getName();
      }
      table = new JTable(out,column);
      table.setEnabled(false);
      sp=new JScrollPane(table);
      this.add(sp,BorderLayout.CENTER);
    }

    for(Locality l : bufferlocalitiesToDeliver){
      comboBoxCodePostal.addItem(l.getPostCode());
    }
    comboBoxCodePostal.setSelectedIndex(-1);

    PostCodeComboBoxListener listener = new PostCodeComboBoxListener();
    comboBoxCodePostal.addItemListener(listener);

    ButtonSearchListener listenerSearch = new ButtonSearchListener();
    search.addActionListener(listenerSearch);

  }

  private class ButtonSearchListener implements ActionListener{
    public void actionPerformed( ActionEvent event) {
      Locality selectedLocality = localitiesWithPostCode.get(comboBoxLocality.getSelectedIndex());
      try{
        ArrayList<Order>  orders = controller.getOrdersToDeliverWithLocalityId(selectedLocality.getIdLocality());
        reloadTable(orders);
      }catch(Exception e){
        JOptionPane.showMessageDialog (null, "il y a eu une erreur dans le chargement des commandes pour cette localité","Information", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  public void reloadTable(ArrayList<Order> orders){
    if (orders.size()==0){
      JOptionPane.showMessageDialog (null, "Il n'y a aucune Commande à livrer dans cette localité","Information", JOptionPane.INFORMATION_MESSAGE);
    }else{
      String [][] out = new String[orders.size()][6];
      for(int i = 0; i<out.length;i++){
        Order current = orders.get(i);
        out[i][0] = Integer.toString(current.getId());
        out[i][1] = current.getClient().getName();
        out[i][2] = Boolean.toString(current.getHasPriority());
        out[i][3] = current.getBusinessUnitId().getStreetName();
        out[i][4] = current.getBusinessUnitId().getLocality().getPostCode();
        out[i][5] = current.getBusinessUnitId().getLocality().getName();
      }
      table = new JTable(out,column);
      table.setEnabled(false);
      sp=new JScrollPane(table);
      this.removeAll();
      this.add(topPanel);
      this.add(sp,BorderLayout.CENTER);
      this.repaint();
    }
  }

  public void localitiesToDeliver(Locality local){
    boolean isNotIn = true;
    for (Locality l: bufferlocalitiesToDeliver){
      if (local.getIdLocality() == l.getIdLocality()){
        isNotIn = false;
      }
    }
    if (isNotIn){
      bufferlocalitiesToDeliver.add(local);
    }
  }

  public void localitiesToDeliverWithPostCode(String postCode){
    comboBoxLocality.removeAllItems();
    for (Locality l: bufferlocalitiesToDeliver){
      if (l.getPostCode().compareTo(postCode)==0){
        comboBoxLocality.addItem(l.getName());
        localitiesWithPostCode.add(l);
        comboBoxLocality.setEnabled(true);
      }
    }
  }

  public class PostCodeComboBoxListener implements ItemListener {
      public void itemStateChanged(ItemEvent event){
        localitiesToDeliverWithPostCode(bufferlocalitiesToDeliver.get(comboBoxCodePostal.getSelectedIndex()).getPostCode());
      }
  }

}
