package com.example.weathercrowd.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.weathercrowd.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HistoryActivity extends AppCompatActivity {

    private FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_history);
        mListView = findViewById(R.id.userHistory);


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(mUser.getUid());
        FirebaseListOptions<String> options = new FirebaseListOptions.Builder<String>()
                .setQuery(databaseReference, String.class)
                .setLayout(android.R.layout.simple_list_item_1)
                .build();

        FirebaseListAdapter<String> adapter = new FirebaseListAdapter<String>(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull String model, int position) {
                TextView textView = v.findViewById(android.R.id.text1);
                textView.setText(model);
            }
        };
        mListView.setAdapter(adapter);
    }

}

