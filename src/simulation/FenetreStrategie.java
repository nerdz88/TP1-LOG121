package simulation;

import batiments.Entrepot;

import java.awt.Dimension;

import javax.swing.JFrame;

public class FenetreStrategie extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String TITRE_FENETRE = "Sélectionnez votre stratégie de vente";
	private static final Dimension DIMENSION = new Dimension(250, 100);
	private final PanneauStrategie panneauStrategie = new PanneauStrategie();

	public FenetreStrategie(Entrepot entrepot) {
		add(panneauStrategie);
		panneauStrategie.setEntrepot(entrepot);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(TITRE_FENETRE);
		setSize(DIMENSION);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}

}
