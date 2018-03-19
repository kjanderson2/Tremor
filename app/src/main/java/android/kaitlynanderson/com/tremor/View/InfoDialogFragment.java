package android.kaitlynanderson.com.tremor.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.kaitlynanderson.com.tremor.R;
import android.os.Bundle;
import android.view.LayoutInflater;

/**
 * Created by kaitlynanderson on 3/18/18.
 * Info dialog for user
 */

public class InfoDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_info, null));
        builder.setPositiveButton(getString(R.string.info_dialog_button),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });
        return builder.create();
    }
}
