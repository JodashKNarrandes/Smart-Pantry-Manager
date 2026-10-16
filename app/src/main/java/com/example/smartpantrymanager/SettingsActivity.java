package com.example.smartpantrymanager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SwitchCompat switchExpiryAlerts;
    private RadioGroup radioGroupUnits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreferences = getSharedPreferences("SmartPantryPrefs", MODE_PRIVATE);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.settings);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        switchExpiryAlerts = findViewById(R.id.switchExpiryAlerts);
        radioGroupUnits = findViewById(R.id.radioGroupUnits);

        // Load saved preferences
        switchExpiryAlerts.setChecked(sharedPreferences.getBoolean("expiry_alerts", true));
        String unitPref = sharedPreferences.getString("unit_preference", "metric");
        if (unitPref.equals("imperial")) {
            radioGroupUnits.check(R.id.radioImperial);
        } else {
            radioGroupUnits.check(R.id.radioMetric);
        }

        // Save preferences when changed
        switchExpiryAlerts.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("expiry_alerts", isChecked);
            editor.apply();
            Toast.makeText(SettingsActivity.this, "Expiry alerts " + (isChecked ? "enabled" : "disabled"), Toast.LENGTH_SHORT).show();
        });

        radioGroupUnits.setOnCheckedChangeListener((group, checkedId) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (checkedId == R.id.radioImperial) {
                editor.putString("unit_preference", "imperial");
            } else {
                editor.putString("unit_preference", "metric");
            }
            editor.apply();
            Toast.makeText(SettingsActivity.this, "Unit preference saved", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
