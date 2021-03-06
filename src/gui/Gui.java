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

    this.controller = new Controller();

    JMenuBar MyMenu = new JMenuBar();
    MyMenu.setBounds(0,0,SIZE_FRAME_WIDTH,30);

    JMenu MenuCommande = new JMenu("Commande");

    JMenuItem MenuNouvelleCommande = new JMenuItem("Nouvelle");
    JMenuItem MenuModifierCommande = new JMenuItem("Modifier");
    JMenuItem MenuRechercheCommande = new JMenuItem("Recherche");


    JMenu MenuStock = new JMenu("Stock");
    JMenu MenuLivraison = new JMenu("Livraison");
    JMenu MenuStatistiques = new JMenu("Statistiques");

    MyMenu.add(MenuCommande);
      MenuCommande.add(MenuNouvelleCommande);
      MenuCommande.add(MenuModifierCommande);
      MenuCommande.add(MenuRechercheCommande);
    MyMenu.add(MenuStock);
    MyMenu.add(MenuLivraison);
    MyMenu.add(MenuStatistiques);

    JMenu MenuScamThread = new JMenu("Scam Thread");
    MyMenu.add(MenuScamThread);

    mainPanel = new OrderPanel(controller, colBackground, colText, colBis);

    this.setJMenuBar(MyMenu);
    this.add(mainPanel);

    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


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
                            changeMainPanel(new DeliveryPanel());
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

      MenuScamThread.addMouseListener(new MouseListener() {
          @Override
          public void mouseReleased(MouseEvent e) {}
          @Override
          public void mousePressed(MouseEvent e) {
              if(JOptionPane.showConfirmDialog (null, "Spam ZONE (ALT+F4) will be your best friend ","Warning",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                  CatchMeThread thread1 = new CatchMeThread();
                  thread1.start();
                  CatchMeThread thread2 = new CatchMeThread();
                  thread2.start();
                  CatchMeThread thread3 = new CatchMeThread();
                  thread3.start();
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
