package edu.northeastern.activityresultexample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Demonstrates the modern approach to handling activity results using ActivityResultLauncher.
 * This is the recommended way to handle activity results in newer Android versions.
 */
public class ModernResultActivity extends AppCompatActivity {

    private static final String TAG = "ModernResultActivity";
    private TextView textViewResult;
    private Button buttonLaunchSecondActivity;

    /**
     * ActivityResultLauncher is registered at activity creation time.
     * This launcher will be used to start the second activity and handle its result.
     * The callback is defined here using a lambda expression, making the code more organized
     * compared to the legacy onActivityResult approach.
     */
    private final ActivityResultLauncher<Intent> secondActivityLauncher = 
        registerForActivityResult(
            // StartActivityForResult is a pre-built contract that handles launching activities
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                Log.d(TAG, "onActivityResult: Modern API callback received");
                if (result.getResultCode() == RESULT_OK) {
                    // If result is OK, get the data intent
                    Intent data = result.getData();
                    if (data != null) {
                        // Extract the result text and update UI
                        String resultText = data.getStringExtra("RESULT_DATA");
                        Log.d(TAG, "onActivityResult: Received result: " + resultText);
                        textViewResult.setText("Result: " + resultText);
                    }
                } else {
                    // Handle canceled or failed result
                    Log.d(TAG, "onActivityResult: No result or canceled");
                    Toast.makeText(this, "No result received or action canceled", 
                                 Toast.LENGTH_SHORT).show();
                }
            }
        );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Modern Activity Result Demo Started");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modern_result);

        // Initialize UI components
        textViewResult = findViewById(R.id.textViewModernResult);
        buttonLaunchSecondActivity = findViewById(R.id.buttonLaunchModern);

        // Set click listener for launch button
        buttonLaunchSecondActivity.setOnClickListener(v -> {
            Log.d(TAG, "onClick: Launching Second Activity using Modern API");
            // Create intent for SecondActivity
            Intent intent = new Intent(ModernResultActivity.this, SecondActivity.class);
            // Launch activity using the registered launcher
            secondActivityLauncher.launch(intent);
        });
    }
}