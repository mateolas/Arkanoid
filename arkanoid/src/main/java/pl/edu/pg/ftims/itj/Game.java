package pl.edu.pg.ftims.itj;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
//import java.util.Random;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import pl.edu.pg.ftims.itj.exceptions.LuckOutOfRangeException;
import pl.edu.pg.ftims.itj.music.Music;

//klasa dziecziny po klasie Jpanel i implementuje interfejsy Movable (okreslajacy ruch pilki i platformy)
//oraz Randomize (generujacy pseudolosowa liczbe z okreslonego zakresu) 
@SuppressWarnings("serial")
public class Game extends JPanel implements Movable, Randomize {

	// okreslenie rozmiaru okna gry
	final static int WIDTH = 500, HEIGHT = 400;

	// parametr okreslajacy pauze przed rozpoczeciem gry
	int start = 0;

	// parametr okreslajacy wybor opcji menu
	static int state = 0;

	Ball ball = new Ball(this);
	Desk desk = new Desk(this);
	Menu menu = new Menu();
	Music music = new Music();

	// ArrayList obiektów typu Brick
	public ArrayList<Brick> brickList = new ArrayList<Brick>();

	// metoda dodajaca cegielki do planszy
	// w zaleznosci od wartosci losowej zmiennej luck przypisywana jest cegielka
	// innego typu
	// rzuca wyjatkiem LuckOutofRangeException w przypadky gdyby parametr luck
	// byl wiekszy niz 90
	public void addBricks() throws LuckOutOfRangeException {

		int luck = 1;

		for (int i = 80; i < 150; i += 20) {
			for (int j = 70; j < 400; j += 35) {
				luck = rand(90);
				if (luck <= 30) {
					brickList.add(new Brick(this, j, i));
				} else if (luck >= 31 && luck <= 60) {
					brickList.add(new BrickDouble(this, j, i));
				} else if (luck >= 61 && luck <= 90) {
					brickList.add(new BrickTriple(this, j, i));
				} else if (luck > 90) {
					throw new LuckOutOfRangeException();
				}
			}
		}

	}

	// metoda zwracajaca losowa wartosc r z zakresu w
	public int rand(int w) {
		Random rand = new Random();
		int r = rand.nextInt(w);
		return r;

	}

	// setter
	public static void setState(int k) {
		state = k;
	}

	// konstruktor ustawiajacy muzyke, dodajcy cegielki, ustawiajacy listenera
	public Game() throws Exception {

		music.music();
		addBricks();
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				desk.keyReleased(e);
			}

			public void keyPressed(KeyEvent e) {
				desk.keyPressed(e);
				start(e);
				menu.keyPressed(e);
				quit(e);
			}
		});
		setFocusable(true);
	}

	// metoda obslugujaca przycisk SPACE; odpowiedzalna za "pauze" przy
	// pierwszym uruchomieniu gry
	public boolean start(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.start = 1;
		}
		return false;
	}

	// metoda obslugujaca klawisz ESC odpowiedzalny za wyjscie z biezacej gry do
	// menu glownego
	public void quit(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			state = 0;
		}

	}

	// metoda obslugujaca poruszanie sie pilki i platformy
	public void move() {

		if (start == 1 && state == 1) {
			ball.move();
			desk.move();
		}

	}

	// metoda paint jest wywolywana za kazdym razem kiedy tworzone jest okno,
	// zmieniany jest jego rozmiar, max/minimalizowane
	@Override
	public void paint(Graphics g) {

		// przywolanie domyslnego konstruktora czyszczenie ekranu
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// jezeli w menu zostala wybrana opcja GRA i nie zostal wcisniety
		// klawisz SPACJI
		if (state == 1 && start == 0) {

			g2d.setColor(Color.gray);
			g2d.setFont(new Font("Verdana", Font.BOLD, 16));
			g2d.drawString("NACIŚNIJ SPACJĘ ABY ROZPOCZĄĆ", 90, 250);

			ball.paint(g2d);
			desk.paint(g2d);
			for (Brick b : this.brickList) {
				b.paint(g2d);
			}
			// jezeli w menu zostala wybrana opcja GRA i zostal wcisniety
			// przycisk SPACJI
		} else if (state == 1 && start == 1) {

			g2d.setColor(Color.gray);
			g2d.setFont(new Font("Verdana", Font.BOLD, 15));
			g2d.drawString("WYNIK: " + String.valueOf(ball.score), 395, 17);

			g2d.setColor(Color.gray);
			g2d.setFont(new Font("Verdana", Font.PLAIN, 10));
			g2d.drawString("Nacisnij klawisz ESC aby wyjść.", 5, 13);

			ball.paint(g2d);
			desk.paint(g2d);
			for (Brick b : this.brickList) {
				b.paint(g2d);
			}
			// domyslny ekran z widokiem menu glownego
		} else if (state == 0) {
			menu.paint(g2d);
		}
	}

	// metoda obslugujaca ekran z informacja o koncu gry w momencie kontaktu
	// pilki z dolna krawedzia okna
	public void gameOver() {
		JOptionPane.showMessageDialog(this, "KONIEC GRY!", "KONIEC !", JOptionPane.YES_NO_OPTION);
		System.exit(ABORT);
	}

	// metoda obslugujaca ekran z informacja o wygranej w momencie zbicia
	// wszystkich cegielek
	public void gameWin() {
		JOptionPane.showMessageDialog(this, "GRATULACJE !", "WYGRAŁEŚ !", JOptionPane.YES_NO_OPTION);
		System.exit(ABORT);
	}

	public static void main(String[] args) throws InterruptedException, LuckOutOfRangeException, Exception {
		JFrame frame = new JFrame("Arkanoid");
		Game game = new Game();

		// dodanie obiektu game do Jframu
		frame.add(game);
		// ustawienie wymiarow okna
		frame.setSize(WIDTH, HEIGHT);
		// ustawienie widocznosci okna
		frame.setVisible(true);
		// ustawienie wymiarow okna jako const.
		frame.setResizable(false);
		// konczy program po zamknieciu okna
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		while (true) {
			game.move();
			// ponowne wywolwanie metody paint
			game.repaint();

			// wylaczenie Thread main na 10 ms aby Thread AWT - EventQueue moglo
			// zrealizowac metode paint
			Thread.sleep(10);

		}

	}

}
