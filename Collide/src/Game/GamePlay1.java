package Game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.Timer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;

public class GamePlay1 extends JPanel implements KeyListener, ActionListener, MouseListener {
	private int score; // player score
	private int numBricks; // keeps track of bricks
	private int platFormX, platFormY; // x/y position of platform
	private int ballX, ballY; // x/y position of the moving ball
	private int ballVelX, ballVelY; // direction the ball moves and its velocity
	private int delay; // controls the speed of the ball through time interval delay
	private Timer time; // thread
	private boolean isPlayerPlaying; // checks to see if player is playing
	private MapGenerator mapBrick; // object for making the map
	private int row, col; // numbers of rows and columns of bricks
	private Rectangle rect; // used for drawing the rectangles
	private JLabel showScore; // label for player's score
	private JLabel showLevel1; // label for game level
	private JButton giveUpBtn, restartBtn; // post game buttons
	private Image background; // game play background
	private Audio musicObject;

	public GamePlay1() {
		// Using tool kit class implement and scale game play background
		Toolkit kit = Toolkit.getDefaultToolkit(); 
		background = kit.getImage("arcadeBack.jpg"); // Importing an image to background
		background = background.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
		// Post game menu components (When Player loses)
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); // sets game play panel to box layout
		giveUpBtn = new JButton("Give Up");
		restartBtn = new JButton("Try Again");
		giveUpBtn.addMouseListener(this);
		restartBtn.addMouseListener(this);
		this.add(Box.createRigidArea(new Dimension(0, 250))); // Creates an invisible box that shifts the post game
																// buttons downwards
		giveUpBtn.setFont(new Font("Arial", Font.BOLD, 45));
		giveUpBtn.setForeground(Color.WHITE);
		giveUpBtn.setBorderPainted(false); // does not paint the button's border
		giveUpBtn.setContentAreaFilled(false); // does not fill button background
		giveUpBtn.setFocusPainted(false); // does not paint anything on the button when clicked on
		restartBtn.setFont(new Font("Arial", Font.BOLD, 45));
		restartBtn.setForeground(Color.WHITE);
		restartBtn.setBorderPainted(false); 
		restartBtn.setContentAreaFilled(false); 
		restartBtn.setFocusPainted(false);
		giveUpBtn.setAlignmentX(this.CENTER_ALIGNMENT); // Centers both post game buttons to the game play panel
		restartBtn.setAlignmentX(this.CENTER_ALIGNMENT);
		add(restartBtn);
		this.add(Box.createRigidArea(new Dimension(400, 70))); // Creates an invisible box between the post game buttons
																// to space them out from each other
		add(giveUpBtn);
		giveUpBtn.setVisible(false);
		restartBtn.setVisible(false);

		showLevel1 = new JLabel();
		showScore = new JLabel();
		add(showScore); // adds labels to panels
		add(showLevel1);
		// Initializing and assigning values to variables
		score = 0;
		isPlayerPlaying = false;
		row = 5;
		col = 4;
		numBricks = 20;
		platFormX = 350;
		platFormY = 700;
		ballX = 400;
		ballY = 600;
		ballVelX = -1;
		ballVelY = -1;
		delay = 3; // delay set to 3 milliseconds
		mapBrick = new MapGenerator(row, col, 1);
		addKeyListener(this); // Allows the class to read keys from user
		setFocusable(true); // allows the class to focus/enables the keyListener to work
		setFocusTraversalKeysEnabled(false);
//		String filepath = "BTS UGH.wav";
//		musicObject = new Audio(filepath);
//		musicObject.startMusic();
//		musicObject.loopMusic();
		time = new Timer(delay, this); // Triggers the actionPreformed method to draw the frames of the game.
										// Fires an action event (actionPreformed method) every 5 milliseconds (delay
										// value)
		time.start(); // starts the timer
	}

	/*
	 * Purpose is to draw everything on the JPanel 
	 * pre: uses Graphics parameter c
	 */
	public void paintComponent(Graphics c) {
		// draw background image
		Graphics2D comp2D = (Graphics2D) c;
		comp2D.drawImage(background, 0, 0, this); 

		// draw ball
		c.setColor(Color.GREEN);
		c.fillOval(ballX, ballY, 20, 20);

		// draw platform
		c.setColor(Color.CYAN);
		c.fillRect(platFormX, platFormY, 110, 20);
		c.setColor(Color.BLACK);
		c.drawRect(platFormX, platFormY, 110, 20);

		// draw map
		mapBrick.draw((Graphics2D) c, 1);

		// draws player's score
		showScore.setForeground(Color.YELLOW);
		showScore.setFont(new Font("Arial", Font.BOLD, 30));
		showScore.setText("Score: " + score);
		showScore.setBounds(340, -20, 200, 100);

		// draws the level 1 label
		showLevel1.setForeground(Color.YELLOW);
		showLevel1.setFont(new Font("Arial", Font.BOLD, 35));
		showLevel1.setText("Level 1");
		showLevel1.setBounds(10, 670, 120, 100);

		// If player loses, erases map and displays post game menu
		if (ballY > 750) {
			isPlayerPlaying = false;
			// deletes everything on the game play screen
			this.remove(showLevel1);
			this.remove(showScore);
			ballVelX = 0; // Stops the ball by setting it's x/y velocity to 0
			ballVelY = 0;
			comp2D.drawImage(background, 0, 0, this); // Overlaps/hides all the rectangles, drawings, etc. 
			// draws the GAME OVER text
			c.setColor(Color.red);
			c.setFont(new Font("Arial", Font.BOLD, 50));
			c.drawString("Game Over!", 265, 100);
			// Draws player's score after they lose
			try { // try catch to handle any IOException errors
				int finalScore = ScoreInputOutput.getScore(score, 1); // calls getScore() get player's final score
				c.setColor(Color.YELLOW);
				c.setFont(new Font("serif", Font.BOLD, 50));
				c.drawString("Score: " + finalScore, 310, 600);
			} catch (IOException e) {
				e.printStackTrace(); // method tells you what happened and where the error happened
			}
			// sets post game buttons to visible
			giveUpBtn.setVisible(true);
			restartBtn.setVisible(true);
		}

		// Detects if the player has destroyed all the bricks
		if (score == 2) {
			isPlayerPlaying = false;
		}
	}

	/*
	 * Purpose is to draw the frames/visuals for the game play 
	 * pre: uses ActionEvent parameter e
	 */
	public void actionPerformed(ActionEvent e) {
		if (isPlayerPlaying == true) {
			Rectangle ball = new Rectangle(ballX, ballY, 20, 20); // Draws an imaginary rectangular hit box for the ball
			Rectangle platForm = new Rectangle(platFormX, platFormY, 110, 20); // Draws an imaginary rectangular hit box
																				// for the platform
			ballX += ballVelX;
			ballY += ballVelY;
			if (ball.intersects(platForm)) { // Checks to see if the hit boxes from the ball and platform intersects
												// each other
				// Checks to see if the ball hit the top of the platform
				if (ball.y <= platForm.y - (platForm.height / 2)) {
				ballVelY = -1; // Ball velocity changes direction when intersection occurs
				}
			}
			// Checks if ball intersects with any bricks
			 for (int i = 0; i < mapBrick.getMapRow(); i++) {
				for (int k = 0; k < mapBrick.getMapCol(); k++) {
					if (mapBrick.getMapValue()[i][k] == 0) {
						rect = mapBrick.getRect(k, i, 1); // gets a rectangle on the map field for each iteration
						if (ball.intersects(rect)) { // Checks to see if any of the rectangles drawn (rect) intersected
														// with the ball
							// Changes x/y direction of the ball depending on where the ball hit the rectangle
							if (ball.y <= rect.y - (rect.height / 2)) {
								// Hit was from below the brick
								ballVelY = -ballVelY;
							} else if (ball.x < rect.x) {
								// Hit was on left
								ballVelX = -ballVelX;
							}
							if (ball.y >= rect.y + (rect.height / 2)) {
								// Hit was from above the brick
								ballVelY = -ballVelY;
							} else if (ball.x > rect.x) {
								// Hit was on right
								ballVelX = -ballVelX;
							}
							score++;
							numBricks--;
							mapBrick.getMapValue()[i][k] = -1; // Brick value changes so that the next frame will not
																// draw that specific brick (destroyed)
						}
					}
				}
			}
			// Changes direction of the ball when it hits the boundary points
			if (ballX < 0) {
				ballVelX = 1;
			} else if (ballX >= 765) {
				ballVelX = -1;
			}
			if (ballY < 0) {
				ballVelY = 1;
			}
		}
		repaint();
		// Player goes to next level if they destroyed all the bricks (level2)
		if (score == 2 && isPlayerPlaying == false) {
			time.stop(); // stops the timer
			musicObject.stopMusic();
			JOptionPane.showMessageDialog(null,
					"                     You Win!.\nClick OK to continue onto the next level.");
			Main.gamePlayFrame.dispose(); // deletes the current JFrame (level 1)
			Main.gamePlayFrame = new JFrame(); // Created a new JFrame for the GamePlay2 class (level 2)
			Main.gamePlayFrame.setBounds(0, 0, 800, 800);
			Main.gamePlayFrame.setTitle("Collide");
			Main.gamePlayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Main.gamePlayFrame.setResizable(false);
			GamePlay2 level2 = new GamePlay2(); // calls GamePlay2 to initialize level 2
			Main.gamePlayFrame.add(level2);
			Main.gamePlayFrame.setVisible(true);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // Checks to see if the right arrow key was pressed
			isPlayerPlaying = true; // Boolean variable is set to true which will be used to inform the compiler
									// that the game is in play
			if (checkCoordPlatRight(platFormX) == true) { // Checks if platform has reached the boundary
				platFormX = 680; // Locks platform to a fixed amount (680) when right boundary is reached to
									// prevent it from crossing over
			} else {
				moveRight(); // Calls moveRight() that moves the platform right
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) { // Checks to see if the left arrow key was pressed
			isPlayerPlaying = true;
			if (checkCoordPlatLeft(platFormX) == true) {
				platFormX = 2; // Locks platform to a fixed amount (2) when left boundary is reached to prevent
								// it from crossing over
			} else {
				moveLeft(); // Calls moveLeft() that moves platform left
			}
		}

	}

	/*
	 * Increases x position of platForm by a fixed amount 
	 * pre: none
	 */
	public void moveRight() {
		platFormX += 30; // speed of platform when moving right
	}

	/*
	 * Decreases x position of platform by a fixed amount 
	 * pre: none
	 */
	public void moveLeft() {
		platFormX -= 30; // speed of platform when moving left
	}

	/*
	 * Checks the x position of the platform to see if it has reached the right side
	 * boundary 
	 * pre: uses an integer parameter platX 
	 * post: returns a boolean
	 */
	public boolean checkCoordPlatRight(int platX) {
		if (platX >= 680) { // x coordinate at 680 is the right boundary
			platX = 680;
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Checks the x position of the platform to see if it has reached the left side
	 * boundary 
	 * pre: uses an integer parameter platX 
	 * post: returns a boolean
	 */
	public boolean checkCoordPlatLeft(int platX) {
		if (platX <= 2) { // x coordinate at 2 is the left boundary
			platX = 2;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == restartBtn) {
			// resetting all the variables back to there values at the start of the level
			isPlayerPlaying = true;
			numBricks = 20;
			platFormX = 350;
			platFormY = 700;
			ballX = 400;
			ballY = 600;
			ballVelX = -1;
			ballVelY = -1;
			score = 0;
			this.add(showLevel1); // adds back the level label and score label
			this.add(showScore);
			giveUpBtn.setVisible(false);
			restartBtn.setVisible(false);
			for (int i = 0; i < mapBrick.getMapRow(); i++) {
				for (int k = 0; k < mapBrick.getMapCol(); k++) {
					mapBrick.getMapValue()[i][k] = 0;
				}
			}
		} else {
			// Yes or No Dialog Box to confirm if player wants to quit or not
			String[] array = { "Yes", "No" };
			int choice = JOptionPane.showOptionDialog(this, "Yes or No?", "Are you sure you want to quit?",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, array, null);
			if (choice == 0) {
				System.exit(0); // Closes window
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Outlines button (paints border) when hovered over
		if (e.getSource() == restartBtn) {
			restartBtn.setBorderPainted(true); 
		} else {
			giveUpBtn.setBorderPainted(true);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Erases button outlines when mouse cursor is not in it
		if (e.getSource() == restartBtn) {
			restartBtn.setBorderPainted(false);
		} else {
			giveUpBtn.setBorderPainted(false);
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	
	

}
