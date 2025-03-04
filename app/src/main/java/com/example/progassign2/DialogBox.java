package com.example.progassign2;
import androidx.fragment.app.DialogFragment;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class DialogBox extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Inflate the dialog layout
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogbox, null);

        // Get references to input fields
        EditText surname = view.findViewById(R.id.editTextSurname);
        EditText name = view.findViewById(R.id.editTextName);
        EditText id = view.findViewById(R.id.editTextId);
        EditText gpa = view.findViewById(R.id.editTextGPA);

        // Get references to buttons
        Button cancelButton = view.findViewById(R.id.buttonCancel);
        Button submitButton = view.findViewById(R.id.buttonSubmit);

        // Build the AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setView(view); // Attach XML layout to the dialog

        // Close the dialog when "Cancel" is clicked
        cancelButton.setOnClickListener(v -> dismiss());

        // Return data when "Submit" is clicked
        submitButton.setOnClickListener(v -> {

            surname.getText().toString();
            name.getText().toString();
            id.getText().toString();
            gpa.getText().toString();

            dismiss();
        });

        return builder.create();}
}

