package start;

	import java.awt.*;
	import javax.swing.JFrame;
	import javax.swing.ImageIcon;
	public class images extends JFrame {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public static void main(String[]args) {
			DisplayMode dm = new DisplayMode(800,600,16, DisplayMode.REFRESH_RATE_UNKNOWN);
			images i = new images();
			i.run(dm);
		}
		
		private screen s;
		private Image bg;
		private Image pic;
		private boolean loaded;
		
		//run method
		public void run(DisplayMode dm) {
			JFrame frame = new JFrame("TestFrame");
			frame.getContentPane().setBackground(Color.BLUE);
			setForeground(Color.WHITE);
			setFont(new Font("Arial", Font.PLAIN, 24));
			loaded = false;
			
			 s = new screen();
			try {
				s.setFullScreen(dm, this);
				loadpics();
				try {
					Thread.sleep(5000);
				}catch(Exception ex) {
					
				}
			}finally {
				s.restoreScreen();
			}
		}
		//loads pictures
		public void loadpics() {
			bg = new ImageIcon("C:\\laks.PNG").getImage();
			pic = new ImageIcon("C:\\meme4.PNG").getImage();
			loaded = true;
			repaint();
		}
		public void paint(Graphics g) {
			if(g instanceof Graphics2D) {
				Graphics2D g2 = (Graphics2D)g;
				g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			}
			 if(loaded) {
				 g.drawImage(bg,170,180,null);
				 g.drawImage(pic, 170, 180, null);
			 }
		}
	}


