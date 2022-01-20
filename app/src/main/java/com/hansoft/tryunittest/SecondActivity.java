package com.hansoft.tryunittest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class SecondActivity extends AppCompatActivity {

    private Button submitButton;
    private EditText scoreEditText;
    private ListView myListView;
    private String[] data={"Apple","Banana","Orange","Watermelon","Pear","Grape","Pineapple","Strawberry","Cherry","Mango","Apple","Banana","Orange","Watermelon","Pear","Grape","Pineapple","Strawberry","Cherry","Mango"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        bindview();
    }

    private void bindview()
    {
        scoreEditText = findViewById(R.id.scoreEditText);
        myListView = findViewById(R.id.myListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SecondActivity.this,android.R.layout.simple_list_item_1,data);
        myListView.setAdapter(adapter);
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("aaa", "onClick: aaa");
                scoreEditText.setText("good morning");
                Intent myIntent = new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(myIntent);

            }
        });
    }
}