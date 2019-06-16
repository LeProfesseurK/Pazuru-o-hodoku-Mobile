package ccm.cours.nicolas.tiniki.Entity;

public class Zone {

    private Integer id;
    private String libelle;
  //  private Position positionCentre;
    private Long latitude;
    private Long longitude;
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
        Position positionCentre = new Position();
        positionCentre.setLatitude(this.getLatitude());
        positionCentre.setLongitude(this.getLongitude());
        return positionCentre;
    }

    public void setPositionCentre(Position positionCentre) {
        this.setLatitude(positionCentre.getLatitude());
        this.setLongitude(positionCentre.getLongitude());
    }

    public Integer getRayon() {
        return rayon;
    }

    public void setRayon(Integer rayon) {
        this.rayon = rayon;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }
}
