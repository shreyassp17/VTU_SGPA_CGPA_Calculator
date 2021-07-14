package com.example.cgpacalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.core.view.GravityCompat;
import androidx.navigation.ui.AppBarConfiguration;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import static com.example.cgpacalculator.R.id.drawer_layout;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    private AppBarConfiguration mAppBarConfiguration;
    private Button btn2018, btn2020;
    private DrawerLayout drawerlayout;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth = FirebaseAuth.getInstance();
        drawerlayout = findViewById(drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        NavigationView navigationview = findViewById(R.id.nav_view);
        navigationview.setNavigationItemSelectedListener(this);
        //getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_container,new HomeActivity());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerlayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState == null){
            navigationview.setCheckedItem(R.id.nav_home);
        }



        btn2018 = findViewById(R.id.button2018);
        btn2020 = findViewById(R.id.button2020);
        btn2018.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, select_sem.class));
            }
        });

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){

        switch (menuItem.getItemId())
        {
            case R.id.nav_home:
                Intent intent = new Intent(this,HomeActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.nav_calc:
                Intent intent2 = new Intent(this,CalculateCGPA.class);
                startActivity(intent2);
                break;

            case R.id.nav_signout:
                mAuth.signOut();
                Intent intent1 = new Intent(this,LoginActivity.class);
                startActivity(intent1);
                finish();
                break;

            case R.id.nav_viewMarks:
                Intent intent3 = new Intent(this,ViewMarks.class);
                startActivity(intent3);
                break;
        }
        drawerlayout.closeDrawer(GravityCompat.START);



        return true;
    }



    @Override
    public void onBackPressed()
    {
        if (drawerlayout.isDrawerOpen(GravityCompat.START)) {
            drawerlayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
}