package com.example.dictionaryapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextSearch;
    private Button buttonSearch;
    private RecyclerView recyclerViewResults;
    private DictionaryAdapter dictionaryAdapter;
    private DictionaryDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        editTextSearch = findViewById(R.id.editTextSearch);
        buttonSearch = findViewById(R.id.buttonSearch);
        recyclerViewResults = findViewById(R.id.recyclerViewResults);

        // Create an instance of the DatabaseHelper
        databaseHelper = new DictionaryDatabaseHelper(this);

        // Add the word "example" and its definition to the database
        String word = "example";
        String definition = "A thing characteristic of its kind or illustrating a general rule";

        databaseHelper.insertWord(word, definition);
        // Set up RecyclerView
        recyclerViewResults.setLayoutManager(new LinearLayoutManager(this));
        dictionaryAdapter = new DictionaryAdapter(new ArrayList<>());
        recyclerViewResults.setAdapter(dictionaryAdapter);

        // Set click listener for the search button
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = editTextSearch.getText().toString().trim();

                // Perform search and update the RecyclerView with the results
                List<DictionaryItem> searchResults = databaseHelper.searchWords(searchQuery);
                dictionaryAdapter.updateData(searchResults);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database connection
        databaseHelper.close();
    }
}