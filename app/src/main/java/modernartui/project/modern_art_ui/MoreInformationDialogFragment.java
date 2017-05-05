package modernartui.project.modern_art_ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;

public class MoreInformationDialogFragment extends DialogFragment{
    static private final String MOMA_URL = "http://www.moma.org";

    public static MoreInformationDialogFragment newInstance() {
        return new MoreInformationDialogFragment();
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        builder.setView(inflater.inflate(R.layout.dialog, null))

                // Add action buttons
                .setPositiveButton("Visit MOMA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        //launch the MOMA webpage
                        Intent MOMA_intent = new Intent(Intent.ACTION_VIEW, Uri.parse(MOMA_URL));
                        startActivity(MOMA_intent);
                    }
                })
                .setNegativeButton("Not Now", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MoreInformationDialogFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

}
