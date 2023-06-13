package com.example.th_android_lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewActivity_lab5 extends AppCompatActivity {
    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_lab5);
        btnBack =  findViewById(R.id.BtnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IntNewactivity = new Intent(NewActivity_lab5.this, MainActivity_lab5.class);
                startActivity(IntNewactivity);
//                overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out);
                overridePendingTransition(R.anim.anim_move_righ_to_left, R.anim.anim_move_left_to_right);


            }
        });
    }
}