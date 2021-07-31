package com.example.fatcarbon.ui;

import android.content.Intent;
import android.os.Bundle;
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
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
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
        setTargetWeight.setText(String.valueOf(user.getTargetWeight()));
        setActLevel.setProgress(user.getActivityLevelIndex());
        actLevelMeter.setText(String.valueOf(setActLevel.getProgress()));
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
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == changePassword){
                    PasswordValidator pwVal = new PasswordValidator();
                    if (user.getPasswordHasher().validatePassword(oldPassword.getText().toString())){
                        if (newPassword.getText().toString().equals(repeatPassword.getText().toString())){
                            if (pwVal.validatePassword(newPassword.getText().toString())){
                                user.setPasswordHasher(new PasswordHasher(newPassword.getText().toString()));
                                UserDataWriter udw = new UserDataWriter(getActivity());
                                udw.writeItem(user);
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
                if (v==saveChanges){
                    user.setTargetWeight(Double.parseDouble(setTargetWeight.getText().toString()));
                    user.setActivityLevel(User.actLevel.values()[setActLevel.getProgress()]);
                    UserDataWriter udw = new UserDataWriter(getActivity());
                    udw.writeItem(user);
                }
            }
        };
        changePassword.setOnClickListener(listener);
        saveChanges.setOnClickListener(listener);

        // Inflate the layout for this fragment
        return root;
    }
}