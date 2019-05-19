package gui.neworderpanel;

import controller.*;
import exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BeerAddForm extends Container{

Controller controller;

private JLabel labelBeer, labelQuantity;
private JComboBox comboBoxBeer;
private JSpinner spinnerQuantity;
private JButton addBeerButton, removeBeerButton;
private BeerTable beerTable;

  public BeerAddForm(Controller controller, BeerTable beerTable){
    this.controller = controller;
    this.beerTable = beerTable;

    this.setLayout(new GridLayout(3,3,5,5));

    //ComboBox Beer
    labelBeer = new JLabel("Ajouter à la commande: ");
    labelBeer.setHorizontalAlignment(SwingConstants.RIGHT);
    //labelBeer.setForeground(colText);
    comboBoxBeer = new JComboBox(loadBeers());
    comboBoxBeer.setMaximumRowCount(5);
    this.add(labelBeer);
    this.add(comboBoxBeer);

    //Jspinner quantity
    labelQuantity = new JLabel("Quantité: ");
    labelQuantity.setHorizontalAlignment(SwingConstants.RIGHT);
    //labelQuantity.setForeground(colText);
    spinnerQuantity = new JSpinner(new SpinnerNumberModel(1,0,100000000,1));
    addBeerButton = new JButton("Ajouter à la commande");
    //addBeerButton.setBackground(colBis);
    this.add(labelQuantity);
    this.add(spinnerQuantity);
    this.add(addBeerButton);

    removeBeerButton = new JButton("Supprimmer la dernière ligne de la commande");
    //removeBeerButton.setBackground(colBis);
    this.add(removeBeerButton);

    ButtonAddListener listenerAddBeer = new ButtonAddListener();
    addBeerButton.addActionListener(listenerAddBeer);

    ButtonRemoveListener listenerRemoveBeer = new ButtonRemoveListener();
    removeBeerButton.addActionListener(listenerRemoveBeer);
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

      private class ButtonAddListener implements ActionListener{
        public void actionPerformed( ActionEvent event) {
          int indexBeer = comboBoxBeer.getSelectedIndex();
          int quantity = (int) spinnerQuantity.getValue();
          /*try{
            controller.addBeer(indexBeer,quantity);
          }catch(UserInputErrorException error){
              JOptionPane.showMessageDialog (null, error.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
          }catch(ProgramErrorException error){
              JOptionPane.showMessageDialog (null, error.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
          }*/
          beerTable.refreshTable();
        }
      }

      private class ButtonRemoveListener implements ActionListener{
        public void actionPerformed( ActionEvent event) {
          if(JOptionPane.showConfirmDialog (null, "êtes-vous sur de vouloir supprimmer cette bière de la commande? ","Warning",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            //controller.removeLastBeer();
            beerTable.refreshTable();
          }
        }
      }
}
