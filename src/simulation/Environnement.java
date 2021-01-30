package simulation;

import reseau.Reseau;

import javax.swing.SwingWorker;

public class Environnement extends SwingWorker<Object, String> {
	private final boolean actif = true;
	private static final int DELAI = 0;
	
	@Override
	protected Object doInBackground() throws Exception {
		while(actif) {
			Thread.sleep(DELAI);

			firePropertyChange("TEST", null, "Ceci est un test");
		}
	}

}