package ccm.cours.nicolas.tiniki.Entity;

public class Zone {

    private Integer id;
    private String libelle;
    private Position positionCentre;
    private Integer rayon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Position getPositionCentre() {
        return positionCentre;
    }

    public void setPositionCentre(Position positionCentre) {
        this.positionCentre = positionCentre;
    }

    public Integer getRayon() {
        return rayon;
    }

    public void setRayon(Integer rayon) {
        this.rayon = rayon;
    }
}
