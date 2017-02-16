package pl.edu.pg.ftims.itj;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

//klasa dziedziczy po JPanel umozliwiajac tym samym przesloniecie metody paint 
@SuppressWarnings("serial")
public class Menu extends JPanel {

	//metoda wyswietlajaca menu
	public Menu() {

	}

	public Rectangle decisionButton = new Rectangle(177, 110, 150, 50);

	public void paint(Graphics2D g) {

		Graphics2D g2d = (Graphics2D) g;

		Font fnt = new Font("arial", Font.BOLD, 35);
		g.setFont(fnt);
		g.setColor(Color.blue);
		g.drawString("ARKANOID", 159, 80);

		Font fnt2 = new Font("arial", Font.PLAIN, 25);
		g.setFont(fnt2);
		g.setColor(Color.black);
		g.drawString("GRAJ", 217, 143);
		g.drawString("WYJŚCIE", 195, 203);
		g.setColor(Color.blue);
		g2d.draw(decisionButton);

	}

	// metoda reagujaca na nacisniecie klawisza enter
	//jezeli klawisz ENTER zostanie nacisniety i prostokat jest w okreslonym polozeniu nastepuje ustawienie parametru state na jeden lyb wyjscie z gry
	public void keyPressed(KeyEvent e) {

		if ((e.getKeyCode() == KeyEvent.VK_ENTER) && decisionButton.y == 110 ) {
			Game.setState(1);
		}
		
		if ((e.getKeyCode() == KeyEvent.VK_ENTER) && decisionButton.y == 170 ) {
			System.exit(ABORT);
		}


		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			decisionButton.setLocation(177, 170);
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			decisionButton.setLocation(177, 110);
		}


	}

}
