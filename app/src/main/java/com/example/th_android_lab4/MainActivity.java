package com.example.th_android_lab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView RS,signup;
    Button button;
    EditText user,pass;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();
        signup = findViewById(R.id.signup);
        button = findViewById(R.id.button);
        user = findViewById(R.id.ET_usename);
        pass = findViewById(R.id.ET_password);
        LayoutInflater inflater = getLayoutInflater();
        View layout = getLayoutInflater().inflate(R.layout.nof_failue, null);
        TextView text = (TextView) layout.findViewById(R.id.notify);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Pass = pass.getText().toString();
//                HashPass(etPassword.getText().toString());
                String User = user.getText().toString();
//                String HashPassLogin = result.getText().toString();

                db.collection("user")
                        .whereEqualTo("User", User)
                        .whereEqualTo("Password", PasswordUtils.hashPassword(Pass))
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    QuerySnapshot snapshot= task.getResult();
                                    if (snapshot.isEmpty()) {
                                        // Tài khoản không tồn tại hoặc mật khẩu không chính xác
                                        LayoutInflater inflater = getLayoutInflater();
                                        View layout = inflater.inflate(R.layout.nof_failue, (ViewGroup) findViewById(R.id.notify_layout));
                                        TextView text = (TextView) layout.findViewById(R.id.notify);
                                        text.setText("Username or Password is incorrect.");
                                        Toast toast = new Toast(getApplicationContext());
                                        toast.setDuration(Toast.LENGTH_SHORT);
                                        toast.setView(layout);
                                        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 40);
                                        toast.show();
                                    } else {
                                        View layout = inflater.inflate(R.layout.nof_success, (ViewGroup) findViewById(R.id.notify_layout));
                                        TextView text = (TextView) layout.findViewById(R.id.notify);
                                        text.setText("Login successfully");
                                        Toast toast = new Toast(getApplicationContext());
                                        toast.setDuration(Toast.LENGTH_SHORT);
                                        toast.setView(layout);
                                        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 40);
                                        toast.show();
                                        Intent intent = new Intent(MainActivity.this, homePage.class);
                                        startActivity(intent);
                                        finishAffinity();
                                    }
                                } else {
                                    Toast.makeText(MainActivity.this, "Error querying database.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
                finishAffinity();
            }
        });





    }
}