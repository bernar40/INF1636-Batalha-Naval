package gameGUI;
import java.awt.*;
import javax.swing.*;

public class GameOverFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private GameOverPanel gop;
	
	public GameOverFrame(int wid, int hei, String winnerName) {
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-wid/2;
		int y=sa/2-hei/2;
		setBounds(x,y,wid,hei);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		gop = new GameOverPanel(winnerName);
		getContentPane().add(gop);
		setTitle("Batalha Naval");
	}
	
	public void dispose() {
		super.dispose();	
	}
}
