package com.example.weathercrowd.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weathercrowd.Misc.DownloadImageTask;
import com.example.weathercrowd.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserInfoActivity extends AppCompatActivity {
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        fillUserData();
    }

    // Function used to fill the view with data
    private void fillUserData() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        TextView textUser = findViewById(R.id.textUser);
        TextView textEmail = findViewById(R.id.textEmail);
        TextView textPhone = findViewById(R.id.textPhone);
        TextView textData = findViewById(R.id.textData);


        textUser.setText(user.getDisplayName());
        textEmail.setText(user.getEmail());
        textPhone.setText(user.getPhoneNumber());
        textData.setText(user.getUid());
        new DownloadImageTask((ImageView) findViewById(R.id.imageView)).execute(user.getPhotoUrl().toString());
    }

    // Sign out and return to main activity
    public void SignOutUser(View v) {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(new Intent(UserInfoActivity.this, MainActivity.class));
                    }
                });

    }
}
