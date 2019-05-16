package gui;

import controller.*;
import exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ModifyPanel extends Container{

  JTextField IdOrder;
  JButton recherche, sauvegarder, supprimer;

  private JPanel panel,topPanel;

  private JLabel labelClient, labelBusiness, labelDays, labelDate;
  private JComboBox comboBoxClient, comboBoxBusiness;
  private JCheckBox checkPriority;
  private JSpinner spinnerDate;
  private JTextField timeLimit;

  public ModifyPanel(){
    this.setLayout(new BorderLayout());

    topPanel = new JPanel();
    IdOrder = new JTextField("XXXX");
    topPanel.setLayout(new FlowLayout());
    topPanel.add(new JLabel("Identifiant de la commande à modifier"));
    topPanel.add(IdOrder);
    recherche = new JButton("Rechercher la commande");
    topPanel.add(recherche);

    this.add(topPanel,BorderLayout.NORTH);

    panel = new JPanel();
    panel.setLayout(new GridLayout(6,2,5,5));
    //ComboBox Client
    labelClient = new JLabel("Client : ");
    labelClient.setHorizontalAlignment(SwingConstants.RIGHT);
    comboBoxClient = new JComboBox();//loadClients());
    comboBoxClient.setMaximumRowCount(5);
    comboBoxClient.setSelectedIndex(-1);

    panel.add(labelClient);
    panel.add(comboBoxClient);

    //ComboBox Business
    labelBusiness = new JLabel("Business : ");
    labelBusiness.setHorizontalAlignment(SwingConstants.RIGHT);
    comboBoxBusiness = new JComboBox();
    comboBoxBusiness.setMaximumRowCount(5);
    //BusinessComboRefresh();

    panel.add(labelBusiness);
    panel.add(comboBoxBusiness);

    labelDate = new JLabel("Date de Livraison Prévue : ");
    labelDate.setHorizontalAlignment(SwingConstants.RIGHT);
    spinnerDate = new JSpinner();
    panel.add(labelDate);
    panel.add(spinnerDate);

    labelDays = new JLabel("Livraison deans les X jours après la date Prévue: ");
    labelDays.setHorizontalAlignment(SwingConstants.RIGHT);
    timeLimit = new JTextField("");
    panel.add(labelDays);
    panel.add(timeLimit);

    panel.add(new JLabel("")); // grid spacer

    checkPriority = new JCheckBox("Livraison Prioritaire ?");
    //checkPriority.setBackground(colBackground);
    checkPriority.setHorizontalAlignment(SwingConstants.RIGHT);
    panel.add(checkPriority);
    sauvegarder = new JButton("sauvegarder la commande");
    panel.add(sauvegarder);

    supprimer = new JButton("supprimer la commande");
    panel.add(supprimer);

    this.add(panel,BorderLayout.CENTER);


      /*ClientComboBoxListener listenerClient = new ClientComboBoxListener();
    comboBoxClient.addItemListener(listenerClient);

    BusinessComboBoxListener listenerBusiness = new BusinessComboBoxListener();
    comboBoxBusiness.addItemListener(listenerBusiness);*/
  }

/*
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
  private String[] loadClients(){ //TODO clients can't be null
    String [] clients = null;
      try{
        clients = controller.getClients();
      }catch(ProgramErrorException error){
          JOptionPane.showMessageDialog (null, "Erreur du chargement des Clients","FATAL_ERROR", JOptionPane.ERROR_MESSAGE);
          System.exit(1);
      }
      return clients;
  }

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
  }*/
}
