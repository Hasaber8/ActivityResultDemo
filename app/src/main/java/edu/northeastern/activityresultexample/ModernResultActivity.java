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

public class ModernResultActivity extends AppCompatActivity {

    private static final String TAG = "ModernResultActivity";
    private TextView textViewResult;
    private Button buttonLaunchSecondActivity;

    private final ActivityResultLauncher<Intent> secondActivityLauncher = 
        registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                Log.d(TAG, "onActivityResult: Modern API callback received");
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        String resultText = data.getStringExtra("RESULT_DATA");
                        Log.d(TAG, "onActivityResult: Received result: " + resultText);
                        textViewResult.setText("Result: " + resultText);
                    }
                } else {
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

        textViewResult = findViewById(R.id.textViewModernResult);
        buttonLaunchSecondActivity = findViewById(R.id.buttonLaunchModern);

        buttonLaunchSecondActivity.setOnClickListener(v -> {
            Log.d(TAG, "onClick: Launching Second Activity using Modern API");
            Intent intent = new Intent(ModernResultActivity.this, SecondActivity.class);
            secondActivityLauncher.launch(intent);
        });
    }
}