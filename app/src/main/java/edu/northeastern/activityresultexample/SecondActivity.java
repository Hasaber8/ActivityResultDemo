package edu.northeastern.activityresultexample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";
    private EditText editTextResult;
    private Button buttonSendResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Second Activity Started");
        setContentView(R.layout.activity_second);

        editTextResult = findViewById(R.id.editTextResult);
        buttonSendResult = findViewById(R.id.buttonSendResult);

        buttonSendResult.setOnClickListener(v -> {
            String resultText = editTextResult.getText().toString();
            Log.d(TAG, "onClick: Sending result back: " + resultText);
            
            Intent resultIntent = new Intent();
            resultIntent.putExtra("RESULT_DATA", resultText);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: User canceled the operation");
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
}