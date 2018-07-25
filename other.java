package start;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
public class other extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static void main(String[]args) {
		DisplayMode dm = new DisplayMode(800,600,16, DisplayMode.REFRESH_RATE_UNKNOWN);
		other b = new other();
		b.run(dm);
	}
	
	private screen screen;
	private Image bg;
	private Animation a;
	
	// loads pictures from computer to java and adds scene
	public void loadPics() {
		bg = new ImageIcon("C:\\images.jpg").getImage();
		Image face1 = new ImageIcon("C:\\laks.PNG").getImage();
		Image face2 = new ImageIcon("C:\\meme4.PNG").getImage();
		a = new Animation();
		a.addScene(face1, 250);
		a.addScene(face2, 250);
	}
	//main engine to run
	public void run(DisplayMode d) {
		screen = new screen();
		try {
			screen.setFullScreen(d, new JFrame());
			loadPics();
			movieLoop();
		}finally {
			screen.restoreScreen();
		}
	}
	//main movie loop
	public void movieLoop() {
		long startingTime = System.currentTimeMillis();
		long cumTime = startingTime;
		
		while(cumTime - startingTime <5000) {
			long timePassed = System.currentTimeMillis() - cumTime;
			cumTime += timePassed;
			a.update(timePassed);
			
			Graphics g = screen.getFullScreenWindow().getGraphics();
			draw(g);
			g.dispose();
			
			try {
				Thread.sleep(20);
			}catch(Exception e) {
				
			}
			
		}

	}
	//draw method
	public void draw(Graphics g){
		g.drawImage(bg, 0,0,null);
		g.drawImage(a.getImage(), 0, 0, null);
	}
}