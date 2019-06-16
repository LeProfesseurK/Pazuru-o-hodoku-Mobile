package ccm.cours.nicolas.tiniki.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ccm.cours.nicolas.tiniki.Entity.Puzzle;
import ccm.cours.nicolas.tiniki.Entity.QCMPuzzle;
import ccm.cours.nicolas.tiniki.R;
import ccm.cours.nicolas.tiniki.Tools.AdapterCheckBox;
import ccm.cours.nicolas.tiniki.Tools.BoiteAOutils;
import ccm.cours.nicolas.tiniki.Tools.GlobalVariable;

public class QCMActivity extends AppCompatActivity {

    TextView nomPuzzle;
    TextView enoncePuzzle;
    RecyclerView rvReponse;
    RecyclerView.Adapter adapterRV;
    RecyclerView.LayoutManager layoutManagerRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qcm);

        // Indices ??

        nomPuzzle = (TextView) findViewById(R.id.id_tvQcmNom);
        enoncePuzzle = (TextView) findViewById(R.id.id_contenuQcm);
        rvReponse = (RecyclerView) findViewById(R.id.id_rvQCMReponse);

        layoutManagerRV = new LinearLayoutManager(this);

        rvReponse.setLayoutManager(layoutManagerRV);

        // Récupération du puzzle correspondant.

        //int position = getIntent().getExtras().getInt("positionPuzzle");
        Puzzle lePuzzle = (QCMPuzzle) getIntent().getExtras().getSerializable("positionPuzzle");

        //Puzzle lePuzzle = GlobalVariable.getInstance().getPointsApparitionDansZone().get(position).getPuzzleDuJour();

        // Affichage du puzzle

        nomPuzzle.setText(lePuzzle.getNom());
        enoncePuzzle.setText(lePuzzle.getEnonce());

        // contenu => <"URL image", ".....">, <"Choix", ["toto", "tata"]>
        // Map du contenu ( reponse qcm )

       // List<String> listeChoix = (ArrayList<String>) lePuzzle.getContenu().get("Choix");

       // adapterRV = new AdapterCheckBox(BoiteAOutils.AleatoireElementsListe(listeChoix));
        rvReponse.setAdapter(adapterRV);

    }
}
