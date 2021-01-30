/******************************************************
 Cours:   LOG121
 Session: A2020
 Groupe:  01
 Projet: Laboratoire #1
 Étudiant(e)s: Pierre Amar Abdelli


 Professeur :
 Nom du fichier: Entrepot.java
 Date créé: 2020-09-10
 Date dern. modif. 2020-10-20
 *******************************************************
 Historique des modifications
 *******************************************************
 2020-10-20 Version finale
 *******************************************************/
package batiments;

import outils.Constantes;
import reseau.Reseau;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Entrepot extends Batiment implements Subject {

    private final double CAPACITE = 5;
    private List<Observer> listObservers = new LinkedList<>();
    private boolean stateFull = false;
    private int compteur = 0;
    private Vente vente = new VenteFix();

    public Entrepot(String name) {

        super(name);

    }

    /**
     * Methode qui effectue la notion de tour
     * @param reseau : le reseau sur lequel l'usine fait le tour
     */
    public void tour(Reseau reseau) {

        if (super.getEntree().size() >= CAPACITE) {
            stateFull = true;
            notifyObservers();
        } else {
            stateFull = false;
            notifyObservers();
        }
        if (super.getEntree().size() > 0) {
            compteur++;
        }
        vente.vendre(this);
    }

    //Accesseurs et Mutateurs
    public void sell() {
        super.getEntree().remove(0);
        System.out.println("JAI VENDU");
    }

    public void resetCompteur() {
        this.compteur = 0;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
    }

    public int getCompteur() {
        return this.compteur;
    }

    /**
     * Methode qui dessine une usine selon son icone
     * @param panel : le panneau principal JPanel sur lequel un se dessine
     * @param g : un objet de type Graphics
     */
    public void draw(JPanel panel, Graphics g) {

        Icon icon = new ImageIcon(super.getIcones().getIconePleinPath());

        if (super.getEntree().size() > CAPACITE / 3 && super.getEntree().size() < 2 * CAPACITE / 3) {
            icon = new ImageIcon(super.getIcones().getIconeUnTiersPath());
        } else if (super.getEntree().size() > 2 * CAPACITE / 3 && super.getEntree().size() < CAPACITE) {
            icon = new ImageIcon(super.getIcones().getIconeDeuxTiersPath());
        } else if (super.getEntree().size() >= CAPACITE) {
            icon = new ImageIcon(super.getIcones().getIconePleinPath());
        } else if (super.getEntree().size() < CAPACITE / 3) {
            icon = new ImageIcon(super.getIcones().getIconeVidePath());
        }

        icon.paintIcon(panel, g, super.getPosition().x() - Constantes.OFFSET_ICONE,
                super.getPosition().y() - Constantes.OFFSET_ICONE);
    }

    /**
     * Methode qui ajoute une observateur a la liste d'observateurs
     * @param o : objet de type Observateur
     */
    public void attach(Observer o) {
        listObservers.add(o);
    }

    /**
     * Methode qui met a jour les observateurs de la liste d'observateurs
     */
    @Override
    public void notifyObservers() {
        for (Observer o : listObservers) {
            o.update(stateFull);
        }
    }

}
