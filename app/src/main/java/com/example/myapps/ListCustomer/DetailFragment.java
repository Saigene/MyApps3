package com.example.myapps.ListCustomer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.myapps.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import com.example.myapps.Adapter.DetailPayListAdapater;
import com.example.myapps.DatabaseClass.DetailPayDatabaseClass;

public class DetailFragment  extends AppCompatActivity {
    ListView listviewCustomer;
    List<DetailPayDatabaseClass> detail = new ArrayList<>();
    TextView detailname;
    Button detailback;
    TextView detailorder;

    private DatabaseReference databasedetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_info);
        listviewCustomer = (ListView) findViewById(R.id.detaillist);
        detailname=(TextView)findViewById(R.id.detailname);
      //  detailback=(Button)findViewById(R.id.buttondetailback);
        detailorder=(TextView)findViewById(R.id.detailorder);
        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();
        String _detailid=bundle.getString("Keydetailsid");
        detailname.setText(bundle.getString("Keydetailname"));
        detailorder.setText(bundle.getString("Keydetailorder"));
        databasedetail = FirebaseDatabase.getInstance().getReference("Customer Detail Pay").child(_detailid);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void onStart() {
        super.onStart();

        databasedetail.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            //    detail.clear();
                for (DataSnapshot customerdatasnapshot : dataSnapshot.getChildren()) {
                    DetailPayDatabaseClass detailPayDatabaseClass = customerdatasnapshot.getValue(DetailPayDatabaseClass.class);
                    detail.add(detailPayDatabaseClass);
                }
                if(DetailFragment.this != null) {
                    DetailPayListAdapater detailPayListAdapater = new DetailPayListAdapater(DetailFragment.this, detail);
                    listviewCustomer.setAdapter(detailPayListAdapater);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
