/******************************************************
 Cours:   LOG121
 Session: A2020
 Groupe:  01
 Projet: Laboratoire #1
 Étudiant(e)s: Pierre Amar Abdelli


 Professeur :
 Nom du fichier: UsineMoteur.java
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

public class UsineMoteur extends Batiment implements Observer {

    private final int quantite = 4;
    private int compteur = 0;
    private Boolean entrepotFull = false;

    public UsineMoteur(String name) {

        super(name);
    }

    /**
     * Methode qui effectue la notion de tour
     * @param reseau le reseau sur lequel l'usine fait le tour
     */
    public void tour(Reseau reseau) {

        if (entrepotFull) {

        } else if (compteur > super.getInterval_production() && super.getEntree().size() >= quantite) {
            Materiel m = new Materiel(Constantes.MOTEUR);
            remove(super.getEntree());
            m.setPosition(positionCopy(super.getPosition()));
            m.getPosition().setDx(-1);
            m.getPosition().setDy(-1);
            reseau.addMateriel(m);
            compteur = 0;
        } else if (super.getEntree().size() >= quantite) {
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
        int min = entreList.size() - quantite;
        for (int i = entreList.size(); i > min; i--) {
            entreList.remove(i - 1);
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
