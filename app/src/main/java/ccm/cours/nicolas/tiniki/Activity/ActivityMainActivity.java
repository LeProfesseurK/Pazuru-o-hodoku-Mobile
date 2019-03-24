package ccm.cours.nicolas.tiniki.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ccm.cours.nicolas.tiniki.R;

public class ActivityMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);
        
    }

    public void onClickInscription(View view) {
        Intent monIntent = new Intent(this, Inscription.class);
        startActivity(monIntent);
    }

    public void onClickLogin(View view) {
        Intent monIntent = new Intent(this, Login.class);
        startActivity(monIntent);
    }
}
