package com.c1ph3r.myatm;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class ControllerClass extends AppCompatActivity {
    public String debitCardNum,dbPin,balanceInAcc,accType;
    public ArrayList<Database> dataFromJson= new ArrayList<Database>();
    public ControllerClass() throws Exception{
        InputStream getJson=getAssets().open("assets/data.json");
        int sizeOfInputStream=getJson.available();
        byte[] myByte=new byte[sizeOfInputStream];
        getJson.read(myByte);
        String data= new String(myByte,"UTF-8");
        JSONArray arr= new JSONArray(data);
        for(int i=0;i<arr.length();i++){
            String name,debitCardNumber,dateOfBirth,typeOfAccount,pin,balance;
            JSONObject jname;
            jname=(JSONObject) arr.get(i);
            name=jname.getString("name");
            debitCardNumber=jname.getString("debitCardNumber");
            dateOfBirth=jname.getString("dateOfBirth");
            typeOfAccount=jname.getString("typeOfAccount");
            pin=jname.getString("pin");
            balance= jname.getString("balance");
            dataFromJson.add(new Database(name,dateOfBirth,debitCardNumber,typeOfAccount,pin,balance));
        }

    }
    public boolean checkAccountDetails(String debitcard,String pin) {
        for (int i = 0; i < dataFromJson.size(); i++) {
            if (dataFromJson.get(i).getDebitCardNumber().equals(debitcard)) {
                if (dataFromJson.get(i).getPin().equals(pin)) {
                    dbPin = pin;
                    debitCardNum = debitcard;
                    return true;
                }
            }
        }
     return false;
    }
    public boolean checkAccountType(String accountType){
        for (int i = 0; i < dataFromJson.size(); i++) {
            if(debitCardNum.equals(dataFromJson.get(i).getDebitCardNumber())){
                if(accountType.equals(dataFromJson.get(i).getTypeOfAccount())){
                    accType=accountType;
                    return true;
                }
            }
        }
        return false;
    }
    public void checkBalance(){
        for (int i = 0; i < dataFromJson.size(); i++) {
            if (debitCardNum.equals(dataFromJson.get(i).getDebitCardNumber())) {
                balanceInAcc=dataFromJson.get(i).getBalance();
            }
        }
    }
}
