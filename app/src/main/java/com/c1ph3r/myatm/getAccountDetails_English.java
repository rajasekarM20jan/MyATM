package com.c1ph3r.myatm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class getAccountDetails_English extends AppCompatActivity {
    ArrayList<Database> fromJson = new ArrayList<>();
    Button next;
    EditText debitCard,pin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_account_details_english);
        next=(Button) findViewById(R.id.nextInAccDetailsEng);
        debitCard=(EditText) findViewById(R.id.etForCardDetails);
        pin=(EditText) findViewById(R.id.etForPassword);
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
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<fromJson.size();i++){
                    if(fromJson.get(i).getDebitCardNumber().equals(String.valueOf(debitCard.getText()))) {
                        if (fromJson.get(i).getPin().equals(String.valueOf(pin.getText()))) {
                            getintent();
                        }
                    }
                }
            }
        });
    }
    void getintent(){
        Intent i=new Intent(this,accountType_English.class);
        i.putExtra("debitCardNumber",String.valueOf(debitCard.getText()));
        startActivity(i);
    }
}