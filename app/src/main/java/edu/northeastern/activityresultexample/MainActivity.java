package edu.northeastern.activityresultexample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Main entry point of the application that demonstrates two different approaches
 * to handling activity results in Android:
 * 1. Modern approach using ActivityResultLauncher (recommended)
 * 2. Legacy approach using startActivityForResult (deprecated)
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button buttonModernDemo;
    private Button buttonLegacyDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Main Activity Started");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        buttonModernDemo = findViewById(R.id.buttonModernDemo);
        buttonLegacyDemo = findViewById(R.id.buttonLegacyDemo);

        // Set click listener for modern approach demo
        buttonModernDemo.setOnClickListener(v -> {
            Log.d(TAG, "onClick: Launching Modern Result Activity Demo");
            Intent intent = new Intent(MainActivity.this, ModernResultActivity.class);
            startActivity(intent);
        });

        // Set click listener for legacy approach demo
        buttonLegacyDemo.setOnClickListener(v -> {
            Log.d(TAG, "onClick: Launching Legacy Result Activity Demo");
            Intent intent = new Intent(MainActivity.this, LegacyResultActivity.class);
            startActivity(intent);
        });
    }
}