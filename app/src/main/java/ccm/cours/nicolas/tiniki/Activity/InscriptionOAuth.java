package ccm.cours.nicolas.tiniki.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ccm.cours.nicolas.tiniki.Database.FirebaseDatabase;
import ccm.cours.nicolas.tiniki.Entity.Utilisateur;
import ccm.cours.nicolas.tiniki.R;
import ccm.cours.nicolas.tiniki.Tools.BoiteAOutils;
import ccm.cours.nicolas.tiniki.Tools.FichierOutils;
import ccm.cours.nicolas.tiniki.Tools.GlobalVariable;

public class InscriptionOAuth extends AppCompatActivity {

    private EditText pseudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_oauth);
        pseudo = (EditText) findViewById(R.id.tb_pseudoOauth);
    }

    public void onClickEnregistrerInscription(View view) {
        GlobalVariable.getInstance().getConnectedUtilisateur().setPseudoUtilisateur((pseudo.getText()).toString());
        FirebaseDatabase.addUtilisateur(GlobalVariable.getInstance().getConnectedUtilisateur(), null, this);
    }

    public void inscriptionSuccess(String id) {
        GlobalVariable.getInstance().getConnectedUtilisateur().setIdUtilisateur(id);
        Toast.makeText(this, "Inscription terminée !", Toast.LENGTH_SHORT).show();
        FichierOutils.verifDateJourFile(this);
        Intent monIntent = new Intent(this, CarteOSM.class);
        startActivity(monIntent);
    }

    public void inscriptionEchec() {
        Toast.makeText(this, "Une erreur est survenue.", Toast.LENGTH_SHORT).show();
    }
}
