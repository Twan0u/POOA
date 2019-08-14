package gui;

import controller.*;
import exceptions.*;
import composants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gui extends JFrame{

  private Controller controller;
  private int SIZE_FRAME_WIDTH = 1080;
  private int SIZE_FRAME_HEIGHT = 720;

  private JFrame me;

  private JPanel mainPanel;

  private Color colText = new Color(29, 34, 40);
  private Color colBis = new Color(250,229,150);
  private Color colBackground = new Color(224,255,255);
  private Color colSidePanel = new Color(63,176,172);

  private String path;

  public Gui(String path) {

    super("BrassiGestion");
    this.path = path;
    setSize(SIZE_FRAME_WIDTH,SIZE_FRAME_HEIGHT);
    setResizable(true);

    me = this;

    this.setLayout(new FlowLayout());

    int [] loadValue = new int[1];
    loadValue[0]=1;//Begin to load The Program
    ThreadLoad threadX = new ThreadLoad(loadValue);
    Thread thread = new Thread(threadX);
    thread.start();

    this.controller = new Controller();

    JMenuBar MyMenu = new JMenuBar();
    MyMenu.setBounds(0,0,SIZE_FRAME_WIDTH,30);

    JMenu MenuCommande = new JMenu("Commande");

    JMenuItem MenuNouvelleCommande = new JMenuItem("Nouvelle");
    JMenuItem MenuModifierCommande = new JMenuItem("Modifier");
    JMenuItem MenuRechercheCommande = new JMenuItem("Recherche");

    JMenu MenuClient = new JMenu("Client");

    JMenuItem MenuRechercheClient = new JMenuItem("Recherche");

    JMenu MenuStock = new JMenu("Stock");
    JMenu MenuLivraison = new JMenu("Livraison");
    JMenu MenuStatistiques = new JMenu("Statistiques");

    MyMenu.add(MenuCommande);
      MenuCommande.add(MenuNouvelleCommande);
      MenuCommande.add(MenuModifierCommande);
      MenuCommande.add(MenuRechercheCommande);
    MyMenu.add(MenuClient);
      MenuClient.add(MenuRechercheClient);
    MyMenu.add(MenuStock);
    MyMenu.add(MenuLivraison);
    MyMenu.add(MenuStatistiques);

    mainPanel = new OrderPanel(controller, colBackground, colText, colBis);

    this.setJMenuBar(MyMenu);
    this.add(mainPanel);

    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    loadValue[0]=0;// finished to load*/

    MenuNouvelleCommande.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent e){
        JPanel newPanel =  new OrderPanel(controller, colBackground, colText, colBis);
        changeMainPanel(newPanel);
      }
    });
    MenuModifierCommande.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent e){
        //JPanel newPanel =  new OrderPanel(controller, colBackground, colText, colBis);
        //changeMainPanel(newPanel);

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
    MenuRechercheCommande.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent e){
          if(JOptionPane.showConfirmDialog (null, "êtes-vous sur de vouloir quitter? ","Warning",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
              changeMainPanel(new SearchPanel());
          }
      }
    });
    MenuRechercheClient.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent e){
        JOptionPane.showMessageDialog(null,"La Recherche de Clients n'est pas encore implémentée");
      }
    });
    MenuStock.addMouseListener(new MouseListener() {
                      @Override
                      public void mouseReleased(MouseEvent e) {}
                      @Override
                      public void mousePressed(MouseEvent e) {
                        if(JOptionPane.showConfirmDialog (null, "êtes-vous sur de vouloir quitter? ","Warning",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                                        changeMainPanel(new StockPanel(colBackground, colText));
                        }
                      }
                      @Override
                      public void mouseExited(MouseEvent e) {}
                      @Override
                      public void mouseEntered(MouseEvent e) {}
                        @Override
                        public void mouseClicked(MouseEvent e) {}
            });

    MenuLivraison.addMouseListener(new MouseListener() {
                      @Override
                      public void mouseReleased(MouseEvent e) {}
                      @Override
                      public void mousePressed(MouseEvent e) {
                        if(JOptionPane.showConfirmDialog (null, "êtes-vous sur de vouloir quitter? ","Warning",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                          JOptionPane.showMessageDialog(null,"la gestion des livraisons n'est pas encore implémentée");
                        }
                      }
                      @Override
                      public void mouseExited(MouseEvent e) {}
                      @Override
                      public void mouseEntered(MouseEvent e) {}
                        @Override
                        public void mouseClicked(MouseEvent e) {}
            });
    MenuStatistiques.addMouseListener(new MouseListener() {
                      @Override
                      public void mouseReleased(MouseEvent e) {}
                      @Override
                      public void mousePressed(MouseEvent e) {
                        if(JOptionPane.showConfirmDialog (null, "êtes-vous sur de vouloir quitter? ","Warning",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                          changeMainPanel(new StatsPanel());
                        }
                      }
                      @Override
                      public void mouseExited(MouseEvent e) {}
                      @Override
                      public void mouseEntered(MouseEvent e) {}
                        @Override
                        public void mouseClicked(MouseEvent e) {}
            });


  }//end of Constructor Gui()

/** Modification de la fenêtre actuelle de l'application
*/
  public void changeMainPanel(JPanel newPanel){
       getContentPane().removeAll();
       getContentPane().add(newPanel, BorderLayout.CENTER);
       getContentPane().doLayout();
       update(getGraphics());
       setVisible(true);
  }
}//end of Class Gui
