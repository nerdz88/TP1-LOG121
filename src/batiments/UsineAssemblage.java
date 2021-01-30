/******************************************************
 Cours:   LOG121
 Session: A2020
 Groupe:  01
 Projet: Laboratoire #1
 Étudiant(e)s: Pierre Amar Abdelli


 Professeur :
 Nom du fichier: UsineAssemblage.java
 Date créé: 2020-09-10
 Date dern. modif. 2020-10-20
 *******************************************************
 Historique des modifications
 *******************************************************
 2020-10-20 Version finale
 *******************************************************/
package batiments;

import outils.Constantes;
import reseau.Materiel;
import reseau.Reseau;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UsineAssemblage extends Batiment implements Observer {

    private int compteur = 0;
    private final int nbAile = 2;
    private final int nbMoteur = 4;
    private Boolean entrepotFull = false;

    public UsineAssemblage(String name) {

        super(name);
    }

    /**
     * Methode qui effectue la notion de tour
     * @param reseau : le reseau sur lequel l'usine fait le tour
     */
    public void tour(Reseau reseau) {

        if (entrepotFull) {

        } else if (compteur > super.getInterval_production() && super.getEntree().size() >= nbMoteur + nbAile) {
            Materiel m = new Materiel(Constantes.AVION);
            remove(super.getEntree());
            m.setPosition(positionCopy(super.getPosition()));
            chekID(this.getId(), m);
            reseau.addMateriel(m);
            compteur = 0;
        } else if (super.getEntree().size() >= nbAile + nbMoteur) {
            compteur++;
        }
    }

    /**
     * Methode qui dessine une usine selon son icone
     * @param panel : le panneau principal JPanel sur lequel un se dessine
     * @param g : un objet de type Graphics
     */
    public void draw(JPanel panel, Graphics g) {

        Icon icon = new ImageIcon(super.getIcones().getIconeVidePath());

        if (compteur < super.getInterval_production() / 3 && compteur > 0) {
            icon = new ImageIcon(super.getIcones().getIconeUnTiersPath());
        } else if (compteur < 2 * super.getInterval_production() / 3 && compteur > super.getInterval_production() / 3) {
            icon = new ImageIcon(super.getIcones().getIconeDeuxTiersPath());
        } else if (compteur > 2 * super.getInterval_production() / 3) {
            icon = new ImageIcon(super.getIcones().getIconePleinPath());
        }

        icon.paintIcon(panel, g, super.getPosition().x() - Constantes.OFFSET_ICONE,
                super.getPosition().y() - Constantes.OFFSET_ICONE);
    }

    /**
     * methode qui enleve le nombre d'element de la liste d'element en fonction de la quantite requise
     * @param entreList : list de materiel que contient la classe
     */
    private void remove(ArrayList entreList) {
        int min = entreList.size() - nbMoteur - nbAile;
        for (int i = entreList.size(); i > min; i--) {
            entreList.remove(i - 1);
        }
    }

    /**
     * Methode qui verifie le ID d'un batiment pour decider du deplacement d'un materiel
     * @param id : le ID du batiment a verifier
     * @param m : le materiel dont on veut attacher un deplacment
     */
    public void chekID(int id, Materiel m) {

        switch (id) {

            case 13:
                m.getPosition().setDx(-1);
                m.getPosition().setDy(-1);
                break;
            case 21:
                m.getPosition().setDx(-1);
                m.getPosition().setDy(1);
                break;
            default:
                m.getPosition().setDx(1);
                m.getPosition().setDy(0);
        }

    }

    /**
     * methode de l'interface observateur qui se met a jour
     * @param bool : la valeur recu du sujet
     */
    @Override
    public void update(Boolean bool) {
        this.entrepotFull = bool;
    }
}
