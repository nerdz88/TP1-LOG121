/******************************************************
 Cours:   LOG121
 Session: A2020
 Groupe:  01
 Projet: Laboratoire #1
 Étudiant(e)s: Pierre Amar Abdelli


 Professeur :
 Nom du fichier: VenteAleatoire.java
 Date créé: 2020-10-10
 Date dern. modif. 2020-10-20
 *******************************************************
 Historique des modifications
 *******************************************************
 2020-10-20 Version finale
 *******************************************************/
package batiments;

import java.util.Random;

public class VenteAleatoire implements Vente {

    private static final int min = 200;
    private static final int max = 1000;
    private static int interval = min;

    public VenteAleatoire() {
    }

    /**
     * Methode qui effectue la vente selon le valeur obtenue de la methode getRandom()
     * @param entrepot : l'entrepot dont on veut effectuer la vente
     */
    public void vendre(Entrepot entrepot) {

        if (entrepot.getCompteur() > interval) {
            getRandom();
            entrepot.sell();
            entrepot.resetCompteur();
        }
    }

    /**
     * Methode qui calcul de facon aleatoire un integer compris entre deux bornes
     */
    public void getRandom() {
        Random r = new Random();
        interval = r.nextInt((max - min) + 1 + min);
    }


}
