package com.example.hechat;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;


import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecuperarDatos {
    public void ValidarUsuario(String URL, final Context context){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                int i;
                for (i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
//Datos a buscar
                      //  textMail.setText(jsonObject.getString("mail"));
                       // textMail.setText(jsonObject.getString("mail"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                       // Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context.getApplicationContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        // Toast.makeText(RegistroActivity.this,requestQueue.toString(),Toast.LENGTH_LONG).show();



    }



    public void CONNECT_SERVER(EditText editmail, String  URL) {
        String msg = editmail.getText().toString();

        if (msg.length() > 0) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(URL);
            try {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("password", msg));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                httpclient.execute(httppost);
            } catch (ClientProtocolException e) {
            } catch (IOException e) {
            }
        } else {
            // Toast.makeText(getBaseContext(),"All field are required",Toast.LENGTH_SHORT).show(); }
        }

    }




}