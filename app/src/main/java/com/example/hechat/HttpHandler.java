package com.example.hechat;


    import android.util.Log;
    import android.widget.Toast;

    import java.io.IOException;
    import java.io.UnsupportedEncodingException;
    import java.util.ArrayList;
    import java.util.List;

    import org.apache.http.HttpEntity;
    import org.apache.http.HttpResponse;
    import org.apache.http.NameValuePair;
    import org.apache.http.client.ClientProtocolException;
    import org.apache.http.client.HttpClient;
    import org.apache.http.client.entity.UrlEncodedFormEntity;
    import org.apache.http.client.methods.HttpPost;
    import org.apache.http.impl.client.DefaultHttpClient;
    import org.apache.http.message.BasicNameValuePair;
    import org.apache.http.util.EntityUtils;


public class HttpHandler {

    public String post(String posturl,String Mail) {

        try {
            HttpClient httpclient;
            HttpPost httppost;
           // ArrayList<NameValuePair> postParameters;
            httpclient = new DefaultHttpClient();
            httppost = new HttpPost(posturl);

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("mail", Mail));

            httppost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = httpclient.execute(httppost);
            /*Una vez añadidos los parametros actualizamos la entidad de httppost, esto quiere decir en pocas palabras anexamos los parametros al objeto para que al enviarse al servidor envien los datos que hemos añadido*/

            /*Finalmente ejecutamos enviando la info al server*/

            HttpEntity ent = response.getEntity();/*y obtenemos una respuesta*/
            String text = EntityUtils.toString(ent);



            return text;
        } catch (Exception e) {   return "error";}

    }




public String GetID(String Mail){

    HttpClient httpclient = new DefaultHttpClient();
    HttpPost httppost = new HttpPost("http://192.168.0.7/hechat/getid.php");
    // Add your data
    List < NameValuePair > nameValuePairs = new ArrayList < NameValuePair > (0);
    nameValuePairs.add(new BasicNameValuePair("mail", Mail));


    try {
        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        Log.d("GetID", "works till here :)");
        try {
            HttpResponse response = httpclient.execute(httppost);
            Log.d("GetID", "response :)" + response.getEntity());
            HttpEntity ent = response.getEntity();/*y obtenemos una respuesta*/
            String text = EntityUtils.toString(ent);
            return text;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    } {return  "error";}
}



    public String CuentaUsuarios(String posturl, String Mail){

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(posturl);
        // Add your data
        List < NameValuePair > nameValuePairs = new ArrayList < NameValuePair > (0);
        nameValuePairs.add(new BasicNameValuePair("mail", Mail));


        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            Log.d("CuentaUsuarios", "works till here :)");
            try {
                HttpResponse response = httpclient.execute(httppost);
                Log.d("CuentaUsuarios", "response :)" + response.getEntity());
                HttpEntity ent = response.getEntity();/*y obtenemos una respuesta*/
                String text = EntityUtils.toString(ent);
                return text;
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } {return  "error";}
    }


    public String AddPicture(String posturl, String idusuario, String imagen){

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(posturl);
        // Add your data
        List < NameValuePair > nameValuePairs = new ArrayList < NameValuePair > (0);
        nameValuePairs.add(new BasicNameValuePair("iduser", idusuario));
        nameValuePairs.add(new BasicNameValuePair("picture1", imagen));
        Log.d("AddPicture", "Se agregó con éxito la imagen  :D" + imagen);

        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            Log.d("AddPicture", "works till here :)");
            try {
                HttpResponse response = httpclient.execute(httppost);
                Log.d("AddPicture", "response :)" + response.getEntity());
                HttpEntity ent = response.getEntity();/*y obtenemos una respuesta*/
                String text = EntityUtils.toString(ent);
                return text;
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } {return  "error";}
    }



}



