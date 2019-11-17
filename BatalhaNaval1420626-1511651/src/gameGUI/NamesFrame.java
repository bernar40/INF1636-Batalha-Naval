package gameGUI;
import java.awt.*;
import javax.swing.*;

public class NamesFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private NamesPanel np;
	
	public NamesFrame(int wid, int hei) {
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-wid/2;
		int y=sa/2-hei/2;
		setBounds(x,y,wid,hei);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		np = new NamesPanel();
		getContentPane().add(np);
		setTitle("Batalha Naval");
	}
	
	public void dispose() {
		getTextBox1();
		getTextBox2();
		super.dispose();	
	}
	
	public void getTextBox1() {
		np.getTextField1();
	}
	
	public void getTextBox2() {
		np.getTextField2();
	}
}
