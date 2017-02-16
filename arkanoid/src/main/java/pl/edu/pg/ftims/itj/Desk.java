package pl.edu.pg.ftims.itj;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

//klasa dziedziczy po JPanel umozliwiajac tym samym przesloniecie metody paint 
//klasa implementuje interfejs Movable; przeslonieta metoda move() okresla zasady poruszania sie platformy
@SuppressWarnings("serial")
public class Desk extends JPanel implements Movable {

	// okreslenie polozenie platformy
	int x = 210;
	int y = 347;

	// okreslenie poczatkowego kierunku i predkosci deski
	int xa = 0;
	// final int Y = 447;

	// okreslenie wielkosci platformy
	final int WIDTH = 80;
	final int HEIGHT = 14;

	private Game game;

	// konstruktor wskazujacy na gre
	public Desk(Game game) {
		this.game = game;
	}

	// metoda okreslajaca ruch pilki
	public void move() {
		if (x + xa > 0 && x + xa < game.getWidth() - 80)
			x = x + xa;

	}

	// metoda okreslajaca rakcje na przycisniecie klawisza strzalek LEWA PRAWA
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			xa = 1;
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			xa = -1;
	}

	// metoda zatrzymujaca platforme po odpuszczeniu klawisza
	public void keyReleased(KeyEvent e) {
		xa = 0;
	}

	// rysowanie platformy
	public void paint(Graphics2D g) {
		g.setColor(Color.blue);
		g.fillRect(this.x, y, WIDTH, HEIGHT);
	}

	// metoda zwracajaca obiekty typu Prostokat z okreslonymi wspolrzednymi x i
	// y oraz rozmiarem
	public Rectangle getBound() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}

}
