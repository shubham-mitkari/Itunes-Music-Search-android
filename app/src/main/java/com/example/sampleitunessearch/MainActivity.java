package com.example.sampleitunessearch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText searchEditText;
    Button searchButton;

    String searchTerm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        searchEditText =findViewById(R.id.main_search_edit_text);
        searchButton =findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                searchTerm= searchEditText.toString();

                Intent i = new Intent(MainActivity.this, SearchResult.class);
                startActivity(i);

            }
        });


    }


}
