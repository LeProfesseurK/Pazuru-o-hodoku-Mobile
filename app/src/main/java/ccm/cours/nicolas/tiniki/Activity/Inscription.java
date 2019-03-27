package ccm.cours.nicolas.tiniki.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ccm.cours.nicolas.tiniki.Database.FirebaseDatabase;
import ccm.cours.nicolas.tiniki.Entity.Utilisateur;
import ccm.cours.nicolas.tiniki.R;
import ccm.cours.nicolas.tiniki.Tools.BoiteAOutils;
import ccm.cours.nicolas.tiniki.Tools.GlobalVariable;

public class Inscription extends AppCompatActivity {

    private EditText pseudo;
    private EditText email;
    private EditText mdp;
    private EditText reMdp;

    private static Utilisateur newUtilisateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        pseudo = (EditText) findViewById(R.id.tb_pseudo);
        email = (EditText) findViewById(R.id.tb_email);
        mdp = (EditText) findViewById(R.id.tb_passwordLogin);
        reMdp = (EditText) findViewById(R.id.tb_repassword);
    }

    public void onClickEnregistrerInscription(View view) {
        if(mdp.getText().toString().equals(reMdp.getText().toString())) {
            FirebaseDatabase.emailExiste(email.getText().toString(), this, "classique");
        }else{
            Toast.makeText(this, "Le mot de passe et sa confirmation ne sont pas identique.", Toast.LENGTH_SHORT).show();
        }
    }

    public void emailCheck(){
        newUtilisateur = new Utilisateur();
        newUtilisateur.setPseudoUtilisateur((pseudo.getText()).toString());
        newUtilisateur.setEmailUtilisateur((email.getText()).toString());
        newUtilisateur.setTypeUtilisateur("classique");
        FirebaseDatabase.addUtilisateur(newUtilisateur, BoiteAOutils.crypteMotDePasse(mdp.getText().toString()), this);
    }

    public void emailUncheck(){
        Toast.makeText(this, "L'email est déja enregistré.", Toast.LENGTH_SHORT).show();
    }

    public void inscriptionSuccess(String idUtilisateur) {
        //newUtilisateur.setMaPosition(new Position());
        newUtilisateur.setIdUtilisateur(idUtilisateur);

        GlobalVariable.getInstance().setConnectedUtilisateur(newUtilisateur);

        Toast.makeText(this, "Inscription terminée !", Toast.LENGTH_SHORT).show();
        Intent monIntent = new Intent(this, ActivityMainActivity.class);
        startActivity(monIntent);
    }

    public void inscriptionEchec(){
        Toast.makeText(this, "Une erreur est survenue.", Toast.LENGTH_SHORT).show();
    }

}
