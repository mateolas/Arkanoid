package pl.edu.pg.ftims.itj;

import java.awt.Color;
import java.awt.Graphics2D;

@SuppressWarnings("serial")
public class BrickTriple extends Brick {

	// konstruktor z parametrami cegielki
	public BrickTriple(Game game, int x, int y) {
		super(game, x, y);
		name = "cyan";
	}

	// rysowanie cegielki
	public void paint(Graphics2D g) {
		g.setPaint(Color.cyan);
		g.fillRect(this.x, this.y, WIDTH, HEIGHT);

	}

}
