package com.example.sqllite.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqllite.R;

import io.reactivex.schedulers.Schedulers;

public class Details extends AppCompatActivity {
    TextView textView3,textView5,textView6;
    ImageView imageView7;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        textView3=findViewById(R.id.textView3);
        textView5=findViewById(R.id.textView5);
        textView6=findViewById(R.id.textView6);
        imageView7=findViewById(R.id.imageView7);

        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Details.this, "Thanks bro...", Toast.LENGTH_SHORT).show();
            }
        });













    }
}