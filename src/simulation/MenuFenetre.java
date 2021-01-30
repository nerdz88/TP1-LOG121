package simulation;

import batiments.Entrepot;
import outils.XMLreader;
import reseau.Reseau;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuFenetre extends JMenuBar {

	private static final long serialVersionUID = 1L;
	private static final String MENU_FICHIER_TITRE = "Fichier";
	private static final String MENU_FICHIER_CHARGER = "Charger";
	private static final String MENU_FICHIER_QUITTER = "Quitter";
	private static final String MENU_SIMULATION_TITRE = "Simulation";
	private static final String MENU_SIMULATION_CHOISIR = "Choisir";
	private static final String MENU_AIDE_TITRE = "Aide";
	private static final String MENU_AIDE_PROPOS = "À propos de...";
	private final PanneauPrincipal panneauPrincipal;
	private Entrepot entrepot;

	public MenuFenetre(PanneauPrincipal panneauPrincipal) {

		this.panneauPrincipal = panneauPrincipal;
		ajouterMenuFichier();
		ajouterMenuSimulation();
		ajouterMenuAide();
	}

	/**
	 * Créer le menu de Fichier
	 */
	private void ajouterMenuFichier() {
		JMenu menuFichier = new JMenu(MENU_FICHIER_TITRE);
		JMenuItem menuCharger = new JMenuItem(MENU_FICHIER_CHARGER);
		JMenuItem menuQuitter = new JMenuItem(MENU_FICHIER_QUITTER);

		menuCharger.addActionListener((ActionEvent e) -> {
			JFileChooser fileChooser = new JFileChooser("D:/Documents/ETS/LOG121/TP1-LOG121/src/ressources/");
			fileChooser.setDialogTitle("Sélectionnez un fichier de configuration");
			fileChooser.setAcceptAllFileFilterUsed(false);
			// Créer un filtre
			FileNameExtensionFilter filtre = new FileNameExtensionFilter(".xml", "xml");
			fileChooser.addChoosableFileFilter(filtre);

			int returnValue = fileChooser.showOpenDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {

				File selectedFile = fileChooser.getSelectedFile();

				XMLreader xmlreader = new XMLreader(selectedFile.getPath());
				try {
					xmlreader.parseXMLfile();
					Reseau reseau = new Reseau(panneauPrincipal);
					for(int i = 0; i < xmlreader.getBatimentSimulation().size(); i++){
						if(xmlreader.getBatimentSimulation().get(i) instanceof Entrepot){
							entrepot = (Entrepot) xmlreader.getBatimentSimulation().get(i);
						}
					}
					reseau.addBatiment(xmlreader.getBatimentSimulation());
					reseau.addChemin(xmlreader.getCheminList());
					panneauPrincipal.addReseau(reseau);

				} catch (Exception exception) {
					exception.printStackTrace();
				}
				System.out.println(selectedFile.getAbsolutePath());

			}
		});
		
		menuQuitter.addActionListener((ActionEvent e) -> {
			System.exit(0);
		});

		menuFichier.add(menuCharger);
		menuFichier.add(menuQuitter);

		add(menuFichier);

	}


	/**
	 * Créer le menu de Simulation
	 */
	private void ajouterMenuSimulation() {
		JMenu menuSimulation = new JMenu(MENU_SIMULATION_TITRE);
		JMenuItem menuChoisir = new JMenuItem(MENU_SIMULATION_CHOISIR);
		menuSimulation.add(menuChoisir);

		menuChoisir.addActionListener((ActionEvent e) -> {
			// Ouvrir la fenêtre de sélection
			// TODO - Récupérer la bonne stratégie de vente
			new FenetreStrategie(entrepot);

		});
		add(menuSimulation);

	}

	/**
	 * Créer le menu Aide
	 */
	private void ajouterMenuAide() {
		JMenu menuAide = new JMenu(MENU_AIDE_TITRE);
		JMenuItem menuPropos = new JMenuItem(MENU_AIDE_PROPOS);
		menuAide.add(menuPropos);

		menuPropos.addActionListener((ActionEvent e) -> {
			JOptionPane.showMessageDialog(null,
					"<html><p>Application simulant une chaine de production d'avions.</p>" + "<br>"
							+ "<p>&copy; &nbsp; 2017 &nbsp; Ghizlane El Boussaidi</p>"
							+ "<p>&copy; &nbsp; 2017 &nbsp; Dany Boisvert</p>"
							+ "<p>&copy; &nbsp; 2017 &nbsp; Vincent Mattard</p>" + "<br>"
							+ "<p>&Eacute;cole de technologie sup&eacute;rieure</p></html>");
		});
		add(menuAide);
	}

}
