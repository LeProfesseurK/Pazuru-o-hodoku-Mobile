package ccm.cours.nicolas.tiniki.Tools;

import java.util.ArrayList;
import java.util.List;

import ccm.cours.nicolas.tiniki.Entity.PointApparition;
import ccm.cours.nicolas.tiniki.Entity.Utilisateur;
import ccm.cours.nicolas.tiniki.Entity.Zone;

public class GlobalVariable {
    private static GlobalVariable gv = null;

    private Utilisateur connectedUtilisateur;

    private List<Zone> listeDesZones;

    private Zone zoneActuelle;

    private List<PointApparition> pointsApparitionDansZone = new ArrayList<PointApparition>();

    public static synchronized GlobalVariable getInstance(){
        if(null == gv){
            gv = new GlobalVariable();
        }
        return gv;
    }

    public Utilisateur getConnectedUtilisateur() {
        return connectedUtilisateur;
    }

    public void setConnectedUtilisateur(Utilisateur connectedUtilisateur) {
        this.connectedUtilisateur = connectedUtilisateur;
    }

    public List<Zone> getListeDesZones(){
        return this.listeDesZones;
    }

    public void setListeDesZones(List<Zone> lesZones){
        this.listeDesZones = lesZones;
    }

    public List<PointApparition> getPointsApparitionDansZone(){
        return this.pointsApparitionDansZone;
    }

    public void setPointsApparitionDansZone(List<PointApparition> lesPointsApparition){
        this.pointsApparitionDansZone = pointsApparitionDansZone;
    }

    public Zone getZoneActuelle() {
        return zoneActuelle;
    }

    public void setZoneActuelle(Zone zoneActuelle) {
        this.zoneActuelle = zoneActuelle;
    }
}
