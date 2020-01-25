package ru.dimasokol.school.testpreferences;

import android.app.Application;

import androidx.preference.PreferenceManager;

public class PreferencesApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceManager.getDefaultSharedPreferences(this);
    }
}
