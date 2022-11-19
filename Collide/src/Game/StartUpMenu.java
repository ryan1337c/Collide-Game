package Game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class StartUpMenu extends JPanel {
	private Image background; // Image Icon

	public StartUpMenu() {
		super();
		Toolkit kit = Toolkit.getDefaultToolkit();
		background = kit.getImage("StartUpMenuBackground.jpg"); // Importing an image to background
		background = background.getScaledInstance(800, 800, Image.SCALE_SMOOTH);

	}

	public void paintComponent(Graphics c) {
		Graphics2D comp2D = (Graphics2D) c;
		comp2D.drawImage(background, 0, 0, this); // Draws background image
	}
}
