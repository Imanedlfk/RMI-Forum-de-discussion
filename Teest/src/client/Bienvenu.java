package client;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Bienvenu extends JFrame implements ActionListener {

    private JButton button;
    private JLabel label;
    
    public Bienvenu() {
    	 super("Chat Application");
    	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	    setPreferredSize(new Dimension(600, 400));
    	    
    	    // Create the panel to hold the buttons and logo
    	    JPanel panel = new JPanel(new BorderLayout());
    	    panel.setBackground(new Color(238, 238, 238));
    	    
    	    // Create the logo icon and label
    	    /*ImageIcon logoIcon = new ImageIcon("chat-logo.png");
    	    Image logoImage = logoIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
    	    logoIcon = new ImageIcon(logoImage);
    	    JLabel logoLabel = new JLabel(logoIcon);
    	    panel.add(logoLabel, BorderLayout.CENTER);*/
    	    
    	    // Create the buttons panel
    	    JPanel buttonsPanel = new JPanel();
    	    buttonsPanel.setBackground(new Color(238, 238, 238));
    	    buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    	    
    	    // Create the buttons
    	    JButton connectButton = new JButton("Connect");
    	    connectButton.setFont(new Font("SansSerif", Font.BOLD, 14));
    	    connectButton.setForeground(Color.WHITE);
    	    connectButton.setBackground(new Color(0, 176, 240)); // blue-green color
    	    connectButton.addActionListener(this);
    	    buttonsPanel.add(connectButton);
    	    
    	    
    	    
    	    
    	    panel.add(buttonsPanel, BorderLayout.SOUTH);
    	    
    	    // Add the panel to the frame
    	    add(panel, BorderLayout.SOUTH);
    	    
    	    // Create and add the title label to the frame
    	    JLabel titleLabel = new JLabel("<< Welcome to Chat >>");
    	    titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
    	    titleLabel.setForeground(new Color(0, 176, 240)); // blue-green color
    	    titleLabel.setHorizontalAlignment(JLabel.CENTER);
    	    add(titleLabel, BorderLayout.CENTER);
    	    
    	    pack();
    	    setLocationRelativeTo(null);
    	    setVisible(true);
    	}
    @Override
    public void actionPerformed(ActionEvent e) {
        
        	new UserImp();
        
    }
    
    public static void main(String[] args) {
    	Bienvenu chat = new Bienvenu();
    }
}
