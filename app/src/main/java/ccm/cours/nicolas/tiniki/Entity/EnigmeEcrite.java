package ccm.cours.nicolas.tiniki.Entity;

import android.content.Context;
import android.content.Intent;

import ccm.cours.nicolas.tiniki.Activity.EnigmeEcriteActivity;

public class EnigmeEcrite extends Puzzle {
    @Override
    public void lanceResolution(Context activityBase) {
        Intent monIntent = new Intent(activityBase, EnigmeEcriteActivity.class);
        monIntent.putExtra("puzzle", this);
        activityBase.startActivity(monIntent);
    }

    @Override
    public boolean estBonneReponse(String reponse) {

        if(reponse.equals(this.getSolution())){
            return true;
        }
        // Voir les autres critères : ~ equals / contient etc... [Retirer les blancs...]
        return false;
    }
}
