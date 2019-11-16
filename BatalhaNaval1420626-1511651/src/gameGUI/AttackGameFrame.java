package gameGUI;
import java.awt.*;
import javax.swing.*;

import utils.Position;

public class AttackGameFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public AttackGameFrame(int wid, int hei) {
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-wid/2;
		int y=sa/2-hei/2;
		Position iniPosRightGrid = new Position (680, 50);
		Position finalPosRightGrid = new Position (1130, 500);
		Position iniPosLeftGrid = new Position (100, 50);
		Position finalPosLeftGrid = new Position (550, 500);
		setBounds(x,y,wid,hei);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(new AttackGamePanel(iniPosLeftGrid, finalPosLeftGrid, iniPosRightGrid, finalPosRightGrid));
		setTitle("Batalha Naval");
	}
}
