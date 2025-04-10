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

public class LegacyResultActivity extends AppCompatActivity {

    private static final String TAG = "LegacyResultActivity";
    private static final int REQUEST_CODE_SECOND_ACTIVITY = 1001;
    
    private TextView textViewResult;
    private Button buttonLaunchSecondActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Legacy Activity Result Demo Started");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_legacy_result);

        textViewResult = findViewById(R.id.textViewLegacyResult);
        buttonLaunchSecondActivity = findViewById(R.id.buttonLaunchLegacy);

        buttonLaunchSecondActivity.setOnClickListener(v -> {
            Log.d(TAG, "onClick: Launching Second Activity using Legacy startActivityForResult");
            Intent intent = new Intent(LegacyResultActivity.this, SecondActivity.class);
            startActivityForResult(intent, REQUEST_CODE_SECOND_ACTIVITY);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: Legacy callback received - RequestCode: " + requestCode);
        
        if (requestCode == REQUEST_CODE_SECOND_ACTIVITY) {
            if (resultCode == RESULT_OK && data != null) {
                String resultText = data.getStringExtra("RESULT_DATA");
                Log.d(TAG, "onActivityResult: Received result: " + resultText);
                textViewResult.setText("Result: " + resultText);
            } else {
                Log.d(TAG, "onActivityResult: No result or canceled");
                Toast.makeText(this, "No result received or action canceled", 
                              Toast.LENGTH_SHORT).show();
            }
        }
    }
}