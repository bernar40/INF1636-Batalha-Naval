package gameGUI;
import java.awt.*;
import javax.swing.*;

import utils.Position;

public class MainGameFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainGameFrame(int wid, int hei) {
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-wid/2;
		int y=sa/2-hei/2;
		Position iniPos = new Position (680, 50);
		Position finalPos = new Position (1130, 500);
		setBounds(x,y,wid,hei);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(new MainGamePanel(iniPos, finalPos));
		setTitle("Batalha Naval");
	}
}
