package com.example.progassign2;
import com.example.progassign2.database.entities.Students;
import com.example.progassign2.database.dao.StudentsDao;
import androidx.fragment.app.DialogFragment;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.progassign2.database.AppDatabase;
import com.example.progassign2.database.entities.Students;


public class DialogBox extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Inflate the dialog layout
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogbox, null);

        // Link input fields
        EditText surname = view.findViewById(R.id.editTextSurname);
        EditText name = view.findViewById(R.id.editTextName);
        EditText id = view.findViewById(R.id.editTextId);
        EditText gpa = view.findViewById(R.id.editTextGPA);

        // Link buttons
        Button cancelButton = view.findViewById(R.id.buttonCancel);
        Button submitButton = view.findViewById(R.id.buttonSubmit);

        // Build the AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setView(view); // Attach XML layout to the dialog

        // Close the dialog when "Cancel" is clicked
        cancelButton.setOnClickListener(v -> dismiss());

        // Return data when "Submit" is clicked
        submitButton.setOnClickListener(v -> {
            int Id = Integer.parseInt(id.getText().toString());
            Students student = new Students(Id,name.getText().toString(),surname.getText().toString(),gpa.getText().toString());
            insertOnDataBase(student);
            dismiss();
        });
        return builder.create();
    }

    private void insertOnDataBase(Students student) {
        AppDatabase db = AppDatabase.getInstance(requireContext());
        db.studentsDao().add(student);

        // Notify MainActivity to refresh UI
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).updateProfileCount();
            ((MainActivity) getActivity()).display(((MainActivity) getActivity()).states);
        }
    }



}

