/******************************************************
 Cours:   LOG121
 Session: A2020
 Groupe:  01
 Projet: Laboratoire #1
 Étudiant(e)s: Pierre Amar Abdelli


 Professeur :
 Nom du fichier: UsineMatiere.java
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
import reseau.Position;
import reseau.Reseau;

import javax.swing.*;
import java.awt.*;

public class UsineMatiere extends Batiment implements Observer {

    private int compteur = 0;
    private boolean entrepotFull = false;

    public UsineMatiere(String name) {

        super(name);

    }

    /**
     * Methode qui effectue la notion de tour
     * @param reseau : le reseau sur lequel l'usine fait le tour
     */
    public void tour(Reseau reseau) {

        if (entrepotFull) {

        } else if (compteur > super.getInterval_production()) {
            Materiel m = new Materiel(Constantes.METAL);
            m.setPosition(positionCopy(super.getPosition()));
            chekID(this.getId(), m);
            reseau.addMateriel(m);
            compteur = 0;
        } else {
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
        } else if (compteur == 0) {
            icon = new ImageIcon(super.getIcones().getIconeVidePath());
        }

        icon.paintIcon(panel, g, super.getPosition().x() - Constantes.OFFSET_ICONE,
                super.getPosition().y() - Constantes.OFFSET_ICONE);
    }

    /**
     * Methode qui effectue une copie profonde d'une position
     * @param position : un objet de type Position que l'on veut copier
     * @return
     */
    public Position positionCopy(Position position) {

        Position newPosition = new Position();
        newPosition.setX(position.x() - Constantes.OFFSET_ICONE);
        newPosition.setY(position.y() - Constantes.OFFSET_ICONE);
        return newPosition;

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
