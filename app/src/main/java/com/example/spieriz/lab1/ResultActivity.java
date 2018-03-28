package com.example.spieriz.lab1;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView text_result;
    private final double UNDERWEIGHT_LEVEL = 18.5;
    private final double OVERWEIGHT_LOWER_LEVEL = 25.0;
    private final double OVERWEIGHT_UPPER_LEVEL = 30.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        setResult();
    }

    private void setResult() {
        text_result = findViewById(R.id.textResult);

        Intent intent = getIntent();
        double result = intent.getDoubleExtra(getString(R.string.result_string), 0.0);

        if (result < UNDERWEIGHT_LEVEL){
            text_result.setTextColor(Color.DKGRAY);
        } else if (result > OVERWEIGHT_LOWER_LEVEL && result < OVERWEIGHT_UPPER_LEVEL){
            text_result.setTextColor(Color.YELLOW);
        } else if (result >= OVERWEIGHT_UPPER_LEVEL){
            text_result.setTextColor(Color.RED);
        } else {
            text_result.setTextColor(Color.GREEN);
        }

        text_result.setText("" + result);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.result_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.back_button:
                finish();
                return true;
            default:
                return false;
        }
    }
}
