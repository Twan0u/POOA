package gui.neworderpanel;

import controller.*;
import exceptions.*;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*; //pour le date format

public class OrderAddForm extends Container{

ControllerNewOrder controller;

private JLabel labelClient, labelBusiness, labelDays, labelDate;
private JComboBox comboBoxClient, comboBoxBusiness;
private JCheckBox checkPriority;
private JSpinner spinnerDate;
private JTextField timeLimit;

  public OrderAddForm(ControllerNewOrder controller,Color colorText){
    this.controller = controller;
    this.setLayout(new GridLayout(5,2,5,5));

    //ComboBox Client
    labelClient = new JLabel("Client : ");
    labelClient.setHorizontalAlignment(SwingConstants.RIGHT);
    labelClient.setForeground(colorText);


    try{
      comboBoxClient = new JComboBox(controller.getClients());
      comboBoxClient.setMaximumRowCount(5);
      comboBoxClient.setSelectedIndex(-1);

      this.add(labelClient);
      this.add(comboBoxClient);

    }catch(ProgramErrorException error){
        JOptionPane.showMessageDialog (null, "Erreur du chargement des Clients","FATAL_ERROR", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }

    //ComboBox Business
    labelBusiness = new JLabel("Business : ");
    labelBusiness.setHorizontalAlignment(SwingConstants.RIGHT);
    labelBusiness.setForeground(colorText);
    String [] initBoxBusiness = {"aucun Client Sélectionné"};
    comboBoxBusiness = new JComboBox(initBoxBusiness);
    comboBoxBusiness.setMaximumRowCount(5);

    this.add(labelBusiness);
    this.add(comboBoxBusiness);

    labelDate = new JLabel("Date de Livraison Souhaitée : ");
    labelDate.setHorizontalAlignment(SwingConstants.RIGHT);
    labelDate.setForeground(colorText);

    SpinnerDateModel model = new SpinnerDateModel();
    JSpinner spinnerDate = new JSpinner(model);

    JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerDate, "dd-MM-yyyy");
    spinnerDate.setEditor(editor);

    this.add(labelDate);
    this.add(spinnerDate);

    labelDays = new JLabel("Livraison maximum ... jours après la date Souhaitée (facultatif) : ");
    labelDays.setHorizontalAlignment(SwingConstants.RIGHT);
    labelDays.setForeground(colorText);
    timeLimit = new JTextField("0");
    this.add(labelDays);
    this.add(timeLimit);

    this.add(new JLabel("")); // grid spacer

    checkPriority = new JCheckBox("Livraison Prioritaire ?");
    //checkPriority.setBackground(colBackground);
    checkPriority.setHorizontalAlignment(SwingConstants.RIGHT);
    this.add(checkPriority);

    ClientComboBoxListener listenerClient = new ClientComboBoxListener();
    comboBoxClient.addItemListener(listenerClient);
  }

  public class ClientComboBoxListener implements ItemListener {
      public void itemStateChanged(ItemEvent event){

        try{
          String [] business = controller.getBusinessOfSelectedClient(comboBoxClient.getSelectedIndex());
          comboBoxBusiness.removeAllItems();
          for(int i=0;i<business.length;i++){
            comboBoxBusiness.addItem(business[i]);
          }
        }catch(ProgramErrorException error){
            JOptionPane.showMessageDialog (null, error.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
        }
      }
  }

  public int getSelectedBusiness(){
    return comboBoxBusiness.getSelectedIndex();
  }
  public String getSelectedDate(){
    return "16-09-1997";
  }
  public String getSelectedTimeLimit(){
    return timeLimit.getText();
  }
  public boolean getSelectedPriority(){
    return checkPriority.isSelected();
  }
}
