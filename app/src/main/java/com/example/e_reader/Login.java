package com.example.e_reader;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button sayHello = findViewById(R.id.buttonSayHello);
        EditText editTextName = findViewById(R.id.username);
        EditText editTextPassword = findViewById(R.id.password);
        TextView textViewHello = findViewById(R.id.textViewHello);

        sayHello.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            if (!name.isEmpty()){
                String helloText = "Hello, " + name + "!";
                textViewHello.setText(helloText);
            }
            String password = editTextPassword.getText().toString();
            if (!password.isEmpty()){
                String passwordText = "Password, " + password + "!";
                Toast.makeText(this, passwordText, Toast.LENGTH_SHORT).show();
            }
        });
    }
}