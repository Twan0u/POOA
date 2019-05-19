package gui;

import controller.*;
import exceptions.*;
import composants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gui extends JFrame{

  private Controller controller;
  private Container frameContainer,  mainPanel;
  private JPanel menuPanel;

  private JLabel logo, commande, modifier, recherche,rechercheClient, stock, livraison;

  private Color colText = new Color(29, 34, 40);
  private Color colBis = new Color(250,229,150);
  private Color colBackground = new Color(224,255,255);
  private Color colSidePanel = new Color(63,176,172);

  private String path;

  public Gui(String path) {
    super("BrassiGestion");
    this.path = path;
    setSize(1080,720);
    setResizable(true);

    int [] loadValue = new int[1];
    loadValue[0]=1;//Begin to load The Program
    ThreadLoad threadX = new ThreadLoad(loadValue);
    Thread thread = new Thread(threadX);
    thread.start();

    this.controller = new Controller();

    menuPanel = new JPanel();
    menuPanel.setPreferredSize(new Dimension(125, 100));
    menuPanel.setBackground(colSidePanel);

    loadMenuPanel();

    mainPanel = new OrderPanel(controller, colBackground, colText, colBis);

    frameContainer = this.getContentPane();
    frameContainer.setLayout(new BorderLayout());
    frameContainer.add(menuPanel, BorderLayout.WEST );
    frameContainer.add(mainPanel, BorderLayout.CENTER );

    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    loadValue[0]=0;// finished to load
  }

  public void loadMenuPanel(){

        ImageIcon icon = new ImageIcon(path + "logo.png");
        logo = new JLabel(icon);

        ImageIcon commandeIcon = new ImageIcon(path + "commande.png");
        commande = new JLabel(commandeIcon);

        ImageIcon modifierIcon = new ImageIcon(path + "edit.png");
        modifier = new JLabel(modifierIcon);

        ImageIcon rechercheIcon = new ImageIcon(path + "search.png");
        recherche = new JLabel(rechercheIcon);

        ImageIcon rechercheClientIcon = new ImageIcon(path + "user.png");
        rechercheClient = new JLabel(rechercheClientIcon);

        ImageIcon stockIcon = new ImageIcon(path + "stock.png");
        stock = new JLabel(stockIcon);

        ImageIcon livraisonIcon = new ImageIcon(path + "livraison.png");
        livraison = new JLabel(livraisonIcon);

        menuPanel.setLayout(new GridLayout(7,1));
        menuPanel.add(logo);
        menuPanel.add(commande);
        menuPanel.add(modifier);
        menuPanel.add(recherche);
        menuPanel.add(rechercheClient);
        menuPanel.add(stock);
        menuPanel.add(livraison);

        // Création d'une Commande
        commande.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
              changeMainPanel(new OrderPanel(controller, colBackground, colText, colBis));
            }
        });

        // Interface de Modification de commande
        modifier.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
              String s = (String)JOptionPane.showInputDialog(null,"Identifiant de la commande à modifier","Selectionner l'Identifiant", JOptionPane.QUESTION_MESSAGE);
              if ((s != null) && (s.length() > 0)){
                try{
                    Order order = controller.getOrder(Integer.parseInt(s));
                  if (order!=null){
                    changeMainPanel(new ModifyPanel(controller, order));
                  }else{
                    throw new ProgramErrorException("Aucune commande n'a pu être chargée");//order not found
                  }
                }catch(NumberFormatException error){
                  JOptionPane.showMessageDialog(null,"Le numéro d'identification est incorrect");
                }catch(ProgramErrorException erreur){
                  JOptionPane.showMessageDialog(null,"Aucune Commande n'est liée avec ce numéro d'identification");
                }
              }else{
                JOptionPane.showMessageDialog(null,"Il n'y a aucune commande sélectionnée");
              }
            }
        });

        //Interface de recherche des commandes
        recherche.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
              if(JOptionPane.showConfirmDialog (null, "êtes-vous sur de vouloir quitter? ","Warning",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                changeMainPanel(new SearchPanel());
             }
            }
        });

        //rechercher les commandes effectuées par un client
        rechercheClient.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
              if(JOptionPane.showConfirmDialog (null, "êtes-vous sur de vouloir quitter? ","Warning",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                changeMainPanel(new SearchOrderByClientPanel());
              }
            }
        });


        //interface d'affichage du stock
        stock.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
              if(JOptionPane.showConfirmDialog (null, "êtes-vous sur de vouloir quitter? ","Warning",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                changeMainPanel(new StockPanel(colBackground, colText));
              }
            }
        });

        //interface permettant aux livreurs de sélectionner toutes les commandes à livrer par localité
        livraison.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
              if(JOptionPane.showConfirmDialog (null, "êtes-vous sur de vouloir quitter? ","Warning",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                changeMainPanel(new DeliveryPanel());
             }
            }
        });
  }
  public void changeMainPanel(Container newPanel){
       frameContainer.removeAll();
       frameContainer.add(menuPanel, BorderLayout.WEST );
       frameContainer.add(newPanel, BorderLayout.CENTER );
       frameContainer.revalidate();
  }

}
