package com.example.ninja_warrior;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceFragmentCompat;

public class ConfigPreferencesFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.config_options, rootKey);

        androidx.preference.EditTextPreference editTextPreference = getPreferenceManager().findPreference("text_num_enemies");
        editTextPreference.setOnBindEditTextListener(editText -> editText.setInputType(InputType.TYPE_CLASS_NUMBER));
    }
}
