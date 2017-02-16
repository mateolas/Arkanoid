package pl.edu.pg.ftims.itj;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

//klasa dziedziczy po JPanel umozliwiajac tym samym przesloniecie metody paint 
//klasa implementuje interfejs Movable; przeslonieta metoda move() okresla zasady poruszania sie pilki
@SuppressWarnings("serial")
public class Ball extends JPanel implements Movable {

	// polozenie pilki na ekranie
	int x = 243;
	int y = 333;

	// kierunek i predkosc pilki
	int xa = 1;
	int ya = 1;

	// wynik
	int score = 0;

	// srednica pilki
	final int DIAMETER = 14;

	// zmienne pomocnicze
	public Game game;
	public Brick brick;
	public Brick brickStd;

	// konstruktor wskazujacy na gre
	public Ball(Game game) {
		this.game = game;
	}

	// metoda odpowiedzialna za zachowanie pilki
	public void move() {

		int index;
		// zmienna okreslajaca numer cegielki z ArrayList ktora zostala
		// dotknieta przez pilke
		index = collisionBrickNr();

		// metoda zwracajaca prawde w przypadku dotkniecia sie prostokata
		// wyznaczonego przez cegielke i prostokoata opisanego na pilce
		collisionBrick();

		// warunki okreslajace zachowanie pilki przy dotknieciu scianek ekranu
		if (x + xa < 0)
			xa = 1;
		if (x + xa > game.getWidth() - 14)
			xa = -1;
		if (y + ya < 0)
			ya = 1;
		if (y + ya > game.getHeight() - 14) {
			game.gameOver();
		}
		if (collisionDesk()) {
			ya = -1;
			y = game.desk.y - 1 - DIAMETER;
		}

		// warunki okreslajace dotkniecie pilki na kazdy z bokow cegielki
		// jezeli cegielka jest koloru cyan zamiana na cegielke typu
		// BrickDouble, ze wspolrzednymi x i y takimi samymi jak aktualna
		// cegielka
		// jezeli cegielka jest kolory green zamiana na cegielke typu Brick
		// jezeli cegielka jest koloru blue znikniecie cegielki (wielkosc i
		// wspolrzedne 0)
		if (collisionBrick() && (x + DIAMETER) == (game.brickList.get(index).x) && xa == 1) {
			xa = -1;
			if (game.brickList.get(index).name == "cyan") {
				game.brickList.set(index,
						new BrickDouble(this.game, game.brickList.get(index).x, game.brickList.get(index).y));
			} else if (game.brickList.get(index).name == "green") {
				game.brickList.set(index,
						new Brick(this.game, game.brickList.get(index).x, game.brickList.get(index).y));
			} else if (game.brickList.get(index).name == "blue") {
				game.brickList.get(index).WIDTH = 0;
				game.brickList.get(index).HEIGHT = 0;
				game.brickList.get(index).x = 0;
				game.brickList.get(index).y = 0;
				score++;
			}

		} else if (collisionBrick() && ((game.brickList.get(index).x + 30) == x) && xa == -1) {
			xa = 1;

			if (game.brickList.get(index).name == "cyan") {
				game.brickList.set(index,
						new BrickDouble(this.game, game.brickList.get(index).x, game.brickList.get(index).y));
			} else if (game.brickList.get(index).name == "green") {
				game.brickList.set(index,
						new Brick(this.game, game.brickList.get(index).x, game.brickList.get(index).y));
			} else if (game.brickList.get(index).name == "blue") {

				game.brickList.get(index).WIDTH = 0;
				game.brickList.get(index).HEIGHT = 0;
				game.brickList.get(index).x = 0;
				game.brickList.get(index).y = 0;
				score++;
			}
		} else if (collisionBrick() && ((game.brickList.get(index).y + 9) == y) && ya == -1) {
			ya = 1;

			if (game.brickList.get(index).name == "cyan") {
				game.brickList.set(index,
						new BrickDouble(this.game, game.brickList.get(index).x, game.brickList.get(index).y));
			} else if (game.brickList.get(index).name == "green") {
				game.brickList.set(index,
						new Brick(this.game, game.brickList.get(index).x, game.brickList.get(index).y));
			} else if (game.brickList.get(index).name == "blue") {

				game.brickList.get(index).WIDTH = 0;
				game.brickList.get(index).HEIGHT = 0;
				game.brickList.get(index).x = 0;
				game.brickList.get(index).y = 0;
				score++;
			}

		} else if (collisionBrick() && game.brickList.get(index).y == y + DIAMETER && ya == 1) {
			ya = -1;

			if (game.brickList.get(index).name == "cyan") {
				game.brickList.set(index,
						new BrickDouble(this.game, game.brickList.get(index).x, game.brickList.get(index).y));
			} else if (game.brickList.get(index).name == "green") {
				game.brickList.set(index,
						new Brick(this.game, game.brickList.get(index).x, game.brickList.get(index).y));
			} else if (game.brickList.get(index).name == "blue") {

				game.brickList.get(index).WIDTH = 0;
				game.brickList.get(index).HEIGHT = 0;
				game.brickList.get(index).x = 0;
				game.brickList.get(index).y = 0;
				score++;
			}
		}

		if (checkWin()) {
			game.gameWin();
		}

		x = x + xa;
		y = y + ya;
	}

	// rysowanie pilki
	public void paint(Graphics2D g) {
		g.setColor(Color.blue);
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}

	// metoda sprawdzajaca czy zmienna score jest rowna wielkosci ArrayList
	// brickList
	private boolean checkWin() {

		if (score == game.brickList.size()) {

			return true;
		}

		return false;

	}

	// metoda sprawdzajaca czy doszlo do zetkniecia sie pilki i platformy
	private boolean collisionDesk() {

		return game.desk.getBound().intersects(getBound());

	}

	// metoda sprawdzajaca ktora cegielke z kolekcji dotknela aktualnie pilka
	private int collisionBrickNr() {

		for (int i = 0; i < game.brickList.size(); i++)
			if (game.brickList.get(i).getBound().intersects(getBound()))
				return i;

		return 0;

	}

	// metoda sprawdzajaca czy doszlo do zetkniecia sie pilki i cegielki
	private boolean collisionBrick() {

		if (game.brickList.get(collisionBrickNr()).getBound().intersects(getBound()))
			return true;
		return false;

	}

	// metoda zwracajaca obiekty typu Prostokat z okreslonymi wspolrzednymi x i
	// y oraz rozmiarem
	public Rectangle getBound() {
		return new Rectangle(x, y, DIAMETER + 1, DIAMETER + 1);
	}

}
