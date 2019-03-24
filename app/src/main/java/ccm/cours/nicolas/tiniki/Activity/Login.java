package ccm.cours.nicolas.tiniki.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ccm.cours.nicolas.tiniki.Database.FirebaseDatabase;
import ccm.cours.nicolas.tiniki.R;
import ccm.cours.nicolas.tiniki.Tools.BoiteAOutils;

public class Login extends AppCompatActivity {

    private EditText login;
    private EditText mdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (EditText) findViewById(R.id.tb_login);
        mdp = (EditText) findViewById(R.id.tb_passwordLogin);
    }


    public void loginEchec() {
        Toast.makeText(this, "Vérifier les informations entrées.", Toast.LENGTH_SHORT).show();
    }

    public void onClickConnexion(View view) {
        FirebaseDatabase.loginUtilisateur(login.getText().toString(), BoiteAOutils.crypteMotDePasse(mdp.getText().toString()), this);
    }

    public void loginSuccess() {
        Toast.makeText(this, "Login succès !", Toast.LENGTH_SHORT).show();
        Intent monIntent = new Intent(this, ActivityMainActivity.class);
        startActivity(monIntent);
    }
}
