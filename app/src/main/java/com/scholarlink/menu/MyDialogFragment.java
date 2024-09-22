package com.scholarlink.menu;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;


public class MyDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use LayoutInflater to inflate the custom layout for the dialog
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_dialog, null);

        ImageButton yesButton = view.findViewById(R.id.positive_button);
        ImageButton noButton = view.findViewById(R.id.negative_button);

        // Set up the dialog builder with the custom layout
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setView(view);

        // Handle positive button click (navigate to FirstFragment)
        yesButton.setOnClickListener(v -> {
            // Navigate to FirstFragment
            FirstFragment firstFragment = new FirstFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, firstFragment);
            transaction.addToBackStack(null);
            transaction.commit();

            // Dismiss the dialog
            dismiss();
        });

        // Handle negative button click (exit the app)
        noButton.setOnClickListener(v -> {
            // Exit the app
            requireActivity().finishAffinity();
        });

        // Return the created dialog
        return builder.create();

    }
}