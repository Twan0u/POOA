package gui;

import controller.*;
import exceptions.*;

import javax.swing.*;
import java.awt.event.*;

public class OrderGui extends JFrame{

  private InterfaceController controller = new Controller();

  private JPanel pannel;
  private JLabel labelClient, labelBusiness, labelBeer;
  private JComboBox comboBoxClient, comboBoxBusiness, comboBoxBeer;
  private JSpinner spinnerQuantity;
  private JButton addBeerButton;
  private JTable table;

  public OrderGui() {
    super("Outil de Création de Commandes");
    setSize(800,400);
    setResizable(true);
    pannel = new JPanel();

    /*ComboBox Client*/
    labelClient = new JLabel("Client : ");
    comboBoxClient = new JComboBox(loadClients());
    comboBoxClient.setSelectedIndex(-1);
    pannel.add(labelClient);
    pannel.add(comboBoxClient);

    /*ComboBox Business*/
    labelBusiness = new JLabel("Business : ");
    comboBoxBusiness = new JComboBox();
    BusinessComboRefresh();
    pannel.add(labelBusiness);
    pannel.add(comboBoxBusiness);

    /*ComboBox Beer*/
    labelBeer = new JLabel("Ajouer : ");
    comboBoxBeer = new JComboBox(loadBeers());
    pannel.add(labelBeer);
    pannel.add(comboBoxBeer);

    /*Jspinner quantity*/
    SpinnerModel model = new SpinnerNumberModel(0,0,100000000,1);
    spinnerQuantity = new JSpinner(model);
    addBeerButton = new JButton("ajouter");
    pannel.add(spinnerQuantity);
    pannel.add(addBeerButton);

    /*Tableau Commande*/
    String column[]={"Bière","Quantité","Prix Unit","Total"};
    table=new JTable(controller.getOrderLines(),column);
    table.setEnabled(false);
    table.setBounds(5,10,100,200);
    JScrollPane sp=new JScrollPane(table);
    pannel.add(sp);


    add(pannel);
    setVisible(true);

    ClientComboBoxListener listenerClient = new ClientComboBoxListener();
    comboBoxClient.addItemListener(listenerClient);

    BusinessComboBoxListener listenerBusiness = new BusinessComboBoxListener();
    comboBoxBusiness.addItemListener(listenerBusiness);

    ButtonListener listenerAddBeer = new ButtonListener();
    addBeerButton.addActionListener(listenerAddBeer);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

  private class ButtonListener implements ActionListener{
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
      //table.setData(controller.getOrderLines());
      new  OrderGui();
    }
  }

}
