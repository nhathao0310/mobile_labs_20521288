package com.example.th_android_lab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;


public class SignUp extends AppCompatActivity {
    Button button;
    TextView RS, Login;
    EditText name,phone, user,pass;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        db = FirebaseFirestore.getInstance();
        RS = (TextView) findViewById(R.id.notif);
        Login = (TextView) findViewById(R.id.tv_login);
        name = findViewById(R.id.ET_Fullname);
        phone = findViewById(R.id.ET_Phone);
        user = findViewById(R.id.ET_usename);
        pass = findViewById(R.id.ET_password);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String Name = name.getText().toString();
                String Phone = phone.getText().toString();
                String User = user.getText().toString();
                String Pass = pass.getText().toString();


                if (TextUtils.isEmpty(Name))
                {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.nof_failue,  findViewById(R.id.notify));
                    TextView text = (TextView) layout.findViewById(R.id.notify);
                    text.setText("Please enter your full name");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 100);
                    toast.show();
                } else if (TextUtils.isEmpty(Phone))
                {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.nof_failue,  findViewById(R.id.notify));
                    TextView text = (TextView) layout.findViewById(R.id.notify);
                    text.setText("Please enter your phone number");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 100);
                    toast.show();
                } else if (TextUtils.isEmpty(User))
                {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.nof_failue,  findViewById(R.id.notify));
                    TextView text = (TextView) layout.findViewById(R.id.notify);
                    text.setText("Please enter your user name");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 100);
                    toast.show();
                } else if (TextUtils.isEmpty(Pass))
                {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.nof_failue,  findViewById(R.id.notify));
                    TextView text = (TextView) layout.findViewById(R.id.notify);
                    text.setText("Please enter your password");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 100);
                    toast.show();
                } else if (User.length()<6) {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.nof_failue,  findViewById(R.id.notify));
                    TextView text = (TextView) layout.findViewById(R.id.notify);
                    text.setText("User name must have more than 6 characters");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 100);
                    toast.show();
                } else if (User.matches(".*\\d.*"))
                {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.nof_failue,  findViewById(R.id.notify));
                    TextView text = (TextView) layout.findViewById(R.id.notify);
                    text.setText("Username mustn't contain numbers");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 100);
                    toast.show();
                } else if (Pass.length()<6)
                {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.nof_failue,  findViewById(R.id.notify));
                    TextView text = (TextView) layout.findViewById(R.id.notify);
                    text.setText("Password must have more than 6 charaters");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 100);
                    toast.show();
                } else   {
                    Map<String, Object> user = new HashMap<>();
                    user.put("Full Name", Name);
                    user.put("Phone", Phone);
                    user.put("User", User);
                    user.put("Password", PasswordUtils.hashPassword(Pass));

                    db.collection("user")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    LayoutInflater inflater = getLayoutInflater();
                                    View layout = inflater.inflate(R.layout.nof_success,  findViewById(R.id.notify));
                                    TextView text = (TextView) layout.findViewById(R.id.notify);
                                    text.setText("Register Successfully");
                                    Toast toast = new Toast(getApplicationContext());
                                    toast.setDuration(Toast.LENGTH_SHORT);
                                    toast.setView(layout);
                                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 100);
                                    toast.show();
                                    Intent intent = new Intent(SignUp.this, MainActivity.class);
                                    startActivity(intent);
                                    finishAffinity();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
//                                    Toast.makeText(SignUp.this, "failue", Toast.LENGTH_SHORT).show();
                                    LayoutInflater inflater = getLayoutInflater();
                                    View layout = inflater.inflate(R.layout.nof_failue,  findViewById(R.id.notify));
                                    TextView text = (TextView) layout.findViewById(R.id.notify);
                                    text.setText("failue");
                                    Toast toast = new Toast(getApplicationContext());
                                    toast.setDuration(Toast.LENGTH_SHORT);
                                    toast.setView(layout);
                                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 100);
                                    toast.show();
                                }
                            });

                }

            }

        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });
    }



}