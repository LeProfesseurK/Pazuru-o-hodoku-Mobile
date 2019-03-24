package ccm.cours.nicolas.tiniki.Tools;

import ccm.cours.nicolas.tiniki.Entity.Utilisateur;

public class GlobalVariable {
    private static GlobalVariable gv = null;

    private Utilisateur connectedUtilisateur;

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
}
