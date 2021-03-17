package com.edevelopers.realtimedbLib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import com.edevelopers.realdbtime.Model.DBColumn;
import com.edevelopers.realdbtime.Lib.Const;
import com.edevelopers.realdbtime.Model.DBColumnResult;
import com.edevelopers.realdbtime.Service.RequestProcess;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestProcess.dbcol = new ArrayList<>();
        RequestProcess.dbcolwhere = new ArrayList<>();
        RequestProcess.setmodelmod("USERID"); // set column name
        RequestProcess.setmodelmod("USERNAME"); // set column name
        RequestProcess.setmodelmod("FIRST_NAME"); // set column name
        RequestProcess.setmodelmod("LAST_NAME"); // set column name
        RequestProcess.setmodelmod("EMAILID"); // set column name
        RequestProcess.setmodelmod("ROLE"); // set column name

      //  RequestProcess.setmodelmodWhere("email","info@something.com");

        String ApiKEy = "Apikey";
        String ApiSecret = "api_secret";
        String AppName = "App";
        String Table_Name = "users";
        Const.API_URL = "";

        RequestProcess.getdatamod(MainActivity.this, ApiKEy, ApiSecret, AppName, Table_Name, Const.MSSQL,0,RequestProcess.dbcol, new RequestProcess.Callback() {
            @Override
            public void onSuccess(ArrayList<HashMap<String, String>> Result) {
                //GEt Your Result HEre
                if(Result.size() > 0){
                    for(int i = 0;i<Result.size();i++){

                    }
                }
            }

            @Override
            public void onError(String Error) {
                // Show Error
            }
        });

    }
}