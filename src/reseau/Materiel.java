/******************************************************
 Cours:   LOG121
 Session: A2020
 Groupe:  01
 Projet: Laboratoire #1
 Étudiant(e)s: Pierre Amar Abdelli


 Professeur :
 Nom du fichier: Materiel.java
 Date créé: 2020-09-10
 Date dern. modif. 2020-10-20
 *******************************************************
 Historique des modifications
 *******************************************************
 2020-10-20 Version finale
 *******************************************************/
package reseau;

import outils.Constantes;

import javax.swing.*;
import java.awt.*;

public class Materiel {

    private final String type;
    private Icon icone = new ImageIcon();
    private Position position;

    public Materiel(String type) {
        this.type = type;
        iconeChooser(type);
    }

    /**
     * Methode qui choisit le type d'icone dependement du type de materiel creer
     * @param type : String qui definit le type de materiel
     */
    public void iconeChooser(String type) {

        switch (type) {

            case Constantes.METAL:
                icone = new ImageIcon("src/ressources/metal.png");
                break;
            case Constantes.MOTEUR:
                icone = new ImageIcon("src/ressources/moteur.png");
                break;
            case Constantes.AILE:
                icone = new ImageIcon("src/ressources/aile.png");
                break;
            case Constantes.AVION:
                icone = new ImageIcon("src/ressources/avion.png");
                break;
        }

    }

    /**
     * Faire tour sur l'objet en appelant sa translation
     */
    public void tour() {
        position.translate();
    }

    /**
     * Methode qui dessine l'objet dans la fenetre
     * @param jpanel : Le panneau principal
     * @param g      : l'objet de type Graphics
     */
    public void draw(JPanel jpanel, Graphics g) {
        icone.paintIcon(jpanel, g, position.x(), position.y());
    }

    //Accesseurs
    public String getType() {
        return this.type;
    }

    public Position getPosition() {
        return this.position;
    }

    //Mutateurs
    public void setPosition(Position position) {
        this.position = position;
    }

}

