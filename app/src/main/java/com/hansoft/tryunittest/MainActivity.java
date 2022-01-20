package com.hansoft.tryunittest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button saveButton;
    private Button revertButton;
    private static final String TAG = "MainActivity";
    private SharedPreferencesHelper mSharedPreferencesHelper;
    private EditText nameEditText;
    private DatePicker mDobPicker;
    private EditText emailEditText;
    private EmailValidator mEmailValidator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindview();
    }

    private void bindview()
    {
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        mDobPicker = (DatePicker) findViewById(R.id.dateOfBirthInput);
        emailEditText = (EditText) findViewById(R.id.emailEditText);

        // Setup field validators.
        mEmailValidator = new EmailValidator();
        emailEditText.addTextChangedListener(mEmailValidator);

        // Instantiate a SharedPreferencesHelper.
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mSharedPreferencesHelper = new SharedPreferencesHelper(sharedPreferences);

        saveButton = findViewById(R.id.saveButton);
        revertButton = findViewById(R.id.revertButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mEmailValidator.isValid()) {
                    emailEditText.setError("Invalid email");
                    Log.d(TAG, "Not saving personal information: Invalid email");
                    return;
                }

                // Get the text from the input fields.
                String name = nameEditText.getText().toString();
                Calendar dateOfBirth = Calendar.getInstance();
                dateOfBirth.set(mDobPicker.getYear(), mDobPicker.getMonth(), mDobPicker.getDayOfMonth());
                String email = emailEditText.getText().toString();

                // Create a Setting model class to persist.
                SharedPreferenceEntry sharedPreferenceEntry =
                        new SharedPreferenceEntry(name, dateOfBirth, email);

                // Persist the personal information.
                boolean isSuccess = mSharedPreferencesHelper.savePersonalInfo(sharedPreferenceEntry);
                if (isSuccess) {
                    Toast.makeText(MainActivity.this, "Personal information saved", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Personal information saved");
                } else {
                    Log.d(TAG, "Failed to write personal information to SharedPreferences");
                }
            }
        });
        revertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateUi();
                Toast.makeText(MainActivity.this, "Personal information reverted", Toast.LENGTH_LONG).show();
                Log.d(TAG, "Personal information reverted");
            }
        });


        populateUi();
    }

    private void populateUi() {
        SharedPreferenceEntry sharedPreferenceEntry;
        sharedPreferenceEntry = mSharedPreferencesHelper.getPersonalInfo();
        nameEditText.setText(sharedPreferenceEntry.getName());
        Calendar dateOfBirth = sharedPreferenceEntry.getDateOfBirth();
        mDobPicker.init(dateOfBirth.get(Calendar.YEAR), dateOfBirth.get(Calendar.MONTH),
                dateOfBirth.get(Calendar.DAY_OF_MONTH), null);
        emailEditText.setText(sharedPreferenceEntry.getEmail());
    }
}