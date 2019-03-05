package com.example.weathercrowd.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.weathercrowd.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HistoryActivity extends AppCompatActivity {

    String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand"};
    private DatabaseReference mDatabase;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_history);
        setupListView();
    }

    private void getUserHistory() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.orderByChild("userId").equalTo(mUser.getUid());
    }

    // Get data from DB then take the ID of entry and send it to the next activity and update it as you see fit.
    private void setupListView() {
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, countryList);

        ListView listView = findViewById(R.id.userHistory);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HistoryActivity.this, EditHistoryActivity.class);
                intent.putExtra("itemIndex", parent.getSelectedItemPosition());
                startActivity(intent);
            }
        });
    }
}

