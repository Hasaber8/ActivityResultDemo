package edu.northeastern.activityresultexample;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonModernDemo;
    private Button buttonLegacyDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        buttonModernDemo = findViewById(R.id.buttonModernDemo);
        buttonLegacyDemo = findViewById(R.id.buttonLegacyDemo);

        buttonModernDemo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ModernResultActivity.class);
            startActivity(intent);
        });

        buttonLegacyDemo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LegacyResultActivity.class);
            startActivity(intent);
        });
    }
}