package com.example.fatcarbon.ui;

/**************************************
 LUT Olio-ohjelmointi Harjoitustyö
 @author Sami Sillanpää
 @copyright Sami Sillanpää 2021
 @licence GNU GPL3.0
 **************************************/

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import com.example.fatcarbon.R;
import com.example.fatcarbon.app.PasswordHasher;
import com.example.fatcarbon.app.PasswordValidator;
import com.example.fatcarbon.app.User;
import com.example.fatcarbon.app.UserDataWriter;

/**
 * Fragment for settings
 */
public class SettingsFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        // retrieve the user from intent.
        Intent intent = getActivity().getIntent();
        User user = (User) intent.getSerializableExtra("user");

        EditText setTargetWeight = root.findViewById(R.id.editTextSetTargetWeight);
        SeekBar setActLevel = root.findViewById(R.id.seekBarSetActLevel);
        EditText oldPassword = root.findViewById(R.id.editTextPasswordOld);
        EditText newPassword = root.findViewById(R.id.editTextPasswordNew);
        EditText repeatPassword = root.findViewById(R.id.editTextPasswordRepeat);
        TextView actLevelMeter = root.findViewById(R.id.textViewActLevelMeter);
        Button changePassword = root.findViewById(R.id.buttonChangePwd);
        Button saveChanges = root.findViewById(R.id.buttonSaveSettings);

        // set the values for input fields to match current settings from user.
        setTargetWeight.setText(String.valueOf(user.getTargetWeight()));
        setActLevel.setProgress(user.getActivityLevelIndex());
        actLevelMeter.setText(String.valueOf(setActLevel.getProgress()));

        // create a listener for teh activity level seekbar, to change the value text when ever changed.
        setActLevel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                actLevelMeter.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // create listener for buttons
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // funcionality if change password button is clicked
                if (v == changePassword){
                    PasswordValidator pwVal = new PasswordValidator();
                    // Validate that old password is correct
                    if (user.getPasswordHasher().validatePassword(oldPassword.getText().toString())){
                        // Validate that passwords match
                        if (newPassword.getText().toString().equals(repeatPassword.getText().toString())){
                            // Validate that new password meets the criteria
                            if (pwVal.validatePassword(newPassword.getText().toString())){
                                // if all ok, change password for user.
                                user.setPasswordHasher(new PasswordHasher(newPassword.getText().toString()));
                                UserDataWriter udw = new UserDataWriter(getActivity());
                                // save user with updated password
                                udw.writeItem(user);
                                // message user password changed ok.
                                Snackbar.make(v, R.string.password_changed, Snackbar.LENGTH_LONG).show();
                            } else {
                                newPassword.setError(getString(R.string.password_hint));
                            }
                        } else {
                            newPassword.setError(getString(R.string.pwd_not_match));
                        }
                    } else {
                        oldPassword.setError(getString(R.string.pwd_wrong));
                    }
                }
                // if save changes button is clicked
                if (v==saveChanges){
                    // check that target weight is not empty
                    if(setTargetWeight.getText().toString().equals("")){
                        setTargetWeight.setError(getString(R.string.fill_all_values));
                    } else {
                        // set new values and save user.
                        user.setTargetWeight(Double.parseDouble(setTargetWeight.getText().toString()));
                        user.setActivityLevel(User.actLevel.values()[setActLevel.getProgress()]);
                        UserDataWriter udw = new UserDataWriter(getActivity());
                        udw.writeItem(user);
                        Snackbar.make(v, R.string.settings_saved, Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        };

        //assign button listener
        changePassword.setOnClickListener(listener);
        saveChanges.setOnClickListener(listener);

        // Inflate the layout for this fragment
        return root;
    }
}