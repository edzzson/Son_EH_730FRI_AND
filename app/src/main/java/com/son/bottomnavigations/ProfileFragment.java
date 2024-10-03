package com.son.bottomnavigations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    private EditText editName, editEmail, editPhone;
    private RadioGroup genderGroup;
    private RadioButton radioMale, radioFemale, radioOther;
    private CheckBox checkBoxMusic, checkBoxSports, checkBoxTravel, checkBoxTechnology;
    private Button saveButton;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize views
        editName = rootView.findViewById(R.id.edit_name);
        editEmail = rootView.findViewById(R.id.edit_email);
        editPhone = rootView.findViewById(R.id.edit_phone);
        genderGroup = rootView.findViewById(R.id.gender_group);
        checkBoxMusic = rootView.findViewById(R.id.checkbox_music);
        checkBoxSports = rootView.findViewById(R.id.checkbox_sports);
        checkBoxTravel = rootView.findViewById(R.id.checkbox_travel);
        checkBoxTechnology = rootView.findViewById(R.id.checkbox_technology);
        saveButton = rootView.findViewById(R.id.save_button);

        // Set the save button click listener
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile();
            }
        });

        return rootView;
    }

    private void saveProfile() {
        String name = editName.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String phone = editPhone.getText().toString().trim();

        // Get selected gender from RadioGroup
        int selectedGenderId = genderGroup.getCheckedRadioButtonId();
        String gender = "";

        if (selectedGenderId != -1) {
            RadioButton selectedGender = rootView.findViewById(selectedGenderId); // Use rootView to find the selected RadioButton
            gender = selectedGender.getText().toString(); // This will be "Male", "Female", or "Other"
        } else {
            Toast.makeText(requireActivity(), "Please select a gender", Toast.LENGTH_SHORT).show();
            return;  // Don't proceed if no gender is selected
        }

        // Get interests
        StringBuilder interests = new StringBuilder();
        if (checkBoxMusic.isChecked()) interests.append("Music ");
        if (checkBoxSports.isChecked()) interests.append("Sports ");
        if (checkBoxTravel.isChecked()) interests.append("Travel ");
        if (checkBoxTechnology.isChecked()) interests.append("Technology ");

        // Check if mandatory fields are filled
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(requireActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show the collected data (you can save this data to a database or shared preferences)
        String profileSummary = "Name: " + name + "\nEmail: " + email + "\nPhone: " + phone + "\nGender: " + gender + "\nInterests: " + interests;
        Toast.makeText(requireActivity(), "Profile Saved:\n" + profileSummary, Toast.LENGTH_LONG).show();
    }

}