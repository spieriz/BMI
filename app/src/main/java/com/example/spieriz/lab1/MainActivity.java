package com.example.spieriz.lab1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private EditText in_mass;
    private EditText in_height;
    private Button but_calculate;
    private ToggleButton toggle_button;

    private TextView textMass;
    private TextView textHeight;

    MenuItem menu_about;

    BMI BMI_CLASS;
    private String defaultValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        restoreBackupData();

        toggle_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toggle_button.isChecked()){
                    changeTextToImperial();
                } else {
                    changeTextToMetrical();
                }
            }
        });

        but_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (in_height.getText().toString().length() == 0 && in_mass.getText().toString().length() == 0) {
                        throw new IllegalArgumentException(getString(R.string.missingArgument));
                    }
                    double height = Double.valueOf(in_height.getText().toString());
                    double mass = Double.valueOf(in_mass.getText().toString());

                    if (toggle_button.isChecked()) {
                        BMI_CLASS = new BmiForLbsIn(mass * 0.454, height * 2.54);
                    } else {
                        BMI_CLASS = new BmiForKgM(mass, height);
                    }

                    double bmi_result = BMI_CLASS.calculate();

                    Intent myIntent = new Intent(MainActivity.this, ResultActivity.class);
                    myIntent.putExtra(getString(R.string.result_string), bmi_result); //Optional parameters
                    startActivity(myIntent);
                } catch (IllegalArgumentException e){
                    Toast.makeText(getApplicationContext(), e.toString(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void findViews() {
        in_mass = findViewById(R.id.input_mass);
        in_height = findViewById(R.id.input_height);
        but_calculate = findViewById(R.id.but_button);
        toggle_button = findViewById(R.id.toggleButton);

        textMass = findViewById(R.id.textMass);
        textHeight = findViewById(R.id.textHeight);

        menu_about = findViewById(R.id.about);
    }

    private void changeTextToMetrical() {
        textMass.setText(R.string.mass_kg);
        textHeight.setText(R.string.height_cm);
        in_mass.setText("");
        in_height.setText("");
    }

    private void changeTextToImperial() {
        textMass.setText(R.string.mass_lbs);
        textHeight.setText(R.string.height_in);
        in_mass.setText("");
        in_height.setText("");
    }

    private void restoreBackupData() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String backup_mass = sharedPref.getString(getString(R.string.saved_data_mass), defaultValue);
        String backup_height = sharedPref.getString(getString(R.string.saved_data_height), defaultValue);

        in_mass.setText(backup_mass);
        in_height.setText(backup_height);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about:
                Intent myIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(myIntent);
                return true;
            case R.id.save:
                SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.clear();

                editor.putString(getString(R.string.saved_data_mass), "" + in_mass.getText());
                editor.putString(getString(R.string.saved_data_height), "" + in_height.getText());
                editor.commit();

                return true;
            default:
                return false;
        }
    }
}
