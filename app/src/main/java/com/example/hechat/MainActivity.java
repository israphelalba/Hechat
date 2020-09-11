package com.example.hechat;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.koushikdutta.ion.Ion;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static com.example.hechat.SlideMenu.EXTRA_DATA;

public class MainActivity extends AppCompatActivity{
Button botonRegistrar, botonlogin;
EditText MailText, PswText;
TextView resultados;
ImageView imagen;
    final ArrayList<User> lista_usuario = new ArrayList<>();
public ArrayList<User> users;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        getSupportActionBar().hide();

        MailText = (EditText) findViewById(R.id.mailtext) ;
        PswText = (EditText) findViewById(R.id.passwordtext) ;
        botonRegistrar =(Button) findViewById(R.id.btnReg);
        resultados = (TextView)findViewById(R.id.textLista);

       // ContextCompat.getDrawable(getDrawable( R.drawable.catchat))
     // imagen =(ImageView) findViewById(R.id.imageView);
       // scaleImage(imagen);
       //imagen.setImageResource(R.drawable.catchat);
      //  Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.catchat);
        //Bitmap bitmaprediemsion =   createBitmapBySize(icon,151,148);

        //imagen.setImageBitmap(bitmaprediemsion);
    //    scaleDrawable(this, 1,140,13);
        //cambia el tamaño del image view
       // imagen.getLayoutParams().width = 151;
        //imagen.getLayoutParams().height = 148;

       //imagen.setAdjustViewBounds(true);
       // imagen.setImageResource(R.drawable.catchat);
  //    imagen.setAdjustViewBounds(true);
    //   imagen.setScaleType(ImageView.ScaleType.FIT_CENTER);
        botonlogin =(Button) findViewById(R.id.btnlogin);
        botonRegistrar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


// We need to adjust the height if the width of the bitmap is
// smaller than the view width, otherwise the image will be boxed.



               // System.out.println(Arrays.toString(User));

               // Object[] nombres = users.toArray();
                //System.out.println(nombres[1]);
                //Toast.makeText(MainActivity.this,nombres[1].toString(),Toast.LENGTH_LONG).show();

             Intent next = new Intent(MainActivity.this, RegistroActivity.class);
             startActivity(next);
            }
        });

       botonlogin.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {
               addList();
//  Toast.makeText(MainActivity.this,"mail: "+MailText.getText().toString(),Toast.LENGTH_LONG).show();
         ValidarUsuario("http://192.168.0.7/hechat/validar_usuario.php");




           }
       });
      
      
      

    }




    private void ValidarUsuario(String URL){
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

//try{
   //User usuario = new User(ID(),MailText.getText().toString());
   //lista_usuario.add(usuario);;
                Intent mIntent = new Intent(MainActivity.this, SlideMenu.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("id", ID());
                mBundle.putString("mail", MailText.getText().toString());
                mIntent.putExtras(mBundle);
                startActivity(mIntent);
   // next.putExtra("datos",usuario);


//}catch(ExceptionInInitializerError e){
   // Log.d("Intent", "Error :(");
//}

              //  Toast.makeText(MainActivity.this,"Valor de id: "+ userID,Toast.LENGTH_LONG).show();




               // Toast.makeText(MainActivity.this,"ACCESO CORRECTO",Toast.LENGTH_SHORT).show();







            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String > parametros = new HashMap<String, String>();
                parametros.put("mail",MailText.getText().toString());
                parametros.put("password",PswText.getText().toString());

                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private String ID(){


        HttpHandler httpHandler = new HttpHandler();
        String userID = httpHandler.GetID(MailText.getText().toString()).replace("<br />","");
        return userID;
    }

    public void addList(){
        try{

            User usuario = new User(ID(),MailText.getText().toString());
            lista_usuario.add(usuario);
            resultados.setText(lista_usuario.toString());
            Toast.makeText(MainActivity.this,"Datos agragados a la lista",Toast.LENGTH_LONG).show();

        }catch (Exception e){
            Log.d("addUsers", "Error :(");
        }

    }


    public static Bitmap createBitmapBySize(Bitmap bitmap, int width, int height) {
        return Bitmap.createScaledBitmap(bitmap, width, height, true);
    }

    public static Drawable scaleDrawable(Context context, int drawableResourceId, int width,
                                         int height) {
        Bitmap sourceBitmap = BitmapFactory.decodeResource(context.getResources(),
                drawableResourceId);
        return new BitmapDrawable(Bitmap.createScaledBitmap(sourceBitmap, width, height, true));
    }

    private void scaleImage(ImageView view) throws NoSuchElementException {
        // Get bitmap from the the ImageView.
        Bitmap bitmap = null;

        try {
            Drawable drawing = view.getDrawable();
            bitmap = ((BitmapDrawable) drawing).getBitmap();
        } catch (NullPointerException e) {
            throw new NoSuchElementException("No drawable on given view");
        } catch (ClassCastException e) {
            // Check bitmap is Ion drawable
           bitmap = Ion.with(view).getBitmap();
        }

        // Get current dimensions AND the desired bounding box
        int width = 0;

        try {
            width = bitmap.getWidth();
        } catch (NullPointerException e) {
            throw new NoSuchElementException("Can't find bitmap on given view/drawable");
        }

        int height = bitmap.getHeight();
        int bounding = dpToPx(250);
        Log.i("Test", "original width = " + Integer.toString(width));
        Log.i("Test", "original height = " + Integer.toString(height));
        Log.i("Test", "bounding = " + Integer.toString(bounding));

        // Determine how much to scale: the dimension requiring less scaling is
        // closer to the its side. This way the image always stays inside your
        // bounding box AND either x/y axis touches it.
        float xScale = ((float) bounding) / width;
        float yScale = ((float) bounding) / height;
        float scale = (xScale <= yScale) ? xScale : yScale;
        Log.i("Test", "xScale = " + Float.toString(xScale));
        Log.i("Test", "yScale = " + Float.toString(yScale));
        Log.i("Test", "scale = " + Float.toString(scale));

        // Create a matrix for the scaling and add the scaling data
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);

        // Create a new bitmap and convert it to a format understood by the ImageView
        Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        width = scaledBitmap.getWidth(); // re-use
        height = scaledBitmap.getHeight(); // re-use
        BitmapDrawable result = new BitmapDrawable(scaledBitmap);
        Log.i("Test", "scaled width = " + Integer.toString(width));
        Log.i("Test", "scaled height = " + Integer.toString(height));

        // Apply the scaled bitmap
        view.setImageDrawable(result);

        // Now change ImageView's dimensions to match the scaled image
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);

        Log.i("Test", "done");
    }

    private int dpToPx(int dp) {
        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        return Math.round((float)dp * density);
    }




}
