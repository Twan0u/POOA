package gui;

import javax.swing.*;

import java.awt.*;

import static java.lang.Thread.sleep;

public class CatchMeThread extends JPanel implements Runnable{

    private Integer[] coords = new Integer[2];
    private JButton catchMeButton;

    public CatchMeThread(){
        this.setMinimumSize(new Dimension(600, 600));
        catchMeButton = new JButton("Click me if you can");
        catchMeButton.setSize(2,2);
        catchMeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        catchMeButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        this.add(catchMeButton);
    }

    public void run(){
        coords[0] = this.getMaximumSize().width;
        coords[1] = this.getMaximumSize().height;
        try {
            sleep(250);
        } catch (InterruptedException error) {
            JOptionPane.showMessageDialog (null, "Le thread n'est pas fatigué et ne veut pas aller dormir","ERREUR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
