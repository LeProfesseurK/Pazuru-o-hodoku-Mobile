package ccm.cours.nicolas.tiniki.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ccm.cours.nicolas.tiniki.Database.FirebaseDatabase;
import ccm.cours.nicolas.tiniki.R;
import ccm.cours.nicolas.tiniki.Tools.BoiteAOutils;

public class Login extends AppCompatActivity {

    private EditText login;
    private EditText mdp;
    private FirebaseAuth mAuth;
    private TextView mStatusTextView;
    private TextView mDetailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (EditText) findViewById(R.id.tb_login);
        mdp = (EditText) findViewById(R.id.tb_passwordLogin);

        mStatusTextView = findViewById(R.id.status);
        mDetailTextView = findViewById(R.id.detail);
        mAuth = FirebaseAuth.getInstance();
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

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            mStatusTextView.setText(user.getEmail());
            mDetailTextView.setText(user.getUid());

            findViewById(R.id.signInButton).setVisibility(View.GONE);
            findViewById(R.id.disconnectButton).setVisibility(View.VISIBLE);
            findViewById(R.id.signOutButton).setVisibility(View.VISIBLE);
        } else {
            mStatusTextView.setText("Personne");
            mDetailTextView.setText(null);

            findViewById(R.id.signInButton).setVisibility(View.VISIBLE);
            findViewById(R.id.disconnectButton).setVisibility(View.GONE);
            findViewById(R.id.signOutButton).setVisibility(View.GONE);
        }
    }
}
