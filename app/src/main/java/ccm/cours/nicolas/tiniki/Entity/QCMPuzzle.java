package ccm.cours.nicolas.tiniki.Entity;

import android.content.Context;
import android.content.Intent;

import ccm.cours.nicolas.tiniki.Activity.QCMActivity;

public class QCMPuzzle extends Puzzle{
    @Override
    public void lanceResolution(Context activityBase) {
        Intent monIntent = new Intent(activityBase, QCMActivity.class);
        monIntent.putExtra("puzzle", this);
        activityBase.startActivity(monIntent);
    }

    @Override
    public boolean estBonneReponse(String reponse) {
        return false;
    }
}