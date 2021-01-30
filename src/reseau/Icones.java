/******************************************************
 Cours:   LOG121
 Session: A2020
 Groupe:  01
 Projet: Laboratoire #1
 Étudiant(e)s: Pierre Amar Abdelli


 Professeur :
 Nom du fichier: Icones.java
 Date créé: 2020-09-10
 Date dern. modif. 2020-10-20
 *******************************************************
 Historique des modifications
 *******************************************************
 2020-10-20 Version finale
 *******************************************************/
package reseau;

public class Icones {

    private String iconeVidePath = "";
    private String iconeUnTiersPath = "";
    private String iconeDeuxTiersPath = "";
    private String iconePleinPath = "";

    public Icones() {
    }

    //Accesseurs et Mutateurs
    public String getIconeVidePath() {
        return this.iconeVidePath;
    }

    public void setIconeVidePath(String iconeVide) {
        this.iconeVidePath = iconeVide;
    }

    public String getIconeUnTiersPath() {
        return this.iconeUnTiersPath;
    }

    public void setIconeUnTiersPath(String iconeUnTiers) {
        this.iconeUnTiersPath = iconeUnTiers;
    }

    public String getIconeDeuxTiersPath() {
        return this.iconeDeuxTiersPath;
    }

    public void setIconeDeuxTiersPath(String iconeDeuxTiers) {
        this.iconeDeuxTiersPath = iconeDeuxTiers;
    }

    public String getIconePleinPath() {
        return this.iconePleinPath;
    }

    public void setIconePleinPath(String iconePlein) {
        this.iconePleinPath = iconePlein;
    }


}
