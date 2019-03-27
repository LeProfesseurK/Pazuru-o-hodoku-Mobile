package ccm.cours.nicolas.tiniki.Database;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import ccm.cours.nicolas.tiniki.Activity.Inscription;
import ccm.cours.nicolas.tiniki.Activity.InscriptionOAuth;
import ccm.cours.nicolas.tiniki.Activity.Login;
import ccm.cours.nicolas.tiniki.Entity.Utilisateur;
import ccm.cours.nicolas.tiniki.Tools.GlobalVariable;

public class FirebaseDatabase {

    private static String NomTableUtilisateur = "Utilisateur";
    private static String NomTableUtilisateurOAuth = "UtilisateurOAuth";

    private static FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    public static void addUtilisateur(Utilisateur newUtilisateur, String password, final Activity context){

        Map<String, Object> nouveauUtilisateur = new HashMap<>();

        nouveauUtilisateur.put("pseudoUtilisateur", newUtilisateur.getPseudoUtilisateur());
        nouveauUtilisateur.put("emailUtilisateur", newUtilisateur.getEmailUtilisateur());
        nouveauUtilisateur.put("expUtilisateur", 0);
        nouveauUtilisateur.put("pieceUtilisateur", 10);

        final String typeUser = newUtilisateur.getTypeUtilisateur();

        String nomTableUtilise = "";
        switch(typeUser){
            case "oauth":
                nomTableUtilise = NomTableUtilisateurOAuth;
                break;
            case "classique":
                // TODO : A crypter
                nouveauUtilisateur.put("password", password);
                nomTableUtilise = NomTableUtilisateur;
                break;
        }

        firebaseFirestore
                //nom de la base
                .collection(nomTableUtilise)
                .add(nouveauUtilisateur) //Ajout de la map
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Log.i("Inscription_Utilisateur", "OK !");
                        switch(typeUser){
                            case "oauth":
                                ((InscriptionOAuth) context).inscriptionSuccess(task.getResult().getId());
                                break;
                            case "classique":
                                ((Inscription) context).inscriptionSuccess(task.getResult().getId());
                                break;
                        }
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("Inscription_Utilisateur", "Erreur.");
                        switch(typeUser){
                            case "oauth":
                                ((InscriptionOAuth) context).inscriptionEchec();
                                break;
                            case "classique":
                                ((Inscription) context).inscriptionEchec();
                                break;
                        }
                    }
                });
    }

    public static void loginUtilisateur(String login, String mdp, final Login context){
        Task<QuerySnapshot> taskQuery = firebaseFirestore
                .collection(NomTableUtilisateur)
                .whereEqualTo("emailUtilisateur", login)
                .whereEqualTo("password", mdp)
                .get(Source.DEFAULT).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Utilisateur utilisateur = new Utilisateur();
                        if(task.getResult().size() != 1){
                            // Pas la bonne récupération.
                            context.loginEchec();
                        }else{
                            // Membre trouvé.
                            DocumentSnapshot result = task.getResult().getDocuments().get(0);
                            utilisateur.setIdUtilisateur(result.getId());
                            utilisateur.setPseudoUtilisateur((String) result.get("pseudoUtilisateur"));
                            utilisateur.setEmailUtilisateur((String) result.get("emailUtilisateur"));

                            //membre.setMaPosition(new Position());
                            GlobalVariable.getInstance().setConnectedUtilisateur(utilisateur);

                            context.loginSuccess();
                        }

                    }
                });
    }

    public static void emailExiste(String email, final Activity context, final String typeUser){
        String nomTableUtilise = "";
        switch(typeUser){
            case "oauth":
                nomTableUtilise = NomTableUtilisateurOAuth;
                break;
            case "classique":
                nomTableUtilise = NomTableUtilisateur;
                break;
        }

        Task<QuerySnapshot> taskQuery = firebaseFirestore
                .collection(nomTableUtilise)
                .whereEqualTo("emailUtilisateur", email)
                .get(Source.DEFAULT).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.getResult().size() != 0){
                            // Existant
                            switch(typeUser){
                                case "oauth":
                                    if(task.getResult().size() == 1){
                                        // Connexion de l'Utilisateur OAuth
                                        DocumentSnapshot result = task.getResult().getDocuments().get(0);
                                        GlobalVariable.getInstance().getConnectedUtilisateur().setIdUtilisateur(result.getId());
                                        GlobalVariable.getInstance().getConnectedUtilisateur().setPseudoUtilisateur((String) result.get("pseudoUtilisateur"));

                                        ((Login) context).loginSuccess();
                                    }else{
                                        Log.i("OAUTH", "Erreur ! Size > 1 !");
                                    }
                                    break;
                                case "classique":
                                    ((Inscription) context).emailUncheck();
                                    break;
                            }
                        }else{
                            // Nouveau
                            switch(typeUser){
                                case "oauth":
                                    ((Login) context).finirInscription();
                                    break;
                                case "classique":
                                    ((Inscription) context).emailCheck();
                                    break;
                            }

                        }
                    }
                });
    }
}
