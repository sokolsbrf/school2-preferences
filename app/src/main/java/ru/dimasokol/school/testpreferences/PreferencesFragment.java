package ru.dimasokol.school.testpreferences;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class PreferencesFragment extends PreferenceFragmentCompat {

    private static final String TAG = "Preferences";
    public static final String ARG_ROOT_SCREEN = "root";

    public static PreferencesFragment newInstance(String rootScreen) {
        Bundle args = new Bundle();
        args.putString(ARG_ROOT_SCREEN, rootScreen);
        PreferencesFragment fragment = new PreferencesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.prefs, getArguments().getString(ARG_ROOT_SCREEN));

        Preference firstPreference = findPreference(getString(R.string.prefs_first_key));
        if (firstPreference != null) {
            firstPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Toast.makeText(requireContext(), getString(R.string.app_name), Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }

        Preference severityPreference = findPreference(getString(R.string.pref_severity_key));
        if (severityPreference != null) {
            severityPreference.setSummaryProvider(new Preference.SummaryProvider() {
                @Override
                public CharSequence provideSummary(Preference preference) {
                    if (getString(R.string.pref_severity_default_value)
                            .equals(((ListPreference) preference).getValue())) {
                        return getResources().getStringArray(R.array.pref_summary_values)[0];
                    }

                    return getResources().getStringArray(R.array.pref_summary_values)[1];
                }
            });
        }
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        if (getString(R.string.pref_feedback_key).equals(preference.getKey())) {
            Toast.makeText(requireContext(), "Send feedback", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Tree click");
            return true;
        }

        return super.onPreferenceTreeClick(preference);
    }
}
