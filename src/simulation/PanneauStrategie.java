package simulation;

import batiments.Entrepot;
import batiments.Vente;
import batiments.VenteAleatoire;
import batiments.VenteFix;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

public class PanneauStrategie extends JPanel {

	private static final long serialVersionUID = 1L;
	private Entrepot entrepot;

	public PanneauStrategie() {

		ButtonGroup groupeBoutons = new ButtonGroup();
		JRadioButton strategie1 = new JRadioButton("Vente Aleatoire");
		JRadioButton strategie2 = new JRadioButton("Vente Fixe");
		
		JButton boutonConfirmer = new JButton("Confirmer");

		boutonConfirmer.addActionListener((ActionEvent e) -> {
			if(strategie1.isSelected()){
				Vente vente = new VenteAleatoire();
				entrepot.setVente(vente);
			} else if(strategie2.isSelected()) {
				Vente vente = new VenteFix();
				entrepot.setVente(vente);
			}
			System.out.println(getSelectedButtonText(groupeBoutons));
			// Fermer la fenêtre du composant
			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
		});

		JButton boutonAnnuler = new JButton("Annuler");

		boutonAnnuler.addActionListener((ActionEvent e) -> {
			// Fermer la fenêtre du composant
			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
		});

		groupeBoutons.add(strategie1);
		groupeBoutons.add(strategie2);		
		add(strategie1);
		add(strategie2);		
		add(boutonConfirmer);
		add(boutonAnnuler);

	}

	public void setEntrepot(Entrepot entrepot) { this.entrepot = entrepot; }

	/**
	 * Retourne le bouton sélectionné dans un groupe de boutons.
	 * @param groupeBoutons
	 * @return
	 */
	public String getSelectedButtonText(ButtonGroup groupeBoutons) {
		for (Enumeration<AbstractButton> boutons = groupeBoutons.getElements(); boutons.hasMoreElements();) {
			AbstractButton bouton = boutons.nextElement();
			if (bouton.isSelected()) {
				return bouton.getText();
			}
		}

		return null;
	}
}
