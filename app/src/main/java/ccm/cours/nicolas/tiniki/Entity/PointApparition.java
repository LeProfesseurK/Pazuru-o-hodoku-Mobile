package ccm.cours.nicolas.tiniki.Entity;

public class PointApparition {
    private Integer id;

    private String nom;

    private String description;

    private Position position;

    private Puzzle puzzleDuJour;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Puzzle getPuzzleDuJour() {
        return puzzleDuJour;
    }

    public void setPuzzleDuJour(Puzzle puzzleDuJour) {
        this.puzzleDuJour = puzzleDuJour;
    }

}
