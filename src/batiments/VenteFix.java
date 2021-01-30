/******************************************************
 Cours:   LOG121
 Session: A2020
 Groupe:  01
 Projet: Laboratoire #1
 Étudiant(e)s: Pierre Amar Abdelli


 Professeur :
 Nom du fichier: VenteFix.java
 Date créé: 2020-10-10
 Date dern. modif. 2020-10-20
 *******************************************************
 Historique des modifications
 *******************************************************
 2020-10-20 Version finale
 *******************************************************/
package batiments;

public class VenteFix implements Vente {

    private int interval = 2000;

    public VenteFix() {
    }

    /**
     * Methode qui effectue une vente selon une valeur fix
     * @param entrepot : l'entrepot dont on veut effectuer la vente
     */
    public void vendre(Entrepot entrepot) {
        if (entrepot.getCompteur() > interval) {
            entrepot.sell();
            entrepot.resetCompteur();
        }
    }

}
