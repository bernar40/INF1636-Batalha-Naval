package models;
import java.awt.*;
import javax.swing.*;

public class MainGameFrame extends JFrame {

	public MainGameFrame(int wid, int hei) {
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-wid/2;
		int y=sa/2-hei/2;
		setBounds(x,y,wid,hei);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(new MainGamePanel());
		setTitle("Batalha Naval");
	}
}
