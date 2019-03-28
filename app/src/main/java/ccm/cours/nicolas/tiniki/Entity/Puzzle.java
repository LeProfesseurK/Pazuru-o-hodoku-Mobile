package ccm.cours.nicolas.tiniki.Entity;

import java.util.ArrayList;
import java.util.Map;

public abstract class Puzzle {

    private Integer id;

    private String nom;

    private String enonce;

    private String solution;

    // Donnée supplémentaire : {URL Image, Choix multiple ([choix:”toto”,choix:”tata”...], etc...}
    // Transformé en : <"URL image", ".....">, <"Choix", ["toto", "tata"]> ...
    private Map<String, Object> contenu;

    // Récupére sous la forme : {indice:[indice1: “rouge”, indice2: “noir”, ...]}
    // Transformé en : ["rouge", "noir",...]
    private ArrayList<String> indices;

    private String type;

    private String difficulte;

    private Integer exp;

    private Integer piece;


    // Abstract function

    public abstract void lanceResolution();



    // Getter / Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEnonce() {
        return enonce;
    }

    public void setEnonce(String enonce) {
        this.enonce = enonce;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Map<String, Object> getContenu() {
        return contenu;
    }

    public void setContenu(Map<String, Object> contenu) {
        this.contenu = contenu;
    }

    public ArrayList<String> getIndices() {
        return indices;
    }

    public void setIndices(ArrayList<String> indices) {
        this.indices = indices;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getPiece() {
        return piece;
    }

    public void setPiece(Integer piece) {
        this.piece = piece;
    }
}
