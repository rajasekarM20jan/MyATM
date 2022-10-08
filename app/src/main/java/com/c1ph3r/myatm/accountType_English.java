package com.c1ph3r.myatm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class accountType_English extends AppCompatActivity {
    ArrayList<Database> fromJson = new ArrayList<>();
    Button savings,current;
    String debitCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_type_english);
        savings=(Button) findViewById(R.id.savingsAccount);
        current=(Button) findViewById(R.id.currentAccount);
        Intent a=getIntent();
        debitCard=a.getStringExtra("debitCardNumber");
        try {
            getdata();
            click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void getdata() throws Exception {
        InputStream getData = getAssets().open("data.json");
        int sizeofJson = getData.available();
        byte[] store = new byte[sizeofJson];
        getData.read(store);
        String abc = new String(store, "UTF-8");
        JSONArray arr = new JSONArray(abc);
        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = (JSONObject) arr.get(i);
            String name=(String) obj.get("name");
            String debitCardNumber=(String) obj.get("debitCardNumber");
            String dateOfBirth=(String) obj.get("dateOfBirth");
            String typeOfAccount=(String) obj.get("typeOfAccount");
            String pin=(String) obj.get("pin");
            String balance=(String) obj.get("balance");
            fromJson.add(new Database(name,dateOfBirth,debitCardNumber,typeOfAccount,pin,balance));
        }
    }
    void click(){
        savings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                for (int i = 0; i < fromJson.size(); i++) {
                    if (fromJson.get(i).getDebitCardNumber().equals(debitCard)){
                        if (fromJson.get(i).getTypeOfAccount().equals("SavingsAccount")) {
                            getintent();
                        }
                    }
                }
            }
        });
        current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < fromJson.size(); i++) {
                    if (fromJson.get(i).getDebitCardNumber().equals(debitCard)){
                        if (fromJson.get(i).getTypeOfAccount().equals("CurrentAccount")) {
                            getintent();
                        }
                    }
                }
            }
        });
    }
    void getintent(){
        Intent i=new Intent(this,atmOptions_English.class);
        i.putExtra("debitCardNumber",debitCard);
        startActivity(i);
    }
}