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

import ccm.cours.nicolas.tiniki.Entity.Puzzle;
import ccm.cours.nicolas.tiniki.Tools.FichierOutils;

public class EchecDialogFragment extends DialogFragment {
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
                .setTitle("Echec...")
                .setNegativeButton("Revenir à la map", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getContext(), "Retour à la map !", Toast.LENGTH_SHORT).show();
                        lActivity.finish();
                    }
                })
                .setPositiveButton("Retenter", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }
}
