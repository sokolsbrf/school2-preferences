package ru.dimasokol.school.testpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity {

    private TextView mWelcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_preferences).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent openSettings = new Intent(MainActivity.this, PreferencesActivity.class);
                        startActivity(openSettings);
                    }
                }
        );

        mWelcomeTextView = findViewById(R.id.welcome_text);
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String greeting = preferences.getString(
                getString(R.string.pref_welcome_key),
                getString(R.string.pref_welcome_default_value)
        );

        mWelcomeTextView.setText(greeting);
    }
}
