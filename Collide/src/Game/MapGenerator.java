package Game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.*;
import java.net.URL;

import javax.imageio.*;
import java.awt.image.*;

public class MapGenerator  {

	private int brickWidth;
	private int brickLength;
	private int[][] mapField;
	private int mapRow;
	private int mapCol;
	private Image img = null;

	public MapGenerator(int row, int col, int level) {
		brickWidth = 140;
		brickLength = 60;
		mapRow = row;
		mapCol = col;
		mapField = new int[row][col];
		// Level 1 Map 
		if (level == 1) {
		for (int i = 0; i < mapField.length; i++) { // Filling the 2-d array with 0 to indicate where to draw the
													// rectangles
			for (int k = 0; k < mapField[1].length; k++) {
				mapField[i][k] = 0;
				}
			}
		}
		// Level 2 Map
		else if (level == 2) {
			for (int i = 0; i < mapField.length; i++) { // Filling the 2-d array with 0 to indicate where to draw the
														// rectangles
				for (int k = 0; k < mapField[1].length; k++) {
					mapField[i][k] = 0;
				}
			}
		}
		// Level 3 Map
		else {
			for (int i = 0; i < mapField.length; i++) { // Filling the 2-d array with 0 to indicate where to draw the
														// rectangles
					for (int k = 0; k < mapField[1].length; k++) {
						mapField[i][k] = 0;
				}
			}
		}
	}

	/*
	 * Draws the rectangles/bricks on the playing field 
	 * pre: uses Graphics2D parameter g
	 */
	public void draw(Graphics2D g, int level) {
		    Graphics g2 = (Graphics2D)g;
			// draws level 1 map
			if (level == 1) {
			for (int i = 0; i < mapField.length; i++) {
				for (int k = 0; k < mapField[1].length; k++) {
					if (mapField[i][k] == 0) { // Draws the rectangle if the position on the 2-d array is 0   
	                    	img =getImage ("/bricks.jpg");
	                    	g2.drawImage(img, k * brickWidth + 120, i * brickLength + 50, brickWidth - 20, brickLength - 10, null);
	                    	g.setStroke(new BasicStroke(3));
							g.setColor(Color.BLACK);
							g.drawRect(k * brickWidth + 120, i * brickLength + 50, brickWidth - 20, brickLength - 10);
					}
				}
			  }
			}
			// draws level 2 map
			else if (level == 2) {
				for (int i = 0; i < mapField.length; i++) {
					for (int k = 0; k < mapField[1].length; k++) {
						// spaces out the bricks
						if (i == 0) {
							if (mapField[i][k] == 0) { // Draws the rectangle if the position on the 2-d array is 0
								img =getImage ("/bricks.jpg");
		                    	g2.drawImage(img, k * brickWidth + 40, i * brickLength + 50, brickWidth,
										brickLength - 30,  null);
								g.setStroke(new BasicStroke(3));
								g.setColor(Color.BLACK);
								g.drawRect(k * brickWidth + 40, i * brickLength + 50, brickWidth,
										brickLength - 30);
							}
						} else if (i == 1) {
							if (mapField[i][k] == 0) { // Draws the rectangle if the position on the 2-d array is 0
								img =getImage ("/bricks.jpg");
		                    	g2.drawImage(img, k * brickWidth + 40, i * brickLength + 100, brickWidth,
										brickLength - 30,  null);
								g.setStroke(new BasicStroke(3));
								g.setColor(Color.BLACK);
								g.drawRect(k * brickWidth + 40, i * brickLength + 100, brickWidth,
										brickLength - 30);
							}
						} else if (i == 2) {
							if (mapField[i][k] == 0) { // Draws the rectangle if the position on the 2-d array is 0
								img =getImage ("/bricks.jpg");
		                    	g2.drawImage(img, k * brickWidth + 40, i * brickLength + 150, brickWidth,
										brickLength - 30,  null);
								g.setStroke(new BasicStroke(3));
								g.setColor(Color.BLACK);
								g.drawRect(k * brickWidth + 40, i * brickLength + 150, brickWidth,
										brickLength - 30);
							}
						} else if (i == 3) {
							if (mapField[i][k] == 0) { // Draws the rectangle if the position on the 2-d array is 0
								img =getImage ("/bricks.jpg");
		                    	g2.drawImage(img,k * brickWidth + 40, i * brickLength + 200, brickWidth,
										brickLength - 30,  null);
								g.setStroke(new BasicStroke(3));
								g.setColor(Color.BLACK);
								g.drawRect(k * brickWidth + 40, i * brickLength + 200, brickWidth,
										brickLength - 30);
							}
						}
					}
				}
			}
			// draws level 3 map
			else if (level == 3) {
				for (int i = 0; i < mapField.length; i++) {
					for (int k = 0; k < mapField[1].length; k++) {
						// Condition that only draws the rectangles on odd positions in mapField
						if (i==0||i==1) {
							if (mapField[i][k] == 0) {
								if (k == 0) {
									img =getImage ("/bricks.jpg");
			                    	g2.drawImage(img,k * brickWidth, i * brickLength + 50, brickWidth - 20, brickLength - 10,  null);
									g.setStroke(new BasicStroke(3));
									g.setColor(Color.BLACK);
									g.drawRect(k * brickWidth, i * brickLength + 50, brickWidth - 20, brickLength - 10);
								} else if (k == 1) {
									img =getImage ("/bricks.jpg");
			                    	g2.drawImage(img,k * brickWidth + 30, i * brickLength + 50, brickWidth - 20,
											brickLength - 10,  null);
									g.setStroke(new BasicStroke(3));
									g.setColor(Color.BLACK);
									g.drawRect(k * brickWidth + 30, i * brickLength + 50, brickWidth - 20,
											brickLength - 10);
								} else if (k == 2) {
									img =getImage ("/bricks.jpg");
			                    	g2.drawImage(img,k * brickWidth + 60, i * brickLength + 50, brickWidth - 20,
											brickLength - 10,  null);
									g.setStroke(new BasicStroke(3));
									g.setColor(Color.BLACK);
									g.drawRect(k * brickWidth + 60, i * brickLength + 50, brickWidth - 20,
											brickLength - 10);
								} else if (k == 3) {
									img =getImage ("/bricks.jpg");
			                    	g2.drawImage(img,k * brickWidth + 90, i * brickLength + 50, brickWidth - 20,
											brickLength - 10, null);
									g.setStroke(new BasicStroke(3));
									g.setColor(Color.BLACK);
									g.drawRect(k * brickWidth + 90, i * brickLength + 50, brickWidth - 20,
											brickLength - 10);
								} else if (k == 4) {
									img =getImage ("/bricks.jpg");
			                    	g2.drawImage(img,k * brickWidth + 115, i * brickLength + 50, brickWidth - 20,
											brickLength - 10, null);
									g.setStroke(new BasicStroke(3));
									g.setColor(Color.BLACK);
									g.drawRect(k * brickWidth + 115, i * brickLength + 50, brickWidth - 20,
											brickLength - 10);
								}
						}
					}
						// Condition that only draws the rectangles on odd positions in mapField.
						// Also checks if the iteration reaches the 3 or 4 row, there will be a greater y
						// position (shift more downwards) for the bricks in those rows
						else if (i == 2 || i == 3) {
							if (mapField[i][k] == 0) {
								if (k == 0) {
									img =getImage ("/bricks.jpg");
			                    	g2.drawImage(img,k * brickWidth, i * brickLength + 150, brickWidth - 20,
											brickLength - 10, null);
									g.setStroke(new BasicStroke(3));
									g.setColor(Color.BLACK);
									g.drawRect(k * brickWidth, i * brickLength + 150, brickWidth - 20,
											brickLength - 10);
								} else if (k == 1) {
									img =getImage ("/bricks.jpg");
			                    	g2.drawImage(img,k * brickWidth + 30, i * brickLength + 150, brickWidth - 20,
											brickLength - 10, null);
									g.setStroke(new BasicStroke(3));
									g.setColor(Color.BLACK);
									g.drawRect(k * brickWidth + 30, i * brickLength + 150, brickWidth - 20,
											brickLength - 10);
								} else if (k == 2) {
									img =getImage ("/bricks.jpg");
			                    	g2.drawImage(img,k * brickWidth + 60, i * brickLength + 150, brickWidth - 20,
											brickLength - 10, null);
									g.setStroke(new BasicStroke(3));
									g.setColor(Color.BLACK);
									g.drawRect(k * brickWidth + 60, i * brickLength + 150, brickWidth - 20,
											brickLength - 10);
								} else if (k == 3) {
									img =getImage ("/bricks.jpg");
			                    	g2.drawImage(img,k * brickWidth + 90, i * brickLength + 150, brickWidth - 20,
											brickLength - 10, null);
									g.setStroke(new BasicStroke(3));
									g.setColor(Color.BLACK);
									g.drawRect(k * brickWidth + 90, i * brickLength + 150, brickWidth - 20,
											brickLength - 10);
								} else if (k == 4) {
									img =getImage ("/bricks.jpg");
			                    	g2.drawImage(img,k * brickWidth + 115, i * brickLength + 150, brickWidth - 20,
											brickLength - 10, null);
									g.setStroke(new BasicStroke(3));
									g.setColor(Color.BLACK);
									g.drawRect(k * brickWidth + 115, i * brickLength + 150, brickWidth - 20,
											brickLength - 10);
								}
							}
						}
					}
				}
			}
		}
	
	public Image getImage(String path) {
		Image tempImage = null;
		try
		{
			URL imageURL = MapGenerator.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		}
		catch(Exception e) {
			System.out.println("An error occured "+e.getMessage());
		}
		return tempImage;
	}
	
	
	/*
	 * Returns the number of rows on 2D array map 
	 * pre: none post: returns int mapRow
	 */
	public int getMapRow() {
		return mapRow;
	}

	/*
	 * Returns the number of columns on 2D array map 
	 * pre: none post: returns int mapCol
	 */
	public int getMapCol() {
		return mapCol;
	}

	/*
	 * Purpose is to update the mapField array value every time a ball intersects a rectangle 
	 * pre: none post: returns 2D array mapField
	 */
	public int[][] getMapValue() {
		return mapField;
	}

	/*
	 * Purpose is to return the rectangle the ball intersected with 
	 * pre: uses int parameters horizontalShift and verticalShift 
	 * post: returns Rectangle object
	 */
	public Rectangle getRect(int horizontalShift, int verticalShift, int level) {
		if (level == 1) {
			Rectangle rectHit = new Rectangle(horizontalShift * brickWidth + 120, verticalShift * brickLength + 50,
					brickWidth - 20, brickLength - 10);
			return rectHit;
		} 
		else if (level == 2) {
			if (verticalShift == 0) {
				Rectangle rectHit = new Rectangle(horizontalShift * brickWidth + 40, verticalShift * brickLength + 50,
						brickWidth, brickLength - 30);
				return rectHit;
			} else if (verticalShift == 1) {
				Rectangle rectHit = new Rectangle(horizontalShift * brickWidth + 40, verticalShift * brickLength + 100,
						brickWidth, brickLength - 30);
				return rectHit;
			} else if (verticalShift == 2) {
				Rectangle rectHit = new Rectangle(horizontalShift * brickWidth + 40, verticalShift * brickLength + 150,
						brickWidth, brickLength - 30);
				return rectHit;
			} else {
				Rectangle rectHit = new Rectangle(horizontalShift * brickWidth + 40, verticalShift * brickLength + 200,
						brickWidth, brickLength - 30);
				return rectHit;
			}
		} 
		else {
			// Condition that checks the row the rectangle is in so that the compiler can
			// correctly determine the y position of the rectangle, since the rectangles in row
			// 3 and 4 have a different y value than the rectangles in row 1 and 2
			if (verticalShift == 0 || verticalShift == 1) {
				if (horizontalShift == 0) {
					Rectangle rectHit = new Rectangle(horizontalShift * brickWidth, verticalShift * brickLength + 50,
							brickWidth - 20, brickLength - 10);
					return rectHit;
				} else if (horizontalShift == 1) {
					Rectangle rectHit = new Rectangle(horizontalShift * brickWidth + 30,
							verticalShift * brickLength + 50, brickWidth - 20, brickLength - 10);
					return rectHit;
				} else if (horizontalShift == 2){
					Rectangle rectHit = new Rectangle(horizontalShift * brickWidth + 60,
							verticalShift * brickLength + 50, brickWidth - 20, brickLength - 10);
					return rectHit;
				}
				else if (horizontalShift == 3){
					Rectangle rectHit = new Rectangle(horizontalShift * brickWidth + 90,
							verticalShift * brickLength + 50, brickWidth - 20, brickLength - 10);
					return rectHit;
				}
				else {
					Rectangle rectHit = new Rectangle(horizontalShift * brickWidth + 115,
							verticalShift * brickLength + 50, brickWidth - 20, brickLength - 10);
					return rectHit;
				}
			} else {
				if (horizontalShift == 0) {
					Rectangle rectHit = new Rectangle(horizontalShift * brickWidth, verticalShift * brickLength + 150,
							brickWidth - 20, brickLength - 10);
					return rectHit;
				} else if (horizontalShift == 1) {
					Rectangle rectHit = new Rectangle(horizontalShift * brickWidth + 30,
							verticalShift * brickLength + 150, brickWidth - 20, brickLength - 10);
					return rectHit;
				} else if (horizontalShift == 2){
					Rectangle rectHit = new Rectangle(horizontalShift * brickWidth + 60,
							verticalShift * brickLength + 150, brickWidth - 20, brickLength - 10);
					return rectHit;
				}
				else if (horizontalShift == 3){
					Rectangle rectHit = new Rectangle(horizontalShift * brickWidth + 90,
							verticalShift * brickLength + 150, brickWidth - 20, brickLength - 10);
					return rectHit;
				}
				else {
						Rectangle rectHit = new Rectangle(horizontalShift * brickWidth + 115,
								verticalShift * brickLength + 150, brickWidth - 20, brickLength - 10);
						return rectHit;
				}
			}
		}
	}
}
