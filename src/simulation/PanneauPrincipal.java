package simulation;

import reseau.Reseau;

import java.awt.*;

import javax.swing.*;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	private Reseau reseau = new Reseau(this);

	public PanneauPrincipal() {
	}

	public void addReseau(Reseau reseau) { this.reseau = reseau; }

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		reseau.tour();
		reseau.draw(g);

	}

}