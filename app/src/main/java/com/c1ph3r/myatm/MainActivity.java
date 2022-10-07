package com.c1ph3r.myatm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button tamil=(Button) findViewById(R.id.setTamil);
        Button english=(Button) findViewById(R.id.selectEnglish);
        tamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accDetailsTamil();
            }
        });
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accDetails();
            }
        });
    }
    void accDetails(){
        Intent i=new Intent(this,getAccountDetails_English.class);
        startActivity(i);
    }
    void accDetailsTamil(){
        Intent i=new Intent(this,getAccountDetails_Tamil.class);
        startActivity(i);
    }
}