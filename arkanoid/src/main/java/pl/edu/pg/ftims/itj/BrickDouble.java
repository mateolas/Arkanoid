package pl.edu.pg.ftims.itj;

import java.awt.Color;
import java.awt.Graphics2D;

//klasa dziedziczy po klasie Brick
@SuppressWarnings("serial")
public class BrickDouble extends Brick {
	
	//konstruktor z parametrami cegielki
	public BrickDouble(Game game, int x, int y) {
	super(game, x, y);
	this.name = "green";	
	
	}

	// rysowanie cegielki
	public void paint(Graphics2D g){
		g.setColor(Color.GREEN);
		g.fillRect(this.x, this.y, WIDTH, HEIGHT);
	}
	
}
