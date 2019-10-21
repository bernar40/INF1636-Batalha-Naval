package gameGUI;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class NamesFrame extends JFrame{
	
	public NamesFrame(int wid, int hei, ActionListener e) {
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-wid/2;
		int y=sa/2-hei/2;
		setBounds(x,y,wid,hei);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(new NamesPanel(e));
		setTitle("Batalha Naval");
	}
}
