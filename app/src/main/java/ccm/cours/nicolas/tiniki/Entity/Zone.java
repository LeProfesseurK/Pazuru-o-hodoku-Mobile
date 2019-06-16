package ccm.cours.nicolas.tiniki.Entity;

public class Zone {

    private Integer id;
    private String libelle;
  //  private Position positionCentre;
    private double latitude;
    private double longitude;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
