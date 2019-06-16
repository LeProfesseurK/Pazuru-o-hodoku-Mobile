package ccm.cours.nicolas.tiniki.Entity;

public class Utilisateur {

    String idUtilisateur;

    String pseudoUtilisateur;

    String emailUtilisateur;

    String typeUtilisateur;

    Position maPosition = new Position();

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getPseudoUtilisateur() {
        return pseudoUtilisateur;
    }

    public void setPseudoUtilisateur(String pseudoUtilisateur) {
        this.pseudoUtilisateur = pseudoUtilisateur;
    }

    public String getEmailUtilisateur() {
        return emailUtilisateur;
    }

    public void setEmailUtilisateur(String emailUtilisateur) {
        this.emailUtilisateur = emailUtilisateur;
    }

    public String getTypeUtilisateur() {
        return typeUtilisateur;
    }

    public void setTypeUtilisateur(String typeUtilisateur) {
        this.typeUtilisateur = typeUtilisateur;
    }

    public Position getMaPosition() {
        return maPosition;
    }

    public void setMaPosition(Position maPosition) {
        this.maPosition = maPosition;
    }
}
