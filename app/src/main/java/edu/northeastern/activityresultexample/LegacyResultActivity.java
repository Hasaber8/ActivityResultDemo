package edu.northeastern.activityresultexample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Demonstrates the legacy approach to handling activity results using startActivityForResult.
 * This approach is deprecated but still widely used in existing applications.
 * It requires managing request codes and handling all results in a single onActivityResult method.
 */
public class LegacyResultActivity extends AppCompatActivity {

    private static final String TAG = "LegacyResultActivity";
    // Request code to identify the activity result callback
    private static final int REQUEST_CODE_SECOND_ACTIVITY = 1001;
    
    private TextView textViewResult;
    private Button buttonLaunchSecondActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Legacy Activity Result Demo Started");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_legacy_result);

        // Initialize UI components
        textViewResult = findViewById(R.id.textViewLegacyResult);
        buttonLaunchSecondActivity = findViewById(R.id.buttonLaunchLegacy);

        // Set click listener for launch button
        buttonLaunchSecondActivity.setOnClickListener(v -> {
            Log.d(TAG, "onClick: Launching Second Activity using Legacy startActivityForResult");
            // Create intent for SecondActivity
            Intent intent = new Intent(LegacyResultActivity.this, SecondActivity.class);
            // Launch activity with request code
            startActivityForResult(intent, REQUEST_CODE_SECOND_ACTIVITY);
        });
    }

    /**
     * Callback method that handles all activity results.
     * This is the legacy way of handling results, where all different activity results
     * must be handled in this single method using request codes.
     *
     * @param requestCode The code originally supplied to startActivityForResult()
     * @param resultCode  The result code returned by the child activity
     * @param data       An Intent containing any return data from the child activity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: Legacy callback received - RequestCode: " + requestCode);
        
        // Check if the result is from our second activity
        if (requestCode == REQUEST_CODE_SECOND_ACTIVITY) {
            if (resultCode == RESULT_OK && data != null) {
                // Extract the result text and update UI
                String resultText = data.getStringExtra("RESULT_DATA");
                Log.d(TAG, "onActivityResult: Received result: " + resultText);
                textViewResult.setText("Result: " + resultText);
            } else {
                // Handle canceled or failed result
                Log.d(TAG, "onActivityResult: No result or canceled");
                Toast.makeText(this, "No result received or action canceled", 
                              Toast.LENGTH_SHORT).show();
            }
        }
    }
}