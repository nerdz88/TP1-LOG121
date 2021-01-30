/******************************************************
 Cours:   LOG121
 Session: A2020
 Groupe:  01
 Projet: Laboratoire #1
 Étudiant(e)s: Pierre Amar Abdelli


 Professeur :
 Nom du fichier: Chemin.java
 Date créé: 2020-09-10
 Date dern. modif. 2020-10-20
 *******************************************************
 Historique des modifications
 *******************************************************
 2020-10-20 Version finale
 *******************************************************/
package reseau;

import batiments.UsineMatiere;

public class Chemin {

    private final int de;
    private final int vers;

    public Chemin(int de, int vers) {
        this.de = de;
        this.vers = vers;
    }

    /**
     * Methode qui retourn la valeur de provenance d'une usine
     * @return : integer de la position
     */
    public int getDe() {
        return this.de;
    }

    /**
     * methode qui retourne la valeur de destination d'une usine
     * @return : integer de la position
     */
    public int getVers() {
        return this.vers;
    }


}
