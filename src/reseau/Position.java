/******************************************************
 Cours:   LOG121
 Session: A2020
 Groupe:  01
 Projet: Laboratoire #1
 Étudiant(e)s: Pierre Amar Abdelli


 Professeur :
 Nom du fichier: Position.java
 Date créé: 2020-09-10
 Date dern. modif. 2020-10-20
 *******************************************************
 Historique des modifications
 *******************************************************
 2020-10-20 Version finale
 *******************************************************/
package reseau;

public class Position {

    private int x;
    private int y;
    private int dx = 1;
    private int dy = 0;

    public Position() {
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Accesseurs et mutateurs
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    /**
     * Methode qui effectue le deplacement de la position
     */
    public void translate() {
        this.x += dx;
        this.y += dy;
    }

}
