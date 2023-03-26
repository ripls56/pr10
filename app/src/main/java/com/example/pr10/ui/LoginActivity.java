package com.example.pr10.ui;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pr10.Classes.AppDatabase;
import com.example.pr10.MainActivity;
import com.example.pr10.MainActivityViewModel;
import com.example.pr10.R;
import com.example.pr10.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding loginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());
        var db = AppDatabase.getDbInstance(getApplicationContext());

        try {
            SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
            int currentUserId = sharedPref.getInt("user_id", -1);
            var currentUser = db.userDao().getUser(currentUserId);
            if (currentUser != null) {
                MainActivityViewModel.getInstance().setCurrentUser(currentUser);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }catch (Exception exception) {
            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
        }

        loginBinding.loginButton.setOnClickListener(view -> {
            try {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                var currentUser = db.userDao().getAUF(
                    loginBinding.loginEdt.getText().toString().trim(),
                    loginBinding.passwordEdt.getText().toString().trim()
                );
                MainActivityViewModel.getInstance().setCurrentUser(currentUser);
                SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("user_id", currentUser.getId());
                editor.apply();
                startActivity(intent);
            }catch (Exception exception) {
                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        loginBinding.registerButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
        });
    }
}