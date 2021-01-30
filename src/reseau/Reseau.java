/******************************************************
 Cours:   LOG121
 Session: A2020
 Groupe:  01
 Projet: Laboratoire #1
 Étudiant(e)s: Pierre Amar Abdelli


 Professeur :
 Nom du fichier: Reseau.java
 Date créé: 2020-09-10
 Date dern. modif. 2020-10-20
 *******************************************************
 Historique des modifications
 *******************************************************
 2020-10-20 Version finale
 *******************************************************/
package reseau;

import batiments.Batiment;
import outils.Constantes;
import simulation.PanneauPrincipal;

import java.awt.*;
import java.util.ArrayList;

public class Reseau {

    private ArrayList<Chemin> cheminList = new ArrayList<>();
    private ArrayList<Batiment> batimentList = new ArrayList<>();
    private ArrayList<Materiel> materielsList = new ArrayList<>();
    private PanneauPrincipal panneauPrincipal;

    public Reseau(PanneauPrincipal panneauPrincipal) {
        this.panneauPrincipal = panneauPrincipal;
    }

    public void addBatiment(ArrayList batimentList) {
        this.batimentList = batimentList;
    }

    public void addChemin(ArrayList cheminList) {
        this.cheminList = cheminList;
    }

    public void addMateriel(Materiel m) {
        this.materielsList.add(m);
    }

    /**
     * Methode qui appel la methode draw() de chaque composantes du reseau soit les chemins,
     * les materiels et les batiments
     * @param g : objet de type Graphics
     */
    public void draw(Graphics g) {
        drawChemin(g);
        drawMateriels(g);
        drawBatiments(g);
    }

    /**
     * Methode qui appel la notion de tour() de chaque batiment et chaque materiel
     * et qui verifie si il y une colision sur le reseau
     */
    public void tour() {
        for (Batiment b : batimentList) b.tour(this);
        for (Materiel m : materielsList) m.tour();
        checkColision();
    }

    /**
     * Methode qui appel la methode draw() de chaque materiel
     * @param g : Objet de type Graphics
     */
    public void drawMateriels(Graphics g) {
        for (Materiel m : materielsList)
            m.draw(panneauPrincipal, g);
    }

    /**
     * Methode qui dessine les chemins sur le reseau
     * @param g : Objet de type Graphics
     */
    public void drawChemin(Graphics g) {

        for (Chemin c : cheminList) {
            Position positionDe = new Position();
            Position positionVers = new Position();
            extractDeVers(c, positionDe, positionVers);
            g.setColor(Color.BLACK);
            g.drawLine(positionDe.x(), positionDe.y(), positionVers.x(), positionVers.y());
        }

    }

    /**
     * Methode qui retire la position de depart et d'arriver d'une chemin
     * @param c : objet de type chemin
     * @param positionDe : Objet de type position du depart de ligne
     * @param positionVers : Objet de type position de la fin de ligne
     */
    private void extractDeVers(Chemin c, Position positionDe, Position positionVers) {
        for (Batiment b : batimentList) {
            setPositionDe(c, positionDe, b);
            setPositionVers(c, positionVers, b);
        }
    }

    /**
     * Methode qui determine la position de depart d'un chemin
     * @param c : le chemin a associer
     * @param positionDe : la position de depart
     * @param b : le batiment de depart
     */
    private void setPositionDe(Chemin c, Position positionDe, Batiment b) {
        if (b.getId() == c.getDe()) {
            positionDe.setX(b.getPosition().x());
            positionDe.setY(b.getPosition().y());
        }
    }

    /**
     * Methode qui determine la position de depart d'un chemin
     * @param c : le chemin a associer
     * @param positionVers : la position de d'arrive
     * @param b : le batiment de d'arrive
     */
    private void setPositionVers(Chemin c, Position positionVers, Batiment b) {
        if (b.getId() == c.getVers()) {
            positionVers.setX(b.getPosition().x());
            positionVers.setY(b.getPosition().y());
        }
    }

    /**
     * Methode qui appel les methodes draw() de chaque batiment
     * @param g : objet de type Graphics
     */
    public void drawBatiments(Graphics g) {

        for (Batiment b : batimentList) {
            b.draw(panneauPrincipal, g);
        }
    }

    /**
     * Methode qui verifie si deux objet son en colision sur le reseau
     */
    public void checkColision() {

        for (Batiment b : batimentList) {
            for (int i = 0; i < materielsList.size(); i++) {
                if (materielMove(i, b)) {
                    i--;
                }
            }
        }
    }

    /**
     * Fonction qui deplace un materiel du reseau et le place dans une usine
     *
     * @param i : index de l'objet de la liste
     * @param b : un objet de type Batiment
     * @return : boolean si un objet a ete deplace ou non
     */
    private boolean materielMove(int i, Batiment b) {

        if (b.getPosition().x() - Constantes.OFFSET_ICONE == materielsList.get(i).getPosition().x()
                && b.getPosition().y() - Constantes.OFFSET_ICONE == materielsList.get(i).getPosition().y()) {
            b.addEntree(materielsList.get(i));
            materielsList.remove(i);
            return true;
        }
        return false;
    }
}
