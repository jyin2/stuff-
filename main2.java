package start;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
public class main2 {
	public static void main(String[]args) {
		main2 m = new main2();
		m.run();
	}
	
	private Animation a;
	private ScreenManager s;
	private Image bg;
	
	
	private static final DisplayMode modes1[] = {
			new DisplayMode(800,600,32,0),
			new DisplayMode(800,600,24,0),
			new DisplayMode(800,600,16,0),
			new DisplayMode(800,480,32,0),
			new DisplayMode(800,480,32,0),
			new DisplayMode(800,480,32,0),
	};
	//load images and add scenes
	public void loadImages() {
		bg = new ImageIcon("C:\\laks.PNG").getImage();
		Image face1 = new ImageIcon("C:\\meme4.PNG").getImage();
		Image face2 = new ImageIcon("C:\\meme1.PNG").getImage();
		
		a = new Animation();
		a.addScene(face1, 250);
		a.addScene(face2, 250);
	}
	//main method called from main
	public void run() {
		s = new ScreenManager();
		try {
			DisplayMode dm = s.findFirstCompatibleMode(modes1);
			s.setFullScreen(dm);
			loadImages();
			movieLoop();
		}finally {
			s.restoreScreen();
		}
	}
	
	// play movie
	public void movieLoop() {
		long startingTime = System.currentTimeMillis();
		long cTime = startingTime;
		while(cTime-startingTime < 5000) {
			long timePassed = System.currentTimeMillis() - cTime;
			cTime += timePassed;
			a.update(timePassed);
			
			//draw and update screen
			Graphics2D g = s.getGraphics();
			draw(g);
			g.dispose();
			s.update();
			
			try {
				Thread.sleep(20);
			}catch(Exception ex) {
				System.out.print("I can't sleep!");
			}
		}
	}
	// draws graphics
	public void draw(Graphics g) {
		g.drawImage(bg, 0, 0, null);
		g.drawImage(a.getImage(), 0, 0, null);
	}
}
