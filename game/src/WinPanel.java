import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class WinPanel extends JPanel implements ActionListener{
    Image dancingKnight;
    Timer timer;
    int xPosition = 0;
    int moveX = 1;

    public WinPanel(){
        this.setBackground(new Color(0,0,0,150));
        dancingKnight = new ImageIcon("Images/DancingKnight.png").getImage();
        timer = new Timer(1, this);
        timer.start();
    }

    //Method used to paint the dancing knight image
    public void paint(Graphics graphic){
        super.paint(graphic);
        Graphics2D graphic2D = (Graphics2D) graphic;
        graphic2D.drawImage(dancingKnight, xPosition, 0, null);
        graphic2D.drawImage(dancingKnight, xPosition+200, 0, null);
    }

    public void actionPerformed(ActionEvent e){
        if(xPosition+moveX >= 850){moveX = -2;}
        if(xPosition-moveX <= 0){moveX = 2;}

        xPosition += moveX;
        repaint();
    }
}
