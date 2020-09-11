package com.example.hechat.ui.gallery;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hechat.HttpHandler;
import com.example.hechat.MainActivity;
import com.example.hechat.Pictures;
import com.example.hechat.R;
import com.example.hechat.User;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GalleryFragment extends Fragment {

    final ArrayList<Pictures> list_pictures = new ArrayList<Pictures>();
    public ArrayList<User> pictures;
    private GalleryViewModel galleryViewModel;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    //    ImageView imagen1 = root.findViewById(R.id.imageView3);

        final Button botn = root.findViewById(R.id.butGalery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {



                botn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openGallery();


                    }
                });


            }
        });
        return root;
    }




    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }




//convertir imagen a bits
    public String ImageToBit(Bitmap bitmap){
        ByteArrayOutputStream v = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,v);
        byte[] imagenBites = v.toByteArray();
        String encodeImagen = Base64.encodeToString(imagenBites,Base64.DEFAULT);
        return encodeImagen;


    }


    //@Override
    //public void onActivityResult(int requestCode, int resultCode, Intent data){
        //super.onActivityResult(requestCode, resultCode, data);
        //  if( requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null){
      //      imageUri = data.getData();
            //imageFile.setImageURI(imageUri);
    //    }
  //  }

    //subir imagen

    public String UploadImage (String URL, final String idusuario, final String picture1){

        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



                Toast.makeText(getContext(),"Carga exitosa",Toast.LENGTH_SHORT).show();

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String > parametros = new HashMap<String, String>();
             //   Bundle j = getActivity().getIntent().getExtras();
              // String    idUser = j.getString("id");

               // String id = list_pictures.get(0).toString();
                //String picture1 = list_pictures.get(1).toString();
             //   String imagen = ImageToBit(bitmap);
              parametros.put("iduser",idusuario);
              parametros.put("picture1",picture1);


                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
        // Toast.makeText(RegistroActivity.this,requestQueue.toString(),Toast.LENGTH_LONG).show();
        return requestQueue.toString();

    }







    public void onActivityResult(int requestCode, int resultCode,
                                    Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        Uri selectedImageUri = null;
        Uri selectedImage;

        String filePath = null;
        switch (requestCode) {
            case PICK_IMAGE:
                if (resultCode == Activity.RESULT_OK) {
                    selectedImage = imageReturnedIntent.getData();
                    String selectedPath=selectedImage.getPath();
                    if (requestCode == PICK_IMAGE) {

                        if (selectedPath != null) {
                            InputStream imageStream = null;

                            try {
                                imageStream = getActivity().getContentResolver().openInputStream(
                                        selectedImage);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                        // Transformamos la URI de la imagen a inputStream y este a un Bitmap
                            Bitmap bmp = BitmapFactory.decodeStream(imageStream);

                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            bmp.compress(Bitmap.CompressFormat.JPEG, 50, stream);
                            byte[] byteFormat = stream.toByteArray();
                             //get the base 64 string
                            String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
                            Bundle j = getActivity().getIntent().getExtras();
                            String    idUser = j.getString("id");
                           // Pictures data = new Pictures(idUser,imgString,"","","","","");
                            //list_pictures.add(data);
                           // Log.i("Imagen :)",list_pictures.get(1).toString() );

                            UploadImage ("http://192.168.0.7/hechat/insert_picture.php",idUser, imgString);



                          //  HttpHandler handler = new HttpHandler();
                            //handler.AddPicture("http://192.168.0.7/hechat/insert_picture.php",idUser, Base64.encodeToString(byteFormat, Base64.DEFAULT));
                         // UploadImage("http://192.168.0.7/hechat/insert_picture.php",idUser,imgString);

                            //decode base64 string to image
                            byteFormat = Base64.decode(imgString, Base64.DEFAULT);
                            Bitmap decodedImage = BitmapFactory.decodeByteArray(byteFormat, 0, byteFormat.length);
                           // ImageView imagen1;

                            ImageView imagen = getView().findViewById(R.id.imageView3);
                            imagen.setImageBitmap(decodedImage);

                          // Toast.makeText(getActivity(),"cadena imagen :" + list_pictures.get(2).toString(),Toast.LENGTH_LONG).show();
                        }
                    }
                }
                break;
        }
    }

}
