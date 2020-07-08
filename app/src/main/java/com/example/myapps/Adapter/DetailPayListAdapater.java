package com.example.myapps.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapps.R;
import com.example.myapps.DatabaseClass.*;
import java.util.List;

public class DetailPayListAdapater extends ArrayAdapter<DetailPayDatabaseClass> {
private Activity context;
private List<DetailPayDatabaseClass>detaillist;

public DetailPayListAdapater(Activity context, List<DetailPayDatabaseClass>detaillist){
    super(context, R.layout.detail_list_item, detaillist);
    this.context=context;
    this.detaillist=detaillist;
}

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.detail_list_item,null,true);
        TextView textdate= (TextView)listViewItem.findViewById(R.id.listitemdetaildate);
        TextView textpay=(TextView)listViewItem.findViewById(R.id.listitemdetailpay);
        TextView textstatus=(TextView)listViewItem.findViewById(R.id.listitemdetailstatus);

        try {
            DetailPayDatabaseClass detailPayDatabaseClass =detaillist.get(position);
            textdate.setText(detailPayDatabaseClass.getDetaildatepay());
            textpay.setText(detailPayDatabaseClass.getDetailpay());
            textstatus.setText(detailPayDatabaseClass.getDetailstatus());

        }catch (NullPointerException e){

        }

        return listViewItem;
    }
}


