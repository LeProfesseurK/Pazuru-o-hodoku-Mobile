package ccm.cours.nicolas.tiniki.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import ccm.cours.nicolas.tiniki.Database.FirebaseDatabase;
import ccm.cours.nicolas.tiniki.Entity.Utilisateur;
import ccm.cours.nicolas.tiniki.R;
import ccm.cours.nicolas.tiniki.Tools.BoiteAOutils;
import ccm.cours.nicolas.tiniki.Tools.GlobalVariable;

public class Login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private EditText login;
    private EditText mdp;

    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (EditText) findViewById(R.id.tb_login);
        mdp = (EditText) findViewById(R.id.tb_passwordLogin);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
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

    public void onClickConnexionGoogle(View view) {
        Intent sign = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(sign, 545);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 545){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                GoogleSignInAccount acct = result.getSignInAccount();
                Utilisateur user = new Utilisateur();
                user.setTypeUtilisateur("oauth");
                user.setEmailUtilisateur(acct.getEmail());
                GlobalVariable.getInstance().setConnectedUtilisateur(user);

                FirebaseDatabase.emailExiste(user.getEmailUtilisateur(), this, "oauth");

                /*Log.i("OAUTHG", acct.getDisplayName());
                Log.i("OAUTHG", acct.toString());*/
            }
        }
    }

    public void finirInscription() {
        Intent monIntent = new Intent(this, InscriptionOAuth.class);
        startActivity(monIntent);
    }
}
