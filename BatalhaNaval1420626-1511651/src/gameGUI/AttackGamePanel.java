package gameGUI;

import utils.*;
import javax.swing.*;
import models.*;
import rules.*;
import rules.Weapons.WeaponType;

import java.awt.*;
import java.awt.event.*;

public class AttackGamePanel extends JPanel implements MouseListener {
	public GridGraphics currentPlayerGrid;
	public GridGraphics oppositePlayerGrid;
	public Position iniPosLeftGrid;
	public Position finalPosLeftGrid;
	public Position iniPosRightGrid;
	public Position finalPosRightGrid;
	private boolean weaponWasHit = false;
	private static final long serialVersionUID = 1L;
	private Rules rules;
	private Font font = new Font("Serif", Font.PLAIN, 15);
	private JButton confirmButton;
	private JButton saveButton;
	private Boolean alreadyAttacked = false;
	private WeaponType hitWeapon;

	public AttackGamePanel(Position iniPosLeftGrid, Position finalPosLeftGrid, Position iniPosRightGrid,
			Position finalPosRightGrid) {
		this.iniPosLeftGrid = iniPosLeftGrid;
		this.finalPosLeftGrid = finalPosLeftGrid;
		this.iniPosRightGrid = iniPosRightGrid;
		this.finalPosRightGrid = finalPosRightGrid;

		addMouseListener(this);

		rules = Rules.getInstance();

		currentPlayerGrid = new GridGraphics(iniPosLeftGrid, finalPosLeftGrid, rules.getCurrentPlayerGrid());
		oppositePlayerGrid = new GridGraphics(iniPosRightGrid, finalPosRightGrid, rules.getOppositePlayerGrid());

		oppositePlayerGrid.buildGrid();
		currentPlayerGrid.buildGrid();

		confirmButton = new JButton("Termina a vez");
		confirmButton.setLocation(560, 10);
		confirmButton.setBounds(560, 10, 150, 20);
		confirmButton.addActionListener(GameController.getInstance());
		confirmButton.setVisible(false);
		this.add(confirmButton);
		confirmButton.repaint();

		saveButton = new JButton("Salvar Jogo");
		saveButton.setLocation(700, 10);
		saveButton.setBounds(700, 10, 150, 20);
		saveButton.addActionListener(GameController.getInstance());
		saveButton.setVisible(true);
		this.add(saveButton);
		saveButton.repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		currentPlayerGrid.paintGrid(g, false);
		oppositePlayerGrid.paintGrid(g, true);

		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString("Sua vez de atacar, " + rules.getCurrentPlayerName(), 350, 25);

		confirmButton.setLocation(560, 10);
		confirmButton.setBounds(560, 10, 150, 20);
		confirmButton.repaint();

		saveButton.setLocation(700, 10);
		saveButton.setBounds(700, 10, 150, 20);
		saveButton.repaint();

		if (weaponWasHit) {
			g.setFont(new Font("Arial", Font.BOLD, 25));
			g.setColor(Color.RED);
			g.drawString("You Hit a " + hitWeapon + "!", 500, 550);
		}

	}

	public void mouseClicked(MouseEvent e) {
		if (alreadyAttacked)
			return;

		int x = e.getX(), y = e.getY();

		if (x < iniPosRightGrid.getX() || x > finalPosRightGrid.getX())
			return;

		if (y < iniPosRightGrid.getY() || y > finalPosRightGrid.getY())
			return;

		x -= iniPosRightGrid.getX();
		y -= iniPosRightGrid.getY();

		int j = x / 30;
		int i = y / 30;

		hitWeapon = rules.getWeaponFromOpposition(new Position(i, j));
		if (rules.hitWeaponInPositionInGrid(new Position(i, j))) {
			if (hitWeapon != null)
				weaponWasHit = true;
			if (rules.isGameOver()) {
				GameController.getInstance().endGame(rules.getCurrentPlayerName());
				return;
			}

			System.out.printf("Clicked %d, %d\n", i, j);

			currentPlayerGrid = new GridGraphics(iniPosLeftGrid, finalPosLeftGrid, rules.getCurrentPlayerGrid());
			oppositePlayerGrid = new GridGraphics(iniPosRightGrid, finalPosRightGrid, rules.getOppositePlayerGrid());
			oppositePlayerGrid.buildGrid();
			currentPlayerGrid.buildGrid();
			paintComponent(getGraphics());

			rules.hit();

			if (rules.attackOver())
				alreadyAttacked = true;
			else
				alreadyAttacked = false;
		}

		if (alreadyAttacked)
			confirmButton.setVisible(true);
		else
			confirmButton.setVisible(false);
		this.add(confirmButton);
		this.add(saveButton);
		weaponWasHit = false;

	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}