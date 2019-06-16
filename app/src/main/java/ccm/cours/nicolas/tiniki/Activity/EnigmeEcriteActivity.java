package ccm.cours.nicolas.tiniki.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ccm.cours.nicolas.tiniki.Dialog.EchecDialogFragment;
import ccm.cours.nicolas.tiniki.Entity.EnigmeEcrite;
import ccm.cours.nicolas.tiniki.Entity.Puzzle;
import ccm.cours.nicolas.tiniki.R;
import ccm.cours.nicolas.tiniki.Dialog.SuccessDialogFragment;
import ccm.cours.nicolas.tiniki.Tools.BoiteAOutils;
import ccm.cours.nicolas.tiniki.Tools.FichierOutils;
import ccm.cours.nicolas.tiniki.Tools.GlobalVariable;

public class EnigmeEcriteActivity extends AppCompatActivity {

    TextView nomEnigme;
    TextView enonceEnigme;
    EditText reponseUtilisateur;

    EnigmeEcrite lePuzzle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enigme_ecrite);

        //int position = getIntent().getExtras().getInt("positionPuzzle");

        //Puzzle lePuzzle = GlobalVariable.getInstance().getPointsApparitionDansZone().get(position).getPuzzleDuJour();

        lePuzzle = (EnigmeEcrite) getIntent().getExtras().getSerializable("puzzle");

        nomEnigme = (TextView) findViewById(R.id.id_nomPuzzleEcrit);
        enonceEnigme = (TextView) findViewById(R.id.id_enonceEcrit);
        reponseUtilisateur = (EditText) findViewById(R.id.id_reponseEcrit);

        nomEnigme.setText(lePuzzle.getNom());
        enonceEnigme.setText(lePuzzle.getEnonce());

        //Log.i("LPK_PUZZLE", lePuzzle.getNom());
        //Log.i("LPK_PUZZLE", lePuzzle.getEnonce());
    }

    public void onClickRepondre(View view) {
        String reponse = reponseUtilisateur.getText().toString();
        //FichierOutils.verifDateJourFile(this);
        if(lePuzzle.estBonneReponse(reponse)){
            //Toast.makeText(this, "Oui !", Toast.LENGTH_SHORT).show();
            //FichierOutils.writeToFile(lePuzzle.getId().toString(), this);
            SuccessDialogFragment dialog = new SuccessDialogFragment();
            dialog.setMessagePuzzle("La réponse était bien " + lePuzzle.getSolution());
            dialog.setPuzzle(lePuzzle);
            dialog.setActivity(this);
            dialog.show(getSupportFragmentManager(), "SUCCESS");
        }else{
            FichierOutils.ajoutePuzzleEchec(lePuzzle.getId().toString(), this);
            EchecDialogFragment dialog = new EchecDialogFragment();
            dialog.setMessagePuzzle("Ne vous laissez pas découragé !");
            dialog.setPuzzle(lePuzzle);
            dialog.setActivity(this);
            dialog.show(getSupportFragmentManager(), "ECHEC");
//            Toast.makeText(this, "Non...", Toast.LENGTH_SHORT).show();
        }
        Log.i("LPK_FILE", FichierOutils.readFromFile(this));
    }
}
