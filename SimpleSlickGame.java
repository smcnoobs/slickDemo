import java.util.logging.Level;

import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.*;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.*;

public class SimpleSlickGame extends BasicGame implements InputProviderListener
{
	private String clicky="", prov="", pos="";
	private int px, py;
	private Command change = new BasicCommand("change");
	private InputProvider provider;
	private Actor a;
	private Vector2f v;
	private static final int UP = 200, LEFT = 203, RIGHT = 205, DOWN = 208;
	
	public SimpleSlickGame(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		//gc.setShowFPS(false);
		//gc.setVSync(true);
		gc.setTargetFrameRate(60);
		provider = new InputProvider(gc.getInput());
		provider.addListener((InputProviderListener) this);
		//provider.bindCommand(new KeyControl(Input.KEY_SPACE), change);
		//provider.bindCommand(new KeyControl(Input.))
		
		a = new Actor(new Circle(100,100,25));
		v = new Vector2f(0,0);
	}
	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		a.move(v);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		GradientFill gf = new GradientFill(0, (float)-5, Color.pink, 0, (float)5, Color.orange);
		g.draw(a.getShape(), gf);
	}
	@Override
	public void mousePressed(int button, int x, int y) {
		clicky = "Pressed! Button #:" + button + ", x:" + x + ", y:" + y + ".";
	}
	@Override
	public void mouseReleased(int button, int x, int y) {
		clicky = "Released! Button #:" + button + ", x:" + x + ", y:" + y + ".";
	}
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		px = newx + 10;
		py = newy + 10;
		pos = "x: " + newx + ", y:" + newy;
	}
	public void controlPressed(Command command) {
		prov = "Pressed: " + command;
		v.set(v.getX(),(float)10);
	}
	public void controlReleased(Command command) {
		prov = "Released: " + command;
	}
	
	public void keyPressed(int key, char c) {
		prov = "Letter: " + c + "Number: " + key;
		switch(key) {
		case UP: v.set(v.getX(),(float)10);
		break;
		case LEFT: v.set((float)-10,v.getY());
		break;
		case DOWN: v.set(v.getX(),(float)-10);
		break;
		case RIGHT: v.set((float)10,v.getY());
		break;
		default: v.set(0,0);
		}
	}
	public void keyReleased(int key, char c) {
		switch(key) {
			case UP: case DOWN: v.set(v.getX(),0);
			case LEFT: case RIGHT: v.set(0,v.getY());
			default: break;
		}
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new SimpleSlickGame("Demo"));
			appgc.setDisplayMode(800, 600, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
