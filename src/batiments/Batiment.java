/******************************************************
 Cours:   LOG121
 Session: A2020
 Groupe:  01
 Projet: Laboratoire #1
 Étudiant(e)s: Pierre Amar Abdelli


 Professeur :
 Nom du fichier: Batiment.java
 Date créé: 2020-09-10
 Date dern. modif. 2020-10-20
 *******************************************************
 Historique des modifications
 *******************************************************
 2020-10-20 Version finale
 *******************************************************/
package batiments;

import outils.Constantes;
import reseau.Icones;
import reseau.Materiel;
import reseau.Position;
import reseau.Reseau;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Batiment {


    private Icones icones;
    private String name = "";
    private final ArrayList<Materiel> entree = new ArrayList<>();
    private final ArrayList<Materiel> sortie = new ArrayList<>();
    private Position position;
    private int id;
    private int interval_production;

    public Batiment(String name) {

        this.name = name;

    }

    //Accesseurs
    public String getName() {
        return this.name;
    }

    public Icones getIcones() {
        return this.icones;
    }

    public ArrayList getSortie() {
        return this.sortie;
    }

    public ArrayList getEntree() {
        return this.entree;
    }

    public Position getPosition() {
        return this.position;
    }

    public int getId() {
        return this.id;
    }

    public int getInterval_production() {
        return this.interval_production;
    }

    //Mutateurs
    public void setIcones(Icones icones) {
        this.icones = icones;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void addEntree(Materiel materiel) {
        entree.add(materiel);
    }

    public void setInterval_production(int interval_production) {
        this.interval_production = interval_production;
    }

    public void tour(Reseau reseau) {
    }

    /**
     * methode pour dessiner un objet dans le reseau
     * @param panel : panneau Principal de type JPanel
     * @param g : l'objet de type Graphics
     */
    public void draw(JPanel panel, Graphics g) {
    }

    /**
     * Methode qui effectue une copie profonde d'une position
     * @param position : la position a copier
     * @return : un objet de type Position qui est une copie de la position en parametre
     */
    public Position positionCopy(Position position) {

        Position newPosition = new Position();
        newPosition.setX(position.x() - Constantes.OFFSET_ICONE);
        newPosition.setY(position.y() - Constantes.OFFSET_ICONE);
        return newPosition;

    }
}
