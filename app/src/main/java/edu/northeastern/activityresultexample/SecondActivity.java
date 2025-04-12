package edu.northeastern.activityresultexample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This activity is launched by both Modern and Legacy result demonstrations.
 * It provides a simple interface for the user to enter text and send it back
 * as a result to the calling activity. The same implementation works for both
 * modern and legacy approaches to handling activity results.
 */
public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";
    private EditText editTextResult;
    private Button buttonSendResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Second Activity Started");
        setContentView(R.layout.activity_second);

        // Initialize UI components
        editTextResult = findViewById(R.id.editTextResult);
        buttonSendResult = findViewById(R.id.buttonSendResult);

        // Set click listener for the send result button
        buttonSendResult.setOnClickListener(v -> {
            // Get the text entered by user
            String resultText = editTextResult.getText().toString();
            Log.d(TAG, "onClick: Sending result back: " + resultText);
            
            // Create intent to hold the result data
            Intent resultIntent = new Intent();
            // Add the entered text as extra data
            resultIntent.putExtra("RESULT_DATA", resultText);
            // Set the result as OK and attach the data
            setResult(RESULT_OK, resultIntent);
            // Close this activity and return to caller
            finish();
        });
    }

    /**
     * Override back button press to ensure we send a canceled result
     * This ensures the calling activity knows the user actively canceled
     * instead of providing a result
     */
    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: User canceled the operation");
        // Set result as canceled before closing
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
}