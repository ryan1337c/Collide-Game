package Game;

import java.awt.Color;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main extends JFrame implements ActionListener, MouseListener {
	private JButton playBtn;
	private JButton quitBtn;
	private StartUpMenu gameMenu;
	public static JFrame gamePlayFrame; // This JFrame will be called after player finishes each level
	
	public Main(){
		super("Collide"); 
		setBounds(0,0,800,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		gameMenu = new StartUpMenu(); // Creating an instance of the class gameMenu
		gameMenu.setLayout(null);
		gameMenu.setBounds(0, 0, 800, 800);
		playBtn = new JButton("Play");
		playBtn.setFont(new Font("Arial",Font.BOLD, 40)); // Adjusting the font size of the button using the setFont() method
		playBtn.setBounds(250,300,300,50);
		playBtn.setForeground(Color.WHITE);
		playBtn.setBorderPainted(false); // does not paint button border
		playBtn.setContentAreaFilled(false); // does not fill button background
		playBtn.setFocusPainted(false); // does not paint anything on the button when clicked on
		playBtn.addMouseListener(this);
		playBtn.addActionListener(this);
		quitBtn = new JButton("Quit");
		quitBtn.setFont(new Font("Arial",Font.BOLD, 40));
		quitBtn.setBounds(250,430,300,50);
		quitBtn.setForeground(Color.WHITE);
		quitBtn.setBorderPainted(false);
		quitBtn.setContentAreaFilled(false);
		quitBtn.setFocusPainted(false);
		quitBtn.addMouseListener(this);
		quitBtn.addActionListener(this);
		ImageIcon gameTitleIcon = new ImageIcon("Collide.png"); // Creates Image Icon that holds the gameTitle Image
		Image titlePic = gameTitleIcon.getImage(); // Holds gameTitleIcon
		Image newTitlePic = titlePic.getScaledInstance(350, 150, java.awt.Image.SCALE_SMOOTH); // Implements scales/boundaries to titlePic 
		ImageIcon newGameTitleIcon = new ImageIcon(newTitlePic); // New ImageIcon that holds newTitlePic
		JLabel gameTitle = new JLabel(newGameTitleIcon); // Sets a Label to the ImageIcon
		gameTitle.setBounds(150,30,500,160);
		// Adds all the components to gameMenu panel
		gameMenu.add(gameTitle);
		gameMenu.add(playBtn);
		gameMenu.add(quitBtn);
		add(gameMenu);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		callMenu();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == playBtn) {
			this.dispose(); // disposes/closes the JFrame that holds the StartUpMenu so that a new JFrame can be created for game play
			gameMenu.setVisible(false); // Erases Game Menu when player presses play
			gamePlayFrame = new JFrame(); // Created a new JFrame for the GamePlay1 class
			GamePlay1 level1 = new GamePlay1();
			gamePlayFrame.setBounds(0,0,800,800);
			gamePlayFrame.setTitle("Collide");
			gamePlayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gamePlayFrame.setResizable(false);
			gamePlayFrame.add(level1);
			gamePlayFrame.setVisible(true);
		}
		if (e.getSource() == quitBtn) {
			System.exit(0); // Closes window 
		}
		
	}
	/*
	 * Purpose is to created an instance of this class that will create the main menu and it also allows for
	 * the GamePlay3 class to return back to the main menu  (when the player beats the game) by calling this static method
	 * pre: none
	 */
	public static void callMenu() {
		new Main();
	}
	@Override
	public void mouseClicked(MouseEvent e) {	
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// Outlines button (paints border) when hovered over
		if (e.getSource() == playBtn) {
			playBtn.setBorderPainted(true);
		} else {
			quitBtn.setBorderPainted(true);
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// Erases button outlines when mouse cursor is not in it
		if (e.getSource() == playBtn) {
			playBtn.setBorderPainted(false);
		} else {
			quitBtn.setBorderPainted(false);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
