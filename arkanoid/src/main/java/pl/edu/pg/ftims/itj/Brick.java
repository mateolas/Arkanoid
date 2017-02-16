package pl.edu.pg.ftims.itj;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

//klasa dziedziczy po JPanel umozliwiajac tym samym przesloniecie metody paint 
@SuppressWarnings("serial")
public class Brick extends JPanel {

	// okreslenie koloru cegielki
	String name = "blue";

	// polozenie cegielki
	int x;
	int y;

	// wielkosc cegielki
	int WIDTH = 30;
	int HEIGHT = 10;
	public Game game;

	// konstruktor okreslajacy parametry cegielki
	public Brick(Game game, int x, int y) {
		this.game = game;
		this.x = x;
		this.y = y;
		this.name = "blue";
	}

	// metoda zwracajaca obiekty typu Prostokat z okreslonymi wspolrzednymi x i
	// y oraz rozmiarem
	public Rectangle getBound() {
		return new Rectangle(x, y, WIDTH + 1, HEIGHT + 1);
	}

	// rysowanie cegielki
	public void paint(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillRect(this.x, this.y, WIDTH, HEIGHT);
	}
}
