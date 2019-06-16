package ccm.cours.nicolas.tiniki.Dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import ccm.cours.nicolas.tiniki.Database.FirebaseDatabase;
import ccm.cours.nicolas.tiniki.Entity.Puzzle;
import ccm.cours.nicolas.tiniki.Tools.FichierOutils;
import ccm.cours.nicolas.tiniki.Tools.GlobalVariable;

public class SuccessDialogFragment extends DialogFragment {
    private String messagePuzzle;
    private Puzzle lePuzzle;
    private Activity lActivity;

    public void setMessagePuzzle(String message) {
        messagePuzzle = message;
    }

    public void setPuzzle(Puzzle puzzle) {
        lePuzzle = puzzle;
    }

    public void setActivity(Activity activity) {
        lActivity = activity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(messagePuzzle)
                .setTitle("Pazuru clear !")
                .setPositiveButton("Revenir à la map", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getContext(), "Retour à la map !", Toast.LENGTH_SHORT).show();
                        // 1 écrire dans le fichier que l'énigme à été résolut.
                        int nombreEchec = FichierOutils.ajoutePuzzleReussit(lePuzzle.getId().toString(), getContext());
                        //FichierOutils.readFromFile(getContext());
                        // 2 mettre à jour les points XP et Pieces dans l'objet de l'utilisateur

//                        GlobalVariable.getInstance().getConnectedUtilisateur().ajouteExpUtilisateur(lePuzzle.getExp());
//                        GlobalVariable.getInstance().getConnectedUtilisateur().ajoutePieceUtilisateur(lePuzzle.getPiece());

                        // 3 mettre à jour la BDD ( firebase pour les piece et XP)
//                        FirebaseDatabase.updateUtilisateur();

                        // Fermer l'activité et forcant la mise à jour de l'affichage. ( ne pas afficher le puzzle.
                        lActivity.finish();

                    }
                });
        return builder.create();
    }
}
