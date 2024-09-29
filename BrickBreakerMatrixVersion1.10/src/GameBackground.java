package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GameBackground extends JFrame{
	
	    public GameBackground() {
		    //this class uses Jframes to create the background of the game
	    	
	        setSize(1200,800);
	        setVisible(true);

	        setLayout(new BorderLayout());

	        JLabel background=new JLabel(new ImageIcon("C:\\Users\\HP\\eclipse-workspace\\Matrix110shumi\\src\\game\\Purple Space Matrix.png"));
	        add(background);

	        background.setLayout(new FlowLayout());

	    }
}
    
