package client;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;

import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import serveur.Forum;
import serveur.proxy;
import serveur.proxyImpl;


public class UserImp implements User,ActionListener {
	
	public JTextArea		text; // used to print out chat messages
    public JTextField	        data; // used to enter chat messages
    public JFrame 		frame;
    JButton connect_button,leave_button,who_button,write_button;
    public Forum frm = null; 	// reference to the forum remote object
	int id;
	String nom;
	
	
	
	public UserImp() {
    super();
    frame = new JFrame("Chat Application");
    frame.setLayout(new BorderLayout());

    // Create the chat text area
    text = new JTextArea();
    text.setEditable(false);
    text.setForeground(Color.WHITE);
    text.setBackground(new Color(33, 33, 33)); // dark gray color
    text.setFont(new Font("Times New Roman", Font.PLAIN, 14));
    JScrollPane scrollPane = new JScrollPane(text);
    frame.add(scrollPane, BorderLayout.CENTER);

    // Create the input field and send button
    JPanel inputPanel = new JPanel(new BorderLayout(10, 10)); // add spacing between components
    data = new JTextField();
    data.setFont(new Font("Times New Roman", Font.PLAIN, 14));
    inputPanel.add(data, BorderLayout.CENTER);

    write_button = new JButton("Send");
    write_button.setFont(new Font("Times New Roman", Font.BOLD, 14));
    write_button.setEnabled(false);
    write_button.addActionListener(this);
    write_button.setBackground(new Color(0, 176, 240)); // blue-green color

    inputPanel.add(write_button, BorderLayout.EAST);
    frame.add(inputPanel, BorderLayout.SOUTH);

    // Create the connection buttons
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 60, 9)); // add spacing between components
    connect_button = new JButton("Connect");
    connect_button.setFont(new Font("Times New Roman", Font.BOLD, 14));
    connect_button.addActionListener(this);
    connect_button.setBackground(new Color(0, 176, 240)); // blue-green color
    buttonPanel.add(connect_button);

    who_button = new JButton("Who");
    who_button.setFont(new Font("Times New Roman", Font.BOLD, 14));
    who_button.setEnabled(false);
    who_button.addActionListener(this);
    who_button.setBackground(new Color(0, 176, 240)); // blue-green color
    buttonPanel.add(who_button);

    leave_button = new JButton("Leave");
    leave_button.setFont(new Font("Times New Roman", Font.BOLD, 14));
    leave_button.setEnabled(false);
    leave_button.addActionListener(this);
    leave_button.setBackground(new Color(0, 176, 240)); // blue-green color
    buttonPanel.add(leave_button);

    frame.add(buttonPanel, BorderLayout.NORTH);

    // Set colors
    frame.setBackground(new Color(245, 245, 245)); // light gray color
    inputPanel.setBackground(Color.WHITE);
    //buttonPanel.setBackground(new Color(0, 136, 204)); // dark blue-green color

    // Align buttons to the right
    buttonPanel.add(Box.createHorizontalGlue());

    frame.setSize(600, 400);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    });
    frame.setVisible(true);
}

	
	
	
	
	
	
	
	
	@Override
	public void ecrire(String msg) {
		// TODO Auto-generated method stub
		try {
    		this.text.append(msg+"\n");
    	} catch (Exception ex) {
			ex.printStackTrace();
			return;
		}
		
	}
	
	
	
	
	
	
	
	
	UserImp(int id,String nom)
		{
			super();
			this.id=id;
			this.nom=nom;
		}
	

	
	
	
	
	
	
	
	
	//BUTTONS
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() ==connect_button) {
			 try {
		            this.frm= (Forum) Naming.lookup("rmi://localhost:1298/Server");
		            proxy proxy = new proxyImpl(this);
		            this.id = this.frm.entrer(proxy);
		            connect_button.setEnabled(false);
		           leave_button.setEnabled(true);
		           who_button.setEnabled(true);
		           write_button.setEnabled(true);
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    
		}
		if (e.getSource() == who_button) {
		    try {
		        String users = this.frm.qui();
		        // Create a new panel to show the list of users
		        JPanel panel = new JPanel();
		        panel.setLayout(new BorderLayout());

		        // Create a label to show the title
		        JLabel titleLabel = new JLabel("List of Users:");
		        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		        panel.add(titleLabel, BorderLayout.NORTH);

		        // Create a text area to show the list of users
		        JTextArea userArea = new JTextArea();
		        userArea.setEditable(false);
		        userArea.setText(users);
		        panel.add(userArea, BorderLayout.CENTER);

		        // Show the panel in a dialog box
		        JOptionPane.showMessageDialog(frame, panel, "Users", JOptionPane.INFORMATION_MESSAGE);
		    } catch (RemoteException e1) {
		        e1.printStackTrace();
		    }
		}

		if(e.getSource() ==leave_button){
			int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to leave?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
			    try {
			        this.frm.quiter(this.id);
			    } catch (RemoteException e1) {
			        e1.printStackTrace();
			    }
			    connect_button.setEnabled(true);
			    leave_button.setEnabled(false);
			    who_button.setEnabled(false);
			    write_button.setEnabled(false);
			}
			}
		
		if(e.getSource() ==write_button){
			try {
				this.frm.dire(this.id,data.getText());
				data.setText("");
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	
    }
}



























