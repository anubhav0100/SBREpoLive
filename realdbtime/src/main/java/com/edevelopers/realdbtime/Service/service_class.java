package com.edevelopers.realdbtime.Service;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.edevelopers.realdbtime.Lib.Const;
import com.edevelopers.realdbtime.Lib.customRequest;
import com.edevelopers.realdbtime.Model.DBColumn;
import com.edevelopers.realdbtime.Model.DBColumnResult;
import com.edevelopers.realdbtime.Model.ModelClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class service_class {
    public static final String apiurl = Const.API_URL;

    public interface Callback
    {
        void onSuccess(ArrayList<HashMap<String, String>> Result);

        void onError(String Error);
    }

    public interface Callbackres
    {
        void onSuccess(String Result);

        void onError(String Error);
    }


    public static void RequestData(ModelClass Modeldata, final Callback callback){
        RequestQueue queue= Volley.newRequestQueue(Modeldata.getContext());
        String url = apiurl+"getresult";
        final ArrayList<DBColumn> dbcol = Modeldata.getDbcolumn();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.accumulate(Const.api_key, Modeldata.getApi_key());
            jsonObject.accumulate(Const.appname, Modeldata.getAppname());
            jsonObject.accumulate(Const.api_secret, Modeldata.getApi_secret());
            jsonObject.accumulate(Const.query, Modeldata.getQuery());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try{
            customRequest jsonObjectRequest = new customRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                  //  ArrayList<DBColumnResult> fed = new ArrayList<>();
                    ArrayList<HashMap<String, String>> fed = new ArrayList<>();
                    try{
                        for(int i = 0;i < response.length();i++){
                            JSONObject explrObject = response.getJSONObject(i);
                            try{
                                HashMap<String, String> map = new HashMap<>();
                                for(int j = 0;j < dbcol.size();j++){
//                                    fed.add (new DBColumnResult(
//                                            dbcol.get(j).getColumnname(),
//                                            explrObject.getString(dbcol.get(j).getColumnname()),
//                                            i
//                                    ));

                                    map.put(dbcol.get(j).getColumnname(),explrObject.getString(dbcol.get(j).getColumnname()));

                                }
                                fed.add(map);
                            }catch (Exception e){
                                callback.onError(explrObject.getString("Error"));
                            }
                        }
                    }
                    catch (Exception e){
                        callback.onError(e.getMessage());
                    }

                    callback.onSuccess(fed);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    callback.onError(error.getMessage());
                }
            });

            queue.add(jsonObjectRequest);
        }catch (Exception e) {
            Log.d(Const.TAG,""+e.getMessage());
        }
    }

    public static void SaveData(ModelClass Modeldata, final Callbackres callback){
        RequestQueue queue= Volley.newRequestQueue(Modeldata.getContext());
        String url = apiurl+"saveresult";
       // final ArrayList<DBColumn> dbcol = Modeldata.getDbcolumn();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.accumulate(Const.api_key, Modeldata.getApi_key());
            jsonObject.accumulate(Const.appname, Modeldata.getAppname());
            jsonObject.accumulate(Const.api_secret, Modeldata.getApi_secret());
            jsonObject.accumulate(Const.query, Modeldata.getQuery());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try{
            customRequest jsonObjectRequest = new customRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try{
                        for(int i = 0;i < response.length();i++){
                            JSONObject explrObject = response.getJSONObject(i);
                            try{
                                String result = explrObject.getString("result");
                                callback.onSuccess(result);
                            }catch (Exception e){
                                callback.onError(explrObject.getString("error"));
                            }
                        }
                    }
                    catch (Exception e){
                        callback.onError(e.getMessage());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    callback.onError(error.getMessage());
                }
            });

            queue.add(jsonObjectRequest);
        }catch (Exception e) {
            Log.d(Const.TAG,""+e.getMessage());
        }
    }

}
