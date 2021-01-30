/******************************************************
 Cours:   LOG121
 Session: A2020
 Groupe:  01
 Projet: Laboratoire #1
 Étudiant(e)s: Pierre Amar Abdelli


 Professeur :
 Nom du fichier: XMLreader.java
 Date créé: 2020-09-10
 Date dern. modif. 2020-10-20
 *******************************************************
 Historique des modifications
 *******************************************************
 2020-10-20 Version finale
 *******************************************************/
package outils;

import batiments.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import reseau.Chemin;
import reseau.Icones;
import reseau.Position;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class XMLreader {


    private final ArrayList<Batiment> batimentSimulation = new ArrayList<>();
    private final ArrayList<Chemin> cheminList = new ArrayList<>();
    private final String filePath;
    private NodeList nodeListSimulation;
    private NodeList nodeListChemin;
    private NodeList nodeListSimulationChild;
    private NodeList nodeListIcones;
    private NodeList nodeListIntervalProd;

    public XMLreader(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Methode qui parcours le fichier XML et instancie toutes les composantes
     * @throws Exception
     */
    public void parseXMLfile() throws Exception {
        nodeListParser();
        createBatimentSimulation();
        createCheminSimulation();
        setDefaultValues();
        attachObservers();
    }

    //Accesseurs
    public ArrayList getBatimentSimulation() {
        return this.batimentSimulation;
    }

    public ArrayList getCheminList() {
        return this.cheminList;
    }

    /**
     * Methode qui attache les observateurs aux sujets
     */
    private void attachObservers() {
        for (Batiment e : batimentSimulation) {
            if (e instanceof Entrepot) {
                for (Batiment o : batimentSimulation) {
                    if (!(o instanceof Entrepot)) {
                        ((Entrepot) e).attach((Observer) o);
                    }
                }
            }
        }
    }

    /**
     * Methode qui parcours une liste XML et creer les batiments de la simulation
     */
    private void createBatimentSimulation() {

        for (int i = 0; i < nodeListSimulationChild.getLength(); i++) {
            Node node = nodeListSimulationChild.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals(Constantes.USINENODE)) {
                String nodeName = (nodeStringExtractor(node, Constantes.TYPE));
                int id = (Integer.parseInt(nodeStringExtractor(node, Constantes.ID)));
                int x = (Integer.parseInt(nodeStringExtractor(node, Constantes.X)));
                int y = (Integer.parseInt(nodeStringExtractor(node, Constantes.Y)));
                Position position = new Position(x, y);
                createBatiment(nodeName, id, position);

            }
        }
    }

    /**
     * Methode qui instancie un batiment
     * @param name : String le nom du batiment
     * @param id : int le ID
     * @param p : l'objet de type position du batiment
     */
    public void createBatiment(String name, int id, Position p) {

        switch (name) {

            case Constantes.USINE_MATIERE:
                Batiment batimentMatiere = new UsineMatiere(name);
                addBatiment(batimentMatiere, id, p);
                break;
            case Constantes.USINE_AILE:
                Batiment batimentAile = new UsineAile(name);
                addBatiment(batimentAile, id, p);
                break;
            case Constantes.USINE_ASSEMBLAGE:
                Batiment batimentAssemblage = new UsineAssemblage(name);
                addBatiment(batimentAssemblage, id, p);
                break;
            case Constantes.USINE_MOTEUR:
                Batiment batimentMoteur = new UsineMoteur(name);
                addBatiment(batimentMoteur, id, p);
                break;
            case Constantes.ENTREPOT:
                Batiment entrepot = new Entrepot(name);
                addBatiment(entrepot, id, p);
                break;
        }
    }

    /**
     * Methode qui rajoute les batiments a la liste de batiments
     * @param b : le Batiment a ajouter
     * @param id : le ID du batiment
     * @param p : la position du batiment
     */
    private void addBatiment(Batiment b, int id, Position p) {
        b.setId(id);
        b.setPosition(p);
        batimentSimulation.add(b);
    }

    /**
     * Methode qui instancie les valeurs par default des batiments
     */
    private void setDefaultValues() {

        for (Batiment b : batimentSimulation) {
            addIcones(b);
            addIntervalProduction(b);
        }
    }

    /**
     * Methode qui creer les chemins de la simulation a partir dune liste de Node
     */
    private void createCheminSimulation() {

        for (int i = 0; i < nodeListChemin.getLength(); i++) {
            Node node = nodeListChemin.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals(Constantes.CHEMINNODE)) {
                Chemin chemin = new Chemin(Integer.parseInt(nodeStringExtractor(node, Constantes.DE)),
                        Integer.parseInt(nodeStringExtractor(node, Constantes.VERS)));
                cheminList.add(chemin);
            }
        }
    }

    /**
     * Methode qui rajoute les intervals de productions au batiment
     * @param batiment
     */
    private void addIntervalProduction(Batiment batiment) {

        for (int a = 0; a < nodeListIntervalProd.getLength(); a++) {
            Node intervalProductionNode = nodeListIntervalProd.item(a);
            if (nodeStringExtractor(intervalProductionNode.getParentNode(),
                    Constantes.TYPE).equals(batiment.getName())) {
                batiment.setInterval_production(Integer.parseInt(intervalProductionNode.getTextContent()));
            }
        }
    }

    /**
     * Methode qui ajoute les icones correspond au batiment
     * @param batiment : batiment auquel on veut ajouter une icone
     */
    private void addIcones(Batiment batiment) {

        Icones icones = new Icones();

        for (int i = 0; i < nodeListIcones.getLength(); i++) {

            Node node = nodeListIcones.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE && nodeStringExtractor(node.getParentNode(), Constantes.TYPE).equals(batiment.getName())) {

                NodeList nodeList = node.getChildNodes();

                for (int j = 0; j < nodeList.getLength(); j++) {
                    Node iconeNode = nodeList.item(j);
                    if (iconeNode.getNodeType() == Node.ELEMENT_NODE) {
                        setIcones(icones, iconeNode);
                    }
                }
                batiment.setIcones(icones);
            }
        }
    }

    /**
     * Methode qui set les icones correspond au type de batiment
     * @param icones : Objet icones qui retient les icones du batiment
     * @param iconeNode : objet de type Node
     */
    private void setIcones(Icones icones, Node iconeNode) {
        String iconeName = (nodeStringExtractor(iconeNode, Constantes.TYPE));
        String iconePath = (nodeStringExtractor(iconeNode, Constantes.PATH));

        switch (iconeName) {

            case Constantes.VIDE:
                icones.setIconeVidePath(iconePath);
                break;
            case Constantes.UN_TIERS:
                icones.setIconeUnTiersPath(iconePath);
                break;
            case Constantes.DEUX_TIERS:
                icones.setIconeDeuxTiersPath(iconePath);
                break;
            case Constantes.PLEIN:
                icones.setIconePleinPath(iconePath);
                break;
        }
    }

    /**
     * methode qui parcours le fichier XML et creer des listes de nodes
     * @throws Exception
     */
    private void nodeListParser() throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(filePath));
        doc.getDocumentElement().normalize();

        nodeListSimulation = doc.getElementsByTagName(Constantes.SIMULATION);
        nodeListSimulationChild = simulationChild(nodeListSimulation);
        nodeListChemin = doc.getElementsByTagName(Constantes.CHEMINNODE);
        nodeListIcones = doc.getElementsByTagName(Constantes.ICONESNODE);
        nodeListIntervalProd = doc.getElementsByTagName(Constantes.INTERVALPROD);

    }

    /**
     * Mothode qui retire les enfants d'une liste de Node
     * @param nodeList : list de Node a retirer les enfants
     * @return une liste de Node
     */
    private NodeList simulationChild(NodeList nodeList) {

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals(Constantes.SIMULATION)) {
                return node.getChildNodes();
            }
        }
        return null;
    }

    /**
     * Methode qui retire la string d'une node
     * @param node : Node dont on veut retirer l'attribut
     * @param string : le type d'attribut dont on veut la String
     * @return String de l'attribut de la Node
     */
    private String nodeStringExtractor(Node node, String string) {

        return ((Element) node).getAttribute(string);
    }
}
