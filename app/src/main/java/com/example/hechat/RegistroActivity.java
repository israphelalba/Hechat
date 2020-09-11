package com.example.hechat;

import androidx.annotation.RequiresApi;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;




public class RegistroActivity extends Activity {
String resultado ="";
      Button butReg;
    EditText textPassword, textMail, textConfir, textresult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        textPassword = (EditText) findViewById(R.id.passwordText);
        textConfir = (EditText) findViewById(R.id.ConfirEText);
        textMail = (EditText) findViewById(R.id.mailText);
     //  textresult = (EditText) findViewById(R.id.editText);
        butReg = (Button)findViewById(R.id.btnOk);

        butReg.setOnClickListener(new View.OnClickListener() {



            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                String a = textPassword.getText().toString();
                String b = textConfir.getText().toString();


                if (Objects.equals(a, b)) {


                    HttpHandler http = new HttpHandler();
        String r= http.CuentaUsuarios("http://192.168.0.7/hechat/contar.php",textMail.getText().toString());

                    //Toast.makeText(RegistroActivity.this,"cantidad de usuarios "+ r ,Toast.LENGTH_LONG).show();
if(Objects.equals(r,"0")){
    RegistrarUsuario("http://192.168.0.7/hechat/insertar_usuarios.php");
  //  Toast.makeText(RegistroActivity.this,"cantidad de usuarios "+ r ,Toast.LENGTH_LONG).show();
}else{

    Toast.makeText(RegistroActivity.this,"Usuario existente ",Toast.LENGTH_SHORT).show();
}

                    // iguales

              //      ContarRegistros("http://192.168.0.7/hechat/contar.php");






                  //  ejecutarServicio("http://192.168.0.7/hechat/insertar_usuarios.php");
                } else {
                    // diferentes
                    Toast.makeText(RegistroActivity.this,"Las contraseñas no coinciden",Toast.LENGTH_LONG).show();
                }










            }
        });


}


private String RegistrarUsuario (String URL){

    StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, URL, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {


         //   Toast.makeText(RegistroActivity.this, "cantidad de usuarios repetidos: " +Cuenta(textMail.getText().toString()),Toast.LENGTH_LONG).show();

                //  Intent intent=  new Intent(getApplicationContext(),MainActivity.class);
                // startActivity(intent);
                Toast.makeText(RegistroActivity.this,"Ususario registrado exitosamente",Toast.LENGTH_LONG).show();

        }


    }, new ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

            Toast.makeText(RegistroActivity.this,error.toString(),Toast.LENGTH_LONG).show();

        }
    }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String > parametros = new HashMap<String, String>();
            parametros.put("mail",textMail.getText().toString());
            parametros.put("password",textPassword.getText().toString());
            return parametros;
        }
    };

    RequestQueue requestQueue = Volley.newRequestQueue(this);
    requestQueue.add(stringRequest);
   // Toast.makeText(RegistroActivity.this,requestQueue.toString(),Toast.LENGTH_LONG).show();
    return requestQueue.toString();

    }


//Coloca información dentro de un edittext
    public void ValidarUsuario(String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                int i;
                for (i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);

                        textMail.setText(jsonObject.getString("mail"));
                        textMail.setText(jsonObject.getString("mail"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        }
            );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

        // Toast.makeText(RegistroActivity.this,requestQueue.toString(),Toast.LENGTH_LONG).show();



    }




    ////

    

}

