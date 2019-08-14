package gui.neworderpanel;

import controller.*;
import exceptions.*;
import composants.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OrderAddForm extends Container{

Controller controller;
ArrayList<Client> allClients;
ArrayList<BusinessUnit> business;

private JLabel labelClient, labelBusiness, labelDays, labelDate;
private JComboBox comboBoxClient, comboBoxBusiness;
private JCheckBox checkPriority;
private JSpinner spinnerDate;
private JTextField timeLimit;

  public OrderAddForm(Controller controller,Color colorText){
    this.controller = controller;
    this.setLayout(new GridLayout(5,2,5,5));

    //ComboBox Client
    labelClient = new JLabel("Client : ");
    labelClient.setHorizontalAlignment(SwingConstants.RIGHT);
    labelClient.setForeground(colorText);

    comboBoxClient = new JComboBox();
    loadClientCombo();
    comboBoxClient.setMaximumRowCount(5);
    comboBoxClient.setSelectedIndex(-1);

    this.add(labelClient);
    this.add(comboBoxClient);

    //ComboBox Business
    labelBusiness = new JLabel("Business : ");
    labelBusiness.setHorizontalAlignment(SwingConstants.RIGHT);
    labelBusiness.setForeground(colorText);

    String [] initBoxBusiness = {"Aucun client sélectionné"};
    comboBoxBusiness = new JComboBox(initBoxBusiness);
    comboBoxBusiness.setEnabled(false);
    comboBoxBusiness.setMaximumRowCount(5);

    this.add(labelBusiness);
    this.add(comboBoxBusiness);

    labelDate = new JLabel("Date de Livraison Souhaitée : ");
    labelDate.setHorizontalAlignment(SwingConstants.RIGHT);
    labelDate.setForeground(colorText);

    SpinnerDateModel model = new SpinnerDateModel();
    spinnerDate = new JSpinner(model);
    spinnerDate.setEditor(new JSpinner.DateEditor(spinnerDate, "yyyy-MM-dd"));
    JFormattedTextField tf1 = ((JSpinner.DefaultEditor) spinnerDate.getEditor()).getTextField();
    tf1.setEditable(false);
    this.add(labelDate);
    this.add(spinnerDate);

    labelDays = new JLabel("Délai acceptable de  ... jours après la date douhaitée (facultatif) : ");
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

  public void loadClientCombo(){
    try{
      allClients = controller.getAllClients();
      if (allClients !=null){
        for(int i=0;i<allClients.size();i++){
          Client current = allClients.get(i);
          comboBoxClient.addItem(current.getName() + "-" + current.getId());
        }
      }else {
        //TODO print message clients not found or no clients
      }
    }catch(ProgramErrorException error){
        JOptionPane.showMessageDialog (null, "Erreur du chargement des Clients","FATAL_ERROR", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }catch(Exception ignore){
      //TODO
    }
  }

  public class ClientComboBoxListener implements ItemListener {
      public void itemStateChanged(ItemEvent event){
        try{
        int idClient = allClients.get(comboBoxClient.getSelectedIndex()).getId();
         business = controller.getBusinessOf(idClient);
          comboBoxBusiness.removeAllItems();
          if (business.size() == 0){
            comboBoxBusiness.addItem("Aucune Adresse De Livraison");
            comboBoxBusiness.setEnabled(false);
          }else{
            comboBoxBusiness.addItem("Pas de Livraison");
            for(int i=0;i<business.size();i++){
              comboBoxBusiness.addItem(business.get(i).getStreetName());
            }
          comboBoxBusiness.setEnabled(true);
        }
        }catch(ProgramErrorException error){
            JOptionPane.showMessageDialog (null, error.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
        }
      }
  }
  public Client getSelectedClient(){
    int index = comboBoxClient.getSelectedIndex();
    if (index >= 0){
      return allClients.get(index);
    }else{
      return null;
    }
  }

  public BusinessUnit getSelectedBusiness(){
    int index = comboBoxBusiness.getSelectedIndex();
    if (index == 0){
      return null;
    }else{
      return business.get(index-1);
    }
  }
  public String getSelectedDate(){
    Date date = (Date)spinnerDate.getValue();
    LocalDate formattedDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    return formattedDate.getYear() + "-" + formattedDate.getMonthValue() + "-" + formattedDate.getDayOfMonth();
  }
  public int getSelectedTimeLimit()throws UserInputErrorException{
    try{
      return Integer.parseInt(timeLimit.getText());
    }catch(NumberFormatException error){
      throw new UserInputErrorException("La valeur que vous venez de rentrer pour le nombre de jours pour effectuer la livraison est incorrect");
    }
  }
  public boolean getSelectedPriority(){
    return checkPriority.isSelected();
  }
}
