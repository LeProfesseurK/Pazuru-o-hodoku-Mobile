package ccm.cours.nicolas.tiniki.Database;

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
import ccm.cours.nicolas.tiniki.Activity.Login;
import ccm.cours.nicolas.tiniki.Entity.Utilisateur;
import ccm.cours.nicolas.tiniki.Tools.GlobalVariable;

public class FirebaseDatabase {

    private static String NomTableUtilisateur = "Utilisateur";

    private static FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    public static void addUtilisateur(Utilisateur newUtilisateur, String password, final Inscription context){

        Map<String, Object> nouveauUtilisateur = new HashMap<>();

        nouveauUtilisateur.put("pseudoUtilisateur", newUtilisateur.getPseudoUtilisateur());
        nouveauUtilisateur.put("emailUtilisateur", newUtilisateur.getEmailUtilisateur());
        // TODO : A crypter
        nouveauUtilisateur.put("password", password);

        firebaseFirestore
                //nom de la base
                .collection(NomTableUtilisateur)
                .add(nouveauUtilisateur) //Ajout de la map
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Log.i("Inscription_Utilisateur", "OK !");
                        context.inscriptionSuccess(task.getResult().getId());
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("Inscription_Utilisateur", "Erreur.");
                        context.inscriptionEchec();
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
                            //context.loginEchec();
                        }else{
                            // Membre trouvé.
                            DocumentSnapshot result = task.getResult().getDocuments().get(0);
                            utilisateur.setIdUtilisateur(result.getId());
                            utilisateur.setPseudoUtilisateur((String) result.get("pseudoUtilisateur"));
                            utilisateur.setEmailUtilisateur((String) result.get("emailUtilisateur"));

                            //membre.setMaPosition(new Position());
                            GlobalVariable.getInstance().setConnectedUtilisateur(utilisateur);

                            //context.loginSuccess();
                        }

                    }
                });
    }

    public static void emailExiste(String email, final Inscription context){
        Task<QuerySnapshot> taskQuery = firebaseFirestore
                .collection(NomTableUtilisateur)
                .whereEqualTo("emailUtilisateur", email)
                .get(Source.DEFAULT).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.getResult().size() != 0){
                            // Existant
                            context.emailUncheck();
                        }else{
                            // Nouveau
                            context.emailCheck();
                        }
                    }
                });
    }
}
