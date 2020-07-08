package com.example.myapps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myapps.AddCustomer.*;
import com.example.myapps.DatabaseClass.UserDatabaseClass;
import com.example.myapps.ListCustomer.*;
import com.example.myapps.Login.CreateAccount;
import com.example.myapps.Login.Login;
import com.example.myapps.OrderList.*;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private long backpresstime;
    private Toast backtoast;
    FirebaseAuth fauth;
    DatabaseReference dbuserref;
    String username;
    String useremail;
    String userNo;
    String userId;
    MenuItem accountname;
    Menu accountmenu;

    List<UserDatabaseClass>userdata=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNav);




        fauth=FirebaseAuth.getInstance();
        userId=fauth.getCurrentUser().getUid();

        dbuserref = FirebaseDatabase.getInstance().getReference("User").child(userId);

        dbuserref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                username=dataSnapshot.child("username").getValue().toString();
                invalidateOptionsMenu();

                Global getuserdata = Global.getInstance();
                getuserdata.setValue(username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Log.i("Test",userId);
//        accountname.setTitle(username);

        bottomNavigationView.getLabelVisibilityMode();
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new ListFragment()).commit();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()){
                    case R.id.list:
                        fragment=new ListFragment();
                        break;
                    case R.id.customer_add:
                        fragment = new AddFragment();
                        break;
                    case R.id.edit:
                        fragment = new OrderList();
                        break;
                    case R.id.profit:
                        fragment = new ProfitFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
                return true;
            }
        });
    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater inflater = getMenuInflater();
       inflater.inflate(R.menu.itemsetting, menu);
       return true;
   }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        accountname=menu.findItem(R.id.accountusername);
        accountname.setTitle(username);
        return super.onPrepareOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.itemmenuaddcaptial:
            Toast.makeText(MainActivity.this,"add Capital", Toast.LENGTH_SHORT).show();
            return true;
            case R.id.itemmenusettings:
                Toast.makeText(MainActivity.this,"Settings", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.itemlogout:
                fauth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish();;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {
        if(backpresstime + 2000 > System.currentTimeMillis()){
            backtoast.cancel();
            super.onBackPressed();
            return;
        }else{
            backtoast=Toast.makeText(getBaseContext(),"Press back again to exit",Toast.LENGTH_SHORT);
            backtoast.show();
        }
        backpresstime=System.currentTimeMillis();
    }
}
