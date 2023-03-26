package com.example.pr10.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pr10.Classes.AppDatabase;
import com.example.pr10.Classes.User;
import com.example.pr10.MainActivity;
import com.example.pr10.MainActivityViewModel;
import com.example.pr10.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding registerBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(registerBinding.getRoot());

        registerBinding.registerButton.setOnClickListener(view -> {
            try {
                User user = new User(
                        registerBinding.loginEdt.getText().toString().trim(),
                        registerBinding.passwordEdt.getText().toString().trim()
                );
                var db = AppDatabase.getDbInstance(getApplicationContext());
                if(db.userDao().getUser(registerBinding.loginEdt.getText().toString().trim()) == null){
                    db.userDao().insertUser(user);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    var currentUser = db.userDao().getAUF(
                            user.getLogin(),
                            user.getPassword()
                    );
                    MainActivityViewModel.getInstance().setCurrentUser(currentUser);
                    SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("user_id", currentUser.getId());
                    editor.apply();
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Логин уже занят",
                            Toast.LENGTH_SHORT).show();
                }
            }catch (Exception exception) {
                Toast.makeText(getApplicationContext(), exception.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}