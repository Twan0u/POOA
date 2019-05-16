package gui;

import controller.*;
import exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OrderPanel extends Container{

  private ControllerNewOrder controller;

  private JPanel left,right;

  private JLabel labelClient, labelBusiness, labelBeer,labelQuantity, labelDays;
  private JComboBox comboBoxClient, comboBoxBusiness, comboBoxBeer;
  private JSpinner spinnerQuantity, spinnerDays;
  private JButton addBeerButton, removeBeerButton, saveOrderButton;
  private JCheckBox checkPriority;
  private JTable table;
  private JScrollPane sp;
  private Color colBackground;
  private Color colText;
  private Color colBis;

  public OrderPanel(Color colBackground, Color colText, Color colBis) {
    try{
      controller = new ControllerNewOrder();
    }catch(Exception e){
      JOptionPane.showMessageDialog (null, e.getMessage(),"FATAL_ERROR", JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    }
    this.colBackground = colBackground;
    this.colText = colText;
    this.colBis = colBis;
    this.setBackground(colBackground);
    this.setLayout(new GridLayout(1,2));

    left = new JPanel();
    left.setBackground(colBackground);
    left.setLayout(new GridLayout(8,2,5,5));

    right = new JPanel();
    right.setBackground(colBackground);
    right.setLayout(new GridLayout(1,1));

    /*ComboBox Client*/
    labelClient = new JLabel("Client : ");
    labelClient.setHorizontalAlignment(SwingConstants.RIGHT);
    labelClient.setForeground(colText);
    comboBoxClient = new JComboBox(loadClients());
    comboBoxClient.setMaximumRowCount(5);
    comboBoxClient.setSelectedIndex(-1);

    left.add(labelClient);
    left.add(comboBoxClient);

    /*ComboBox Business*/
    labelBusiness = new JLabel("Business : ");
    labelBusiness.setHorizontalAlignment(SwingConstants.RIGHT);
    labelBusiness.setForeground(colText);
    comboBoxBusiness = new JComboBox();
    comboBoxBusiness.setMaximumRowCount(5);
    BusinessComboRefresh();
    left.add(labelBusiness);
    left.add(comboBoxBusiness);

    /*ComboBox Beer*/
    labelBeer = new JLabel("Ajouter : ");
    labelBeer.setHorizontalAlignment(SwingConstants.RIGHT);
    labelBeer.setForeground(colText);
    comboBoxBeer = new JComboBox(loadBeers());
    comboBoxBeer.setMaximumRowCount(5);
    left.add(labelBeer);
    left.add(comboBoxBeer);

    /*Jspinner quantity*/
    labelQuantity = new JLabel("Quantité : ");
    labelQuantity.setHorizontalAlignment(SwingConstants.RIGHT);
    labelQuantity.setForeground(colText);
    spinnerQuantity = new JSpinner(new SpinnerNumberModel(1,0,100000000,1));
    addBeerButton = new JButton("+");
    addBeerButton.setBackground(colBis);
    left.add(labelQuantity);
    left.add(spinnerQuantity);
    left.add(addBeerButton);

    removeBeerButton = new JButton("-");
    removeBeerButton.setBackground(colBis);
    left.add(removeBeerButton);

    labelDays = new JLabel("Livraison dans les ... jours: ");
    labelDays.setHorizontalAlignment(SwingConstants.RIGHT);
    labelDays.setForeground(colText);
    spinnerDays = new JSpinner(new SpinnerNumberModel(7,0,365,1));
    left.add(labelDays);
    left.add(spinnerDays);

    checkPriority = new JCheckBox("Livraison Prioritaire ?");
    checkPriority.setBackground(colBackground);
    checkPriority.setHorizontalAlignment(SwingConstants.RIGHT);
    left.add(checkPriority);

    left.add(new JLabel("")); // gui spacer
    left.add(new JLabel("")); // gui spacer

    saveOrderButton = new JButton("sauvegarder");
    saveOrderButton.setBackground(colBis);
    left.add(saveOrderButton);

    /*Tableau Commande*/
    String column[]={"Bière","Quantité","Prix Unit","Total"};
    table=new JTable(controller.getOrderLines(),column);
    tableStyle();
    table.setOpaque(false);
    sp=new JScrollPane(table);
    sp.setBackground(new Color(0,0,0,0));
    sp.setOpaque(false);
    sp.getViewport().setOpaque(false);
    right.add(sp);

    ClientComboBoxListener listenerClient = new ClientComboBoxListener();
    comboBoxClient.addItemListener(listenerClient);

    BusinessComboBoxListener listenerBusiness = new BusinessComboBoxListener();
    comboBoxBusiness.addItemListener(listenerBusiness);

    ButtonAddListener listenerAddBeer = new ButtonAddListener();
    addBeerButton.addActionListener(listenerAddBeer);

    ButtonRemoveListener listenerRemoveBeer = new ButtonRemoveListener();
    removeBeerButton.addActionListener(listenerRemoveBeer);

    ButtonSaveListener listenerSave = new ButtonSaveListener();
    saveOrderButton.addActionListener(listenerSave);

    this.add(left);
    this.add(right);
  }

  /** Actualise le combobox contenant les adresses de livraison
  *
  * @param index
  *             index du client dans le tableau contenant tous les clients
  * @since 1.2
  */
  private void BusinessComboRefresh(){
    String [] business = null;
    try{
      business = controller.getBusiness();
    }catch(ProgramErrorException error){
        JOptionPane.showMessageDialog (null, error.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
    }
    comboBoxBusiness.removeAllItems();
    for(int i=0;i<business.length;i++){
      comboBoxBusiness.addItem(business[i]);
      }
  }
  public void tableStyle(){
    table.setEnabled(false);
    table.setBackground(colBackground);
    table.setGridColor(colText);
    table.setForeground(colText);
  }

  private String[] loadBeers(){ //TODO RETRY
    String [] beers = null;
      try{
        beers = controller.getBeers();
      }catch(ProgramErrorException error){
          JOptionPane.showMessageDialog (null, "Erreur du chargement des bières","FATAL_ERROR", JOptionPane.ERROR_MESSAGE);
          System.exit(1);
      }
      return beers;
  }

  private String[] loadClients(){//TODO RETRY
    String [] clients = null;
      try{
        clients = controller.getClients();
      }catch(ProgramErrorException error){
          JOptionPane.showMessageDialog (null, "Erreur du chargement des Clients","FATAL_ERROR", JOptionPane.ERROR_MESSAGE);
          System.exit(1);
      }
      return clients;
  }

  private void refreshTable(){ // TODO Find an alternative
    right.remove(sp);
    String column[]={"Bière","Quantité","Prix Unit","Total"};
    table=new JTable(controller.getOrderLines(),column);
    tableStyle();
    sp=new JScrollPane(table);
    right.add(sp);
    right.updateUI();
  }
  /***/
  public class ClientComboBoxListener implements ItemListener {
      public void itemStateChanged(ItemEvent event){
        try{
          controller.selectClient(comboBoxClient.getSelectedIndex());
        }catch(ProgramErrorException error){
            JOptionPane.showMessageDialog (null, error.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
        }
        BusinessComboRefresh();
      }
  }

  public class BusinessComboBoxListener implements ItemListener {
      public void itemStateChanged(ItemEvent event){
        controller.selectBusiness(comboBoxBusiness.getSelectedIndex());//l'index de la selection dans la combobox est recupéré et envoyé au
      }
  }

  private class ButtonAddListener implements ActionListener{
    public void actionPerformed( ActionEvent event) {
      int indexBeer = comboBoxBeer.getSelectedIndex();
      int quantity = (int) spinnerQuantity.getValue();
      try{
        controller.addBeer(indexBeer,quantity);
      }catch(UserInputErrorException error){
          JOptionPane.showMessageDialog (null, error.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
      }catch(ProgramErrorException error){
          JOptionPane.showMessageDialog (null, error.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
      }
      refreshTable();
    }
  }

  private class ButtonRemoveListener implements ActionListener{
    public void actionPerformed( ActionEvent event) {
      controller.removeLastBeer();
      refreshTable();
    }
  }
  private class ButtonSaveListener implements ActionListener{
    public void actionPerformed( ActionEvent event) {
      int numDays = (int) spinnerDays.getValue();
      boolean priority = checkPriority.isSelected();
      try{
        String idRetour =Integer.toString(controller.saveOrder(priority,numDays));
        JOptionPane.showMessageDialog(null, "Votre Identifiant de commande est : "+ idRetour,"Ajout Successfull", JOptionPane.INFORMATION_MESSAGE);
      }catch(UserInputErrorException error){
        JOptionPane.showMessageDialog (null, error.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
      }
    }
  }


}
