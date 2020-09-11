package com.example.hechat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hechat.ui.gallery.GalleryFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class SlideMenu extends AppCompatActivity {
    public static final String EXTRA_DATA ="EXTRA_DATA";
    public ArrayList<User> users;
    private AppBarConfiguration mAppBarConfiguration;
    TextView textid;
public String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);





        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.registroFragment,R.id.roomFragment)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        Bundle j = getIntent().getExtras();
        idUser = j.getString("id");
        String uri = "@drawable/isra";
        View headerView = navigationView.getHeaderView(0);
        textid = (TextView) headerView.findViewById(R.id.idusuario);
        textid.setText(idUser);


        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        ImageView profile = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageView);
        profile.setScaleType(ImageView.ScaleType.MATRIX);
        Drawable res = getResources().getDrawable(imageResource);
        profile.setImageDrawable(res);



       // Intent intent = new Intent(SlideMenu.this, GalleryFragment.class);
        //Bundle b = new Bundle();
      //  b.putString("id", idUser);
        //intent.putExtras(b);
        //startActivity(intent);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.slide_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }




    public void RecuperarDatos(View view){
        Intent i=new Intent(this, SlideMenu.class);
        startActivityForResult(i, 1);
    }


    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.isra);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }






}
