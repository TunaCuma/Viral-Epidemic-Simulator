import java.awt.*;

import javax.swing.*;

/**
 * GameFrame
 */
public class GameFrame extends JFrame{
    Simulation panel ;

    GameFrame(){
        panel = new Simulation();
        this.add(panel);
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);//Appear middle on the screen

    }   
    
}